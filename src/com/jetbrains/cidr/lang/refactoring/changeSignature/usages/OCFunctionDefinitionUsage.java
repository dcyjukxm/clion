// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCChangeTypeIntentionAction;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureUsageProcessor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;

public class OCFunctionDefinitionUsage extends OCCallableDefinitionUsage<OCFunctionDeclaration>
{
    public OCFunctionDefinitionUsage(@NotNull final OCFunctionDeclaration ocFunctionDeclaration, final boolean b) {
        if (ocFunctionDeclaration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionDefinitionUsage", "<init>"));
        }
        super(ocFunctionDeclaration, b);
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo ocChangeInfo, @NotNull final OCFunctionDeclaration ocFunctionDeclaration, @NotNull final Project project) {
        try {
            if (ocChangeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeInfo", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionDefinitionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (ocFunctionDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "function", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionDefinitionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionDefinitionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (ocChangeInfo.isParameterSetOrOrderChanged()) {
                return super.processUsage(ocChangeInfo, ocFunctionDeclaration, project);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        if (ocChangeInfo.isNameChanged()) {
            final OCDeclarator declarator = ocFunctionDeclaration.getDeclarator();
            PsiElement nameIdentifier = null;
            Label_0186: {
                try {
                    if (declarator != null) {
                        nameIdentifier = declarator.getNameIdentifier();
                        break Label_0186;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw c(ex5);
                }
                nameIdentifier = null;
            }
            final PsiElement psiElement = nameIdentifier;
            try {
                if (psiElement != null) {
                    OCElementUtil.replaceWithIdentifier(psiElement, ocChangeInfo.getNewName(), (PsiElement)ocFunctionDeclaration);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw c(ex6);
            }
        }
        try {
            if (ocChangeInfo.isParameterTypesChanged()) {
                OCChangeSignatureUsageProcessor.processParameters(ocChangeInfo, ocFunctionDeclaration, ocFunctionDeclaration.getParameterList(), project, this.myInherited);
            }
        }
        catch (IllegalArgumentException ex7) {
            throw c(ex7);
        }
        Label_0325: {
            try {
                if (!ocChangeInfo.isReturnTypeChanged() || ocChangeInfo.getOldMethodDescriptor().isConstructor()) {
                    break Label_0325;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw c(ex8);
            }
            final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = ocFunctionDeclaration.getSymbol();
            final OCType type = OCElementFactory.typeElementFromText(ocChangeInfo.getNewReturnType(), (PsiElement)ocFunctionDeclaration).getType();
            try {
                if (ocSymbolWithQualifiedName != null) {
                    new OCChangeTypeIntentionAction(ocSymbolWithQualifiedName, type, true).invoke(project, null, ocFunctionDeclaration.getContainingFile());
                }
            }
            catch (IllegalArgumentException ex9) {
                throw c(ex9);
            }
        }
        final OCParameterList parameterList = ocFunctionDeclaration.getParameterList();
        if (parameterList != null) {
            final TextRange rangeWithMacros = parameterList.getRangeWithMacros();
            CodeStyleManager.getInstance(project).reformatRange((PsiElement)ocFunctionDeclaration.getContainingFile(), rangeWithMacros.getStartOffset(), rangeWithMacros.getEndOffset(), true);
        }
        return true;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
