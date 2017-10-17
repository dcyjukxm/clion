// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElementVisitor;

public class CMakeCacheVisitor extends PsiElementVisitor
{
    public void visitCacheEntry(@NotNull final CacheEntry cacheEntry) {
        try {
            if (cacheEntry == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheVisitor", "visitCacheEntry"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCacheEntryMixin(cacheEntry);
    }
    
    public void visitEntryName(@NotNull final EntryName entryName) {
        try {
            if (entryName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheVisitor", "visitEntryName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCacheElement(entryName);
    }
    
    public void visitEntryType(@NotNull final EntryType entryType) {
        try {
            if (entryType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheVisitor", "visitEntryType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCacheElement(entryType);
    }
    
    public void visitEntryValue(@NotNull final EntryValue entryValue) {
        try {
            if (entryValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheVisitor", "visitEntryValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitCMakeCacheElement(entryValue);
    }
    
    public void visitCMakeCacheEntryMixin(@NotNull final CMakeCacheEntryMixin cMakeCacheEntryMixin) {
        try {
            if (cMakeCacheEntryMixin == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheVisitor", "visitCMakeCacheEntryMixin"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitElement((PsiElement)cMakeCacheEntryMixin);
    }
    
    public void visitCMakeCacheElement(@NotNull final CMakeCacheElement cMakeCacheElement) {
        try {
            if (cMakeCacheElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheVisitor", "visitCMakeCacheElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitElement((PsiElement)cMakeCacheElement);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
