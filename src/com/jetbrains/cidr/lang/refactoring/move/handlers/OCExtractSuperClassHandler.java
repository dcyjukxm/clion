// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCExtractSuperClassDialog;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCAbstractMoveDialog;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.refactoring.RefactoringBundle;

public class OCExtractSuperClassHandler extends OCMoveRefactoringHandler
{
    @Override
    protected String getTitle() {
        return RefactoringBundle.message("extract.superclass.title");
    }
    
    @Override
    protected void showDialog(final OCSymbolDeclarator ocSymbolDeclarator, final Condition<PsiElement> condition, final Editor editor) {
        final Project project = ocSymbolDeclarator.getProject();
        final OCClassSymbol symbol = ocSymbolDeclarator.getSymbol();
        try {
            if (symbol instanceof OCProtocolSymbol) {
                CommonRefactoringUtil.showErrorHint(project, editor, "Use \"Extract Super Protocol\" instead", this.getTitle(), (String)null);
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0120: {
            try {
                if (!(symbol instanceof OCClassSymbol) || symbol.getCategoryName() == null) {
                    break Label_0120;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            final OCInterfaceSymbol mainInterface = symbol.getMainInterface();
            Label_0102: {
                try {
                    if (mainInterface == null) {
                        break Label_0120;
                    }
                    final OCInterfaceSymbol ocInterfaceSymbol = mainInterface;
                    final boolean b = OCSearchScope.isInProjectSources(ocInterfaceSymbol);
                    if (!b) {
                        break Label_0102;
                    }
                    break Label_0120;
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCInterfaceSymbol ocInterfaceSymbol = mainInterface;
                    final boolean b = OCSearchScope.isInProjectSources(ocInterfaceSymbol);
                    if (!b) {
                        CommonRefactoringUtil.showErrorHint(project, editor, "Selected element is a category on the class which is not located inside the project", this.getTitle(), (String)null);
                        return;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
        }
        super.showDialog(ocSymbolDeclarator, condition, editor);
    }
    
    @Override
    protected OCAbstractMoveDialog createDialog(final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition, final Editor editor) {
        return new OCExtractSuperClassDialog(ocSymbolDeclarator, ocSymbol, condition);
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.extractSuperclass";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCExtractSuperClassHandler", "getFeatureID"));
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
