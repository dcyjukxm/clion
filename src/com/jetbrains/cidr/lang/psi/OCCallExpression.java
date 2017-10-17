// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;

public interface OCCallExpression extends OCExpression
{
    @NotNull
    OCExpression getFunctionReferenceExpression();
    
    @Nullable
    OCType getCalleeType(@NotNull final OCResolveContext p0);
    
    @NotNull
    OCArgumentList getArgumentList();
    
    @NotNull
    List<OCExpression> getArguments();
}
