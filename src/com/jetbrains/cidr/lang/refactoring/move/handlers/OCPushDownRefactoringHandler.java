// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCExtractSubClassDialog;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCPushDownDialog;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCAbstractMoveDialog;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.refactoring.RefactoringBundle;

public class OCPushDownRefactoringHandler extends OCMoveRefactoringHandler
{
    private boolean mySelectAllInheritors;
    
    public OCPushDownRefactoringHandler() {
    }
    
    public OCPushDownRefactoringHandler(final boolean mySelectAllInheritors) {
        this.mySelectAllInheritors = mySelectAllInheritors;
    }
    
    @Override
    protected String getTitle() {
        return RefactoringBundle.message("push.members.down.title");
    }
    
    @Override
    protected OCAbstractMoveDialog createDialog(final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition, final Editor editor) {
        final OCPushDownDialog ocPushDownDialog = new OCPushDownDialog(ocSymbolDeclarator, ocSymbol, condition) {
            @Override
            protected boolean selectAllInheritors() {
                return OCPushDownRefactoringHandler.this.mySelectAllInheritors;
            }
        };
        try {
            if (ocPushDownDialog.isInheritorsSearchCancelled()) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0053: {
            try {
                if (!ocPushDownDialog.getInheritors().isEmpty()) {
                    return ocPushDownDialog;
                }
                final boolean b = OCNewFileActionBase.isNewFileActionSupported();
                if (b) {
                    break Label_0053;
                }
                return null;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final boolean b = OCNewFileActionBase.isNewFileActionSupported();
                if (b) {
                    return new OCExtractSubClassDialog(ocSymbolDeclarator, ocSymbol, condition);
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.pushDown";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCPushDownRefactoringHandler", "getFeatureID"));
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
