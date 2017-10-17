// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.actions.newFile.OCNewClassAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveObjCProcessor;

class OCMoveMembersDialog$7 extends OCMoveObjCProcessor {
    @Override
    protected String getCommandName() {
        return OCMoveMembersDialog.this.getTitle();
    }
    
    @Nullable
    @Override
    protected OCNewFileActionBase getNewClassAction() {
        return new OCNewClassAction();
    }
}