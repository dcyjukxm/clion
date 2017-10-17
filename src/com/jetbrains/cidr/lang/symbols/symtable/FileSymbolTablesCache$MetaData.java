// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import java.io.File;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

static class MetaData
{
    private Map<String, String> projectKeysWithPaths;
    private transient boolean isFileUpToDate;
    
    MetaData() {
        this.projectKeysWithPaths = new HashMap<String, String>();
    }
    
    private boolean a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectKey", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$MetaData", "checkProjectExistsForKey"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String s2 = this.projectKeysWithPaths.get(s);
        try {
            if (s2 == null || !new File(s2).exists()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return true;
        b = false;
        return b;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
