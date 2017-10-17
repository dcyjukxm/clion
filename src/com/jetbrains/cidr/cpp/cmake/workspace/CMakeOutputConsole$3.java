// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.execution.impl.ConsoleViewImpl;
import java.awt.event.HierarchyEvent;
import com.intellij.execution.ui.ConsoleView;
import java.awt.event.HierarchyListener;

static final class CMakeOutputConsole$3 implements HierarchyListener {
    final /* synthetic */ ConsoleView val$console;
    
    @Override
    public void hierarchyChanged(final HierarchyEvent hierarchyEvent) {
        if ((hierarchyEvent.getChangeFlags() & 0x4L) != 0x0L) {
            ((ConsoleViewImpl)this.val$console).removeHierarchyListener(this);
            ((ConsoleViewImpl)this.val$console).requestScrollingToEnd();
        }
    }
}