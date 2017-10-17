// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.actions.newFile.OCNewCppClassAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveCppProcessor;

class OCMoveMembersDialog$8 extends OCMoveCppProcessor {
    @Override
    protected String getCommandName() {
        return OCMoveMembersDialog.this.getTitle();
    }
    
    @Nullable
    @Override
    protected OCNewFileActionBase getNewClassAction() {
        return new OCNewCppClassAction();
    }
}