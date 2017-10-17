// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeArgument;

public interface OCGenericArgument extends OCTypeArgument, OCElement
{
    @Nullable
    OCTypeElement getTypeElement();
}
