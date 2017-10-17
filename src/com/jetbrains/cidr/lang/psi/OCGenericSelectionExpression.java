// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface OCGenericSelectionExpression extends OCExpression
{
    @Nullable
    OCExpression getControllingExpression();
    
    @NotNull
    List<OCGenericSelectionAssociation> getAssociations();
    
    @Nullable
    OCGenericSelectionAssociation getAssociationByType(@NotNull final OCType p0);
}
