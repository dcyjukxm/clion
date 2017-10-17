// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.lang.annotation.Annotation;
import java.util.List;
import com.jetbrains.cidr.lang.quickfixes.OCSafeDeleteIntentionAction;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.psi.impl.search.PsiSearchHelperImpl;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.CommonProcessors;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCProvideStringLocalizationsIntentionAction;
import com.intellij.lang.annotation.ProblemGroup;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInspection.ProblemHighlightType;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.resolve.references.OCStringResourceReference;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCLocalizedStringSymbol;
import com.jetbrains.cidr.lang.psi.OCLocalizedString;
import com.intellij.lang.annotation.AnnotationHolder;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.lang.annotation.Annotator;

public class OCStringsAnnotator implements Annotator
{
    public void annotate(@NotNull final PsiElement psiElement, @NotNull final AnnotationHolder annotationHolder) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/daemon/OCStringsAnnotator", "annotate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (annotationHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/daemon/OCStringsAnnotator", "annotate"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!(psiElement instanceof OCLocalizedString) || psiElement.getContainingFile().getName().equals("InfoPlist.strings")) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCLocalizedStringSymbol ocLocalizedStringSymbol = ((OCLocalizedString)psiElement).getSymbol();
        final OCStringResourceReference ocStringResourceReference = new OCStringResourceReference(psiElement.getFirstChild(), FileUtil.getNameWithoutExtension(psiElement.getContainingFile().getName()));
        final List<String> notLocalizedLanguages = ocStringResourceReference.getNotLocalizedLanguages();
        Label_0339: {
            StringBuilder sb2 = null;
            String s3 = null;
            Label_0226: {
                Label_0215: {
                    try {
                        if (notLocalizedLanguages.isEmpty()) {
                            break Label_0339;
                        }
                        final StringBuilder sb = new StringBuilder();
                        final List<String> list = notLocalizedLanguages;
                        final String s = ", ";
                        final String s2 = StringUtil.join((Collection)list, s);
                        sb2 = sb.append(s2);
                        final List<String> list2 = notLocalizedLanguages;
                        final int n = list2.size();
                        final int n2 = 1;
                        if (n > n2) {
                            break Label_0215;
                        }
                        break Label_0215;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final StringBuilder sb = new StringBuilder();
                        final List<String> list = notLocalizedLanguages;
                        final String s = ", ";
                        final String s2 = StringUtil.join((Collection)list, s);
                        sb2 = sb.append(s2);
                        final List<String> list2 = notLocalizedLanguages;
                        final int n = list2.size();
                        final int n2 = 1;
                        if (n > n2) {
                            s3 = " localizations don't";
                            break Label_0226;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                s3 = " localization doesn't";
            }
            final String string = sb2.append(s3).append(" contain the value for ").toString();
            final ProblemHighlightType inspectionHighlightType = OCAnnotator.getInspectionHighlightType(psiElement, OCInspections.StringLocalizationInspection.class, ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
            if (inspectionHighlightType != null) {
                final Annotation warningAnnotation = annotationHolder.createWarningAnnotation(psiElement, string + "'" + ((OCLocalizedString)psiElement).getKey() + "'");
                warningAnnotation.setHighlightType(inspectionHighlightType);
                warningAnnotation.setProblemGroup((ProblemGroup)new OCProblemGroup(OCInspections.StringLocalizationInspection.class.getSimpleName()));
                warningAnnotation.registerFix((IntentionAction)new OCProvideStringLocalizationsIntentionAction(ocStringResourceReference));
            }
        }
        if (ocLocalizedStringSymbol != null) {
            final CommonProcessors.FindFirstProcessor<VirtualFile> processor = new CommonProcessors.FindFirstProcessor<VirtualFile>() {
                protected boolean accept(final VirtualFile virtualFile) {
                    final String extension = virtualFile.getExtension();
                    return extension != null && !extension.equals("strings");
                }
            };
            ((PsiSearchHelperImpl)PsiSearchHelper.SERVICE.getInstance(psiElement.getProject())).processFilesWithText(OCSearchScope.getProjectSourcesScope(psiElement.getProject()), (short)4, true, ocLocalizedStringSymbol.getName(), (Processor<VirtualFile>)processor);
            if (!processor.isFound()) {
                final ProblemHighlightType inspectionHighlightType2 = OCAnnotator.getInspectionHighlightType(psiElement, OCInspections.UnusedLocalization.class, ProblemHighlightType.LIKE_UNUSED_SYMBOL);
                if (inspectionHighlightType2 != null) {
                    final Annotation warningAnnotation2 = annotationHolder.createWarningAnnotation(psiElement, ocLocalizedStringSymbol.getNameWithKindUppercase() + " is never used");
                    warningAnnotation2.setHighlightType(inspectionHighlightType2);
                    warningAnnotation2.setProblemGroup((ProblemGroup)new OCProblemGroup(OCInspections.StringLocalizationInspection.class.getSimpleName()));
                    warningAnnotation2.registerFix((IntentionAction)new OCSafeDeleteIntentionAction(psiElement, ocLocalizedStringSymbol.getNameWithKindLowercase()));
                }
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
