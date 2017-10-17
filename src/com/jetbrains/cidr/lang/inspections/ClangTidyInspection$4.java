// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.settings.CPPLanguageConfigurable;
import com.intellij.openapi.options.ShowSettingsUtil;
import java.awt.Component;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import javax.swing.event.HyperlinkEvent;
import com.intellij.ui.HyperlinkLabel;
import javax.swing.event.HyperlinkListener;

static final class ClangTidyInspection$4 implements HyperlinkListener {
    final /* synthetic */ HyperlinkLabel val$hyperlinkLabel;
    
    @Override
    public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
        final Project project = (Project)CommonDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext((Component)this.val$hyperlinkLabel));
        if (project != null) {
            ShowSettingsUtil.getInstance().showSettingsDialog(project, (Class)CPPLanguageConfigurable.class);
        }
    }
}