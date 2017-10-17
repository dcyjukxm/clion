// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface OCCppNamespaceQualifier extends OCElement, OCNamespaceQualifierOwner, PsiNameIdentifierOwner, OCTemplateArgumentsOwner
{
    @NotNull
    List<OCSymbol> resolveToSymbols();
    
    @NotNull
    List<OCSymbol> resolveToSymbols(@NotNull final OCResolveContext p0);
    
    @Nullable
    OCSymbol getPredeclarationInParent(final OCSymbolWithQualifiedName p0, final boolean p1);
}
