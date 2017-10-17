// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import org.jetbrains.annotations.NotNull;

public interface SignatureBuilder
{
    void setDefined(@NotNull final String p0, final boolean p1);
    
    void setDefinition(@NotNull final String p0, @NotNull final OCMacroSymbol p1);
    
    void enterConformanceCheckMode();
    
    void exitConformanceCheckMode(final boolean p0);
}
