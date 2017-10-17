// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.resolve.v2.ImplicitConversionSequence;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

private static class Overload
{
    OCSymbol mySymbol;
    List<ImplicitConversionSequence> myConversionSequences;
    boolean myHasMagic;
    
    public Overload(final OCSymbol mySymbol, final List<ImplicitConversionSequence> myConversionSequences) {
        this.mySymbol = mySymbol;
        this.myConversionSequences = myConversionSequences;
        this.myHasMagic = (mySymbol.getKind().isTemplateParameter() || myConversionSequences.stream().anyMatch(implicitConversionSequence -> implicitConversionSequence.hasMagic()) || OCResolveOverloadsUtil.access$000(mySymbol));
    }
}
