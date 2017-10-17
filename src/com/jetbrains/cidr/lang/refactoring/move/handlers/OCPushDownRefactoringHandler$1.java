// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCPushDownDialog;

class OCPushDownRefactoringHandler$1 extends OCPushDownDialog {
    @Override
    protected boolean selectAllInheritors() {
        return OCPushDownRefactoringHandler.access$000(OCPushDownRefactoringHandler.this);
    }
}