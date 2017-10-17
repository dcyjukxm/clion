// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.actions;

import com.jetbrains.cidr.execution.debugger.CidrDebuggerSettings;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.util.PlatformUtils;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.openapi.actionSystem.ToggleAction;

public class CidrEnableValueRenderersAction extends ToggleAction
{
    public CidrEnableValueRenderersAction() {
        super(CidrDebuggerBundle.message("debug.settings.enableValueRenderers.action", new Object[0]));
    }
    
    public void update(@NotNull final AnActionEvent anActionEvent) {
        try {
            if (anActionEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/execution/debugger/actions/CidrEnableValueRenderersAction", "update"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Presentation presentation = null;
        boolean enabledAndVisible = false;
        Label_0068: {
            try {
                super.update(anActionEvent);
                presentation = anActionEvent.getPresentation();
                if (!PlatformUtils.isCLion()) {
                    enabledAndVisible = true;
                    break Label_0068;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            enabledAndVisible = false;
        }
        presentation.setEnabledAndVisible(enabledAndVisible);
    }
    
    public boolean isSelected(final AnActionEvent anActionEvent) {
        return CidrDebuggerSettings.getInstance().RENDERERS_ENABLED;
    }
    
    public void setSelected(final AnActionEvent anActionEvent, final boolean renderers_ENABLED) {
        CidrDebuggerSettings.getInstance().RENDERERS_ENABLED = renderers_ENABLED;
        CidrDebuggerSettings.updateCurrentDebugSession(anActionEvent);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
