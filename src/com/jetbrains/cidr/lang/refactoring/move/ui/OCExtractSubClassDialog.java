// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.refactoring.move.OCTargetClass;
import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.refactoring.move.OCCppTargetClass;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.actions.newFile.OCNewCppClassAction;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveCppProcessor;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.refactoring.move.OCObjcTargetClass;
import com.jetbrains.cidr.lang.actions.newFile.OCNewClassAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewProtocolAction;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveObjCProcessor;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveProcessor;
import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.refactoring.classMembers.UsedByDependencyMemberInfoModel;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;

public class OCExtractSubClassDialog extends OCAbstractExtractDialog
{
    public OCExtractSubClassDialog(@NotNull final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition) {
        if (ocSymbolDeclarator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/refactoring/move/ui/OCExtractSubClassDialog", "<init>"));
        }
        super(ocSymbolDeclarator, ocSymbol, condition, ocSymbolDeclarator.getProject());
        this.myMemberInfos = ((AbstractMemberInfoStorage<T, PsiElement, OCMemberInfo>)this.myStorage).getClassMemberInfos((PsiElement)ocSymbolDeclarator);
        this.setMembersChecked();
        this.setTitle("Extract Subclass");
        this.init();
    }
    
    @Nullable
    protected String getHelpId() {
        return "refactoring.extract.subclass.dialog";
    }
    
    @Override
    protected String getClassType() {
        return "Subclass";
    }
    
    @Override
    protected MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> createModel() {
        return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new UsedByDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo>(this.mySourceClass) {
            public boolean isMemberEnabled(final OCMemberInfo ocMemberInfo) {
                return OCExtractSubClassDialog.this.isMemberEnabled(ocMemberInfo);
            }
        };
    }
    
    @Override
    protected String getConflictMessage(final OCMemberInfo ocMemberInfo, final OCSymbol ocSymbol) {
        return "Extracted code will be inaccessible from the " + ocSymbol.getNameWithKindLowercase();
    }
    
    @Override
    protected OCMoveProcessor createProcessor() {
        try {
            if (this.mySourceClass instanceof OCClassDeclaration) {
                return new OCMoveObjCProcessor((OCClassDeclaration)this.mySourceClass, this.getSelectedMemberInfos(), this.getClassName(), Collections.emptyList()) {
                    @Nullable
                    @Override
                    protected OCNewFileActionBase getNewClassAction() {
                        if (this.mySourceClass instanceof OCProtocol) {
                            return new OCNewProtocolAction();
                        }
                        return new OCNewClassAction();
                    }
                    
                    public void addSuperClasses() {
                        if (((OCClassDeclaration)this.mySourceClass).getSuperClassRef().getReferenceElement() != null) {
                            this.myMover.setSuperClass(((OCTargetClass<OCClassDeclaration, S>)this.myTargetClasses.get(0)).getPsi(), ((OCClassSymbol)this.mySourceClassSymbol).getName());
                        }
                    }
                    
                    @Override
                    protected String getCommandName() {
                        return OCExtractSubClassDialog.this.getTitle();
                    }
                    
                    @Override
                    protected boolean importSourceFromTarget() {
                        return true;
                    }
                };
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.mySourceClass instanceof OCStruct) {
                return new OCMoveCppProcessor((OCStruct)this.mySourceClass, this.getSelectedMemberInfos(), this.getClassName(), Collections.emptyList()) {
                    @Override
                    protected OCNewFileActionBase getNewClassAction() {
                        final OCStructSymbol ocStructSymbol = ((OCStruct)this.mySourceClass).getSymbol();
                        final OCQualifiedName ocQualifiedName = (ocStructSymbol != null) ? ocStructSymbol.getResolvedQualifiedName() : null;
                        return new OCNewCppClassAction((ocQualifiedName != null) ? ocQualifiedName.getQualifier() : null);
                    }
                    
                    @Override
                    protected void addSuperClasses() {
                        this.myMover.addBaseClass(((OCTargetClass<OCStruct, S>)this.myTargetClasses.get(0)).getPsi(), (OCStructSymbol)this.mySourceClassSymbol, OCVisibility.PUBLIC, true);
                    }
                    
                    @Override
                    protected String getCommandName() {
                        return OCExtractSubClassDialog.this.getTitle();
                    }
                    
                    @Override
                    protected boolean importSourceFromTarget() {
                        return true;
                    }
                };
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            assert false : this.mySourceClass;
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCExtractSubClassDialog.class.desiredAssertionStatus()) {
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
