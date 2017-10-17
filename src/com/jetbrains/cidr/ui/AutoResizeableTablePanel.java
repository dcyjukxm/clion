// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import java.awt.Insets;
import javax.swing.table.JTableHeader;
import java.awt.Dimension;
import com.intellij.util.ui.UIUtil;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.intellij.ui.CommonActionsPanel;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JPanel;

public class AutoResizeableTablePanel extends JPanel
{
    private final JTable myTable;
    private final JComponent myTableContainer;
    private final CommonActionsPanel myTableActionsPanel;
    private final int myMinLines;
    private final int myMaxLines;
    
    public AutoResizeableTablePanel(final JTable myTable, final JComponent myTableContainer, final CommonActionsPanel myTableActionsPanel, final int myMinLines, final int myMaxLines) {
        super(new BorderLayout());
        this.myTable = myTable;
        this.myTableContainer = myTableContainer;
        this.myTableActionsPanel = myTableActionsPanel;
        this.myMinLines = myMinLines;
        this.myMaxLines = myMaxLines;
        this.add(myTableContainer);
        myTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(final TableModelEvent tableModelEvent) {
                AutoResizeableTablePanel.this.updateSize();
            }
        });
    }
    
    public void updateSize() {
        this.invalidate();
        final Component ultimateParent = UIUtil.findUltimateParent((Component)this);
        if (ultimateParent != null) {
            ultimateParent.validate();
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        final Dimension minimumSize = this.getMinimumSize();
        final Dimension maximumSize = this.getMaximumSize();
        return new Dimension(Math.min(maximumSize.width, minimumSize.width), Math.min(maximumSize.height, minimumSize.height));
    }
    
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(400, this.a(Math.max(this.myMinLines, this.myTable.getRowCount())));
    }
    
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, this.a(this.myMaxLines));
    }
    
    private int a(final int n) {
        int n2 = (this.myTable.getRowHeight() + this.myTable.getIntercellSpacing().height) * n;
        final JTableHeader tableHeader = this.myTable.getTableHeader();
        if (tableHeader != null) {
            n2 += tableHeader.getHeight();
        }
        final Insets insets = this.myTable.getInsets();
        final int n3 = n2 + (insets.bottom + insets.top);
        final Insets insets2 = this.getInsets();
        final int n4 = n3 + (insets2.bottom + insets2.top);
        final Insets insets3 = this.myTableContainer.getInsets();
        int max = n4 + (insets3.bottom + insets3.top);
        switch (this.myTableActionsPanel.getPosition()) {
            case TOP:
            case BOTTOM: {
                max += this.myTableActionsPanel.getHeight();
                break;
            }
            case LEFT:
            case RIGHT: {
                max = Math.max(this.myTableActionsPanel.getPreferredSize().height, max);
                break;
            }
        }
        return max;
    }
}
