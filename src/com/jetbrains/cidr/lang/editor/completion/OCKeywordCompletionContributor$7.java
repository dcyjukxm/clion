// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.PatternCondition;

class OCKeywordCompletionContributor$7 extends PatternCondition<PsiElement> {
    public boolean accepts(@NotNull final PsiElement psiElement, final ProcessingContext processingContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$7", "accepts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PsiElement prevLeaf = psiElement;
        try {
            if (!psiElement.getText().startsWith("#")) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    Label_0098_Outer:
        while (true) {
            while (true) {
                if (!((prevLeaf = OCElementUtil.getPrevLeaf(prevLeaf)) instanceof PsiWhiteSpace)) {
                    Label_0121: {
                        try {
                            if (prevLeaf == null) {
                                break Label_0121;
                            }
                            final PsiElement psiElement2 = prevLeaf;
                            final int n = psiElement2.getTextLength();
                            if (n == 0) {
                                break Label_0098;
                            }
                            break Label_0121;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final PsiElement psiElement2 = prevLeaf;
                            final int n = psiElement2.getTextLength();
                            if (n != 0) {
                                break Label_0121;
                            }
                            if (!prevLeaf.getText().contains("\n")) {
                                continue Label_0098_Outer;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                        return true;
                        try {
                            if (prevLeaf == null) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    return false;
                }
                continue;
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}