// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.scopes;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import com.intellij.openapi.fileTypes.FileTypeRegistry;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFileVisitor;

static final class OCSearchScope$2 extends VirtualFileVisitor {
    final /* synthetic */ ProjectFileIndex val$index;
    final /* synthetic */ FileTypeRegistry val$typeRegistry;
    final /* synthetic */ Set val$result;
    
    public boolean visitFile(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "each", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope$2", "visitFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0106: {
            Label_0081: {
                try {
                    if (this.val$index.isExcluded(virtualFile)) {
                        return false;
                    }
                    final VirtualFileVisitor virtualFileVisitor = this;
                    final FileTypeRegistry fileTypeRegistry = virtualFileVisitor.val$typeRegistry;
                    final VirtualFile virtualFile2 = virtualFile;
                    final boolean b = fileTypeRegistry.isFileIgnored(virtualFile2);
                    if (b) {
                        return false;
                    }
                    break Label_0081;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final VirtualFileVisitor virtualFileVisitor = this;
                    final FileTypeRegistry fileTypeRegistry = virtualFileVisitor.val$typeRegistry;
                    final VirtualFile virtualFile2 = virtualFile;
                    final boolean b = fileTypeRegistry.isFileIgnored(virtualFile2);
                    if (b) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (virtualFile.isDirectory()) {
                        return true;
                    }
                    final VirtualFileVisitor virtualFileVisitor2 = this;
                    final ProjectFileIndex projectFileIndex = virtualFileVisitor2.val$index;
                    final VirtualFile virtualFile3 = virtualFile;
                    final boolean b2 = OCSearchScope.access$000(projectFileIndex, virtualFile3);
                    if (b2) {
                        break Label_0106;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            try {
                final VirtualFileVisitor virtualFileVisitor2 = this;
                final ProjectFileIndex projectFileIndex = virtualFileVisitor2.val$index;
                final VirtualFile virtualFile3 = virtualFile;
                final boolean b2 = OCSearchScope.access$000(projectFileIndex, virtualFile3);
                if (b2) {
                    this.val$result.add(virtualFile);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}