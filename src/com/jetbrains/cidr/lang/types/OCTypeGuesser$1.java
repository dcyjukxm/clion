// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

static final class OCTypeGuesser$1 implements Processor<OCSymbol> {
    final /* synthetic */ OCSymbol val$target;
    final /* synthetic */ boolean[] val$metSymbol;
    final /* synthetic */ List val$enumConsts;
    
    public boolean process(final OCSymbol ocSymbol2) {
        if (ocSymbol2 == this.val$target) {
            this.val$metSymbol[0] = true;
            return false;
        }
        if (ocSymbol2.getKind() == OCSymbolKind.ENUM_CONST) {
            this.val$enumConsts.add(ocSymbol2);
        }
        else {
            this.val$enumConsts.clear();
        }
        if (ocSymbol2.getKind() == OCSymbolKind.NAMESPACE) {
            final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
            ((OCNamespaceSymbol)ocSymbol2).processMembers(null, (Processor<OCSymbol>)collectProcessor);
            final List list = (List)collectProcessor.getResults();
            Collections.sort((List<Object>)list, (ocSymbol, ocSymbol3) -> OCSymbolOffsetUtil.compare(ocSymbol.getComplexOffset(), ocSymbol3.getComplexOffset()));
            if (!ContainerUtil.process(list, (Processor)this)) {
                return false;
            }
        }
        return true;
    }
}