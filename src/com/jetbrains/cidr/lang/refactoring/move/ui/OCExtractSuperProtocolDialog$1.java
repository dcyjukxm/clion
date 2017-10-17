// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.actions.newFile.OCNewProtocolAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveObjCProcessor;

class OCExtractSuperProtocolDialog$1 extends OCMoveObjCProcessor {
    @Override
    protected OCNewFileActionBase getNewClassAction() {
        return new OCNewProtocolAction();
    }
    
    public void addSuperClasses() {
        this.myMover.addBaseProtocol(this.myMainInterface, this.myTargetClassName);
    }
    
    @Override
    protected boolean importTargetFromSource() {
        return true;
    }
    
    @Override
    protected String getCommandName() {
        return OCExtractSuperProtocolDialog.this.getTitle();
    }
}