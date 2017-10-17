// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import org.jetbrains.annotations.NotNull;

public static class Replacement
{
    @NotNull
    public final ReplacePosition position;
    @NotNull
    public final String text;
    
    public Replacement(@NotNull final ReplacePosition position, @NotNull final String s) {
        if (position == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/lang/generate/OCGenerateUtil$Replacement", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/generate/OCGenerateUtil$Replacement", "<init>"));
        }
        this.position = position;
        final StringBuilder sb = new StringBuilder();
        String s2;
        if (position.addNewLineBefore) {
            s2 = "\n";
        }
        else {
            s2 = "";
        }
        StringBuilder append = null;
        String s3 = null;
        Label_0136: {
            try {
                append = sb.append(s2).append(s);
                if (position.addNewLineAfter) {
                    s3 = "\n";
                    break Label_0136;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            s3 = "";
        }
        this.text = append.append(s3).toString();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
