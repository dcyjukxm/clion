// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCLocalizedStringSymbol;
import com.intellij.psi.PsiNamedElement;

public interface OCLocalizedString extends PsiNamedElement, OCSymbolDeclarator<OCLocalizedStringSymbol>
{
    @NotNull
    String getKey();
    
    @NotNull
    String getValue();
}
