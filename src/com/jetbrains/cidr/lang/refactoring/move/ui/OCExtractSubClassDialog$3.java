// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.refactoring.move.OCTargetClass;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.refactoring.move.OCCppTargetClass;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.actions.newFile.OCNewCppClassAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveCppProcessor;

class OCExtractSubClassDialog$3 extends OCMoveCppProcessor {
    @Override
    protected OCNewFileActionBase getNewClassAction() {
        final OCStructSymbol ocStructSymbol = ((OCStruct)this.mySourceClass).getSymbol();
        final OCQualifiedName ocQualifiedName = (ocStructSymbol != null) ? ocStructSymbol.getResolvedQualifiedName() : null;
        return new OCNewCppClassAction((ocQualifiedName != null) ? ocQualifiedName.getQualifier() : null);
    }
    
    @Override
    protected void addSuperClasses() {
        this.myMover.addBaseClass(((OCTargetClass<OCStruct, S>)this.myTargetClasses.get(0)).getPsi(), (OCStructSymbol)this.mySourceClassSymbol, OCVisibility.PUBLIC, true);
    }
    
    @Override
    protected String getCommandName() {
        return OCExtractSubClassDialog.this.getTitle();
    }
    
    @Override
    protected boolean importSourceFromTarget() {
        return true;
    }
}