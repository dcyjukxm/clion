// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;

class CidrRunConfigurationExecutableEditor$1$1 extends FileChooserDescriptor {
    public boolean isFileSelectable(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/CidrRunConfigurationExecutableEditor$1$1", "isFileSelectable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return CidrRunConfigurationExecutableEditor$1.access$200(ActionListener.this, virtualFile);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}