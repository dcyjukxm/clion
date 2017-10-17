// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCMoveTopLevelDialog;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCAbstractMoveDialog;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.refactoring.RefactoringBundle;

public static class MoveTopLevelHandler extends OCMoveTopLevelRefactoringHandler
{
    private String myTargetFileName;
    
    public MoveTopLevelHandler() {
    }
    
    public MoveTopLevelHandler(final String myTargetFileName) {
        this.myTargetFileName = myTargetFileName;
    }
    
    @Override
    protected String getTitle() {
        return RefactoringBundle.message("move.members.title");
    }
    
    @Nullable
    @Override
    protected OCAbstractMoveDialog createDialog(final OCFile ocFile, final Condition<PsiElement> condition) {
        return new OCMoveTopLevelDialog(ocFile, condition, this.myTargetFileName);
    }
    
    @Nullable
    @Override
    protected OCAbstractMoveDialog createDialog(final OCCppNamespace ocCppNamespace, final Condition<PsiElement> condition) {
        return new OCMoveTopLevelDialog(ocCppNamespace, ocCppNamespace.getSymbol(), condition, this.myTargetFileName);
    }
}
