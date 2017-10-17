// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Nullable;

public interface OCReferenceExpression extends OCExpression
{
    @Nullable
    OCReferenceElement getReferenceElement();
    
    @Nullable
    OCSymbol resolveToSymbol();
    
    @Nullable
    OCSymbol resolveToSymbol(@NotNull final OCResolveContext p0);
    
    boolean isExpression();
    
    @Nullable
    OCElementTypes.SelfSuperToken getSelfSuperToken();
    
    boolean isCppThis();
    
    boolean isSelfSuperOrThis();
    
    boolean isParameterOfBuiltInTrait();
}
