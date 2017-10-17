// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation;

import org.jetbrains.annotations.NotNull;

public static class TableRow extends HtmlItem
{
    public static TableRow withData(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$TableRow", "withData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final TableRow tableRow = new TableRow();
        tableRow.addData(s);
        return tableRow;
    }
    
    public void addData(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$TableRow", "addData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        HtmlHelper.access$000(this.sb, "td", s);
    }
    
    @Override
    public String toString() {
        return "<tr valign=\"top\">" + this.sb.toString() + "</tr>";
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
