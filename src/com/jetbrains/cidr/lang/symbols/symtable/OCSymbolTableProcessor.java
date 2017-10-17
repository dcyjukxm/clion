// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

public class OCSymbolTableProcessor implements Processor<OCSymbol>
{
    private final FileSymbolTable myTable;
    
    public OCSymbolTableProcessor(@NotNull final FileSymbolTable myTable) {
        if (myTable == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "table", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProcessor", "<init>"));
        }
        this.myTable = myTable;
    }
    
    public boolean process(final OCSymbol ocSymbol) {
        this.myTable.append(ocSymbol);
        return true;
    }
}
