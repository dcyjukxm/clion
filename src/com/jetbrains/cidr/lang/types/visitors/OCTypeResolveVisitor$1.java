// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.OCInternator;

static final class OCTypeResolveVisitor$1 extends OCInternator<ArrayList<VirtualFile>> {
    @NotNull
    @Override
    protected ArrayList<VirtualFile> valueToStore(@NotNull final ArrayList<VirtualFile> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$1", "valueToStore"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            list.trimToSize();
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$1", "valueToStore"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return list;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}