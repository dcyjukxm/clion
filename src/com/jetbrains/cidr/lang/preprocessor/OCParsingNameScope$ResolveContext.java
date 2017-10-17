// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.intellij.util.SmartList;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import gnu.trove.THashMap;

private static class ResolveContext
{
    private THashMap<OCParsingNameScope, List<Integer>> myCurrentOffsets;
    
    public boolean shouldCheckAtOffset(@NotNull final OCParsingNameScope ocParsingNameScope, final int n) {
        try {
            if (ocParsingNameScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$ResolveContext", "shouldCheckAtOffset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<Integer> list;
        if (this.myCurrentOffsets == null) {
            this.myCurrentOffsets = (THashMap<OCParsingNameScope, List<Integer>>)new THashMap();
            list = null;
        }
        else {
            list = (List<Integer>)this.myCurrentOffsets.get((Object)ocParsingNameScope);
        }
        if (list != null) {
            try {
                if (!list.contains(n)) {
                    list.add(n);
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return false;
        }
        this.myCurrentOffsets.put((Object)ocParsingNameScope, (Object)new SmartList((Object)n));
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
