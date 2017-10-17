// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl;

import com.intellij.psi.SingleRootFileViewProvider;
import com.jetbrains.cidr.modulemap.psi.ModuleMapExternModuleDeclaration;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.modulemap.psi.ModuleMapModuleDeclaration;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.testFramework.LightVirtualFile;
import com.jetbrains.cidr.modulemap.ModuleMapFileType;
import com.intellij.psi.PsiManager;
import com.intellij.lang.Language;
import com.jetbrains.cidr.modulemap.ModuleMapLanguage;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.FileViewProvider;
import com.jetbrains.cidr.modulemap.psi.ModuleMapFile;
import com.intellij.extapi.psi.PsiFileBase;

public class ModuleMapFileImpl extends PsiFileBase implements ModuleMapFile
{
    public ModuleMapFileImpl(@NotNull final FileViewProvider viewProvider) {
        if (viewProvider == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "viewProvider", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapFileImpl", "<init>"));
        }
        super(viewProvider, ModuleMapLanguage.INSTANCE);
    }
    
    public static ModuleMapFile createFromText(@NotNull final CharSequence charSequence, @NotNull final PsiManager psiManager) {
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapFileImpl", "createFromText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiManager == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "manager", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapFileImpl", "createFromText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (ModuleMapFile)new ModuleMapFileViewProvider(psiManager, (VirtualFile)new LightVirtualFile("module.modulemap", (FileType)ModuleMapFileType.INSTANCE, charSequence), false).getPsi(ModuleMapLanguage.INSTANCE);
    }
    
    @NotNull
    public FileType getFileType() {
        ModuleMapFileType instance;
        try {
            instance = ModuleMapFileType.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapFileImpl", "getFileType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (FileType)instance;
    }
    
    @NotNull
    @Override
    public List<ModuleMapModuleDeclaration> getModuleDeclarations() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapModuleDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapFileImpl", "getModuleDeclarations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<ModuleMapModuleDeclaration>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapExternModuleDeclaration> getExternModuleDeclarations() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)ModuleMapExternModuleDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/modulemap/psi/impl/ModuleMapFileImpl", "getExternModuleDeclarations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<ModuleMapExternModuleDeclaration>)childrenOfTypeAsList;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
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
}
