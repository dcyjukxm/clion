// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.intellij.psi.PsiFile;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import java.util.Collection;
import com.intellij.openapi.progress.Task;

class OCMoveProcessor$1 extends Task.Modal {
    final /* synthetic */ Collection val$files;
    final /* synthetic */ FileProcessor val$processor;
    final /* synthetic */ Ref val$finished;
    
    public void run(@NotNull final ProgressIndicator progressIndicator) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$1", "run"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int size = this.val$files.size();
        int n = 0;
        for (final VirtualFile virtualFile : this.val$files) {
            progressIndicator.setText2(virtualFile.getPresentableUrl());
            progressIndicator.setFraction(n++ / size);
            final PsiFile file = PsiManager.getInstance(this.myProject).findFile(virtualFile);
            try {
                if (!(file instanceof OCFile)) {
                    continue;
                }
                this.val$processor.process(virtualFile, (OCFile)file);
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        this.val$finished.set((Object)true);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}