// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCTypeOwner;

public interface OCGenericSelectionAssociation extends OCElement, OCTypeOwner
{
    boolean isDefault();
    
    @Nullable
    OCType getAssociationType();
    
    @Nullable
    OCType getAssociationResolvedType();
    
    @Nullable
    OCType getResolvedType(@NotNull final OCResolveContext p0);
}
