// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.codeStyle.NewCodeStyleSettingsPanel;
import com.intellij.openapi.options.Configurable;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.application.options.codeStyle.CodeStyleMainPanel;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.ide.actions.ShowSettingsUtilImpl;
import com.intellij.openapi.options.ex.ConfigurableVisitor;
import com.jetbrains.cidr.lang.OCLanguage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OCNewFileWithSelectedExtensionAction$CreateFileDialog$3 implements ActionListener {
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final Configurable find = new ConfigurableVisitor.ByID("preferences.sourceCode." + OCLanguage.getInstance().getDisplayName()).find(ShowSettingsUtilImpl.getConfigurableGroups(CreateFileDialog.this.this$0.myState.project, true));
        OCLog.LOG.assertTrue(find != null);
        final CodeStyleMainPanel codeStyleMainPanel;
        final NewCodeStyleSettingsPanel[] array;
        int length;
        int i;
        final TabbedLanguageCodeStylePanel tabbedLanguageCodeStylePanel;
        ShowSettingsUtil.getInstance().editConfigurable(CreateFileDialog.this.this$0.myState.project, find, () -> {
            codeStyleMainPanel = (CodeStyleMainPanel)find.createComponent();
            OCLog.LOG.assertTrue(codeStyleMainPanel != null);
            codeStyleMainPanel.getPanels();
            for (length = array.length; i < length; ++i) {
                array[i].getSelectedPanel();
                OCLog.LOG.assertTrue(tabbedLanguageCodeStylePanel != null);
                tabbedLanguageCodeStylePanel.changeTab(OCBundle.message("fileExtensions.tabName", new Object[0]));
            }
        });
    }
}