// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import org.jetbrains.annotations.NotNull;
import java.util.LinkedHashSet;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collection;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.MultiMap;

class OCInclusionContextImpl$PCHCache$1 extends MultiMap<VirtualFile, OCResolveConfiguration> {
    @NotNull
    protected Collection<OCResolveConfiguration> createCollection() {
        LinkedHashSet linkedHashSet;
        try {
            linkedHashSet = ContainerUtil.newLinkedHashSet();
            if (linkedHashSet == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl$PCHCache$1", "createCollection"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Collection<OCResolveConfiguration>)linkedHashSet;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}