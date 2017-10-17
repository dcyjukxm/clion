// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;

public interface OCTypeElement extends OCElement
{
    @NotNull
    OCType getType();
    
    @NotNull
    OCType getRawType();
    
    int getArrayLengths();
    
    boolean isEmptyType();
}
