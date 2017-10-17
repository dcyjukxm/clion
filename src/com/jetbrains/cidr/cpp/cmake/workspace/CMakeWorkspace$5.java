// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.jetbrains.cidr.cpp.cmake.actions.ChangeCMakeProjectContentRootAction;
import javax.swing.event.HyperlinkEvent;
import com.intellij.ui.HyperlinkAdapter;

class CMakeWorkspace$5 extends HyperlinkAdapter {
    protected void hyperlinkActivated(final HyperlinkEvent hyperlinkEvent) {
        if ("change".equals(hyperlinkEvent.getDescription())) {
            ChangeCMakeProjectContentRootAction.perform(CMakeWorkspace.access$300(CMakeWorkspace.this));
        }
        else if ("ignore".equals(hyperlinkEvent.getDescription())) {
            CMakeWorkspace.access$402(CMakeWorkspace.this, true);
        }
    }
}