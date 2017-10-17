// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.lang.surroundWith.Surrounder;
import com.intellij.lang.surroundWith.SurroundDescriptor;

public class OCStatementSurroundDescriptor implements SurroundDescriptor
{
    private static final Surrounder[] SURROUNDERS;
    
    @NotNull
    public PsiElement[] getElementsToSurround(final PsiFile psiFile, final int n, final int n2) {
        FeatureUsageTracker.getInstance().triggerFeatureUsed("codeassists.surroundwith.expression");
        final PsiElement[] statementsAtRange = OCCodeInsightUtil.findStatementsAtRange(psiFile, n, n2, true);
        PsiElement[] array = null;
        Label_0034: {
            try {
                if (statementsAtRange != null) {
                    final PsiElement[] empty_ARRAY;
                    array = (empty_ARRAY = statementsAtRange);
                    break Label_0034;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            PsiElement[] empty_ARRAY;
            array = (empty_ARRAY = PsiElement.EMPTY_ARRAY);
            try {
                if (empty_ARRAY == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/surround/OCStatementSurroundDescriptor", "getElementsToSurround"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return array;
    }
    
    @NotNull
    public Surrounder[] getSurrounders() {
        Surrounder[] surrounders;
        try {
            surrounders = OCStatementSurroundDescriptor.SURROUNDERS;
            if (surrounders == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/surround/OCStatementSurroundDescriptor", "getSurrounders"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return surrounders;
    }
    
    public boolean isExclusive() {
        return false;
    }
    
    static {
        SURROUNDERS = new Surrounder[] { new OCIfSurrounder(), new OCIfElseSurrounder(), new OCIfRespondsToSurrounder(), new OCWhileSurrounder(), new OCDoWhileSurrounder(), new OCForSurrounder(), new OCTryCatchSurrounder(), new OCTryCatchFinallySurrounder(), new OCSynchronizedSurrounder(), new OCAutoreleasePoolSurrounder(), new OCBlockSurrounder(), new OCBlockExpressionStatementSurrounder() };
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
