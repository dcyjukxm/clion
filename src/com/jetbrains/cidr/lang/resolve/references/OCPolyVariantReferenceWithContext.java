// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCPolyVariantReference;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public interface OCPolyVariantReferenceWithContext<T extends OCSymbol> extends OCPolyVariantReference<T>, OCReferenceWithContext<T>
{
    @NotNull
    List<T> resolveToSymbols(@NotNull final OCResolveContext p0);
}
