// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.refactoring.move.OCTopLevelModel;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.actions.newFile.OCNewSourceFileAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveTopLevelProcessor;

class OCMoveTopLevelDialog$2 extends OCMoveTopLevelProcessor {
    @Override
    protected String getCommandName() {
        return OCMoveTopLevelDialog.this.getTitle();
    }
    
    @Nullable
    @Override
    protected OCNewFileActionBase getNewClassAction() {
        return new OCNewSourceFileAction();
    }
    
    @Override
    protected boolean importTargetFromSource() {
        return ((OCTopLevelModel)OCMoveTopLevelDialog.this.myMemberInfoModel).importTargetFromSource();
    }
    
    @Override
    protected boolean importSourceFromTarget() {
        return ((OCTopLevelModel)OCMoveTopLevelDialog.this.myMemberInfoModel).importSourceFromTarget();
    }
}