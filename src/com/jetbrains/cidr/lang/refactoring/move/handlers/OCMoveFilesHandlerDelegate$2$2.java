// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.command.undo.DocumentReference;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.command.undo.UndoManager;
import com.intellij.openapi.command.undo.DocumentReferenceManager;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.refactoring.move.MoveCallback;

class OCMoveFilesHandlerDelegate$2$2 implements MoveCallback {
    final /* synthetic */ PsiDirectory val$targetDir;
    final /* synthetic */ Project val$project;
    final /* synthetic */ PsiFileSystemItem val$sourceElement;
    final /* synthetic */ String val$srcPath;
    final /* synthetic */ Object val$manipulator;
    final /* synthetic */ Object val$sourceRef;
    final /* synthetic */ Object val$targetGroup;
    final /* synthetic */ Object val$targetProvider;
    
    @Override
    public void refactoringCompleted() {
        final DocumentReference create = DocumentReferenceManager.getInstance().create(this.val$targetDir.getVirtualFile());
        Object o = null;
        Label_0083: {
            try {
                UndoManager.getInstance(this.val$project).nonundoableActionPerformed(create, true);
                if (this.val$sourceElement.isDirectory()) {
                    o = this.val$targetDir.findSubdirectory(this.val$sourceElement.getName());
                    break Label_0083;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            o = this.val$targetDir.findFile(this.val$sourceElement.getName());
        }
        final Object o2 = o;
        VirtualFile virtualFile = null;
        Label_0102: {
            try {
                if (o2 == null) {
                    virtualFile = null;
                    break Label_0102;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            virtualFile = ((PsiFileSystemItem)o2).getVirtualFile();
        }
        final VirtualFile virtualFile2 = virtualFile;
        if (virtualFile2 != null) {
            OCCopyMoveProcessor.this.this$0.moveReference(this.val$manipulator, this.val$sourceRef, OCCopyMoveProcessor.this.this$0.getReferencePathProviderForCopying(virtualFile2.getPath(), this.val$srcPath), this.val$targetGroup, this.val$targetProvider);
        }
        WriteAction.run(() -> {
            try {
                completer.oneTaskExecuted();
                if (moveCallback != null) {
                    moveCallback.refactoringCompleted();
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
        });
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}