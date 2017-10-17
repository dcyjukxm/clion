// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import java.util.Iterator;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public static class Helper
{
    @NotNull
    private List<CustomHeaderProvider> myProviders;
    @NotNull
    private String myHeaderName;
    @Nullable
    private OCResolveConfiguration myConfiguration;
    
    public Helper(@NotNull final List<CustomHeaderProvider> myProviders, @NotNull final String myHeaderName, @Nullable final OCResolveConfiguration myConfiguration) {
        if (myProviders == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "providers", "com/jetbrains/cidr/lang/CustomHeaderProvider$Helper", "<init>"));
        }
        if (myHeaderName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerName", "com/jetbrains/cidr/lang/CustomHeaderProvider$Helper", "<init>"));
        }
        this.myProviders = myProviders;
        this.myHeaderName = myHeaderName;
        this.myConfiguration = myConfiguration;
    }
    
    @Nullable
    VirtualFile getCustomHeader(final HeaderSearchStage headerSearchStage) {
        final Iterator<CustomHeaderProvider> iterator = this.myProviders.iterator();
        while (iterator.hasNext()) {
            final VirtualFile customHeaderFile = iterator.next().getCustomHeaderFile(this.myHeaderName, headerSearchStage, this.myConfiguration);
            try {
                if (customHeaderFile != null) {
                    return customHeaderFile;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
