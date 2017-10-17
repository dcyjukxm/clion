// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.openapi.util.Condition;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;

static final class AbstractGlobalProjectSymbolsCache$1 extends OCCommonProcessors.OrderedProcessor<T> {
    @Override
    public Collection<T> sort(final Collection<T> collection) {
        final ArrayList<Object> list = new ArrayList<Object>(collection);
        Collections.sort(list, (ocSymbol, ocSymbol2) -> ocSymbol.hashCode() - ocSymbol2.hashCode());
        return (Collection<T>)list;
    }
}