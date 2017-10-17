// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCExtractSuperClassDialog;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCPullUpDialog;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCAbstractMoveDialog;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.refactoring.RefactoringBundle;

public class OCPullUpRefactoringHandler extends OCMoveRefactoringHandler
{
    private String myTargetClassName;
    private boolean myRemoveConflictingMembers;
    private boolean mySelectAllMembers;
    
    public OCPullUpRefactoringHandler(final String myTargetClassName, final boolean mySelectAllMembers, final boolean myRemoveConflictingMembers) {
        this.mySelectAllMembers = mySelectAllMembers;
        this.myTargetClassName = myTargetClassName;
        this.myRemoveConflictingMembers = myRemoveConflictingMembers;
    }
    
    public OCPullUpRefactoringHandler() {
    }
    
    @Override
    protected String getTitle() {
        return RefactoringBundle.message("pull.members.up.title");
    }
    
    @Override
    protected void showDialog(final OCSymbolDeclarator ocSymbolDeclarator, final Condition<PsiElement> condition, final Editor editor) {
        final Project project = ocSymbolDeclarator.getProject();
        final OCClassSymbol symbol = ocSymbolDeclarator.getSymbol();
        Label_0101: {
            try {
                if (!(symbol instanceof OCClassSymbol) || symbol.getCategoryName() == null) {
                    break Label_0101;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final OCInterfaceSymbol mainInterface = symbol.getMainInterface();
            Label_0076: {
                try {
                    if (mainInterface == null) {
                        break Label_0101;
                    }
                    final OCInterfaceSymbol ocInterfaceSymbol = mainInterface;
                    final boolean b = OCSearchScope.isInProjectSources(ocInterfaceSymbol);
                    if (!b) {
                        break Label_0076;
                    }
                    break Label_0101;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCInterfaceSymbol ocInterfaceSymbol = mainInterface;
                    final boolean b = OCSearchScope.isInProjectSources(ocInterfaceSymbol);
                    if (!b) {
                        CommonRefactoringUtil.showErrorHint(project, editor, OCBundle.message("refactoring.categoryElement.not.in.project", new Object[0]), this.getTitle(), (String)null);
                        return;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
        }
        super.showDialog(ocSymbolDeclarator, condition, editor);
    }
    
    @Override
    protected OCAbstractMoveDialog createDialog(final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition, final Editor editor) {
        final OCPullUpDialog ocPullUpDialog = new OCPullUpDialog(ocSymbolDeclarator, ocSymbol, condition, this.myTargetClassName, this.myRemoveConflictingMembers) {
            @Override
            protected boolean selectAllMembers() {
                return OCPullUpRefactoringHandler.this.mySelectAllMembers;
            }
        };
        Label_0047: {
            try {
                if (!ocPullUpDialog.getSuperClasses().isEmpty()) {
                    return ocPullUpDialog;
                }
                final boolean b = OCNewFileActionBase.isNewFileActionSupported();
                if (b) {
                    break Label_0047;
                }
                return null;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final boolean b = OCNewFileActionBase.isNewFileActionSupported();
                if (b) {
                    return new OCExtractSuperClassDialog(ocSymbolDeclarator, ocSymbol, condition);
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.pullUp";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCPullUpRefactoringHandler", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
