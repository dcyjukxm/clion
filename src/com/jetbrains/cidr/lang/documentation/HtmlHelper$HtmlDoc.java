// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation;

import org.jetbrains.annotations.NotNull;

public static class HtmlDoc extends HtmlItem
{
    public void addText(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$HtmlDoc", "addText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.sb.append(s);
    }
    
    public void addItem(@NotNull final HtmlItem htmlItem) {
        try {
            if (htmlItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$HtmlDoc", "addItem"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.sb.append(htmlItem.toString());
    }
    
    @Override
    public String toString() {
        return this.sb.toString();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
