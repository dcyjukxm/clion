// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;

public class DxFileTypeFactory extends FileTypeFactory
{
    public void createFileTypes(@NotNull final FileTypeConsumer fileTypeConsumer) {
        try {
            if (fileTypeConsumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileTypeConsumer", "com/jetbrains/cidr/doxygen/DxFileTypeFactory", "createFileTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        fileTypeConsumer.consume((FileType)DxFileType.INSTANCE, DxFileType.INSTANCE.getDefaultExtension());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
