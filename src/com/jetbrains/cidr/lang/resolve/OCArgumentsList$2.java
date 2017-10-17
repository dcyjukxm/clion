// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.visitors.OCNonPrimitiveTypeCloneVisitor;

static final class OCArgumentsList$2 extends OCNonPrimitiveTypeCloneVisitor {
    final /* synthetic */ int val$expansionIndex;
    
    @Override
    public OCType visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
        if (this.val$expansionIndex < ocExpansionPackType.getExpansionsCnt()) {
            final OCTypeArgument ocTypeArgument = ocExpansionPackType.getExpansions().get(this.val$expansionIndex);
            if (ocTypeArgument instanceof OCType) {
                return (OCType)ocTypeArgument;
            }
        }
        return OCUnknownType.INSTANCE;
    }
}