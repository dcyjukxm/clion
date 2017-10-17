// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import java.util.Set;

class OCGlobalUnusedInspection$1 extends OCGlobalSearchScopeForUnusedCode {
    final /* synthetic */ Set val$analyzedFiles;
    
    @Override
    public boolean contains(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection$1", "contains"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0072: {
            try {
                if (!super.contains(virtualFile)) {
                    return false;
                }
                final OCGlobalSearchScopeForUnusedCode ocGlobalSearchScopeForUnusedCode = this;
                final Set set = ocGlobalSearchScopeForUnusedCode.val$analyzedFiles;
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = set.contains(virtualFile2);
                if (!b) {
                    break Label_0072;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCGlobalSearchScopeForUnusedCode ocGlobalSearchScopeForUnusedCode = this;
                final Set set = ocGlobalSearchScopeForUnusedCode.val$analyzedFiles;
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = set.contains(virtualFile2);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}