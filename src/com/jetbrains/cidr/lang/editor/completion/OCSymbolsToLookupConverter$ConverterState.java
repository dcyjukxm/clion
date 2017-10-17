// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.HashSet;
import com.intellij.util.containers.hash.HashMap;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.Map;
import java.util.Set;

public static class ConverterState
{
    private final Set<String> myNames;
    private final Map<String, OCFunctionSymbol> myFunctionNamesToSymbol;
    private final MultiMap<String, String> myFunctionNamesToType;
    
    public ConverterState() {
        this.myFunctionNamesToType = (MultiMap<String, String>)MultiMap.createSet();
        this.myFunctionNamesToSymbol = (Map<String, OCFunctionSymbol>)new HashMap();
        this.myNames = new HashSet<String>();
    }
    
    boolean registerSymbol(final OCSymbol ocSymbol) {
        final String name = ocSymbol.getName();
        if (!(ocSymbol instanceof OCFunctionSymbol)) {
            return this.myNames.add(ocSymbol.getSignature());
        }
        if (this.myFunctionNamesToSymbol.containsKey(name)) {
            if (!this.myFunctionNamesToType.containsKey((Object)name)) {
                this.myFunctionNamesToType.putValue((Object)name, (Object)a(this.myFunctionNamesToSymbol.get(name)));
            }
            return this.myFunctionNamesToType.get((Object)name).add(a(ocSymbol));
        }
        this.myFunctionNamesToSymbol.put(name, (OCFunctionSymbol)ocSymbol);
        return true;
    }
    
    private static String a(final OCSymbol ocSymbol) {
        return ocSymbol.getResolvedType().getCanonicalName();
    }
}
