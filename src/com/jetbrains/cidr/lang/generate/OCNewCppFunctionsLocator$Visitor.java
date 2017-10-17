// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;

private interface Visitor
{
    void visitFunction(@NotNull final OCFunctionDeclaration p0, @NotNull final OCVisibility p1);
    
    int getResult();
}
