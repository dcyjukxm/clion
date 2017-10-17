// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;

public interface OCCommaExpression extends OCExpression
{
    @NotNull
    OCExpression getHeadExpression();
    
    @NotNull
    OCExpression getTailExpression();
}
