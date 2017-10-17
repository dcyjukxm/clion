// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.compiler;

import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum TypeSize implements Type<Short>
{
    BOOL((short)1), 
    CHAR((short)1), 
    CHAR16_T((short)2), 
    CHAR32_T((short)4), 
    WCHAR_T((short)4), 
    SHORT((short)2), 
    INT((short)4), 
    LONG((short)4), 
    SIZE_T(TypeSize.INT), 
    PTRDIFF_T(TypeSize.SIZE_T), 
    POINTER(TypeSize.SIZE_T), 
    LONG_LONG((short)8), 
    INT128_T((short)16), 
    FLOAT((short)4), 
    DOUBLE((short)8), 
    LONG_DOUBLE((short)8);
    
    @Nullable
    private final TypeSize myBaseType;
    private final short myDefault;
    
    private TypeSize(final short myDefault) {
        this.myBaseType = null;
        this.myDefault = myDefault;
    }
    
    private TypeSize(final TypeSize myBaseType) {
        if (myBaseType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseType", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$TypeSize", "<init>"));
        }
        super(s, n);
        this.myBaseType = myBaseType;
        this.myDefault = this.myBaseType.getDefault();
    }
    
    @NotNull
    @Override
    public Short getDefault() {
        Short value;
        try {
            value = this.myDefault;
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$TypeSize", "getDefault"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return value;
    }
    
    @Nullable
    @Override
    public Short compute(@NotNull final OCImmutableInclusionContext ocImmutableInclusionContext) {
        try {
            if (ocImmutableInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$TypeSize", "compute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (this.myBaseType != null) {
                return ocImmutableInclusionContext.getCompilerFeature((Type<Short>)this.myBaseType);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
