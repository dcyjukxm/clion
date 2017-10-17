// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;

public class OCAddFieldInitializerFix extends OCPsiElementQuickFix<OCFunctionDefinition>
{
    private OCDeclaratorSymbol myField;
    
    public OCAddFieldInitializerFix(final OCFunctionDefinition ocFunctionDefinition, final OCDeclaratorSymbol myField) {
        super((PsiElement)ocFunctionDefinition);
        this.myField = myField;
    }
    
    @Override
    protected boolean isAvailable(@NotNull final OCFunctionDefinition ocFunctionDefinition) {
        try {
            if (ocFunctionDefinition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "constructor", "com/jetbrains/cidr/lang/quickfixes/OCAddFieldInitializerFix", "isAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return OCSearchScope.isInProjectSources(this.myField);
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Add constructor initializer for field";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCAddFieldInitializerFix", "getFamilyName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Override
    protected String getTextInternal() {
        return "Add constructor initializer for " + this.myField.getNameWithKindLowercase();
    }
    
    @Override
    protected void invoke(final PsiFile psiFile, @NotNull final OCFunctionDefinition ocFunctionDefinition) {
        try {
            if (ocFunctionDefinition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "constructor", "com/jetbrains/cidr/lang/quickfixes/OCAddFieldInitializerFix", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCType resolvedType = this.myField.getResolvedType();
        OCAddInitializerIntentionAction.invokeSmartCompletion(addFieldInitializer(ocFunctionDefinition, this.myField, resolvedType.getDefaultValue((PsiElement)psiFile)).getArguments().get(0), resolvedType);
    }
    
    public static OCConstructorFieldInitializer addFieldInitializer(final OCFunctionDefinition ocFunctionDefinition, final OCDeclaratorSymbol ocDeclaratorSymbol, final String s) {
        final OCConstructorFieldInitializer constructorFieldInitializerFromText = OCElementFactory.constructorFieldInitializerFromText(ocDeclaratorSymbol.getName() + "(" + s + ")", (PsiElement)ocFunctionDefinition);
        final OCConstructorInitializationList constructorInitializationList = ocFunctionDefinition.getConstructorInitializationList();
        OCConstructorFieldInitializer ocConstructorFieldInitializer;
        if (constructorInitializationList != null) {
            ocConstructorFieldInitializer = OCChangeUtil.add((PsiElement)constructorInitializationList, constructorFieldInitializerFromText);
        }
        else {
            final OCConstructorInitializationList constructorInitializationList2 = OCElementFactory.constructorInitializationList(ocFunctionDefinition);
            OCChangeUtil.add((PsiElement)constructorInitializationList2, constructorFieldInitializerFromText);
            ocConstructorFieldInitializer = ocFunctionDefinition.setConstructorInitializationList(constructorInitializationList2).getInitializers().get(0);
        }
        return ocConstructorFieldInitializer;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
