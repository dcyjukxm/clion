// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.util.Key;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessAdapter;

class CMakeOutputConsole$4 extends ProcessAdapter {
    final /* synthetic */ int val$tabIndex;
    
    public void onTextAvailable(final ProcessEvent processEvent, final Key key) {
        CMakeOutputConsole.access$200(CMakeOutputConsole.this, () -> CMakeOutputConsole.access$300(CMakeOutputConsole.this, this.val$tabIndex).print(processEvent.getText(), ConsoleViewContentType.NORMAL_OUTPUT));
    }
}