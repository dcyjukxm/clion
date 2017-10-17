// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;

public interface OCFunctionDefinition extends OCFunctionDeclaration, OCLocalScopeable
{
    @Nullable
    OCFunctionSymbol getSymbol();
    
    @Nullable
    OCConstructorInitializationList getConstructorInitializationList();
    
    OCConstructorInitializationList setConstructorInitializationList(@NotNull final OCConstructorInitializationList p0) throws IncorrectOperationException;
}
