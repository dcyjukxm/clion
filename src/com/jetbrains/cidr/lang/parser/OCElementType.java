// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;

public class OCElementType extends IElementType
{
    private String name;
    
    public OCElementType(@NotNull @NonNls final String s, final String name) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugPrefix", "com/jetbrains/cidr/lang/parser/OCElementType", "<init>"));
        }
        super(s + ((name != null) ? name : ""), (Language)OCLanguage.getInstance());
        this.name = name;
    }
    
    public OCElementType(@NotNull @NonNls final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugName", "com/jetbrains/cidr/lang/parser/OCElementType", "<init>"));
        }
        super(s, (Language)OCLanguage.getInstance());
    }
    
    @NotNull
    public String getName() {
        String s = null;
        Label_0020: {
            try {
                if (this.name != null) {
                    final String name;
                    s = (name = this.name);
                    break Label_0020;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            String name;
            s = (name = "<unnamed>");
            try {
                if (name == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/parser/OCElementType", "getName"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
