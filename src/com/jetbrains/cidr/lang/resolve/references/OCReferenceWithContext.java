// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCReference;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public interface OCReferenceWithContext<T extends OCSymbol> extends OCReference<T>
{
    @Nullable
    T resolveToSymbol(@NotNull final OCResolveContext p0);
}
