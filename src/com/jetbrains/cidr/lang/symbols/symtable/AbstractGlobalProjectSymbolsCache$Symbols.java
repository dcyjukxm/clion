// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;

public interface Symbols<T> extends Processor<T>
{
    boolean processAliasNamesForType(@NotNull final String p0, @NotNull final Processor<String> p1);
    
    boolean processTopLevel(final Processor<? super T> p0, final String p1);
    
    boolean processAllSymbols(final Processor<? super T> p0, final String p1);
    
    boolean isEmpty();
}
