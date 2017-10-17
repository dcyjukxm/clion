// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class EntryTypeImpl extends CMakeCacheElementBase implements EntryType
{
    public EntryTypeImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    public void accept(@NotNull final CMakeCacheVisitor cMakeCacheVisitor) {
        try {
            if (cMakeCacheVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/EntryTypeImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        cMakeCacheVisitor.visitEntryType(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/EntryTypeImpl", "accept"));
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
