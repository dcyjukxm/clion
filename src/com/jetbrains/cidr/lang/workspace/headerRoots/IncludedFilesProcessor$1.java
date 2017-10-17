// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import org.jetbrains.annotations.Nullable;
import java.util.Arrays;
import com.intellij.psi.PsiFileSystemItem;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileVisitor;

static final class IncludedFilesProcessor$1 extends VirtualFileVisitor {
    final /* synthetic */ Project val$project;
    final /* synthetic */ Condition val$condition;
    final /* synthetic */ PsiElementProcessor val$processor;
    
    @NotNull
    public VirtualFileVisitor.Result visitFileEx(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1", "visitFileEx"));
            }
        }
        catch (1CancelException ex) {
            throw b(ex);
        }
        Label_0100: {
            VirtualFileVisitor.Result result = null;
            Label_0065: {
                try {
                    if (!IncludedFilesProcessor.shouldSkip(virtualFile)) {
                        break Label_0100;
                    }
                    result = IncludedFilesProcessor$1.SKIP_CHILDREN;
                    if (result == null) {
                        break Label_0065;
                    }
                    return result;
                }
                catch (1CancelException ex2) {
                    throw b(ex2);
                }
                try {
                    result = IncludedFilesProcessor$1.SKIP_CHILDREN;
                    if (result == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1", "visitFileEx"));
                    }
                }
                catch (1CancelException ex3) {
                    throw b(ex3);
                }
            }
            return result;
            try {
                if (!IncludedFilesProcessor.processFile(this.val$project, virtualFile, (Condition<VirtualFile>)this.val$condition, (PsiElementProcessor<PsiFileSystemItem>)this.val$processor)) {
                    throw new 1CancelException();
                }
            }
            catch (1CancelException ex4) {
                throw b(ex4);
            }
        }
        VirtualFileVisitor.Result continue1;
        try {
            continue1 = IncludedFilesProcessor$1.CONTINUE;
            if (continue1 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1", "visitFileEx"));
            }
        }
        catch (1CancelException ex5) {
            throw b(ex5);
        }
        return continue1;
    }
    
    @Nullable
    public Iterable<VirtualFile> getChildrenIterable(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedFilesProcessor$1", "getChildrenIterable"));
            }
        }
        catch (1CancelException ex) {
            throw b(ex);
        }
        return (Iterable<VirtualFile>)IncludedFilesProcessor.access$000(Arrays.asList(virtualFile.getChildren()));
    }
    
    private static 1CancelException b(final 1CancelException ex) {
        return ex;
    }
}