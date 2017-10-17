// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.types.OCType;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface OCBlockExpression extends OCExpression, OCCallable
{
    @Nullable
    OCParameterList getParameterList();
    
    @Nullable
    List<OCDeclarator> getParameters();
    
    OCType calcReturnTypeByBody();
}
