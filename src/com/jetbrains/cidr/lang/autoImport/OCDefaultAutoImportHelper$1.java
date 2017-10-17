// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.autoImport;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.workspace.headerRoots.AppleFramework;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.search.PsiElementProcessor;

class OCDefaultAutoImportHelper$1 implements PsiElementProcessor<PsiFileSystemItem> {
    final /* synthetic */ VirtualFile val$fileToImport;
    final /* synthetic */ Ref val$found;
    
    public boolean execute(@NotNull final PsiFileSystemItem psiFileSystemItem) {
        try {
            if (psiFileSystemItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/autoImport/OCDefaultAutoImportHelper$1", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(psiFileSystemItem instanceof AppleFramework)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (((AppleFramework)psiFileSystemItem).containsHeader(this.val$fileToImport)) {
                this.val$found.set((Object)psiFileSystemItem);
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}