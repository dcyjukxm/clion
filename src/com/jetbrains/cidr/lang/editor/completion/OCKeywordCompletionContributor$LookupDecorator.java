// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import org.jetbrains.annotations.NotNull;

private interface LookupDecorator
{
    @NotNull
    LookupElementBuilder decorate(@NotNull final OCCompletionParameters p0, @NotNull final LookupElementBuilder p1);
}
