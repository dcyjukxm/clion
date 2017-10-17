// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public static class ImportData
{
    @NotNull
    public final List<Import> imports;
    @NotNull
    public final List<String> rawImports;
    
    public ImportData() {
        this.imports = new ArrayList<Import>();
        this.rawImports = new ArrayList<String>();
    }
    
    public void addImport(@NotNull final Import import1) {
        try {
            if (import1 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "anImport", "com/jetbrains/cidr/lang/quickfixes/OCIncludeSuggester$ImportData", "addImport"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.imports.add(import1);
        this.rawImports.add(import1.header);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
