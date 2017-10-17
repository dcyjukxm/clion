// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import javax.swing.Icon;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.ui.ColoredTableCellRenderer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import com.intellij.ui.ScrollPaneFactory;
import javax.swing.JTable;
import com.intellij.ui.TableSpeedSearch;
import com.intellij.util.ui.JBUI;
import java.awt.Dimension;
import javax.swing.table.TableCellRenderer;
import com.intellij.ui.TableUtil;
import javax.swing.table.TableModel;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JComponent;
import java.util.Iterator;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.intellij.util.containers.hash.HashMap;
import java.util.ArrayList;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.psi.SmartPsiElementPointer;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Map;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.List;
import com.intellij.ui.table.JBTable;
import com.intellij.openapi.ui.DialogWrapper;

public class OCEscalateVisibilityDialog extends DialogWrapper
{
    private static final int CHECKED_COLUMN = 0;
    private static final int MEMBER_COLUMN = 1;
    private static final int FORMER_VISIBILITY_COLUMN = 2;
    private static final int REQUIRED_VISIBILITY_COLUMN = 3;
    private JBTable myTable;
    private List<OCMemberInfo> myMembers;
    private Map<OCSymbol, OCVisibility> myRequiredVisibilities;
    private Map<OCSymbol, SmartPsiElementPointer> mySymbolToElementMap;
    
    public OCEscalateVisibilityDialog(@Nullable final Project project, final Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> map) {
        super(project);
        this.myMembers = new ArrayList<OCMemberInfo>();
        this.myRequiredVisibilities = (Map<OCSymbol, OCVisibility>)new HashMap();
        this.mySymbolToElementMap = (Map<OCSymbol, SmartPsiElementPointer>)new HashMap();
        for (final SmartPsiElementPointer smartPsiElementPointer : map.keySet()) {
            final Pair<OCSymbol, OCVisibility> pair = map.get(smartPsiElementPointer);
            final OCSymbol ocSymbol = (OCSymbol)pair.getFirst();
            this.myMembers.add(new OCMemberInfo(ocSymbol, ocSymbol, true, null));
            this.myRequiredVisibilities.put(ocSymbol, (OCVisibility)pair.getSecond());
            this.mySymbolToElementMap.put(ocSymbol, smartPsiElementPointer);
        }
        Collections.sort(this.myMembers, (ocMemberInfo, ocMemberInfo2) -> ocMemberInfo.getDisplayName().compareTo(ocMemberInfo2.getDisplayName()));
        final Iterator<OCMemberInfo> iterator2 = this.myMembers.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().setChecked(true);
        }
        this.setTitle("Escalate Visibility");
        this.getOKAction().putValue("Name", "Yes");
        this.getCancelAction().putValue("Name", "No");
        this.init();
    }
    
    public List<Pair<SmartPsiElementPointer, OCVisibility>> getCheckedMembers() {
        final ArrayList<Pair> list = (ArrayList<Pair>)new ArrayList<Pair<SmartPsiElementPointer, OCVisibility>>();
        for (final OCMemberInfo ocMemberInfo : this.myMembers) {
            if (ocMemberInfo.isChecked()) {
                final OCSymbol symbol = ocMemberInfo.getSymbol();
                list.add(Pair.create((Object)this.mySymbolToElementMap.get(symbol), (Object)this.myRequiredVisibilities.get(symbol)));
            }
        }
        return (List<Pair<SmartPsiElementPointer, OCVisibility>>)list;
    }
    
    protected JComponent createNorthPanel() {
        final Box verticalBox = Box.createVerticalBox();
        final JLabel label = new JLabel();
        label.setText("Do you want to escalate the visibility for the following members?");
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, "North");
        verticalBox.add(panel);
        verticalBox.add(Box.createVerticalStrut(7));
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(verticalBox, "Center");
        return panel2;
    }
    
    protected JComponent createCenterPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        (this.myTable = new JBTable()).setModel((TableModel)new MyModel());
        final TableColumnModel columnModel = this.myTable.getColumnModel();
        TableUtil.setupCheckboxColumn(columnModel.getColumn(0));
        columnModel.getColumn(1).setCellRenderer((TableCellRenderer)new MyTableRenderer());
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(3).setCellRenderer((TableCellRenderer)new MyTableRenderer());
        this.myTable.setPreferredScrollableViewportSize(new Dimension(630, this.myTable.getRowHeight() * 12));
        this.myTable.getSelectionModel().setSelectionMode(2);
        this.myTable.setShowGrid(false);
        this.myTable.setIntercellSpacing((Dimension)JBUI.emptySize());
        new TableSpeedSearch((JTable)this.myTable);
        panel.add(ScrollPaneFactory.createScrollPane((Component)this.myTable), "Center");
        return panel;
    }
    
    private class MyModel extends AbstractTableModel
    {
        @Override
        public int getRowCount() {
            return OCEscalateVisibilityDialog.this.myMembers.size();
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
            final OCMemberInfo ocMemberInfo = OCEscalateVisibilityDialog.this.myMembers.get(n);
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
                        return OCEscalateVisibilityDialog.this.myRequiredVisibilities.get(ocMemberInfo.getSymbol());
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
                    OCEscalateVisibilityDialog.this.myMembers.get(n).setChecked((boolean)o);
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
    
    private class MyTableRenderer extends ColoredTableCellRenderer
    {
        public void customizeCellRenderer(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
            final int convertColumnIndexToModel = OCEscalateVisibilityDialog.this.myTable.convertColumnIndexToModel(n2);
            final OCMemberInfo ocMemberInfo = OCEscalateVisibilityDialog.this.myMembers.get(n);
            String string = o.toString();
            if (convertColumnIndexToModel == 3 && OCVisibility.shouldBeDeclaredInInterface(ocMemberInfo.getSymbol(), (OCVisibility)o)) {
                string = "declare in interface";
            }
            if (convertColumnIndexToModel == 1) {
                this.setIcon(((OCSymbolHolderVirtualPsiElement)ocMemberInfo.getMember()).getIcon(0));
            }
            else {
                this.setIcon((Icon)null);
            }
            this.setIconOpaque(false);
            this.setOpaque(false);
            this.append(string);
        }
    }
}
