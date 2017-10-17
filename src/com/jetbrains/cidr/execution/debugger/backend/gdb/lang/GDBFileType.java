// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb.lang;

import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;

public class GDBFileType extends LanguageFileType
{
    public static final GDBFileType INSTANCE;
    
    public GDBFileType() {
        super((Language)GDBLanguage.INSTANCE);
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "GDBCommands";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/lang/GDBFileType", "getName"));
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
            s = "GDBCommands";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/lang/GDBFileType", "getDescription"));
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
            s = "GDBCommandsFileTypeExtension";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/lang/GDBFileType", "getDefaultExtension"));
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
        INSTANCE = new GDBFileType();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
