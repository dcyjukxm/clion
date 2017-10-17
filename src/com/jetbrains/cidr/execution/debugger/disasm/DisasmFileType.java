// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.disasm;

import org.jetbrains.annotations.Nullable;
import com.intellij.execution.runners.ExecutionUtil;
import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.StdLanguages;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;

public class DisasmFileType extends LanguageFileType
{
    public static final DisasmFileType INSTANCE;
    
    private DisasmFileType() {
        super(a());
    }
    
    private static Language a() {
        Language language = Language.findLanguageByID("ASM");
        if (language == null) {
            language = StdLanguages.TEXT;
        }
        return language;
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "Disassembly";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/disasm/DisasmFileType", "getName"));
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
            s = "Binary image disassembly";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/disasm/DisasmFileType", "getDescription"));
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
            s = "dis";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/disasm/DisasmFileType", "getDefaultExtension"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public boolean isReadOnly() {
        return true;
    }
    
    @Nullable
    public Icon getIcon() {
        return ExecutionUtil.getLiveIndicator(AllIcons.FileTypes.JavaClass);
    }
    
    static {
        INSTANCE = new DisasmFileType();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
