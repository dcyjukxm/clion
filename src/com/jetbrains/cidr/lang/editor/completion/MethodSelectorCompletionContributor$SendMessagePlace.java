// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;

private static class SendMessagePlace
{
    @NotNull
    public final OCSendMessageExpression expression;
    @NotNull
    public final OCMessageArgument argument;
    
    public SendMessagePlace(@NotNull final OCMessageArgument argument) {
        if (argument == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$SendMessagePlace", "<init>"));
        }
        this.expression = (OCSendMessageExpression)argument.getParent();
        this.argument = argument;
    }
}
