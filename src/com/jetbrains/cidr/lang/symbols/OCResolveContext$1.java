// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import java.util.Set;
import com.jetbrains.cidr.lang.types.visitors.OCBooleanTypeVisitor;

class OCResolveContext$1 extends OCBooleanTypeVisitor {
    final /* synthetic */ Set val$dependencies;
    
    @Override
    public Boolean visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        this.val$dependencies.add(ocTypeParameterType.getSymbol());
        return true;
    }
}