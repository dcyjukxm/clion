// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;
import com.intellij.refactoring.ui.AbstractMemberSelectionTable;
import java.awt.Insets;
import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveCppProcessor;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveObjCProcessor;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveProcessor;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import com.intellij.refactoring.classMembers.AbstractUsesDependencyMemberInfoModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ListCellRenderer;
import com.jetbrains.cidr.lang.psi.impl.OCElementBase;
import javax.swing.JList;
import com.intellij.ui.ListCellRendererWrapper;
import java.awt.Component;
import javax.swing.JLabel;
import com.intellij.util.ui.JBUI;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JComponent;
import com.intellij.refactoring.RefactoringBundle;
import com.intellij.util.containers.hash.HashMap;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Map;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import java.util.List;
import javax.swing.JComboBox;

public class OCPullUpDialog extends OCAbstractMoveDialog
{
    private JComboBox mySuperClassCombo;
    private List<OCSymbolDeclarator> mySuperClasses;
    private String myTargetClassName;
    private boolean myRemoveConflictingMembers;
    private Map<OCSymbolDeclarator, OCSymbol> myTargetSymbolsMap;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCPullUpDialog(@NotNull final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition, @Nullable final String myTargetClassName, final boolean myRemoveConflictingMembers) {
        if (ocSymbolDeclarator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/refactoring/move/ui/OCPullUpDialog", "<init>"));
        }
        super(ocSymbolDeclarator, ocSymbol, condition, ocSymbolDeclarator.getProject());
        this.myTargetSymbolsMap = (Map<OCSymbolDeclarator, OCSymbol>)new HashMap();
        this.myTargetClassName = myTargetClassName;
        this.myRemoveConflictingMembers = myRemoveConflictingMembers;
        this.mySuperClasses = this.myStorage.getSuperClasses();
        this.setTitle(RefactoringBundle.message("pull.members.up.title"));
        this.init();
    }
    
    @Nullable
    protected String getHelpId() {
        return "refactoring.pullMembersUp";
    }
    
    protected JComponent createNorthPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = (Insets)JBUI.insets(4, 8);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 17;
        final JLabel label = new JLabel();
        panel.add(label, gridBagConstraints);
        for (final OCSymbolDeclarator<OCSymbol> ocSymbolDeclarator : this.mySuperClasses) {
            this.myTargetSymbolsMap.put(ocSymbolDeclarator, ocSymbolDeclarator.getSymbol());
        }
        this.mySuperClassCombo = new JComboBox((E[])this.mySuperClasses.toArray());
        int i = 0;
        Label_0334: {
            if (this.myTargetClassName != null) {
                for (i = 0; i < this.mySuperClasses.size(); ++i) {
                    final OCSymbol ocSymbol = this.myTargetSymbolsMap.get(this.mySuperClasses.get(i));
                    Label_0242: {
                        try {
                            if (ocSymbol == null) {
                                continue;
                            }
                            final OCSymbol ocSymbol2 = ocSymbol;
                            final String s = ocSymbol2.getName();
                            final OCPullUpDialog ocPullUpDialog = this;
                            final String s2 = ocPullUpDialog.myTargetClassName;
                            final boolean b = s.equals(s2);
                            if (b) {
                                break Label_0242;
                            }
                            continue;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            final OCSymbol ocSymbol2 = ocSymbol;
                            final String s = ocSymbol2.getName();
                            final OCPullUpDialog ocPullUpDialog = this;
                            final String s2 = ocPullUpDialog.myTargetClassName;
                            final boolean b = s.equals(s2);
                            if (b) {
                                break;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                }
                Label_0282: {
                    try {
                        if (OCPullUpDialog.$assertionsDisabled) {
                            break Label_0334;
                        }
                        final int n = i;
                        final OCPullUpDialog ocPullUpDialog2 = this;
                        final List<OCSymbolDeclarator> list = ocPullUpDialog2.mySuperClasses;
                        final int n2 = list.size();
                        if (n >= n2) {
                            break Label_0282;
                        }
                        break Label_0334;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final int n = i;
                        final OCPullUpDialog ocPullUpDialog2 = this;
                        final List<OCSymbolDeclarator> list = ocPullUpDialog2.mySuperClasses;
                        final int n2 = list.size();
                        if (n >= n2) {
                            throw new AssertionError((Object)("Target class '" + this.myTargetClassName + "' is not found"));
                        }
                        break Label_0334;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
            }
            i = this.mySuperClasses.size() - 1;
        }
        this.mySuperClassCombo.setSelectedIndex(i);
        this.mySuperClassCombo.setRenderer((ListCellRenderer)new ListCellRendererWrapper<OCSymbolDeclarator>() {
            public void customize(final JList list, final OCSymbolDeclarator ocSymbolDeclarator, final int n, final boolean b, final boolean b2) {
                final OCSymbol ocSymbol = OCPullUpDialog.this.myTargetSymbolsMap.get(ocSymbolDeclarator);
                if (ocSymbol != null) {
                    this.setText(ocSymbol.getPresentableName());
                    this.setIcon(ocSymbol.getIcon());
                }
                else {
                    this.setText(((OCElementBase)ocSymbolDeclarator).getName());
                }
            }
        });
        label.setText(RefactoringBundle.message("pull.up.members.to", new Object[] { this.myClassSymbol.getNameWithKindLowercase() }));
        label.setLabelFor(this.mySuperClassCombo);
        this.mySuperClassCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == 1) {
                    OCPullUpDialog.this.c();
                    if (OCPullUpDialog.this.myMemberSelectionPanel != null) {
                        ((AbstractUsesDependencyMemberInfoModel)OCPullUpDialog.this.myMemberInfoModel).setSuperClass((PsiElement)OCPullUpDialog.this.mySuperClassCombo.getSelectedItem());
                        ((AbstractMemberSelectionTable<T, OCMemberInfo>)OCPullUpDialog.this.myMemberSelectionPanel.getTable()).setMemberInfos(OCPullUpDialog.this.myMemberInfos);
                        OCPullUpDialog.this.myMemberSelectionPanel.getTable().fireExternalDataChange();
                    }
                }
            }
        });
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        panel.add(this.mySuperClassCombo, gridBagConstraints);
        this.c();
        return panel;
    }
    
