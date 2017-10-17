// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.resolve.references.OCReferenceWithContext;

public interface OCReferenceElement extends OCReferenceWithContext, OCElement, OCNamespaceQualifiedNameOwner, OCTemplateArgumentsOwner, OCSymbolDeclarator<OCInstanceVariableSymbol>
{
    @Nullable
    PsiElement getNameIdentifier();
    
    @NotNull
    String getName();
    
    @NotNull
    PsiElement setNameOfIdentifier(@NonNls @NotNull final String p0);
    
    @Nullable
    OCSymbol resolveToSymbol();
    
    @Nullable
    OCSymbol resolveToSymbolIgnoringSymbolContext();
    
    @Nullable
    OCSymbol resolveToSymbol(@Nullable final OCSymbolGroupContext p0);
    
    @Nullable
    OCSymbol resolveToSymbol(@Nullable final OCSymbolGroupContext p0, @NotNull final OCResolveContext p1, final boolean p2, final boolean p3, final boolean p4);
    
    @Nullable
    OCSymbol resolveToSymbol(@NotNull final OCResolveContext p0);
    
    @NotNull
    Collection<OCSymbol> resolveToOverloadsSymbols();
    
    @NotNull
    Collection<OCSymbol> resolveToOverloadsSymbols(@Nullable final OCSymbolGroupContext p0, final boolean p1);
    
    @Nullable
    OCSymbolGroupContext getSymbolContext();
    
    boolean isCppThis();
}
