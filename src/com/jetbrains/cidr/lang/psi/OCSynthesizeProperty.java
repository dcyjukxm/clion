// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;

public interface OCSynthesizeProperty extends OCElement, OCSymbolDeclarator<OCSynthesizeSymbol>
{
    @Nullable
    OCReferenceElement getPropertyRef();
    
    @Nullable
    OCReferenceElement getInstanceVariableRef();
    
    boolean isSynthesize();
    
    @Nullable
    OCPropertyAttributesList getPropertyAttributesList();
}
