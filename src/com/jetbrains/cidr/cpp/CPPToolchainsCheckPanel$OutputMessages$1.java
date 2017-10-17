// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import java.awt.Cursor;
import com.intellij.util.ui.UIUtil;
import javax.swing.JTextPane;
import javax.swing.JComponent;
import com.intellij.util.PairFunction;
import javax.swing.Icon;
import com.intellij.openapi.ui.Messages;

static final class CPPToolchainsCheckPanel$OutputMessages$1 extends Messages.TwoStepConfirmationDialog {
    protected JComponent createNorthPanel() {
        return null;
    }
    
    protected JComponent createCenterPanel() {
        final JComponent doCreateCenterPanel = this.doCreateCenterPanel();
        final JTextPane textPane = (JTextPane)UIUtil.findComponentOfType(doCreateCenterPanel, (Class)JTextPane.class);
        if (textPane != null) {
            textPane.setBackground(UIUtil.getTextFieldBackground());
            textPane.setBorder(UIUtil.getTextFieldBorder());
            textPane.setCursor(Cursor.getPredefinedCursor(2));
        }
        return doCreateCenterPanel;
    }
}