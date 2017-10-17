// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.refactoring.classMembers.UsedByDependencyMemberInfoModel;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import com.jetbrains.cidr.lang.actions.newFile.OCNewProtocolAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveObjCProcessor;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveProcessor;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;

public class OCExtractSuperProtocolDialog extends OCAbstractExtractDialog
{
    public OCExtractSuperProtocolDialog(@NotNull final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition) {
        if (ocSymbolDeclarator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/refactoring/move/ui/OCExtractSuperProtocolDialog", "<init>"));
        }
        super(ocSymbolDeclarator, ocSymbol, condition, ocSymbolDeclarator.getProject());
        this.myMemberInfos = ((AbstractMemberInfoStorage<T, PsiElement, OCMemberInfo>)this.myStorage).getClassMemberInfos((PsiElement)ocSymbolDeclarator);
        this.setMembersChecked();
        this.setTitle("Extract Super Protocol");
        this.init();
    }
    
    @Nullable
    protected String getHelpId() {
        return "Extract_Protocol_Dialog";
    }
    
    @Override
    protected String getClassType() {
        return "Super Protocol";
    }
    
    @Override
    protected OCSymbolKind getSymbolKind() {
        return OCSymbolKind.PROTOCOL;
    }
    
    @Override
    protected OCMoveProcessor createProcessor() {
        return new OCMoveObjCProcessor((OCClassDeclaration)this.mySourceClass, this.getSelectedMemberInfos(), this.getClassName(), Collections.emptyList()) {
            @Override
            protected OCNewFileActionBase getNewClassAction() {
                return new OCNewProtocolAction();
            }
            
            public void addSuperClasses() {
                this.myMover.addBaseProtocol(this.myMainInterface, this.myTargetClassName);
            }
            
            @Override
            protected boolean importTargetFromSource() {
                return true;
            }
            
            @Override
            protected String getCommandName() {
                return OCExtractSuperProtocolDialog.this.getTitle();
            }
        };
    }
    
    @Override
    protected MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> createModel() {
        return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new UsedByDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo>(this.mySourceClass) {
            public int checkForProblems(@NotNull final OCMemberInfo ocMemberInfo) {
                try {
                    if (ocMemberInfo == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/ui/OCExtractSuperProtocolDialog$2", "checkForProblems"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                return 0;
            }
            
            public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
                return OCExtractSuperProtocolDialog.this.isMemberEnabled(ocMemberInfo);
            }
            
            private static IllegalArgumentException b(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    @Override
    protected boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
        try {
            if (!(ocMemberInfo.getSymbol() instanceof OCInstanceVariableSymbol)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
