// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.daemon.impl.DefaultHighlightVisitorBasedInspection;
import com.intellij.codeInspection.ProblemDescriptor;
import com.jetbrains.cidr.lang.psi.OCPragma;
import com.jetbrains.cidr.lang.daemon.OCProblemGroup;
import com.intellij.codeInspection.reference.RefEntity;
import com.intellij.codeInspection.CommonProblemDescriptor;
import com.intellij.codeInspection.ex.GlobalInspectionContextUtil;
import com.intellij.codeInspection.SuppressIntentionAction;
import com.intellij.lang.annotation.ProblemGroup;
import com.intellij.codeInspection.IntentionWrapper;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.InspectionManager;
import com.jetbrains.cidr.lang.daemon.OCPragmaSuppressionUtils;
import com.jetbrains.cidr.lang.daemon.OCAnnotator;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import com.jetbrains.cidr.lang.parser.OCMacroRange;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.Iterator;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.intellij.codeInspection.GlobalInspectionContext;
import com.intellij.codeInspection.ProblemDescriptionsProcessor;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.codeHighlighting.HighlightDisplayLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.codeInspection.LocalInspectionTool;

public abstract class OCInspection extends LocalInspectionTool
{
    @Nls
    @NotNull
    public String getGroupDisplayName() {
        String prettyNameFromClassName;
        try {
            prettyNameFromClassName = OCCodeInsightUtil.getPrettyNameFromClassName(this.getClass().getSuperclass());
            if (prettyNameFromClassName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspection", "getGroupDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return prettyNameFromClassName;
    }
    
    @Nls
    @NotNull
    public String getDisplayName() {
        String prettyNameFromClassName;
        try {
            prettyNameFromClassName = OCCodeInsightUtil.getPrettyNameFromClassName(this.getClass());
            if (prettyNameFromClassName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspection", "getDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return prettyNameFromClassName;
    }
    
    @NotNull
    public String getShortName() {
        String simpleName;
        try {
            simpleName = this.getClass().getSimpleName();
            if (simpleName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspection", "getShortName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return simpleName;
    }
    
    public boolean isEnabledByDefault() {
        return true;
    }
    
    @NotNull
    public HighlightDisplayLevel getDefaultLevel() {
        HighlightDisplayLevel highlightDisplayLevel = null;
        Label_0021: {
            try {
                if (this instanceof OCInspections.Hidden) {
                    final HighlightDisplayLevel highlightDisplayLevel2;
                    highlightDisplayLevel = (highlightDisplayLevel2 = HighlightDisplayLevel.NON_SWITCHABLE_ERROR);
                    break Label_0021;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            HighlightDisplayLevel highlightDisplayLevel2;
            highlightDisplayLevel = (highlightDisplayLevel2 = super.getDefaultLevel());
            try {
                if (highlightDisplayLevel2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspection", "getDefaultLevel"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return highlightDisplayLevel;
    }
    
    protected void registerProblems(@Nullable final ProblemsHolder problemsHolder, @Nullable final ProblemDescriptionsProcessor problemDescriptionsProcessor, @Nullable final GlobalInspectionContext globalInspectionContext, final boolean b, final List<? extends PsiElement> list, final String s, @Nullable final String s2, final ProblemHighlightType problemHighlightType, final IntentionAction... array) {
        for (final PsiElement psiElement : list) {
            try {
                if (psiElement.getTextLength() <= 0) {
                    continue;
                }
                this.registerProblem(problemsHolder, problemDescriptionsProcessor, globalInspectionContext, b, psiElement, s, s2, problemHighlightType, array);
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
    }
    
    protected void registerProblem(@Nullable final ProblemsHolder problemsHolder, @Nullable final ProblemDescriptionsProcessor problemDescriptionsProcessor, @Nullable final GlobalInspectionContext globalInspectionContext, final boolean b, @Nullable PsiElement psiElement, final String s, @Nullable final String s2, final ProblemHighlightType problemHighlightType, final IntentionAction... array) {
        try {
            if (psiElement == null) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        if (psiElement.getTextLength() == 0) {
            final OCMacroRange rangeInMacroCall = OCElementUtil.getRangeInMacroCall(psiElement);
            if (rangeInMacroCall == null) {
                return;
            }
            final Object macroCall = rangeInMacroCall.getMacroCall();
            if (macroCall != psiElement.getPrevSibling()) {
                return;
            }
            psiElement = (PsiElement)macroCall;
        }
        this.a(problemsHolder, problemDescriptionsProcessor, globalInspectionContext, b, psiElement, null, s, s2, problemHighlightType, OCElementUtil.getRangeWithMacros(psiElement), array);
    }
    
    protected void registerProblem(@Nullable final ProblemsHolder problemsHolder, @Nullable final ProblemDescriptionsProcessor problemDescriptionsProcessor, @Nullable final GlobalInspectionContext globalInspectionContext, final boolean b, @Nullable final OCElementsRange ocElementsRange, final String s, @Nullable final String s2, final ProblemHighlightType problemHighlightType, final IntentionAction... array) {
        try {
            if (ocElementsRange == null) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final TextRange textRange = ocElementsRange.getTextRange();
        try {
            if (textRange.isEmpty()) {
                return;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        this.a(problemsHolder, problemDescriptionsProcessor, globalInspectionContext, b, ocElementsRange.getFirstElement(), ocElementsRange.getLastElement(), s, s2, problemHighlightType, textRange, array);
    }
    
    private void a(final ProblemsHolder problemsHolder, final ProblemDescriptionsProcessor problemDescriptionsProcessor, final GlobalInspectionContext globalInspectionContext, final boolean b, PsiElement parent, @Nullable PsiElement parent2, final String s, final String s2, ProblemHighlightType generic_ERROR, final TextRange textRange, final IntentionAction[] array) {
        while (parent instanceof OCMacroForeignLeafElement) {
            parent = parent.getParent();
        }
        while (parent2 instanceof OCMacroForeignLeafElement) {
            parent2 = parent.getParent();
        }
        Label_0077: {
            try {
                if (parent2 == null) {
                    break Label_0077;
                }
                final PsiElement psiElement = parent;
                final int n = psiElement.getTextOffset();
                final PsiElement psiElement2 = parent2;
                final TextRange textRange2 = psiElement2.getTextRange();
                final int n2 = textRange2.getEndOffset();
                if (n >= n2) {
                    return;
                }
                break Label_0077;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement = parent;
                final int n = psiElement.getTextOffset();
                final PsiElement psiElement2 = parent2;
                final TextRange textRange2 = psiElement2.getTextRange();
                final int n2 = textRange2.getEndOffset();
                if (n >= n2) {
                    return;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final OCFile ocFile = (OCFile)parent.getContainingFile();
        final List mapNotNull = ContainerUtil.mapNotNull((Object[])array, intentionAction -> {
            try {
                if (intentionAction instanceof SuppressIntentionAction) {
                    return null;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return new IntentionWrapper(intentionAction, (PsiFile)ocFile);
        });
        final OCProblemGroup problemGroup = OCAnnotator.getProblemGroup(ocFile, textRange, this.getClass(), s2);
        String suppressOption = null;
        Label_0136: {
            try {
                if (problemGroup != null) {
                    suppressOption = problemGroup.getSuppressOption();
                    break Label_0136;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            suppressOption = null;
        }
        final OCPragma.Mode diagnosticMode = OCPragmaSuppressionUtils.getDiagnosticMode(ocFile, textRange.getStartOffset(), suppressOption);
        Label_0209: {
            Label_0201: {
                try {
                    if (diagnosticMode == null) {
                        break Label_0209;
                    }
                    final int[] array2 = OCInspection.OCInspection$1.$SwitchMap$com$jetbrains$cidr$lang$psi$OCPragma$Mode;
                    final OCPragma.Mode mode = diagnosticMode;
                    final int n3 = mode.ordinal();
                    final int n4 = array2[n3];
                    switch (n4) {
                        case 1: {
                            return;
                        }
                        case 2:
                        case 3: {
                            break Label_0201;
                        }
                        default: {
                            break Label_0209;
                        }
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    final int[] array2 = OCInspection.OCInspection$1.$SwitchMap$com$jetbrains$cidr$lang$psi$OCPragma$Mode;
                    final OCPragma.Mode mode = diagnosticMode;
                    final int n3 = mode.ordinal();
                    final int n4 = array2[n3];
                    switch (n4) {
                        case 1: {
                            return;
                        }
                        case 2:
                        case 3: {
                            break;
                        }
                        default: {
                            break Label_0209;
                        }
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            generic_ERROR = ProblemHighlightType.GENERIC_ERROR;
        }
        final InspectionManager instance = InspectionManager.getInstance(parent.getProject());
        ProblemDescriptor problemDescriptor;
        if (parent2 == null) {
            problemDescriptor = instance.createProblemDescriptor(parent, s, (LocalQuickFix[])mapNotNull.toArray(new IntentionWrapper[mapNotNull.size()]), generic_ERROR, b, false);
        }
        else {
            problemDescriptor = instance.createProblemDescriptor(parent, parent2, s, generic_ERROR, b, (LocalQuickFix[])mapNotNull.toArray(new IntentionWrapper[mapNotNull.size()]));
        }
        if (problemGroup != null) {
            problemDescriptor.setProblemGroup((ProblemGroup)problemGroup);
            for (final IntentionAction intentionAction : array) {
                try {
                    if (intentionAction instanceof SuppressIntentionAction) {
                        problemGroup.addSuppressAction((SuppressIntentionAction)intentionAction);
                    }
                }
                catch (IllegalStateException ex6) {
                    throw a(ex6);
                }
            }
        }
        try {
            if (problemDescriptionsProcessor != null) {
                problemDescriptionsProcessor.addProblemElement((RefEntity)GlobalInspectionContextUtil.retrieveRefElement(parent, globalInspectionContext), new CommonProblemDescriptor[] { problemDescriptor });
            }
        }
        catch (IllegalStateException ex7) {
            throw a(ex7);
        }
        try {
            if (problemsHolder != null) {
                problemsHolder.registerProblem(problemDescriptor);
            }
        }
        catch (IllegalStateException ex8) {
            throw a(ex8);
        }
    }
    
    public String getMainToolId() {
        try {
            if (OCInspections.class.equals(this.getClass().getDeclaringClass())) {
                return new DefaultHighlightVisitorBasedInspection.AnnotatorBasedInspection().getShortName();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public abstract static class ObjC extends OCInspection
    {
        @NotNull
        public String[] getGroupPath() {
            String[] array;
            try {
                array = new String[] { "Objective-C", this.getGroupDisplayName() };
                if (array == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspection$ObjC", "getGroupPath"));
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            return array;
        }
        
        private static IllegalStateException b(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public abstract static class Cpp extends OCInspection
    {
        @NotNull
        public String[] getGroupPath() {
            String[] array;
            try {
                array = new String[] { "C/C++", this.getGroupDisplayName() };
                if (array == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCInspection$Cpp", "getGroupPath"));
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            return array;
        }
        
        private static IllegalStateException b(final IllegalStateException ex) {
            return ex;
        }
    }
}
