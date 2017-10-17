// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.codeInsight.navigation.actions.GotoImplementationAction;

class OCActionsManager$1 extends GotoImplementationAction {
    @Override
    public void update(final AnActionEvent anActionEvent) {
        super.update(anActionEvent);
        anActionEvent.getPresentation().setText("D_efinition(s)");
    }
}