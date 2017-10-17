// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.refactoring.move.OCObjcTargetClass;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.actions.newFile.OCNewCategoryAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveObjCProcessor;

class OCExtractCategoryDialog$1 extends OCMoveObjCProcessor {
    @Override
    protected OCNewFileActionBase getNewClassAction() {
        return new OCNewCategoryAction() {
            @Nullable
            @Override
            protected OCClassSymbol getBaseClass() {
                return ((OCClassDeclaration)OCMoveObjCProcessor.this.mySourceClass).getSymbol();
            }
        };
    }
    
    @Override
    protected void locateTargetClasses() {
        ((OCObjcTargetClass)this.myTargetClasses.get(0)).setName(OCCodeInsightUtil.getClassNameWithCategory(((OCClassSymbol)this.mySourceClassSymbol).getName(), OCExtractCategoryDialog.this.getClassName()));
        super.locateTargetClasses();
    }
    
    @Override
    protected String getCommandName() {
        return OCExtractCategoryDialog.this.getTitle();
    }
}