// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.actions.newFile.OCNewCategoryAction;

class OCExtractCategoryDialog$1$1 extends OCNewCategoryAction {
    @Nullable
    @Override
    protected OCClassSymbol getBaseClass() {
        return ((OCClassDeclaration)OCExtractCategoryDialog$1.access$000(OCMoveObjCProcessor.this)).getSymbol();
    }
}