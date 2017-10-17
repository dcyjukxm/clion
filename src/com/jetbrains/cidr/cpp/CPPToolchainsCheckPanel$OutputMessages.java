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

private static class OutputMessages extends Messages
{
    public static void showMessageDialog(final String s) {
        new Messages.TwoStepConfirmationDialog(s, "CMake Errors", new String[] { "Close" }, null, false, 0, -1, null, null) {
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
        }.show();
    }
}
