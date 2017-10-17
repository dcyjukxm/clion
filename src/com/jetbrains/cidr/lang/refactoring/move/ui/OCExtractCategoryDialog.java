// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.refactoring.classMembers.UsedByDependencyMemberInfoModel;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import com.intellij.openapi.ui.Messages;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.refactoring.move.OCObjcTargetClass;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.actions.newFile.OCNewCategoryAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveObjCProcessor;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveProcessor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;

public class OCExtractCategoryDialog extends OCAbstractExtractDialog
{
    public OCExtractCategoryDialog(@NotNull final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition) {
        if (ocSymbolDeclarator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/refactoring/move/ui/OCExtractCategoryDialog", "<init>"));
        }
        super(ocSymbolDeclarator, ocSymbol, condition, ocSymbolDeclarator.getProject());
        this.myMemberInfos = ((AbstractMemberInfoStorage<T, PsiElement, OCMemberInfo>)this.myStorage).getClassMemberInfos((PsiElement)ocSymbolDeclarator);
        this.setMembersChecked();
        this.setTitle("Extract Category");
        this.init();
    }
    
    @Override
    protected String getClassType() {
        return "Category";
    }
    
    @Override
    protected OCMoveProcessor createProcessor() {
        return new OCMoveObjCProcessor((OCClassDeclaration)this.mySourceClass, this.getSelectedMemberInfos(), this.getClassName(), Collections.emptyList()) {
            @Override
            protected OCNewFileActionBase getNewClassAction() {
                return new OCNewCategoryAction() {
                    @Nullable
                    @Override
                    protected OCClassSymbol getBaseClass() {
                        return ((OCClassDeclaration)OCMoveObjCProcessor.this.mySourceClass).getSymbol();
                    }
                };
            }
            
            @Override
            protected void locateTargetClasses() {
                ((OCObjcTargetClass)this.myTargetClasses.get(0)).setName(OCCodeInsightUtil.getClassNameWithCategory(((OCClassSymbol)this.mySourceClassSymbol).getName(), OCExtractCategoryDialog.this.getClassName()));
                super.locateTargetClasses();
            }
            
            @Override
            protected String getCommandName() {
                return OCExtractCategoryDialog.this.getTitle();
            }
        };
    }
    
    @Override
    public boolean checkConflicts() {
        final OCSymbol resolveNameInScope = OCCodeInsightUtil.resolveNameInScope(this.getSymbolKind(), this.myClassSymbol.getName(), this.getClassName(), null, this.myProject);
        try {
            if (resolveNameInScope != null) {
                Messages.showErrorDialog(resolveNameInScope.getNameWithKindUppercase() + " already exists", "Unable to Create a File");
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return true;
    }
    
    @Override
    protected String getConflictMessage(final OCMemberInfo ocMemberInfo, final OCSymbol ocSymbol) {
        return "Extracted code will be inaccessible from the " + ocSymbol.getNameWithKindLowercase();
    }
    
    @Override
    protected MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> createModel() {
        return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new UsedByDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo>(this.mySourceClass) {
            public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
                return OCExtractCategoryDialog.this.isMemberEnabled(ocMemberInfo);
            }
        };
    }
    
    @Override
    protected boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
        try {
            if (ocMemberInfo.getSymbol() instanceof OCInstanceVariableSymbol) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(ocMemberInfo.getSymbol() instanceof OCPropertySymbol)) {
                return true;
            }
            final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
            final OCSymbol ocSymbol = ocMemberInfo2.getSymbol();
            final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)ocSymbol;
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            final boolean b = ocPropertySymbol.processSynthesizes((Processor<? super OCSynthesizeSymbol>)findFirstProcessor);
            if (!b) {
                return false;
            }
            return true;
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            final OCMemberInfo ocMemberInfo2 = ocMemberInfo;
            final OCSymbol ocSymbol = ocMemberInfo2.getSymbol();
            final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)ocSymbol;
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            final boolean b = ocPropertySymbol.processSynthesizes((Processor<? super OCSynthesizeSymbol>)findFirstProcessor);
            if (!b) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
