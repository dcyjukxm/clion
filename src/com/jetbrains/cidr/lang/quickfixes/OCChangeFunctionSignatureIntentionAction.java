// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import java.util.Collection;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.intellij.openapi.util.Pair;
import java.util.ArrayList;
import java.util.HashSet;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCChangeFunctionSignatureIntentionAction extends OCSymbolQuickFix<OCSymbol<?>>
{
    private OCFunctionType myNewType;
    
    public OCChangeFunctionSignatureIntentionAction(final OCSymbol ocSymbol, final OCFunctionType myNewType) {
        super(ocSymbol);
        this.myNewType = myNewType;
    }
    
    @Override
    protected String getTextInternal(final OCSymbol ocSymbol) {
        return "Change signature of " + ocSymbol.getNameWithKindLowercase() + " to '" + this.myNewType.getName((PsiElement)ocSymbol.getContainingOCFile()) + "'";
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Change function signature";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeFunctionSignatureIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected boolean isAvailable(final OCSymbol ocSymbol) {
        return OCSearchScope.isInProjectSources(ocSymbol);
    }
    
    @Override
    protected void invoke(final OCSymbol ocSymbol) {
        final PsiElement locateDefinition = ocSymbol.locateDefinition();
        Label_0038: {
            try {
                if (!(locateDefinition instanceof OCDeclarator)) {
                    return;
                }
                final PsiElement psiElement = locateDefinition;
                final PsiElement psiElement2 = psiElement.getParent();
                final boolean b = psiElement2 instanceof OCFunctionDeclaration;
                if (!b) {
                    return;
                }
                break Label_0038;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement = locateDefinition;
                final PsiElement psiElement2 = psiElement.getParent();
                final boolean b = psiElement2 instanceof OCFunctionDeclaration;
                if (!b) {
                    return;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final OCFunctionDeclaration ocFunctionDeclaration = (OCFunctionDeclaration)locateDefinition.getParent();
        final OCChangeSignatureHandler handler = OCChangeSignatureActionHandler.getHandler(ocFunctionDeclaration, (PsiElement)ocFunctionDeclaration, false, true, true, false);
        final OCParameterList parameterList = ((OCDeclarator)locateDefinition).getParameterList();
        try {
            if (parameterList == null) {
                return;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        handler.setReturnType(this.myNewType.getReturnType());
        final HashSet<String> set = new HashSet<String>();
        final Iterator<OCType> iterator = this.myNewType.getParameterTypes(true).iterator();
        final ArrayList<Pair> list = new ArrayList<Pair>();
        final Iterator<OCParameterDeclaration> iterator2 = parameterList.getParameterDeclarations().iterator();
        while (iterator2.hasNext()) {
            final OCDeclarator declarator = iterator2.next().getDeclarator();
            String name = null;
            Label_0192: {
                try {
                    if (declarator != null) {
                        name = declarator.getName();
                        break Label_0192;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                name = null;
            }
            final String s = name;
            try {
                handler.removeParameter(0);
                if (s != null) {
                    set.add(s);
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            ArrayList<Pair> list2 = null;
            String s3 = null;
            Label_0259: {
                Label_0248: {
                    try {
                        if (!iterator.hasNext()) {
                            continue;
                        }
                        list2 = list;
                        final String s2 = s;
                        if (s2 != null) {
                            break Label_0248;
                        }
                        break Label_0248;
                    }
                    catch (IllegalStateException ex6) {
                        throw a(ex6);
                    }
                    try {
                        list2 = list;
                        final String s2 = s;
                        if (s2 != null) {
                            s3 = s;
                            break Label_0259;
                        }
                    }
                    catch (IllegalStateException ex7) {
                        throw a(ex7);
                    }
                }
                s3 = "";
            }
            list2.add(Pair.create((Object)s3, (Object)iterator.next()));
        }
        while (iterator.hasNext()) {
            final OCType ocType = iterator.next();
            final Collection<String> suggestForType = OCNameSuggester.suggestForType(ocType, locateDefinition, set);
            String s4 = null;
            Label_0344: {
                try {
                    if (suggestForType.isEmpty()) {
                        s4 = "param";
                        break Label_0344;
                    }
                }
                catch (IllegalStateException ex8) {
                    throw a(ex8);
                }
                s4 = suggestForType.iterator().next();
            }
            final String s5 = s4;
            set.add(s5);
            list.add(Pair.create((Object)s5, (Object)ocType));
        }
        int n = 0;
        for (final Pair pair : list) {
            handler.addParameter((String)pair.getFirst(), (OCType)pair.getSecond(), n++);
        }
        handler.invokeSynchronously();
    }
    
    @Override
    public boolean startInWriteAction() {
        return false;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
