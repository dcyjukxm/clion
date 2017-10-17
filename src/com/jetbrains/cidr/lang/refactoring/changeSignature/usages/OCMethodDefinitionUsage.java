// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCParameterInfo;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import java.util.Iterator;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCMethod;

public class OCMethodDefinitionUsage extends OCCallableDefinitionUsage<OCMethod>
{
    public OCMethodDefinitionUsage(@NotNull final OCMethod ocMethod, final boolean b) {
        if (ocMethod == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodDefinitionUsage", "<init>"));
        }
        super(ocMethod, b);
    }
    
    @Override
    public int getUsageRank() {
        return 0;
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo ocChangeInfo, @NotNull final OCMethod ocMethod, @NotNull final Project project) {
        try {
            if (ocChangeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeInfo", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodDefinitionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (ocMethod == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "method", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodDefinitionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodDefinitionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (ocChangeInfo.isParameterSetOrOrderChanged()) {
                return super.processUsage(ocChangeInfo, ocMethod, project);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        Label_0376: {
            try {
                if (!ocChangeInfo.isNameChanged()) {
                    if (!ocChangeInfo.isParameterTypesChanged()) {
                        break Label_0376;
                    }
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
            int n = 0;
            for (final OCMethodSelectorPart ocMethodSelectorPart : ocMethod.getParameters()) {
                final PsiElement selectorIdentifier = ocMethodSelectorPart.getSelectorIdentifier();
                final PsiElement parameter = ocMethodSelectorPart.getParameter();
                final OCTypeElement typeElement = ocMethodSelectorPart.getTypeElement();
                final OCParameterInfo ocParameterInfo = ocChangeInfo.getNewParameters()[n++];
                Label_0295: {
                    Label_0272: {
                        try {
                            if (selectorIdentifier == null) {
                                break Label_0295;
                            }
                            final OCParameterInfo ocParameterInfo2 = ocParameterInfo;
                            final String s = ocParameterInfo2.getSelector();
                            final boolean b = s.isEmpty();
                            if (b) {
                                break Label_0272;
                            }
                            break Label_0272;
                        }
                        catch (IllegalArgumentException ex6) {
                            throw c(ex6);
                        }
                        try {
                            final OCParameterInfo ocParameterInfo2 = ocParameterInfo;
                            final String s = ocParameterInfo2.getSelector();
                            final boolean b = s.isEmpty();
                            if (b) {
                                OCChangeUtil.delete(selectorIdentifier);
                                break Label_0295;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw c(ex7);
                        }
                    }
                    OCElementUtil.replaceWithIdentifier(selectorIdentifier, ocParameterInfo.getSelector(), (PsiElement)ocMethod);
                    try {
                        if (parameter != null) {
                            OCElementUtil.replaceWithIdentifier(parameter, ocChangeInfo.getNewParameterName(this.myInherited, ocMethod, ocParameterInfo), (PsiElement)ocMethod);
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw c(ex8);
                    }
                }
                Label_0351: {
                    try {
                        if (typeElement == null) {
                            continue;
                        }
                        final OCParameterInfo ocParameterInfo3 = ocParameterInfo;
                        final OCTypeElement ocTypeElement = typeElement;
                        final String s2 = ocTypeElement.getTextWithMacros();
                        final boolean b2 = ocParameterInfo3.isTypeChanged(s2);
                        if (b2) {
                            break Label_0351;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex9) {
                        throw c(ex9);
                    }
                    try {
                        final OCParameterInfo ocParameterInfo3 = ocParameterInfo;
                        final OCTypeElement ocTypeElement = typeElement;
                        final String s2 = ocTypeElement.getTextWithMacros();
                        final boolean b2 = ocParameterInfo3.isTypeChanged(s2);
                        if (!b2) {
                            continue;
                        }
                        OCChangeUtil.replaceHandlingMacros((PsiElement)typeElement, (PsiElement)OCElementFactory.typeElementFromText(ocParameterInfo.getTypeText(), (PsiElement)ocMethod));
                    }
                    catch (IllegalArgumentException ex10) {
                        throw c(ex10);
                    }
                }
            }
        }
        if (ocChangeInfo.isReturnTypeChanged()) {
            final OCTypeElement returnTypeElement = ocMethod.getReturnTypeElement();
            try {
                if (returnTypeElement != null) {
                    OCChangeUtil.replaceHandlingMacros((PsiElement)returnTypeElement, (PsiElement)OCElementFactory.typeElementFromText(ocChangeInfo.getNewReturnType(), (PsiElement)ocMethod));
                }
            }
            catch (IllegalArgumentException ex11) {
                throw c(ex11);
            }
        }
        final TextRange headerRange = ocMethod.getHeaderRange();
        CodeStyleManager.getInstance(project).reformatRange((PsiElement)ocMethod.getContainingFile(), headerRange.getStartOffset(), headerRange.getEndOffset(), true);
        return true;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
