// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import org.jetbrains.annotations.Contract;
import com.intellij.openapi.util.Computable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;

interface OCLocalFormatterData
{
    boolean isNull();
    
    @Nullable
    IElementType getType();
    
    @NotNull
    OCLocalFormatterData getParent();
    
    @NotNull
    OCLocalFormatterData getAncestor(@NotNull final IElementType p0);
    
    @Nullable
    OCFormatterInfo get(@NotNull final Object p0);
    
    @NotNull
    OCFormatterInfo get(@NotNull final Object p0, @NotNull final Computable<OCFormatterInfo> p1);
    
    @Contract("_, null -> null")
    OCFormatterInfo put(@NotNull final Object p0, @Nullable final OCFormatterInfo p1);
    
    void merge(@Nullable final OCLocalFormatterData p0);
}
