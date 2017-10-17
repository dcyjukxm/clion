// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.openapi.util.Computable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;

private static class OCLocalFormatterNullParentData implements OCLocalFormatterData
{
    public static final OCLocalFormatterNullParentData INSTANCE;
    
    @Override
    public boolean isNull() {
        return true;
    }
    
    @Nullable
    @Override
    public IElementType getType() {
        return null;
    }
    
    @NotNull
    @Override
    public OCLocalFormatterData getParent() {
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "getParent"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this;
    }
    
    @NotNull
    @Override
    public OCLocalFormatterData getAncestor(@NotNull final IElementType elementType) {
        try {
            if (elementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "getAncestor"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCLocalFormatterNullParentData instance;
        try {
            instance = OCLocalFormatterNullParentData.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "getAncestor"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return instance;
    }
    
    @Nullable
    @Override
    public OCFormatterInfo get(@NotNull final Object o) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "get"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return OCFormatterInfo.EMPTY;
    }
    
    @NotNull
    @Override
    public OCFormatterInfo get(@NotNull final Object o, @NotNull final Computable<OCFormatterInfo> computable) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "get"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (computable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "factory", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "get"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCFormatterInfo ocFormatterInfo;
        try {
            ocFormatterInfo = (OCFormatterInfo)computable.compute();
            if (ocFormatterInfo == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "get"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return ocFormatterInfo;
    }
    
    @Nullable
    @Override
    public OCFormatterInfo put(@NotNull final Object o, @Nullable final OCFormatterInfo ocFormatterInfo) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "put"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocFormatterInfo;
    }
    
    @Override
    public void merge(@Nullable final OCLocalFormatterData ocLocalFormatterData) {
    }
    
    static {
        INSTANCE = new OCLocalFormatterNullParentData();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
