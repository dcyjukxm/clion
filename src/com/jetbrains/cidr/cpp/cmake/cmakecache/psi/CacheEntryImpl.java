// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class CacheEntryImpl extends CMakeCacheEntryImplMixin implements CacheEntry
{
    public CacheEntryImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    public void accept(@NotNull final CMakeCacheVisitor cMakeCacheVisitor) {
        try {
            if (cMakeCacheVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CacheEntryImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        cMakeCacheVisitor.visitCacheEntry(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CacheEntryImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (visitor instanceof CMakeCacheVisitor) {
                this.accept((CMakeCacheVisitor)visitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        super.accept(visitor);
    }
    
    @NotNull
    @Override
    public EntryName getEntryName() {
        EntryName entryName;
        try {
            entryName = this.findNotNullChildByClass(EntryName.class);
            if (entryName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CacheEntryImpl", "getEntryName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return entryName;
    }
    
    @Nullable
    @Override
    public EntryType getEntryType() {
        return this.findChildByClass(EntryType.class);
    }
    
    @Nullable
    @Override
    public EntryValue getEntryValue() {
        return this.findChildByClass(EntryValue.class);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
