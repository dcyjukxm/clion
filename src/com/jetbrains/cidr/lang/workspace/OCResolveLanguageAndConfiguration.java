// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import org.jetbrains.annotations.Nullable;

public class OCResolveLanguageAndConfiguration
{
    @Nullable
    private final OCResolveConfiguration myConfiguration;
    @NotNull
    private final OCLanguageKind myLanguageKind;
    
    public OCResolveLanguageAndConfiguration(@Nullable final OCResolveConfiguration myConfiguration, @NotNull final OCLanguageKind myLanguageKind) {
        if (myLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/lang/workspace/OCResolveLanguageAndConfiguration", "<init>"));
        }
        this.myConfiguration = myConfiguration;
        this.myLanguageKind = myLanguageKind;
    }
    
    @Nullable
    public OCResolveConfiguration getConfiguration() {
        return this.myConfiguration;
    }
    
    @NotNull
    public OCLanguageKind getLanguageKind() {
        OCLanguageKind myLanguageKind;
        try {
            myLanguageKind = this.myLanguageKind;
            if (myLanguageKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/OCResolveLanguageAndConfiguration", "getLanguageKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myLanguageKind;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
