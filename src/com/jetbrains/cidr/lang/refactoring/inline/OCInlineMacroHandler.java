// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.Map;
import com.intellij.openapi.project.Project;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;

public class OCInlineMacroHandler extends OCInlineActionHandlerBase<OCDefineDirective>
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @Override
    protected String getElementKind(final OCDefineDirective ocDefineDirective) {
        return "macro";
    }
    
    public boolean canInlineElement(final PsiElement psiElement) {
        return psiElement instanceof OCDefineDirective;
    }
    
    @Override
    protected String checkUsageValid(final PsiElement psiElement, final OCDefineDirective ocDefineDirective) {
        try {
            if (PsiTreeUtil.getParentOfType(psiElement, (Class)OCDefineDirective.class) != null) {
                return "Cannot inline the macro inside another macro definition";
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    protected void inlineUsage(final PsiElement psiElement, final OCDefineDirective ocDefineDirective, final PsiElement psiElement2, final Project project, final Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> map) {
        final OCMacroCall ocMacroCall = (OCMacroCall)PsiTreeUtil.getParentOfType(psiElement, (Class)OCMacroCall.class);
        Label_0038: {
            try {
                if (ocMacroCall == null) {
                    return;
                }
                final OCMacroCall ocMacroCall2 = ocMacroCall;
                final boolean b = ocMacroCall2.isValid();
                if (!b) {
                    return;
                }
                break Label_0038;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCMacroCall ocMacroCall2 = ocMacroCall;
                final boolean b = ocMacroCall2.isValid();
                if (!b) {
                    return;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final PsiFile containingFile = ocMacroCall.getContainingFile();
        final Document document = PsiDocumentManager.getInstance(project).getDocument(containingFile);
        Label_0077: {
            try {
                if (OCInlineMacroHandler.$assertionsDisabled) {
                    break Label_0077;
                }
                final Document document2 = document;
                if (document2 == null) {
                    break Label_0077;
                }
                break Label_0077;
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            try {
                final Document document2 = document;
                if (document2 == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        final String replacementText = ocMacroCall.getReplacementText();
        final TextRange textRange = ocMacroCall.getTextRange();
        final int startOffset = textRange.getStartOffset();
        document.replaceString(startOffset, textRange.getEndOffset(), (CharSequence)replacementText);
        PsiDocumentManager.getInstance(project).commitDocument(document);
        OCChangeUtil.reformatTextIfNotInjected(containingFile, startOffset, startOffset + replacementText.length());
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.inlineMacro";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/inline/OCInlineMacroHandler", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCInlineMacroHandler.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
