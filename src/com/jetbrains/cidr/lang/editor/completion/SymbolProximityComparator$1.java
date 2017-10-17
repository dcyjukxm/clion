// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.psi.util.ProximityLocation;
import com.intellij.psi.WeighingComparable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.containers.FactoryMap;

class SymbolProximityComparator$1 extends FactoryMap<OCSymbol, WeighingComparable<OCSymbol, ProximityLocation>> {
    protected WeighingComparable<OCSymbol, ProximityLocation> create(final OCSymbol ocSymbol) {
        return SymbolProximityComparator.getProximity(ocSymbol, SymbolProximityComparator.access$000(SymbolProximityComparator.this));
    }
}