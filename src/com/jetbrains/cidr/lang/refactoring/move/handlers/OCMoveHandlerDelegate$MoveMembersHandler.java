// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCMoveMembersDialog;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCAbstractMoveDialog;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.refactoring.RefactoringBundle;

public static class MoveMembersHandler extends OCMoveRefactoringHandler
{
    private String myTargetClassName;
    
    public MoveMembersHandler() {
    }
    
    public MoveMembersHandler(final String myTargetClassName) {
        this.myTargetClassName = myTargetClassName;
    }
    
    @Override
    protected String getTitle() {
        return RefactoringBundle.message("move.members.title");
    }
    
    @Override
    protected OCAbstractMoveDialog createDialog(final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition, final Editor editor) {
        return new OCMoveMembersDialog(ocSymbolDeclarator, ocSymbol, condition, this.myTargetClassName);
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.moveMembers";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveHandlerDelegate$MoveMembersHandler", "getFeatureID"));
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
