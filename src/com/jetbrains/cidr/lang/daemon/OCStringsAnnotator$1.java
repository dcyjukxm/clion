// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.CommonProcessors;

class OCStringsAnnotator$1 extends CommonProcessors.FindFirstProcessor<VirtualFile> {
    protected boolean accept(final VirtualFile virtualFile) {
        final String extension = virtualFile.getExtension();
        return extension != null && !extension.equals("strings");
    }
}