// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import java.awt.Insets;
import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;
import javax.swing.Icon;
import com.intellij.ui.ColoredTableCellRenderer;
import javax.swing.table.AbstractTableModel;
import com.jetbrains.cidr.lang.search.OCDirectInheritorsSearch;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.search.OCDirectStructInheritorsSearch;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveCppProcessor;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveObjCProcessor;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveProcessor;
import com.intellij.openapi.project.Project;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.refactoring.classMembers.UsedByDependencyMemberInfoModel;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import com.intellij.openapi.options.ConfigurationException;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumnModel;
import com.intellij.ui.ScrollPaneFactory;
import javax.swing.JTable;
import com.intellij.ui.TableSpeedSearch;
import java.awt.Dimension;
import javax.swing.table.TableCellRenderer;
import com.intellij.ui.TableUtil;
import javax.swing.table.TableModel;
import java.awt.Component;
import javax.swing.JLabel;
import com.intellij.util.ui.JBUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nullable;
import com.intellij.refactoring.RefactoringBundle;
import java.util.HashSet;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import com.intellij.ui.table.JBTable;

public class OCPushDownDialog extends OCAbstractMoveDialog
{
    private static final int CHECKED_COLUMN = 0;
    private static final int INHERITOR_COLUMN = 1;
    private JBTable myInheritorsTable;
    private List<OCSymbol> myInheritors;
    private Set<OCSymbol> myCheckedInheritors;
    private boolean myInheritorsCancelled;
    
    public OCPushDownDialog(@NotNull final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition) {
        if (ocSymbolDeclarator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/refactoring/move/ui/OCPushDownDialog", "<init>"));
        }
        super(ocSymbolDeclarator, ocSymbol, condition, ocSymbolDeclarator.getProject());
        this.myCheckedInheritors = new HashSet<OCSymbol>();
        this.myMemberInfos = ((AbstractMemberInfoStorage<T, PsiElement, OCMemberInfo>)this.myStorage).getClassMemberInfos((PsiElement)ocSymbolDeclarator);
        this.setMembersChecked();
        this.setTitle(RefactoringBundle.message("push.members.down.title"));
        this.c();
        this.init();
    }
    
    @Nullable
    protected String getHelpId() {
        return "refactoring.pushMembersDown";
    }
    
