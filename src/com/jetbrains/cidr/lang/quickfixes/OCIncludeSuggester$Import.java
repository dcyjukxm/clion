// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import org.jetbrains.annotations.NotNull;

public static class Import
{
    @NotNull
    public final String header;
    @NotNull
    public final Availability availability;
    
    public Import(@NotNull final String header, @NotNull final Availability availability) {
        if (header == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "header", "com/jetbrains/cidr/lang/quickfixes/OCIncludeSuggester$Import", "<init>"));
        }
        if (availability == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "availability", "com/jetbrains/cidr/lang/quickfixes/OCIncludeSuggester$Import", "<init>"));
        }
        this.header = header;
        this.availability = availability;
    }
}
