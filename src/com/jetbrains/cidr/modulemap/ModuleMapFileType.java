// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap;

import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;

public class ModuleMapFileType extends LanguageFileType
{
    public static final ModuleMapFileType INSTANCE;
    
    protected ModuleMapFileType() {
        super((Language)ModuleMapLanguage.INSTANCE);
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "ModuleMap";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/ModuleMapFileType", "getName"));
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
            s = "Clang Module Map";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/ModuleMapFileType", "getDescription"));
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
            s = "modulemap";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/ModuleMapFileType", "getDefaultExtension"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Nullable
    public Icon getIcon() {
        return AllIcons.FileTypes.Text;
    }
    
    @Nullable
    public String getCharset(@NotNull final VirtualFile virtualFile, @NotNull final byte[] array) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/modulemap/ModuleMapFileType", "getCharset"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "content", "com/jetbrains/cidr/modulemap/ModuleMapFileType", "getCharset"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return "UTF-8";
    }
    
    static {
        INSTANCE = new ModuleMapFileType();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
