// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import javax.swing.event.HyperlinkListener;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.ui.MessageType;

interface BalloonNotifier
{
    void notify(@NotNull final MessageType p0, @NotNull final String p1, @NotNull final HyperlinkListener p2);
}
