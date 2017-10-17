// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import com.jetbrains.cidr.cpp.cmake.CMakeCacheFileType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.lang.Language;
import com.jetbrains.cidr.cpp.cmake.cmakecache.CMakeCacheLanguage;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.FileViewProvider;
import com.intellij.extapi.psi.PsiFileBase;

public class CMakeCacheTxtFile extends PsiFileBase
{
    public CMakeCacheTxtFile(@NotNull final FileViewProvider viewProvider) {
        if (viewProvider == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "viewProvider", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTxtFile", "<init>"));
        }
        super(viewProvider, CMakeCacheLanguage.getInstance());
    }
    
    @NotNull
    public FileType getFileType() {
        FileType instance;
        try {
            instance = CMakeCacheFileType.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTxtFile", "getFileType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return instance;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
