// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import org.jetbrains.annotations.NotNull;

public static class SpecifierUsage
{
    @NotNull
    private String name;
    private int lengthWithFormat;
    private int offset;
    @NotNull
    private TextAttributesKey specType;
    
    public SpecifierUsage(@NotNull final String name, final int offset, final int lengthWithFormat, @NotNull final TextAttributesKey specType) {
        if (name == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage", "<init>"));
        }
        if (specType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "specType", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage", "<init>"));
        }
        this.name = name;
        this.lengthWithFormat = lengthWithFormat;
        this.offset = offset;
        this.specType = specType;
    }
    
    @NotNull
    public String getName() {
        String name;
        try {
            name = this.name;
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return name;
    }
    
    @NotNull
    public TextAttributesKey getType() {
        TextAttributesKey specType;
        try {
            specType = this.specType;
            if (specType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$SpecifierUsage", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return specType;
    }
    
    @Nullable
    public TextRange getRange() {
        try {
            if (this.offset < 0) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new TextRange(this.offset, this.offset + this.lengthWithFormat);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
