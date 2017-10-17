// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCExtractSubClassDialog;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCAbstractMoveDialog;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;

public class OCExtractSubClassHandler extends OCMoveRefactoringHandler
{
    @Override
    protected String getTitle() {
        return "Extract Subclass";
    }
    
    @Override
    protected void showDialog(final OCSymbolDeclarator ocSymbolDeclarator, final Condition<PsiElement> condition, final Editor editor) {
        final Project project = ocSymbolDeclarator.getProject();
        final OCSymbol symbol = ocSymbolDeclarator.getSymbol();
        try {
            if (symbol instanceof OCProtocolSymbol) {
                CommonRefactoringUtil.showErrorHint(project, editor, "This action is not available for protocols", this.getTitle(), (String)null);
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        super.showDialog(ocSymbolDeclarator, condition, editor);
    }
    
    @Override
    protected OCAbstractMoveDialog createDialog(final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition, final Editor editor) {
        return new OCExtractSubClassDialog(ocSymbolDeclarator, ocSymbol, condition);
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.extractSubclass";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCExtractSubClassHandler", "getFeatureID"));
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
