// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import java.util.HashSet;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import java.util.List;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateConstructorHandler;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;

public class OCAddSuperConstructorCallsFix extends OCSymbolQuickFix<OCFunctionSymbol>
{
    private final Collection<OCStructSymbol> myClassesMissingBaseCtors;
    
    public OCAddSuperConstructorCallsFix(final OCFunctionSymbol ocFunctionSymbol, final Collection<OCStructSymbol> myClassesMissingBaseCtors) {
        super(ocFunctionSymbol);
        this.myClassesMissingBaseCtors = myClassesMissingBaseCtors;
    }
    
    @Override
    protected boolean isAvailable(final OCFunctionSymbol ocFunctionSymbol) {
        return OCSearchScope.isInProjectSources(ocFunctionSymbol);
    }
    
    @Override
    protected String getTextInternal(final OCFunctionSymbol ocFunctionSymbol) {
        return this.getFamilyName();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Add missing base constructor calls";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCAddSuperConstructorCallsFix", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    public boolean startInWriteAction() {
        return false;
    }
    
    @Override
    protected void invoke(final OCFunctionSymbol ocFunctionSymbol) {
        final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)ocFunctionSymbol.locateFunctionDefinition();
        try {
            if (ocFunctionDefinition == null) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final ArrayList<Collection<OCFunctionSymbol>> list = new ArrayList<Collection<OCFunctionSymbol>>();
        for (final OCStructSymbol ocStructSymbol : this.myClassesMissingBaseCtors) {
            final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
            try {
                ocStructSymbol.processConstructors((Processor<? super OCFunctionSymbol>)collectProcessor);
                if (collectProcessor.getResults().isEmpty()) {
                    continue;
                }
                list.add(collectProcessor.getResults());
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final List<OCFunctionSymbol> chooseBaseConstructors = OCGenerateConstructorHandler.chooseBaseConstructors(list, ocFunctionSymbol.getProject());
        try {
            if (chooseBaseConstructors == null) {
                return;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final OCChangeSignatureHandler handler = OCChangeSignatureActionHandler.getHandler(ocFunctionDefinition, (PsiElement)ocFunctionDefinition, true);
        final HashSet<String> set = new HashSet<String>(ContainerUtil.map((Collection)ocFunctionSymbol.getParameterSymbols(), ocDeclaratorSymbol -> ocDeclaratorSymbol.getName()));
        int n = 0;
        final OCConstructorInitializationList constructorInitializationList = OCElementFactory.constructorInitializationList(ocFunctionDefinition);
        for (final OCFunctionSymbol ocFunctionSymbol2 : chooseBaseConstructors) {
            final StringBuilder sb = new StringBuilder();
            sb.append(ocFunctionSymbol2.getName()).append("(");
            int n2 = 1;
            for (final OCDeclaratorSymbol ocDeclaratorSymbol : ocFunctionSymbol2.getParameterSymbols()) {
                final String suggestUniqueName = OCNameSuggester.suggestUniqueName(OCSymbolKind.PARAMETER, ocDeclaratorSymbol.getName(), null, set);
                try {
                    set.add(suggestUniqueName);
                    handler.insertParameter(suggestUniqueName, ocDeclaratorSymbol.getType(), n++);
                    if (n2 == 0) {
                        sb.append(",");
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                n2 = 0;
                sb.append(suggestUniqueName);
            }
            sb.append(")");
            OCChangeUtil.add((PsiElement)constructorInitializationList, OCElementFactory.constructorFieldInitializerFromText(sb.toString(), (PsiElement)ocFunctionDefinition));
        }
        final OCConstructorInitializationList constructorInitializationList2 = ocFunctionDefinition.getConstructorInitializationList();
        if (constructorInitializationList2 != null) {
            final Iterator<OCConstructorFieldInitializer> iterator4 = constructorInitializationList2.getInitializers().iterator();
            while (iterator4.hasNext()) {
                OCChangeUtil.add((PsiElement)constructorInitializationList, iterator4.next());
            }
        }
        handler.getGeneratedInfo().runOnSuccess(() -> ocFunctionDefinition.setConstructorInitializationList(constructorInitializationList));
        handler.setTitle(this.getText());
        handler.invoke();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
