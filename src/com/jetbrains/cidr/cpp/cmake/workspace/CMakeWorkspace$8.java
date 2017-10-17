// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import gnu.trove.THashMap;
import com.intellij.openapi.vfs.VirtualFile;
import java.io.File;
import com.intellij.util.NullableFunction;

class CMakeWorkspace$8 implements NullableFunction<File, VirtualFile> {
    private final THashMap<File, VirtualFile> myCache = new THashMap(FileUtil.FILE_HASHING_STRATEGY);
    final /* synthetic */ LocalFileSystem val$fs;
    
    public VirtualFile fun(final File file) {
        if (this.myCache.containsKey((Object)file)) {
            return (VirtualFile)this.myCache.get((Object)file);
        }
        final VirtualFile fileByIoFile = this.val$fs.findFileByIoFile(file);
        this.myCache.put((Object)file, (Object)fileByIoFile);
        return fileByIoFile;
    }
}