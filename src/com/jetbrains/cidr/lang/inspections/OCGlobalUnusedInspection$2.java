// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.SortedList;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.MultiMap;

class OCGlobalUnusedInspection$2 extends MultiMap<VirtualFile, OCSymbol> {
    @NotNull
    protected Collection<OCSymbol> createCollection() {
        SortedList list;
        try {
            list = new SortedList((ocSymbol, ocSymbol2) -> OCSymbolOffsetUtil.compare(ocSymbol.getComplexOffset(), ocSymbol2.getComplexOffset()));
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCGlobalUnusedInspection$2", "createCollection"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Collection<OCSymbol>)list;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}