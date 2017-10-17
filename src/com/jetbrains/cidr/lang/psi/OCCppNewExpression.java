// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;

public interface OCCppNewExpression extends OCExpression
{
    @NotNull
    OCType getConstructingType();
    
    @Nullable
    OCTypeElement getConstructingTypeElement();
    
    @Nullable
    OCReferenceElement getReferenceElement();
    
    @Nullable
    OCArgumentList getArgumentList();
    
    @NotNull
    List<OCExpression> getArguments();
    
    @NotNull
    List<OCExpression> getInitializers();
    
    boolean isNoThrow();
}
