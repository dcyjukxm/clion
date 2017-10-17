// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public static class CustomOption
{
    public static final CustomOption OPTION_SEPARATOR;
    @NotNull
    public final String fieldName;
    @NotNull
    public final String title;
    @Nullable
    public final String groupName;
    @Nullable
    public final CodeStyleSettingsCustomizable.OptionAnchor anchor;
    @Nullable
    public final String anchorFieldName;
    public final Object[] options;
    
    public CustomOption(@NotNull final String fieldName, @NotNull final String title, @Nullable final String groupName, @Nullable final CodeStyleSettingsCustomizable.OptionAnchor anchor, @Nullable final String anchorFieldName, final Object... options) {
        if (fieldName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fieldName", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
        }
        if (title == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
        }
        this.fieldName = fieldName;
        this.title = title;
        this.groupName = groupName;
        this.anchor = anchor;
        this.anchorFieldName = anchorFieldName;
        this.options = options;
    }
    
    public CustomOption(@NotNull final String s, @NotNull final String s2, @Nullable final String s3, final Object... array) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fieldName", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
        }
        if (s2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
        }
        this(s, s2, s3, null, null, array);
    }
    
    public CustomOption(@NotNull final String s, @NotNull final String s2) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fieldName", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
        }
        if (s2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/lang/settings/OCCustomOption$CustomOption", "<init>"));
        }
        this(s, s2, null, null, null, new Object[0]);
    }
    
    static {
        OPTION_SEPARATOR = new CustomOption("SPACE_SEPARATOR", "SPACE_SEPARATOR");
    }
}
