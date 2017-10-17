// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.refactoring.move.OCTargetClass;
import com.intellij.refactoring.classMembers.AbstractMemberInfoStorage;
import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.refactoring.classMembers.AbstractUsesDependencyMemberInfoModel;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.refactoring.move.OCCppTargetClass;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.actions.newFile.OCNewCppClassAction;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveCppProcessor;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.refactoring.move.OCObjcTargetClass;
import com.jetbrains.cidr.lang.actions.newFile.OCNewClassAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewProtocolAction;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveObjCProcessor;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveProcessor;
import org.jetbrains.annotations.Nullable;
import com.intellij.refactoring.RefactoringBundle;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;

public class OCExtractSuperClassDialog extends OCAbstractExtractDialog
{
    public OCExtractSuperClassDialog(@NotNull final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition) {
        if (ocSymbolDeclarator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/refactoring/move/ui/OCExtractSuperClassDialog", "<init>"));
        }
        super(ocSymbolDeclarator, ocSymbol, condition, ocSymbolDeclarator.getProject());
        this.myMemberInfos = ((AbstractMemberInfoStorage<T, PsiElement, OCMemberInfo>)this.myStorage).getClassMemberInfos((PsiElement)ocSymbolDeclarator);
        this.setMembersChecked();
        this.setTitle(RefactoringBundle.message("extract.superclass.title"));
        this.init();
    }
    
    @Nullable
    protected String getHelpId() {
        return "refactoring.extract.superclass.dialog";
    }
    
    @Override
    protected String getClassType() {
        return "Superclass";
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
                        final OCReferenceElement referenceElement = ((OCClassDeclaration)this.mySourceClass).getSuperClassRef().getReferenceElement();
                        if (referenceElement != null) {
                            this.myMover.setSuperClass(((OCTargetClass<OCClassDeclaration, S>)this.myTargetClasses.get(0)).getPsi(), referenceElement.getName());
                        }
                        this.myMover.setSuperClass(this.myMainInterface, this.myTargetClassName);
                    }
                    
                    @Override
                    protected boolean importTargetFromSource() {
                        return true;
                    }
                    
                    @Override
                    protected String getCommandName() {
                        return OCExtractSuperClassDialog.this.getTitle();
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
                        this.myMover.addBaseClass((OCStruct)this.mySourceClass, ((OCTargetClass<C, OCStructSymbol>)this.myTargetClasses.get(0)).getSymbol(), OCVisibility.PUBLIC, true);
                    }
                    
                    @Override
                    protected boolean importTargetFromSource() {
                        return true;
                    }
                    
                    @Override
                    protected String getCommandName() {
                        return OCExtractSuperClassDialog.this.getTitle();
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
    
    @Override
    protected MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> createModel() {
        return (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new AbstractUsesDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, OCSymbolDeclarator, OCMemberInfo>(this.mySourceClass, null, false) {
            @Override
            protected int doCheck(@NotNull final OCMemberInfo ocMemberInfo, final int n) {
                try {
                    if (ocMemberInfo == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/ui/OCExtractSuperClassDialog$3", "doCheck"));
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
                return OCExtractSuperClassDialog.this.isMemberEnabled(ocMemberInfo);
            }
            
            private static IllegalArgumentException c(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCExtractSuperClassDialog.class.desiredAssertionStatus()) {
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
