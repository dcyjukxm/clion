// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.lang.Language;
import com.jetbrains.cidr.modulemap.ModuleMapFileType;
import com.jetbrains.cidr.modulemap.ModuleMapLanguage;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiManager;
import com.intellij.psi.SingleRootFileViewProvider;

private static class ModuleMapFileViewProvider extends SingleRootFileViewProvider
{
    public ModuleMapFileViewProvider(@NotNull final PsiManager psiManager, @NotNull final VirtualFile virtualFile, final boolean b) {
        if (psiManager == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "manager", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapFileImpl$ModuleMapFileViewProvider", "<init>"));
        }
        if (virtualFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapFileImpl$ModuleMapFileViewProvider", "<init>"));
        }
        super(psiManager, virtualFile, b, ModuleMapLanguage.INSTANCE, (FileType)ModuleMapFileType.INSTANCE);
    }
}
