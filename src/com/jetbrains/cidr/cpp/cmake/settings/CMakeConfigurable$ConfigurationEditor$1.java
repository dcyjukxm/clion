// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.ui.TextBrowseFolderListener;

class CMakeConfigurable$ConfigurationEditor$1 extends TextBrowseFolderListener {
    @Nullable
    protected VirtualFile getInitialFile() {
        File file = ConfigurationEditor.access$1300(ConfigurationEditor.this).getEffectiveGenerationDir(ConfigurationEditor.this);
        while (true) {
            try {
                if (file == null || file.exists()) {
                    break;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            file = file.getParentFile();
        }
        try {
            if (file == null) {
                return CMakeConfigurable.access$700(ConfigurationEditor.this.this$0).getEffectiveContentRoot();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return VfsUtil.findFileByIoFile(file, false);
    }
    
    @NotNull
    protected String chosenFileToResultingText(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenFile", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor$1", "chosenFileToResultingText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final File virtualToIoFile = VfsUtilCore.virtualToIoFile(virtualFile);
        final File projectSubDir = CMakeConfigurable.access$700(ConfigurationEditor.this.this$0).getProjectSubDir(new File(""));
        String chosenFileToResultingText = null;
        Label_0141: {
            if (FileUtil.isAncestor(projectSubDir, virtualToIoFile, true)) {
                final String relativePath = FileUtil.getRelativePath(projectSubDir, virtualToIoFile);
                String s = null;
                Label_0106: {
                    try {
                        if (relativePath == null) {
                            break Label_0141;
                        }
                        s = relativePath;
                        if (s == null) {
                            break Label_0106;
                        }
                        return s;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        s = relativePath;
                        if (s == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor$1", "chosenFileToResultingText"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return s;
            }
            try {
                chosenFileToResultingText = super.chosenFileToResultingText(virtualFile);
                if (chosenFileToResultingText == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeConfigurable$ConfigurationEditor$1", "chosenFileToResultingText"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return chosenFileToResultingText;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}