// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions;

import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.symtable.OCSymbolTablesBuildingActivity;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.actionSystem.AnAction;

public class OCCompactSymbolsAction extends AnAction implements DumbAware
{
    public void actionPerformed(final AnActionEvent anActionEvent) {
        final Project eventProject = getEventProject(anActionEvent);
        if (eventProject == null) {
            return;
        }
        OCSymbolTablesBuildingActivity.getInstance(eventProject).rebuildSymbols(OCSymbolTablesBuildingActivity.Mode.COMPACT);
    }
    
    public void update(final AnActionEvent anActionEvent) {
        super.update(anActionEvent);
        anActionEvent.getPresentation().setEnabled(getEventProject(anActionEvent) != null);
    }
}
