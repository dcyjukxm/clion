// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import java.util.Iterator;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.ProjectOpenHelper;

static final class CMakeProjectOpenProcessor$1 implements ProjectOpenHelper.SupportedFileChecker {
    @Override
    public boolean isSupportedFile(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$1", "isSupportedFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.isSupportedFile(virtualFile.getName());
    }
    
    @Override
    public boolean isSupportedFile(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileName", "com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$1", "isSupportedFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final String s2 : CMakeProjectOpenProcessor.SUPPORTED_FILES) {
            try {
                if (FileUtil.namesEqual(s2, s)) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}