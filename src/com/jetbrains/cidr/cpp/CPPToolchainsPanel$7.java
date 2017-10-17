// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.openapi.vfs.VfsUtil;
import java.io.File;
import com.intellij.openapi.vfs.VfsUtilCore;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.util.Function;
import com.intellij.openapi.ui.TextBrowseFolderListener;

static final class CPPToolchainsPanel$7 extends TextBrowseFolderListener {
    final /* synthetic */ Function val$isAppBundle;
    final /* synthetic */ Function val$getFileFromAppBundle;
    
    protected void onFileChosen(@NotNull VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenFile", "com/jetbrains/cidr/cpp/CPPToolchainsPanel$7", "onFileChosen"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final File virtualToIoFile = VfsUtilCore.virtualToIoFile(virtualFile);
        if (this.val$isAppBundle.fun((Object)virtualToIoFile)) {
            final VirtualFile fileByIoFile = VfsUtil.findFileByIoFile((File)this.val$getFileFromAppBundle.fun((Object)virtualToIoFile), false);
            if (fileByIoFile != null) {
                virtualFile = fileByIoFile;
            }
        }
        super.onFileChosen(virtualFile);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}