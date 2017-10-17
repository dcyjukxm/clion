// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCPullUpDialog;

class OCPullUpRefactoringHandler$1 extends OCPullUpDialog {
    @Override
    protected boolean selectAllMembers() {
        return OCPullUpRefactoringHandler.access$000(OCPullUpRefactoringHandler.this);
    }
}