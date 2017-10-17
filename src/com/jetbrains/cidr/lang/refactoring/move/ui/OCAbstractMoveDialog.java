// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCCppBaseClause;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCCppBaseClauseList;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveProcessor;
import java.util.ArrayList;
import com.intellij.refactoring.BaseRefactoringProcessor;
import com.jetbrains.cidr.lang.formatting.OCFormattingModelBuilder;
import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.options.ConfigurationException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Collection;
import com.intellij.refactoring.classMembers.MemberInfoChange;
import com.intellij.refactoring.classMembers.MemberInfoChangeListener;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfoStorage;
import com.intellij.refactoring.ui.RefactoringDialog;

public abstract class OCAbstractMoveDialog extends RefactoringDialog
{
    protected OCMemberInfoStorage myStorage;
    protected List<OCMemberInfo> myMemberInfos;
    protected MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> myMemberInfoModel;
    protected OCFile mySourceFile;
    protected final OCSymbolDeclarator mySourceClass;
    protected OCSymbol myClassSymbol;
    private Condition<PsiElement> mySelectedCondition;
    private boolean myEnableValidation;
    protected OCMemberSelectionPanel myMemberSelectionPanel;
    
    protected OCAbstractMoveDialog(@Nullable final OCSymbolDeclarator mySourceClass, @Nullable final OCSymbol myClassSymbol, final Condition<PsiElement> mySelectedCondition, final Project project) {
        super(project, true);
        this.mySourceClass = mySourceClass;
        this.mySourceFile = ((mySourceClass != null) ? ((OCFile)mySourceClass.getContainingFile()) : null);
        this.mySelectedCondition = mySelectedCondition;
        this.getRefactorAction().putValue("Name", this.getOKButtonText());
        this.myStorage = new OCMemberInfoStorage((PsiElement)mySourceClass);
        this.myMemberInfoModel = this.createModel();
        this.myClassSymbol = myClassSymbol;
    }
    
    protected JComponent createCenterPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(this.myMemberSelectionPanel = new OCMemberSelectionPanel(this.myMemberInfos, null), "Center");
        this.myMemberSelectionPanel.getTable().setMemberInfoModel(this.myMemberInfoModel);
        final Iterator<? extends MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>> iterator = this.getAllModels().iterator();
        while (iterator.hasNext()) {
            this.myMemberSelectionPanel.getTable().addMemberInfoChangeListener((com.intellij.refactoring.classMembers.MemberInfoChangeListener<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)iterator.next());
        }
        this.myMemberInfoModel.memberInfoChanged(new MemberInfoChange((Collection)this.myMemberInfos));
        this.myMemberSelectionPanel.getTable().addMemberInfoChangeListener((com.intellij.refactoring.classMembers.MemberInfoChangeListener<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new MemberInfoChangeListener<OCSymbolHolderVirtualPsiElement, OCMemberInfo>() {
            public void memberInfoChanged(final MemberInfoChange<OCSymbolHolderVirtualPsiElement, OCMemberInfo> memberInfoChange) {
                OCAbstractMoveDialog.this.validateButtons();
            }
        });
        return panel;
    }
    
    protected List<? extends MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>> getAllModels() {
        return Collections.singletonList(this.myMemberInfoModel);
    }
    
    @Override
    protected final void canRun() throws ConfigurationException {
        try {
            if (!this.myEnableValidation) {
                return;
            }
        }
        catch (ConfigurationException ex) {
            throw b(ex);
        }
        this.checkCanRun();
    }
    
    protected void checkCanRun() throws ConfigurationException {
        Label_0026: {
            try {
                if (this.allowsEmptySelection()) {
                    return;
                }
                final OCAbstractMoveDialog ocAbstractMoveDialog = this;
                final List<OCMemberInfo> list = ocAbstractMoveDialog.getSelectedMemberInfos();
                final boolean b = list.isEmpty();
                if (b) {
                    break Label_0026;
                }
                return;
            }
            catch (ConfigurationException ex) {
                throw b(ex);
            }
            try {
                final OCAbstractMoveDialog ocAbstractMoveDialog = this;
                final List<OCMemberInfo> list = ocAbstractMoveDialog.getSelectedMemberInfos();
                final boolean b = list.isEmpty();
                if (b) {
                    throw new ConfigurationException("No members are selected");
                }
            }
            catch (ConfigurationException ex2) {
                throw b(ex2);
            }
        }
    }
    
    public boolean checkConflicts() {
        return true;
    }
    
    @Override
    protected void doAction() {
        this.myEnableValidation = true;
        this.validateButtons();
        if (this.getRefactorAction().isEnabled() && this.checkConflicts()) {
            this.invokeRefactoring();
            this.closeOKAction();
        }
    }
    