    private void c() {
        final OCSymbolDeclarator ocSymbolDeclarator = (OCSymbolDeclarator)this.mySuperClassCombo.getSelectedItem();
        try {
            if (ocSymbolDeclarator != null) {
                this.myMemberInfos = (List<OCMemberInfo>)((AbstractMemberInfoStorage<PsiElement, PsiElement, MemberInfoBase>)this.myStorage).getIntermediateMemberInfosList((PsiElement)ocSymbolDeclarator);
                this.setMembersChecked();
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myMemberInfos = ((AbstractMemberInfoStorage<T, PsiElement, OCMemberInfo>)this.myStorage).getClassMemberInfos((PsiElement)this.mySourceClass);
    }
    
    @Override
    protected boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
        final OCSymbolDeclarator ocSymbolDeclarator = (OCSymbolDeclarator)this.mySuperClassCombo.getSelectedItem();
        try {
            if (((AbstractMemberInfoStorage<T, PsiElement, M>)this.myStorage).getExtending((PsiElement)ocSymbolDeclarator).contains(ocMemberInfo.getSymbol().locateDefinition())) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0072: {
            try {
                if (!(ocSymbolDeclarator instanceof OCProtocol)) {
                    break Label_0072;
                }
                final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
                final OCSymbol ocSymbol = ocMemberInfo2.getSymbol();
                final boolean b = ocSymbol instanceof OCInstanceVariableSymbol;
                if (b) {
                    return false;
                }
                break Label_0072;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
                final OCSymbol ocSymbol = ocMemberInfo2.getSymbol();
                final boolean b = ocSymbol instanceof OCInstanceVariableSymbol;
                if (b) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (((AbstractMemberInfoStorage<PsiElement, PsiElement, MemberInfoBase>)this.myStorage).getDuplicatedMemberInfos((PsiElement)ocSymbolDeclarator).contains(ocMemberInfo)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return true;
    }
    
    public Collection<OCSymbolDeclarator> getSuperClasses() {
        return this.mySuperClasses;
    }
    
    @Override
    protected OCMoveProcessor createProcessor() {
        final OCSymbol ocSymbol = this.myTargetSymbolsMap.get(this.mySuperClassCombo.getSelectedItem());
        Label_0052: {
            Label_0040: {
                try {
                    if (OCPullUpDialog.$assertionsDisabled) {
                        break Label_0052;
                    }
                    final OCSymbol ocSymbol2 = ocSymbol;
                    if (ocSymbol2 == null) {
                        break Label_0040;
                    }
                    break Label_0052;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCSymbol ocSymbol2 = ocSymbol;
                    if (ocSymbol2 == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            try {
                if (this.mySourceClass instanceof OCClassDeclaration) {
                    return new OCMoveObjCProcessor((OCClassDeclaration)this.mySourceClass, this.getSelectedMemberInfos(), null, Collections.singletonList(ocSymbol)) {
                        @Override
                        protected String getCommandName() {
                            return OCPullUpDialog.this.getTitle();
                        }
                        
                        @Override
                        protected boolean removeConflictingMembers() {
                            return OCPullUpDialog.this.myRemoveConflictingMembers;
                        }
                    };
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        try {
            if (this.mySourceClass instanceof OCStruct) {
                return new OCMoveCppProcessor((OCStruct)this.mySourceClass, this.getSelectedMemberInfos(), null, Collections.singletonList(ocSymbol)) {
                    @Override
                    protected String getCommandName() {
                        return OCPullUpDialog.this.getTitle();
                    }
                    
                    @Override
                    protected boolean removeConflictingMembers() {
                        return OCPullUpDialog.this.myRemoveConflictingMembers;
                    }
                };
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            assert false : this.mySourceClass;
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return null;
    }
    
    @Override
    protected MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> createModel() {
        return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new AbstractUsesDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo>(this.mySourceClass, null, false) {
            @Override
            protected int doCheck(@NotNull final OCMemberInfo ocMemberInfo, final int n) {
                try {
                    if (ocMemberInfo == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/ui/OCPullUpDialog$5", "doCheck"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    if (n != 2) {
                        return n;
                    }
                    final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
                    final boolean b = ocMemberInfo2.isStatic();
                    if (b) {
                        return 1;
                    }
                    return n;
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                try {
                    final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
                    final boolean b = ocMemberInfo2.isStatic();
                    if (b) {
                        return 1;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
                return n;
            }
            
            public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
                return OCPullUpDialog.this.isMemberEnabled(ocMemberInfo);
            }
            
            private static IllegalArgumentException c(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    @Override
    protected String getConflictMessage(final OCMemberInfo ocMemberInfo, final OCSymbol ocSymbol) {
        return ocSymbol.getNameWithKindUppercase() + " will be inaccessible in the pulled code";
    }
    
    @Override
    protected String getOKButtonText() {
        return "P&ull";
    }
    
    @Override
    public boolean allowsEmptySelection() {
        return false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCPullUpDialog.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
