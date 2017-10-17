// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import com.intellij.refactoring.move.MoveCallback;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.move.moveFilesOrDirectories.MoveFilesOrDirectoriesDialog;

class OCMoveFilesHandlerDelegate$1 implements MoveFilesOrDirectoriesDialog.Callback {
    final /* synthetic */ Project val$project;
    final /* synthetic */ PsiElement[] val$elements;
    final /* synthetic */ MoveCallback val$callback;
    
    @Override
    public void run(final MoveFilesOrDirectoriesDialog moveFilesOrDirectoriesDialog) {
        OCMoveFilesHandlerDelegate.this.doMove(this.val$project, this.val$elements, (PsiElement)moveFilesOrDirectoriesDialog.getTargetDirectory(), this.val$callback);
        moveFilesOrDirectoriesDialog.close(1);
    }
}