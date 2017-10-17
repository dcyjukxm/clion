// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReference;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;

class CMakeFileReferenceSet extends FileReferenceSet
{
    private boolean fragmentOnlyRename;
    
    public void setFragmentOnlyRename(final boolean fragmentOnlyRename) {
        this.fragmentOnlyRename = fragmentOnlyRename;
    }
    
    public boolean isFragmentOnlyRename() {
        return this.fragmentOnlyRename;
    }
    
    public CMakeFileReferenceSet(@NotNull final String str, @NotNull final PsiElement element, final int startInElement, @Nullable final PsiReferenceProvider provider, final boolean isCaseSensitive, final boolean endingSlashNotAllowed) {
        if (str == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "str", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferenceSet", "<init>"));
        }
        if (element == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferenceSet", "<init>"));
        }
        super(str, element, startInElement, provider, isCaseSensitive, endingSlashNotAllowed);
    }
    
    @NotNull
    @Override
    public FileReference createFileReference(@NotNull final TextRange textRange, final int n, @NotNull final String s) {
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferenceSet", "createFileReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferenceSet", "createFileReference"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        CMakeFileReference cMakeFileReference;
        try {
            cMakeFileReference = new CMakeFileReference(this, textRange, n, s);
            if (cMakeFileReference == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferenceSet", "createFileReference"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return cMakeFileReference;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
