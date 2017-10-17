// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import javax.swing.table.AbstractTableModel;

private class MyModel extends AbstractTableModel
{
    @Override
    public int getRowCount() {
        return OCEscalateVisibilityDialog.access$200(OCEscalateVisibilityDialog.this).size();
    }
    
    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public Class getColumnClass(final int n) {
        try {
            if (n == 0) {
                return Boolean.class;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (n == 1) {
                return OCSymbol.class;
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return OCVisibility.class;
    }
    
    @Override
    public Object getValueAt(final int n, final int n2) {
        final OCMemberInfo ocMemberInfo = OCEscalateVisibilityDialog.access$200(OCEscalateVisibilityDialog.this).get(n);
        try {
            switch (n2) {
                case 0: {
                    return ocMemberInfo.isChecked();
                }
                case 1: {
                    break;
                }
                case 2: {
                    return ocMemberInfo.getVisibility();
                }
                case 3: {
                    return OCEscalateVisibilityDialog.access$300(OCEscalateVisibilityDialog.this).get(ocMemberInfo.getSymbol());
                }
                default: {
                    throw new RuntimeException("Incorrect column index");
                }
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return ocMemberInfo.getDisplayName();
    }
    
    @Override
    public void setValueAt(final Object o, final int n, final int n2) {
        try {
            if (n2 == 0) {
                OCEscalateVisibilityDialog.access$200(OCEscalateVisibilityDialog.this).get(n).setChecked((boolean)o);
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
    }
    
    @Override
    public String getColumnName(final int n) {
        try {
            switch (n) {
                case 0: {
                    return " ";
                }
                case 1: {
                    break;
                }
                case 2: {
                    return "Current visibility";
                }
                case 3: {
                    return "Required visibility";
                }
                default: {
                    throw new RuntimeException("Incorrect column index");
                }
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return "Member";
    }
    
    @Override
    public boolean isCellEditable(final int n, final int n2) {
        try {
            if (n2 == 0) {
                return true;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return false;
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
