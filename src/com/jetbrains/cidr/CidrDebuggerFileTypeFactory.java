// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import com.jetbrains.cidr.execution.debugger.backend.lldb.lang.LLDBFileType;
import com.intellij.openapi.fileTypes.FileType;
import com.jetbrains.cidr.execution.debugger.backend.gdb.lang.GDBFileType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;

public class CidrDebuggerFileTypeFactory extends FileTypeFactory
{
    public void createFileTypes(@NotNull final FileTypeConsumer fileTypeConsumer) {
        try {
            if (fileTypeConsumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/CidrDebuggerFileTypeFactory", "createFileTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        fileTypeConsumer.consume((FileType)GDBFileType.INSTANCE);
        fileTypeConsumer.consume((FileType)LLDBFileType.INSTANCE);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
