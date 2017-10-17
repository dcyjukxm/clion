// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import java.util.Iterator;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.openapi.project.Project;
import java.util.List;
import com.intellij.openapi.command.WriteCommandAction;

class OCCopyFilesHandlerDelegate$MyProcessor$1 extends WriteCommandAction {
    final /* synthetic */ List val$notInProjectFiles;
    final /* synthetic */ Project val$project;
    final /* synthetic */ PsiDirectory val$targetDirectory;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCCopyFilesHandlerDelegate$MyProcessor$1", "run"));
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            final Iterator<VirtualFile> iterator = this.val$notInProjectFiles.iterator();
            while (iterator.hasNext()) {
                final PsiFile file = PsiManager.getInstance(this.val$project).findFile((VirtualFile)iterator.next());
                try {
                    if (file == null) {
                        continue;
                    }
                    MyProcessor.access$000(MyProcessor.this, this.val$targetDirectory, (PsiFileSystemItem)file);
                }
                catch (IOException ex2) {
                    throw a(ex2);
                }
            }
        }
        catch (IOException ex3) {
            Messages.showErrorDialog("Failed to copy files to \"" + this.val$targetDirectory.getName() + "\"", "Error");
        }
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
}