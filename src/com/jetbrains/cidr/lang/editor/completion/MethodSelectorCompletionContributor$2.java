// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class MethodSelectorCompletionContributor$2 extends OCCompletionProvider {
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$2", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final SendMessagePlace access$000 = MethodSelectorCompletionContributor.access$000(ocCompletionParameters.getPosition());
        try {
            if (access$000 == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final StringBuilder sb = new StringBuilder();
        final OCSendMessageExpression expression = access$000.expression;
        for (final OCMessageArgument ocMessageArgument : expression.getArguments()) {
            try {
                if (ocMessageArgument == access$000.argument) {
                    break;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            sb.append(ocMessageArgument.getArgumentSelector().getSelectorName());
        }
        MethodSelectorCompletionContributor.addCompletionsForReceiver(expression.getReceiverExpression(), sb.toString(), ocCompletionParameters, set, (Condition<OCMethodSymbol>)MethodSelectorCompletionContributor.access$100(expression, ocCompletionParameters.getCompletionType()));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}