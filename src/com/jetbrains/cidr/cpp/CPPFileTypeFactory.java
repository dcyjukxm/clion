// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.jetbrains.cidr.cpp.cmake.CMakeCacheFileType;
import com.intellij.openapi.fileTypes.ExtensionFileNameMatcher;
import com.intellij.openapi.fileTypes.ExactFileNameMatcher;
import com.intellij.openapi.fileTypes.FileNameMatcher;
import com.jetbrains.cidr.cpp.cmake.CMakeListsFileType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.ide.highlighter.ModuleFileType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;

public class CPPFileTypeFactory extends FileTypeFactory
{
    public void createFileTypes(@NotNull final FileTypeConsumer fileTypeConsumer) {
        try {
            if (fileTypeConsumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/cpp/CPPFileTypeFactory", "createFileTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        fileTypeConsumer.consume((FileType)ModuleFileType.INSTANCE, "iml");
        fileTypeConsumer.consume(CMakeListsFileType.INSTANCE, new FileNameMatcher[] { new ExactFileNameMatcher("CMakeLists.txt", true), new ExtensionFileNameMatcher("cmake") });
        fileTypeConsumer.consume(CMakeCacheFileType.INSTANCE, new FileNameMatcher[] { new ExactFileNameMatcher("CMakeCache.txt", true) });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
