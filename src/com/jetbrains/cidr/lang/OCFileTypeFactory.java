// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.lang.asm.AsmFileType;
import com.jetbrains.cidr.modulemap.ModuleMapFileType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileNameMatcher;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.fileTypes.ExtensionFileNameMatcher;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;

public class OCFileTypeFactory extends FileTypeFactory
{
    public void createFileTypes(@NotNull final FileTypeConsumer fileTypeConsumer) {
        try {
            if (fileTypeConsumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/OCFileTypeFactory", "createFileTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList<ExtensionFileNameMatcher> list = new ArrayList<ExtensionFileNameMatcher>();
        for (final String s : ContainerUtil.concat(new Iterable[] { OCFileTypeHelpers.HEADER_FILE_EXTENSIONS, OCFileTypeHelpers.SOURCE_FILE_EXTENSIONS })) {
            try {
                if (StringUtil.isEmptyOrSpaces(s)) {
                    continue;
                }
                list.add(new ExtensionFileNameMatcher(s));
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        fileTypeConsumer.consume((FileType)OCFileType.INSTANCE, (FileNameMatcher[])list.toArray(new FileNameMatcher[list.size()]));
        fileTypeConsumer.consume((FileType)ModuleMapFileType.INSTANCE);
        fileTypeConsumer.consume((FileType)AsmFileType.INSTANCE, StringUtil.join((Collection)AsmFileType.EXTENSIONS, ";"));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
