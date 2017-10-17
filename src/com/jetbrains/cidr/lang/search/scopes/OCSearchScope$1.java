// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.scopes;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.psi.search.DelegatingGlobalSearchScope;

static final class OCSearchScope$1 extends DelegatingGlobalSearchScope {
    final /* synthetic */ ProjectFileIndex val$index;
    
    public boolean contains(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/search/scopes/OCSearchScope$1", "contains"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCSearchScope.access$000(this.val$index, virtualFile);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}