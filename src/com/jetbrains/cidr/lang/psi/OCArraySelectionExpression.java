// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public interface OCArraySelectionExpression extends OCExpression
{
    @NotNull
    OCExpression getArrayExpression();
    
    @Nullable
    OCExpression getIndexExpression();
    
    @Nullable
    OCMethodSymbol getArraySubscriptMethod(@NotNull final OCResolveContext p0);
    
    @Nullable
    String getArraySubscriptAccessorName(final OCType p0, final ReadWriteAccessDetector.Access p1);
    
    @Nullable
    String getArraySubscriptMethodSignature(final OCType p0, final ReadWriteAccessDetector.Access p1);
}
