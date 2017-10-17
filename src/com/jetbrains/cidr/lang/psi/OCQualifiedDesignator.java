// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface OCQualifiedDesignator extends OCElement, OCSymbolNameOwner, PsiNameIdentifierOwner
{
    @Nullable
    String getName();
    
    @Nullable
    OCExpression getArrayStartIndexer();
    
    @Nullable
    OCExpression getArrayStopIndexer();
    
    @Nullable
    OCSymbol resolveToSymbol();
    
    @Nullable
    OCType getParentType();
}
