// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;

public interface OCDeclaration extends OCElement, OCTemplateArgumentsOwner
{
    @NotNull
    OCType getType();
    
    @NotNull
    OCType getResolvedType();
    
    @Nullable
    OCTypeElement getTypeElement();
    
    @NotNull
    List<OCDeclarator> getDeclarators();
    
    @Nullable
    OCTemplateParameterList getTemplateParameterList();
    
    boolean isTypedef();
    
    boolean isStatic();
}
