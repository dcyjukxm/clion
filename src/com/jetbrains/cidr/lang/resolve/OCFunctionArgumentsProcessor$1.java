// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.types.visitors.OCBooleanTypeVisitor;

static final class OCFunctionArgumentsProcessor$1 extends OCBooleanTypeVisitor {
    final /* synthetic */ ArgumentsProcessor val$processor;
    final /* synthetic */ OCDeclaratorSymbol val$paramSymbol;
    
    @Override
    public Boolean visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        return !this.val$processor.process(ocTypeParameterType, this.val$paramSymbol, new OCExpansionPackType(), null, true);
    }
}