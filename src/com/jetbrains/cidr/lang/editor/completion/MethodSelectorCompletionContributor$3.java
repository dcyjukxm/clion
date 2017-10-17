// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class MethodSelectorCompletionContributor$3 extends OCCompletionProvider {
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$3", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement position = ocCompletionParameters.getPosition();
        final OCExpression methodReceiverExpression = MethodSelectorCompletionContributor.getMethodReceiverExpression(position);
        try {
            if (methodReceiverExpression == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0119: {
            if (MethodSelectorCompletionContributor.PLACE.accepts((Object)position)) {
                final SendMessagePlace access$000 = MethodSelectorCompletionContributor.access$000(position);
                try {
                    if (access$000 == null) {
                        break Label_0119;
                    }
                    final SendMessagePlace sendMessagePlace = access$000;
                    final OCSendMessageExpression ocSendMessageExpression = sendMessagePlace.expression;
                    final OCExpression ocExpression = methodReceiverExpression;
                    final PsiElement psiElement = ocExpression.getParent();
                    if (ocSendMessageExpression == psiElement) {
                        return;
                    }
                    break Label_0119;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final SendMessagePlace sendMessagePlace = access$000;
                    final OCSendMessageExpression ocSendMessageExpression = sendMessagePlace.expression;
                    final OCExpression ocExpression = methodReceiverExpression;
                    final PsiElement psiElement = ocExpression.getParent();
                    if (ocSendMessageExpression == psiElement) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
        }
        MethodSelectorCompletionContributor.access$200(methodReceiverExpression, ocCompletionParameters, set);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}