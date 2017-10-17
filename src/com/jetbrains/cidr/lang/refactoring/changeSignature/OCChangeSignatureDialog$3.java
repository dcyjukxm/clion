// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Font;
import com.intellij.openapi.editor.colors.EditorFontType;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.util.ui.table.JBTableRow;
import com.intellij.util.ui.ColumnInfo;
import javax.swing.JComponent;
import javax.swing.JLabel;
import com.intellij.util.IJSwingUtilities;
import java.awt.Component;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.UIUtil;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiDocumentManager;
import javax.swing.JPanel;
import com.intellij.openapi.ui.VerticalFlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JTable;
import com.intellij.refactoring.changeSignature.ParameterTableModelItemBase;
import com.intellij.ui.EditorTextField;
import java.util.List;
import com.intellij.util.ui.table.JBTableRowEditor;

class OCChangeSignatureDialog$3 extends JBTableRowEditor {
    private List<EditorTextField> myEditors = new ArrayList<EditorTextField>();
    final /* synthetic */ ParameterTableModelItemBase val$item;
    final /* synthetic */ JTable val$t;
    
    @Override
    public void prepareEditor(final JTable table, final int n) {
        this.setLayout(new BorderLayout());
        final String[] array = { "West", "Center", "East" };
        int n2 = 0;
        for (final ColumnInfo columnInfo : ((OCParameterTableModel)OCChangeSignatureDialog.access$500(OCChangeSignatureDialog.this)).getColumnInfos()) {
            final JPanel panel = new JPanel((LayoutManager)new VerticalFlowLayout(0, 4, 2, true, false));
            EditorTextField editorTextField;
            if (OCParameterTableModel.isTypeColumn(columnInfo)) {
                editorTextField = new EditorTextField(PsiDocumentManager.getInstance(OCChangeSignatureDialog.access$600(OCChangeSignatureDialog.this)).getDocument((PsiFile)this.val$item.typeCodeFragment), OCChangeSignatureDialog.access$700(OCChangeSignatureDialog.this), (FileType)OCChangeSignatureDialog.this.getFileType());
            }
            else {
                editorTextField = new EditorTextField((String)columnInfo.valueOf((Object)this.val$item), OCChangeSignatureDialog.access$800(OCChangeSignatureDialog.this), (FileType)OCChangeSignatureDialog.this.getFileType());
            }
            editorTextField.addDocumentListener((DocumentListener)new DocumentListener() {
                public void documentChanged(final DocumentEvent documentEvent) {
                    JBTableRowEditor.this.fireDocumentChanged(documentEvent, n2);
                }
            });
            final JBLabel jbLabel = new JBLabel(columnInfo.getName(), UIUtil.ComponentStyle.SMALL);
            panel.add((Component)jbLabel);
            panel.add((Component)editorTextField);
            IJSwingUtilities.adjustComponentsOnMac((JLabel)jbLabel, (JComponent)editorTextField);
            editorTextField.setPreferredWidth(this.val$t.getWidth() / ((OCParameterTableModel)OCChangeSignatureDialog.access$900(OCChangeSignatureDialog.this)).getColumnCount());
            this.add(panel, array[n2]);
            this.myEditors.add(editorTextField);
            ++n2;
        }
    }
    
    @Override
    public JBTableRow getValue() {
        return new JBTableRow() {
            @Override
            public Object getValueAt(final int n) {
                if (OCParameterTableModel.isTypeColumn(((OCParameterTableModel)OCChangeSignatureDialog.access$1000(OCChangeSignatureDialog.this)).getColumnInfos()[n])) {
                    return JBTableRowEditor.this.val$item.typeCodeFragment;
                }
                return JBTableRowEditor.this.myEditors.get(n).getText();
            }
        };
    }
    
    private int a(final int n) {
        final Font font = EditorColorsManager.getInstance().getGlobalScheme().getFont(EditorFontType.PLAIN);
        final Font font2 = new Font(font.getFontName(), font.getStyle(), 12);
        final int n2 = (OCChangeSignatureDialog.access$1200(OCChangeSignatureDialog.this) + 1) * Toolkit.getDefaultToolkit().getFontMetrics(font2).stringWidth("W");
        final int n3 = (OCChangeSignatureDialog.access$1300(OCChangeSignatureDialog.this) + 1) * Toolkit.getDefaultToolkit().getFontMetrics(font2).stringWidth("W") + n2;
        if (OCChangeSignatureDialog.access$200(OCChangeSignatureDialog.this).isMethod()) {
            return (n <= n2) ? 0 : ((n <= n3) ? 1 : 2);
        }
        return (n > n3) ? 1 : 0;
    }
    
    @Override
    public JComponent getPreferredFocusedComponent() {
        final MouseEvent mouseEvent = this.getMouseEvent();
        if (mouseEvent == null) {
            return this.myEditors.get(0).getFocusTarget();
        }
        return this.myEditors.get(this.a((int)mouseEvent.getPoint().getX())).getFocusTarget();
    }
    
    @Override
    public JComponent[] getFocusableComponents() {
        final JComponent[] array = new JComponent[this.myEditors.size()];
        for (int i = 0; i < this.myEditors.size(); ++i) {
            array[i] = this.myEditors.get(i).getFocusTarget();
        }
        return array;
    }
}