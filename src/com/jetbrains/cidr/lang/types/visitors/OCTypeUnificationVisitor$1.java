// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCTypeParameterType;

class OCTypeUnificationVisitor$1 extends OCBooleanTypeVisitor {
    @Override
    public Boolean visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        OCTypeUnificationVisitor.access$000(OCTypeUnificationVisitor.this).add(ocTypeParameterType.getSymbol());
        return true;
    }
}