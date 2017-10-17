// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;

public interface OCTemplateArgumentsOwner extends OCElement
{
    @Nullable
    OCTypeArgumentList getTemplateArgumentList();
    
    @NotNull
    Collection<OCSymbol> resolveTemplateDeclarations();
}