    @Nullable
    public Pair<OCFile[], String> invokeRefactoring() {
        final OCMoveProcessor processor = this.createProcessor();
        if (processor != null) {
            final MultiMap<PsiElement, String> a = this.a();
            processor.setConflicts(a);
            final StringBuilder sb = new StringBuilder();
            for (final OCMemberInfo ocMemberInfo : this.myMemberInfos) {
                if (this.myMemberInfoModel.checkForProblems((MemberInfoBase)ocMemberInfo) == 1) {
                    sb.append("Warning: ").append(ocMemberInfo.getDisplayName()).append("\n");
                }
                if (!this.isMemberEnabled(ocMemberInfo)) {
                    sb.append("Disabled: ").append(ocMemberInfo.getDisplayName()).append("\n");
                }
            }
            try {
                OCFormattingModelBuilder.requestAlwaysCreateFullModel();
                this.invokeRefactoring(processor);
            }
            finally {
                OCFormattingModelBuilder.releaseAlwaysCreateFullModel();
            }
            final ArrayList list = new ArrayList<String>(a.values());
            Collections.sort((List<Comparable>)list);
            final Iterator<String> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                sb.append("Error: ").append(iterator2.next()).append("\n");
            }
            return (Pair<OCFile[], String>)Pair.create((Object)processor.getCreatedFiles(), (Object)sb.toString());
        }
        return null;
    }
    
    @Nullable
    protected abstract OCMoveProcessor createProcessor();
    
    protected abstract String getOKButtonText();
    
    @Override
    protected boolean hasPreviewButton() {
        return true;
    }
    
    public boolean allowsEmptySelection() {
        return false;
    }
    
    protected abstract MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> createModel();
    
    protected abstract String getConflictMessage(final OCMemberInfo p0, final OCSymbol p1);
    
    private MultiMap<PsiElement, String> a() {
        final MultiMap multiMap = new MultiMap();
        for (final OCMemberInfo ocMemberInfo : this.myMemberInfos) {
            if (this.myMemberInfoModel.checkForProblems((MemberInfoBase)ocMemberInfo) == 2) {
                final OCSymbol symbol = ((OCSymbolHolderVirtualPsiElement)ocMemberInfo.getMember()).getSymbol();
                assert symbol != null;
                final PsiElement locateDefinition = symbol.locateDefinition();
                if (locateDefinition == null) {
                    continue;
                }
                multiMap.putValue((Object)locateDefinition, (Object)this.getConflictMessage(ocMemberInfo, symbol));
            }
        }
        return (MultiMap<PsiElement, String>)multiMap;
    }
    
    public List<OCMemberInfo> getMemberInfos() {
        return this.myMemberInfos;
    }
    
    public List<OCMemberInfo> getSelectedMemberInfos() {
        final ArrayList<OCMemberInfo> list = new ArrayList<OCMemberInfo>(this.myMemberInfos.size());
        for (final OCMemberInfo ocMemberInfo : this.myMemberInfos) {
            if (ocMemberInfo.isChecked() && this.isMemberEnabled(ocMemberInfo)) {
                list.add(ocMemberInfo);
            }
        }
        return list;
    }
    
    protected boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
        return true;
    }
    
    protected boolean selectAllMembers() {
        return false;
    }
    
    protected void setMembersChecked() {
        if (this.selectAllMembers()) {
            for (final OCMemberInfo ocMemberInfo : this.myMemberInfos) {
                ocMemberInfo.setChecked(this.myMemberInfoModel.checkForProblems((MemberInfoBase)ocMemberInfo) == 0 && this.isMemberEnabled(ocMemberInfo));
            }
            return;
        }
        for (final OCMemberInfo ocMemberInfo2 : this.myMemberInfos) {
            if (!this.isMemberEnabled(ocMemberInfo2)) {
                ocMemberInfo2.setChecked(false);
            }
            else {
                final OCSymbol symbol = ocMemberInfo2.getSymbol();
                if (this.mySourceClass instanceof OCClassDeclaration && symbol instanceof OCProtocolSymbol) {
                    ocMemberInfo2.setChecked(this.mySelectedCondition.value((Object)ContainerUtil.find((Iterable)((OCClassDeclaration)this.mySourceClass).getProtocolList().getProtocols(), ocReferenceElement -> ocReferenceElement.getName().equals(symbol.getName()))));
                }
                else if (this.mySourceClass instanceof OCStruct && symbol instanceof OCStructSymbol && ((OCStructSymbol)symbol).getParent() != this.myClassSymbol) {
                    final OCCppBaseClauseList baseClausesList = ((OCStruct)this.mySourceClass).getBaseClausesList();
                    ocMemberInfo2.setChecked(this.mySelectedCondition.value((Object)((baseClausesList != null) ? ((PsiElement)ContainerUtil.find((Iterable)baseClausesList.getBaseClauses(), ocCppBaseClause -> {
                        final OCReferenceElement referenceElement = ocCppBaseClause.getReferenceElement();
                        return referenceElement != null && referenceElement.getName().equals(symbol.getName());
                    })) : null)));
                }
                else {
                    if (symbol == null) {
                        continue;
                    }
                    symbol.processSameSymbols(ocSymbol2 -> {
                        if (symbol instanceof OCClassSymbol && !((OCClassSymbol)symbol).isSameCategory(ocSymbol2)) {
                            return true;
                        }
                        if (this.mySelectedCondition.value(ocSymbol2.locateDefinition())) {
                            ocMemberInfo2.setChecked(true);
                        }
                        return true;
                    });
                }
            }
        }
    }
    
    private static ConfigurationException b(final ConfigurationException ex) {
        return ex;
    }
}
