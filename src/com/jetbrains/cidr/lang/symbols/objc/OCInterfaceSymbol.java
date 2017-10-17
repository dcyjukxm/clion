// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.types.OCTypeArgument;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.symbols.cpp.OCTemplateSymbol;

public interface OCInterfaceSymbol extends OCClassSymbol, OCTemplateSymbol<OCClassDeclarationBase>
{
    public static final Condition<OCSymbol> IS_GENERIC_OBJC_CLASS = ocSymbol -> ocSymbol instanceof OCInterfaceSymbol && !((OCInterfaceSymbol)ocSymbol).getGenericParameters().isEmpty();
    
    @Nullable
    OCImplementationSymbol getImplementation(@Nullable final String p0);
    
    @NotNull
    List<OCGenericParameterSymbol> getGenericParameters();
    
    @NotNull
    List<OCTypeArgument> getGenericArguments();
}
