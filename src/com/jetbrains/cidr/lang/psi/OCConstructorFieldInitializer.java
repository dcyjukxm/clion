// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface OCConstructorFieldInitializer extends OCElement
{
    @Nullable
    OCReferenceElement getReferenceElement();
    
    @Nullable
    OCArgumentList getArgumentList();
    
    @NotNull
    List<OCExpression> getArguments();
    
    @NotNull
    List<OCExpression> getInitializers();
}
