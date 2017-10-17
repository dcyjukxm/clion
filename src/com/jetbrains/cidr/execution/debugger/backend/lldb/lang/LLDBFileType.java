// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.lang;

import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;

public class LLDBFileType extends LanguageFileType
{
    public static final LLDBFileType INSTANCE;
    
    public LLDBFileType() {
        super((Language)LLDBLanguage.INSTANCE);
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "LLDBCommands";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBFileType", "getName"));
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
            s = "LLDBCommands";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBFileType", "getDescription"));
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
            s = "LLDBCommandsFileTypeExtension";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBFileType", "getDefaultExtension"));
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
    
    public Icon getIcon() {
        return null;
    }
    
    static {
        INSTANCE = new LLDBFileType();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
