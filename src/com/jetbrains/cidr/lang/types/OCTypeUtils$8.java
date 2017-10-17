// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.types.visitors.OCBooleanTypeVisitor;

static final class OCTypeUtils$8 extends OCBooleanTypeVisitor {
    @Override
    public Boolean visitAutoType(final OCAutoType ocAutoType) {
        return ocAutoType.getExpressionSymbol() == null && ocAutoType.getExpressionElement() == null;
    }
}