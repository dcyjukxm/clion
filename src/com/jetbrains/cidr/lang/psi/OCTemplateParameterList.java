// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface OCTemplateParameterList extends OCElement
{
    @NotNull
    List<OCElement> getParameters();
    
    @NotNull
    List<OCParameterDeclaration> getParameterDeclarations();
    
    @NotNull
    List<OCTypeParameterDeclaration> getTypeParameterDeclarations();
}
