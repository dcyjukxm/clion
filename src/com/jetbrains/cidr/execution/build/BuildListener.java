// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.build;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.ui.MessageType;

interface BuildListener
{
    void finished(@NotNull final MessageType p0, @NotNull final String p1);
}
