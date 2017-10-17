// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;

public enum CTypeId
{
    BOOL, 
    SIGNED_CHAR(OCCompilerFeatures.TypeSize.CHAR), 
    CHAR, 
    CHAR16_T, 
    CHAR32_T, 
    WCHAR_T, 
    SHORT, 
    INT, 
    LONG, 
    SIZE_T, 
    PTRDIFF_T, 
    POINTER, 
    LONG_LONG, 
    INT128_T, 
    FLOAT, 
    DOUBLE, 
    LONG_DOUBLE;
    
    @NotNull
    private final OCCompilerFeatures.TypeSize mySizeKey;
    
    private CTypeId(final OCCompilerFeatures.TypeSize mySizeKey) {
        if (mySizeKey == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeSize", "com/jetbrains/cidr/lang/types/CTypeId", "<init>"));
        }
        super(s, n);
        this.mySizeKey = mySizeKey;
    }
    
    private CTypeId() {
        this.mySizeKey = OCCompilerFeatures.TypeSize.valueOf(this.name());
    }
    
    public short getBytes(@Nullable final PsiFile psiFile, @Nullable final OCInclusionContext ocInclusionContext) {
        return OCCompilerFeatures.getFeatureForFile(psiFile, ocInclusionContext, (OCCompilerFeatures.Type<Short>)this.mySizeKey);
    }
}
