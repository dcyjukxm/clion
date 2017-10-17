// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import org.jetbrains.annotations.Nullable;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;

public class DxFileType extends LanguageFileType
{
    public static final DxFileType INSTANCE;
    
    private DxFileType() {
        super((Language)DxLanguage.INSTANCE);
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "Doxygen file";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxFileType", "getName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getDescription() {
        String s;
        try {
            s = "Doxygen language file";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxFileType", "getDescription"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getDefaultExtension() {
        String s;
        try {
            s = "doxygen";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/DxFileType", "getDefaultExtension"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Nullable
    public Icon getIcon() {
        return null;
    }
    
    static {
        INSTANCE = new DxFileType();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
