// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import org.jetbrains.annotations.Nullable;
import icons.CidrLangIcons;
import javax.swing.Icon;
import com.intellij.util.PlatformUtils;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;

public class OCFileType extends LanguageFileType
{
    public static final OCFileType INSTANCE;
    
    private OCFileType() {
        super((Language)OCLanguage.getInstance());
    }
    
    @NotNull
    public String getName() {
        String id;
        try {
            id = OCLanguage.getInstance().getID();
            if (id == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCFileType", "getName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return id;
    }
    
    @NotNull
    public String getDescription() {
        String string;
        try {
            string = OCLanguage.getInstance().getDisplayName() + " files";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCFileType", "getDescription"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @NotNull
    public String getDefaultExtension() {
        String s = null;
        Label_0017: {
            try {
                if (PlatformUtils.isAppCode()) {
                    final String s2;
                    s = (s2 = "m");
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            String s2;
            s = (s2 = "cpp");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCFileType", "getDefaultExtension"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    @Nullable
    public Icon getIcon() {
        try {
            if (PlatformUtils.isAppCode()) {
                return CidrLangIcons.FileType_m;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return CidrLangIcons.FileType_cpp;
    }
    
    static {
        INSTANCE = new OCFileType();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
