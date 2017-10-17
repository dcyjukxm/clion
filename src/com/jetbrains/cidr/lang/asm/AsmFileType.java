// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm;

import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Nullable;
import icons.CidrLangIcons;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import java.util.List;
import com.intellij.openapi.fileTypes.LanguageFileType;

public class AsmFileType extends LanguageFileType
{
    public static final AsmFileType INSTANCE;
    public static final List<String> EXTENSIONS;
    
    private AsmFileType() {
        super((Language)AsmLanguage.INSTANCE);
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "Assembly file";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmFileType", "getName"));
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
            s = "Assembly language file";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmFileType", "getDescription"));
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
            s = "s";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/asm/AsmFileType", "getDefaultExtension"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Nullable
    public Icon getIcon() {
        return CidrLangIcons.FileType_asm;
    }
    
    static {
        INSTANCE = new AsmFileType();
        EXTENSIONS = (List)ContainerUtil.immutableList((Object[])new String[] { "s", "asm" });
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
