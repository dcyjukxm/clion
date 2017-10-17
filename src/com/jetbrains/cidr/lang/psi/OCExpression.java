// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCTypeOwner;

public interface OCExpression extends OCCompoundInitializerMember, OCElement, OCTypeOwner
{
    @NotNull
    OCType getType();
    
    @NotNull
    OCType getType(@NotNull final OCResolveContext p0);
    
    @NotNull
    OCType getResolvedType();
    
    @NotNull
    OCType getResolvedType(@NotNull final OCResolveContext p0);
    
    @NotNull
    String findBestTypeName(final OCType p0);
    
    @Nullable
    OCObjectTypeContext getTypeContext();
    
    @Nullable
    OCObjectTypeContext getTypeContext(final boolean p0, final boolean p1);
    
    @Nullable
    OCObjectTypeContext getTypeContext(final OCExpression p0, final OCType p1, final OCType p2, final boolean p3, final boolean p4);
}
