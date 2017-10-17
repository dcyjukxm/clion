// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;

public interface OCSizeofExpression extends OCExpression
{
    @Nullable
    OCExpression getOperand();
    
    @Nullable
    OCTypeElement getTypeElement();
    
    boolean isVariadic();
}