    protected JComponent createNorthPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = (Insets)JBUI.insets(4, 8);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 17;
        panel.add(new JLabel("Push down members of " + this.myClassSymbol.getNameWithKindLowercase() + " to:"), gridBagConstraints);
        (this.myInheritorsTable = new JBTable()).setModel((TableModel)new MyModel());
        final TableColumnModel columnModel = this.myInheritorsTable.getColumnModel();
        TableUtil.setupCheckboxColumn(columnModel.getColumn(0));
        columnModel.getColumn(1).setCellRenderer((TableCellRenderer)new MyTableRenderer());
        this.myInheritorsTable.setPreferredScrollableViewportSize(new Dimension(JBUI.scale(400), this.myInheritorsTable.getRowHeight() * 4));
        this.myInheritorsTable.getSelectionModel().setSelectionMode(2);
        this.myInheritorsTable.setShowGrid(false);
        this.myInheritorsTable.setIntercellSpacing((Dimension)JBUI.emptySize());
        new TableSpeedSearch((JTable)this.myInheritorsTable);
        final JScrollPane scrollPane = ScrollPaneFactory.createScrollPane((Component)this.myInheritorsTable);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        panel.add(scrollPane, gridBagConstraints);
        return panel;
    }
    
    @Override
    protected String getOKButtonText() {
        return "P&ush";
    }
    
    @Override
    public boolean allowsEmptySelection() {
        return false;
    }
    
    @Override
    protected void checkCanRun() throws ConfigurationException {
        try {
            super.checkCanRun();
            if (this.myCheckedInheritors.isEmpty()) {
                throw new ConfigurationException("No inheritors are selected");
            }
        }
        catch (ConfigurationException ex) {
            throw c((Exception)ex);
        }
    }
    
    public List<OCSymbol> getInheritors() {
        return this.myInheritors;
    }
    
    public boolean isInheritorsSearchCancelled() {
        return this.myInheritorsCancelled;
    }
    
    @Override
    protected MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> createModel() {
        return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new UsedByDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo>(this.mySourceClass) {
            public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
                return OCPushDownDialog.this.isMemberEnabled(ocMemberInfo);
            }
        };
    }
    
    @Override
    protected String getConflictMessage(final OCMemberInfo ocMemberInfo, final OCSymbol ocSymbol) {
        return "Pushed code will be inaccessible from the " + ocSymbol.getNameWithKindLowercase();
    }
    
    private void c() {
        Computable computable;
        if (this.mySourceClass instanceof OCClassDeclaration) {
            computable = (() -> OCDirectInheritorsSearch.search((OCClassDeclaration)this.mySourceClass).findAll());
        }
        else if (this.mySourceClass instanceof OCStruct) {
            computable = (() -> OCDirectStructInheritorsSearch.search((OCStructLike)this.mySourceClass).findAll());
        }
        else {
            computable = null;
        }
        Label_0117: {
            Label_0089: {
                OCPushDownDialog ocPushDownDialog = null;
                boolean myInheritorsCancelled = false;
                Label_0086: {
                    Label_0077: {
                        final Computable computable2;
                        try {
                            if (computable == null) {
                                break Label_0089;
                            }
                            ocPushDownDialog = this;
                            final ProgressManager progressManager = ProgressManager.getInstance();
                            final Runnable runnable = () -> this.myInheritors = (List<OCSymbol>)ApplicationManager.getApplication().runReadAction((Computable)new Computable<List<OCSymbol>>() {
                                final /* synthetic */ Computable val$inheritanceFinder;
                                
                                public List<OCSymbol> compute() {
                                    return new ArrayList<OCSymbol>((Collection<? extends OCSymbol>)this.val$inheritanceFinder.compute());
                                }
                            });
                            final String s = "Searching the inheritors";
                            final boolean b = true;
                            final OCPushDownDialog ocPushDownDialog2 = this;
                            final Project project = ocPushDownDialog2.myProject;
                            final boolean b2 = progressManager.runProcessWithProgressSynchronously(runnable, s, b, project);
                            if (!b2) {
                                break Label_0077;
                            }
                            break Label_0077;
                        }
                        catch (IllegalArgumentException ex) {
                            throw c(ex);
                        }
                        try {
                            ocPushDownDialog = this;
                            final ProgressManager progressManager = ProgressManager.getInstance();
                            final Runnable runnable = () -> this.myInheritors = (List<OCSymbol>)ApplicationManager.getApplication().runReadAction((Computable)new Computable<List<OCSymbol>>() {
                                final /* synthetic */ Computable val$inheritanceFinder;
                                
                                public List<OCSymbol> compute() {
                                    return new ArrayList<OCSymbol>((Collection<? extends OCSymbol>)this.val$inheritanceFinder.compute());
                                }
                            });
                            final String s = "Searching the inheritors";
                            final boolean b = true;
                            final OCPushDownDialog ocPushDownDialog2 = this;
                            final Project project = ocPushDownDialog2.myProject;
                            final boolean b2 = progressManager.runProcessWithProgressSynchronously(runnable, s, b, project);
                            if (!b2) {
                                myInheritorsCancelled = true;
                                break Label_0086;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw c(ex2);
                        }
                    }
                    myInheritorsCancelled = false;
                }
                ocPushDownDialog.myInheritorsCancelled = myInheritorsCancelled;
                try {
                    Collections.sort(this.myInheritors);
                    if (this.myInheritors == null) {
                        return;
                    }
                    final OCPushDownDialog ocPushDownDialog3 = this;
                    final boolean b3 = ocPushDownDialog3.selectAllInheritors();
                    if (b3) {
                        break Label_0117;
                    }
                    return;
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
            }
            try {
                final OCPushDownDialog ocPushDownDialog3 = this;
                final boolean b3 = ocPushDownDialog3.selectAllInheritors();
                if (b3) {
                    this.myCheckedInheritors.addAll(this.myInheritors);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
        }
    }
    
    protected boolean selectAllInheritors() {
        try {
            if (this.myInheritors.size() == 1) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @Nullable
    @Override
    protected OCMoveProcessor createProcessor() {
        final ArrayList<Comparable> list = new ArrayList<Comparable>(this.myCheckedInheritors);
        try {
            Collections.sort(list);
            if (this.mySourceClass instanceof OCClassDeclaration) {
                return new OCMoveObjCProcessor((OCClassDeclaration)this.mySourceClass, this.getSelectedMemberInfos(), null, list) {
                    @Override
                    protected String getCommandName() {
                        return OCPushDownDialog.this.getTitle();
                    }
                };
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (this.mySourceClass instanceof OCStruct) {
                return new OCMoveCppProcessor((OCStruct)this.mySourceClass, this.getSelectedMemberInfos(), null, list) {
                    @Override
                    protected String getCommandName() {
                        return OCPushDownDialog.this.getTitle();
                    }
                };
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            assert false : this.mySourceClass;
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return null;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCPushDownDialog.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
    
    private class MyModel extends AbstractTableModel
    {
        @Override
        public int getRowCount() {
            return OCPushDownDialog.this.myInheritors.size();
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
            final OCSymbol ocSymbol = OCPushDownDialog.this.myInheritors.get(n);
            try {
                switch (n2) {
                    case 0: {
                        return OCPushDownDialog.this.myCheckedInheritors.contains(ocSymbol);
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
                            OCPushDownDialog.this.myCheckedInheritors.add(OCPushDownDialog.this.myInheritors.get(n));
                            break Label_0077;
                        }
                    }
                    catch (RuntimeException ex2) {
                        throw b(ex2);
                    }
                }
                OCPushDownDialog.this.myCheckedInheritors.remove(OCPushDownDialog.this.myInheritors.get(n));
            }
            OCPushDownDialog.this.validateButtons();
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
    
    private class MyTableRenderer extends ColoredTableCellRenderer
    {
        public void customizeCellRenderer(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
            final int convertColumnIndexToModel = OCPushDownDialog.this.myInheritorsTable.convertColumnIndexToModel(n2);
            final OCSymbol ocSymbol = OCPushDownDialog.this.myInheritors.get(n);
            if (convertColumnIndexToModel == 1) {
                this.setIcon(ocSymbol.getIcon());
            }
            else {
                this.setIcon((Icon)null);
            }
            this.setIconOpaque(false);
            this.setOpaque(false);
            this.append((String)o);
        }
    }
}
