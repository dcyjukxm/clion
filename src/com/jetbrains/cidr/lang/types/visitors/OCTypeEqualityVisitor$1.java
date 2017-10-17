// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.util.containers.FactoryMap;

class OCTypeEqualityVisitor$1 extends FactoryMap<OCStructSymbol, String> {
    @Nullable
    protected String create(final OCStructSymbol ocStructSymbol) {
        return new OCTypeNameVisitor(OCType.Presentation.SHORT, OCTypeEqualityVisitor.this.myContext).getName(ocStructSymbol.getType());
    }
}