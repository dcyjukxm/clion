// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;

public interface OCContextChangeBuilder
{
    void define(final String p0, final OCMacroSymbol p1);
    
    void undef(final String p0);
    
    void addSymbol(@NotNull final OCSymbol p0);
    
    void addProcessedFile(@NotNull final VirtualFile p0);
}
