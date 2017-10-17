// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.refactoring.move.OCTargetClass;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.refactoring.move.OCObjcTargetClass;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.actions.newFile.OCNewClassAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewProtocolAction;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveObjCProcessor;

class OCExtractSuperClassDialog$1 extends OCMoveObjCProcessor {
    @Nullable
    @Override
    protected OCNewFileActionBase getNewClassAction() {
        if (this.mySourceClass instanceof OCProtocol) {
            return new OCNewProtocolAction();
        }
        return new OCNewClassAction();
    }
    
    public void addSuperClasses() {
        final OCReferenceElement referenceElement = ((OCClassDeclaration)this.mySourceClass).getSuperClassRef().getReferenceElement();
        if (referenceElement != null) {
            this.myMover.setSuperClass(((OCTargetClass<OCClassDeclaration, S>)this.myTargetClasses.get(0)).getPsi(), referenceElement.getName());
        }
        this.myMover.setSuperClass(this.myMainInterface, this.myTargetClassName);
    }
    
    @Override
    protected boolean importTargetFromSource() {
        return true;
    }
    
    @Override
    protected String getCommandName() {
        return OCExtractSuperClassDialog.this.getTitle();
    }
}