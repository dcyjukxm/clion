// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ide.hierarchy.HierarchyProvider;
import com.intellij.lang.LanguageExtension;
import com.intellij.ide.hierarchy.actions.BrowseHierarchyActionBase;

public class BrowseIncludeHierarchyAction extends BrowseHierarchyActionBase
{
    public BrowseIncludeHierarchyAction() {
        super(LanguageIncludeHierarchy.INSTANCE);
    }
    
    @Override
    public final void update(final AnActionEvent anActionEvent) {
        final Presentation presentation = anActionEvent.getPresentation();
        if (!ActionPlaces.isMainMenuOrActionSearch(anActionEvent.getPlace())) {
            presentation.setText("Imports Hierarchy");
        }
        super.update(anActionEvent);
    }
}
