// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.types.visitors.OCBooleanTypeVisitor;

static final class OCArgumentsList$1 extends OCBooleanTypeVisitor {
    final /* synthetic */ Ref val$expansionsCnt;
    final /* synthetic */ OCResolveContext val$context;
    
    @Override
    public Boolean visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
        if (this.val$expansionsCnt.isNull() || (int)this.val$expansionsCnt.get() < ocExpansionPackType.getExpansionsCnt()) {
            this.val$expansionsCnt.set((Object)ocExpansionPackType.getExpansionsCnt());
        }
        return true;
    }
    
    @Override
    public Boolean visitReferenceType(final OCReferenceType ocReferenceType) {
        this.val$context.setDontUseSymbolContextsInDepends(true);
        ocReferenceType.getSubstitution().getMinimalDependentSubstitution(ocReferenceType, this.val$context).processSubstitutions((Processor<Map.Entry<OCTypeParameterSymbol, OCTypeArgument>>)(entry -> {
            final OCTypeArgument ocTypeArgument = entry.getValue();
            if (ocTypeArgument instanceof OCExpansionPackType) {
                map.put(entry.getKey(), ocTypeArgument);
            }
            if (ocTypeArgument instanceof OCType) {
                ((OCExpansionPackType)ocTypeArgument).accept((OCTypeVisitor<Object>)this);
            }
            return true;
        }));
        this.val$context.setDontUseSymbolContextsInDepends(false);
        return true;
    }
}