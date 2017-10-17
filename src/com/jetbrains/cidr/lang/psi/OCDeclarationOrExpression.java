// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;

public interface OCDeclarationOrExpression extends OCElement
{
    @NotNull
    OCType getType();
    
    @NotNull
    OCType getResolvedType();
    
    @Nullable
    OCDeclaration getDeclaration();
    
    @Nullable
    OCExpression getExpression();
}
