// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.intellij.ui.EditorTextField;
import com.intellij.util.ui.table.JBTableRow;

class OCChangeSignatureDialog$3$2 implements JBTableRow {
    @Override
    public Object getValueAt(final int n) {
        if (OCParameterTableModel.isTypeColumn(((OCParameterTableModel)OCChangeSignatureDialog.access$1000(JBTableRowEditor.this.this$0)).getColumnInfos()[n])) {
            return JBTableRowEditor.this.val$item.typeCodeFragment;
        }
        return OCChangeSignatureDialog$3.access$1100(JBTableRowEditor.this).get(n).getText();
    }
}