// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.search.PsiElementProcessor;

public abstract class HeadersSearchRootProcessor implements PsiElementProcessor<PsiFileSystemItem>
{
    public abstract boolean process(@NotNull final VirtualFile p0);
    
    public boolean processFramework(@NotNull final RealFramework realFramework) {
        try {
            if (realFramework == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "framework", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRootProcessor", "processFramework"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return true;
    }
    
    public boolean shouldProcessRootsOnly() {
        return false;
    }
    
    public final boolean execute(@NotNull final PsiFileSystemItem psiFileSystemItem) {
        try {
            if (psiFileSystemItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRootProcessor", "execute"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        throw new UnsupportedOperationException();
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
}
