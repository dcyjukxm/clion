// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public interface OCMessageArgument extends OCElement
{
    @NotNull
    OCArgumentSelector getArgumentSelector();
    
    @Nullable
    OCExpression getArgumentExpression();
}
