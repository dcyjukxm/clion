// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import java.util.Iterator;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import gnu.trove.THashSet;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import java.util.List;
import com.intellij.openapi.command.WriteCommandAction;

class OCMoveFilesHandlerDelegate$2$1 extends WriteCommandAction {
    final /* synthetic */ List val$notInProjectFiles;
    final /* synthetic */ Object val$projectFile;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveFilesHandlerDelegate$2$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        final THashSet set = new THashSet(FileUtil.PATH_HASHING_STRATEGY);
        final Iterator<VirtualFile> iterator = this.val$notInProjectFiles.iterator();
        while (iterator.hasNext()) {
            VfsUtil.processFilesRecursively((VirtualFile)iterator.next(), virtualFile -> set.add((Object)virtualFile.getPath()));
        }
        OCCopyMoveProcessor.this.this$0.removePaths(set, this.val$projectFile);
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}