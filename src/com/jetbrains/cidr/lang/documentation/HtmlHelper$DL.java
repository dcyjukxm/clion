// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation;

import org.jetbrains.annotations.NotNull;

public static class DL extends HtmlItem
{
    public void defineTerm(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$DL", "defineTerm"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        HtmlHelper.access$000(this.sb, "dt", s);
    }
    
    public void detailedDescription(@NotNull final HtmlItem htmlItem) {
        try {
            if (htmlItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$DL", "detailedDescription"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.detailedDescription(htmlItem.toString());
    }
    
    public void detailedDescription(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arg", "com/jetbrains/cidr/lang/documentation/HtmlHelper$DL", "detailedDescription"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        HtmlHelper.access$000(this.sb, "dd", s);
    }
    
    @Override
    public String toString() {
        return HtmlHelper.access$100("dl", this.sb.toString());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
