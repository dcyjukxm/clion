// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.actions;

import com.jetbrains.cidr.execution.debugger.CidrDebuggerSettings;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.openapi.actionSystem.ToggleAction;

public class CidrEnableFilteringValuesAction extends ToggleAction
{
    public CidrEnableFilteringValuesAction() {
        super(CidrDebuggerBundle.message("debug.settings.enableValuesFilter.checkbox", new Object[0]));
    }
    
    public boolean isSelected(final AnActionEvent anActionEvent) {
        return CidrDebuggerSettings.getInstance().VALUES_FILTER_ENABLED;
    }
    
    public void setSelected(final AnActionEvent anActionEvent, final boolean values_FILTER_ENABLED) {
        CidrDebuggerSettings.getInstance().VALUES_FILTER_ENABLED = values_FILTER_ENABLED;
        CidrDebuggerSettings.updateCurrentDebugSession(anActionEvent);
    }
}
