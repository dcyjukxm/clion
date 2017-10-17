// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.codeInsight.navigation.actions.GotoImplementationAction;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.components.ApplicationComponent;

public class OCActionsManager implements ApplicationComponent
{
    public void initComponent() {
        final ActionManager instance = ActionManager.getInstance();
        instance.unregisterAction("GotoImplementation");
        instance.registerAction("GotoImplementation", (AnAction)new GotoImplementationAction() {
            @Override
            public void update(final AnActionEvent anActionEvent) {
                super.update(anActionEvent);
                anActionEvent.getPresentation().setText("D_efinition(s)");
            }
        });
    }
}
