// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithSubstitution;
import java.io.Serializable;
import com.jetbrains.cidr.lang.symbols.DeepEqual;

public interface SelectorPartSymbol extends DeepEqual.Equality<SelectorPartSymbol>, Serializable, OCSymbolWithSubstitution
{
    @Nullable
    OCDeclaratorSymbol getParameter();
    
    @Nullable
    OCDeclaratorSymbol getParameterWithoutSubstitution();
    
    @Nullable
    String getSelectorName();
}
