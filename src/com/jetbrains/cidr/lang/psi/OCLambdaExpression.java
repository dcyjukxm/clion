// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public interface OCLambdaExpression extends OCExpression, OCCallable
{
    @NotNull
    OCLambdaIntroducer getLambdaIntroducer();
    
    @Nullable
    OCParameterList getParameterList();
}
