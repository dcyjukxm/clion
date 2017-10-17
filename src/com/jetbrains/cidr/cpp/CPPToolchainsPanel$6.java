// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Function;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;

static final class CPPToolchainsPanel$6 extends FileChooserDescriptor {
    final /* synthetic */ Function val$isAppBundle;
    
    public boolean isFileSelectable(final VirtualFile virtualFile) {
        return super.isFileSelectable(virtualFile) || (boolean)this.val$isAppBundle.fun((Object)VfsUtilCore.virtualToIoFile(virtualFile));
    }
}