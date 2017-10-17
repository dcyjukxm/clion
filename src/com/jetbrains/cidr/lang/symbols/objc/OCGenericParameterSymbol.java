// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.types.OCTypeArgument;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCGenericParameter;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;

public interface OCGenericParameterSymbol extends OCTypeParameterSymbol<OCType>, OCSymbol<OCGenericParameter>
{
    @NotNull
    String getName();
    
    @NotNull
    Covariance getCovariance();
    
    @NotNull
    OCType getDefaultValue();
    
    public enum Covariance
    {
        INVARIANT, 
        COVARIANT, 
        CONTRAVARIANT;
    }
}
