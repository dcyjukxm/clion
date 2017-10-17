// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import javax.swing.table.AbstractTableModel;

private class MyModel extends AbstractTableModel
{
    @Override
    public int getRowCount() {
        return OCPushDownDialog.access$200(OCPushDownDialog.this).size();
    }
    
    @Override
    public int getColumnCount() {
        return 2;
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
        return OCSymbol.class;
    }
    
    @Override
    public Object getValueAt(final int n, final int n2) {
        final OCSymbol ocSymbol = OCPushDownDialog.access$200(OCPushDownDialog.this).get(n);
        try {
            switch (n2) {
                case 0: {
                    return OCPushDownDialog.access$300(OCPushDownDialog.this).contains(ocSymbol);
                }
                case 1: {
                    break;
                }
                default: {
                    throw new RuntimeException("Incorrect column index");
                }
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return ocSymbol.getPresentableName();
    }
    
    @Override
    public void setValueAt(final Object o, final int n, final int n2) {
        Label_0077: {
            Label_0018: {
                try {
                    if (n2 != 0) {
                        return;
                    }
                    final Object o2 = o;
                    final Boolean b = Boolean.TRUE;
                    if (o2 == b) {
                        break Label_0018;
                    }
                    break Label_0018;
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
                try {
                    final Object o2 = o;
                    final Boolean b = Boolean.TRUE;
                    if (o2 == b) {
                        OCPushDownDialog.access$300(OCPushDownDialog.this).add(OCPushDownDialog.access$200(OCPushDownDialog.this).get(n));
                        break Label_0077;
                    }
                }
                catch (RuntimeException ex2) {
                    throw b(ex2);
                }
            }
            OCPushDownDialog.access$300(OCPushDownDialog.this).remove(OCPushDownDialog.access$200(OCPushDownDialog.this).get(n));
        }
        OCPushDownDialog.access$400(OCPushDownDialog.this);
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
                default: {
                    throw new RuntimeException("Incorrect column index");
                }
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return "Inheritor";
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
