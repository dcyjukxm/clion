// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;

public interface OCImplementation extends OCClassDeclaration<OCImplementationSymbol>
{
    OCImplementationSymbol getSymbol();
    
    @NotNull
    List<OCSynthesizePropertiesList> getSynthesizePropertyLists();
}
