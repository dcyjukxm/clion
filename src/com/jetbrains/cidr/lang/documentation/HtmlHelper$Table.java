// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation;

import org.jetbrains.annotations.NotNull;

public static class Table extends HtmlItem
{
    public void addRow(@NotNull final TableRow tableRow) {
        try {
            if (tableRow == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/HtmlHelper$Table", "addRow"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.sb.append(tableRow.toString());
    }
    
    @Override
    public String toString() {
        return HtmlHelper.access$100("table", this.sb.toString());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
