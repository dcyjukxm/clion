// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.jetbrains.cidr.cpp.cmake.CMakeProjectOpenProcessor;
import com.intellij.openapi.application.ReadAction;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ide.actions.OpenProjectFileChooserDescriptor;

class OpenCPPProjectAction$1 extends OpenProjectFileChooserDescriptor {
    @Override
    public boolean isFileSelectable(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/OpenCPPProjectAction$1", "isFileSelectable"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return (boolean)ReadAction.compute(() -> {
            try {
                if (virtualFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/OpenCPPProjectAction$1", "lambda$isFileSelectable$0"));
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                if (CMakeProjectOpenProcessor.findSupportedSubFile(virtualFile) != null) {
                    return true;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            return false;
        });
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}