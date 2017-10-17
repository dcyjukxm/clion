// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCAutoType;

class OCTypeCompatibilityVisitor_OCAutoType extends OCTypeCompatibilityVisitor<OCAutoType>
{
    protected OCTypeCompatibilityVisitor_OCAutoType(@NotNull final OCAutoType ocAutoType, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        if (ocAutoType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceType", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCAutoType", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCAutoType", "<init>"));
        }
        super(ocAutoType, ocTypeOwner, psiElement, b, b2, ocResolveContext);
    }
    
    @Override
    public OCType.TypeCheckResult visitFunctionType(final OCFunctionType ocFunctionType) {
        return this.visitType(ocFunctionType);
    }
    
    @Override
    public OCType.TypeCheckResult visitObjectType(final OCObjectType ocObjectType) {
        return this.visitType(ocObjectType);
    }
    
    @Override
    public OCType.TypeCheckResult visitPointerType(final OCPointerType ocPointerType) {
        return this.visitType(ocPointerType);
    }
    
    @Override
    public OCType.TypeCheckResult visitStructType(final OCStructType ocStructType) {
        return this.checkStructCompatibleCtor(ocStructType);
    }
}
