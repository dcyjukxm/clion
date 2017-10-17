// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Collections;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.HashSet;
import java.util.Collection;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.extensions.ExtensionPointName;

public abstract class OCResourceFilesProvider
{
    private static final ExtensionPointName<OCResourceFilesProvider> EP_NAME;
    
    @NotNull
    public static Collection<VirtualFile> getAccessibleResources(@NotNull final Project project, @NotNull final VirtualFile virtualFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/resolve/references/OCResourceFilesProvider", "getAccessibleResources"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/resolve/references/OCResourceFilesProvider", "getAccessibleResources"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final HashSet set = new HashSet();
        final OCResourceFilesProvider[] array = (OCResourceFilesProvider[])OCResourceFilesProvider.EP_NAME.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            set.addAll((Collection)array[i].getAccessibleResourceFiles(project, virtualFile));
        }
        HashSet set2;
        try {
            set2 = set;
            if (set2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCResourceFilesProvider", "getAccessibleResources"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (Collection<VirtualFile>)set2;
    }
    
    @NotNull
    public static Collection<VirtualFile> getAccessibleResources(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/references/OCResourceFilesProvider", "getAccessibleResources"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final VirtualFile a = a(psiElement);
        List<VirtualFile> emptyList = null;
        Label_0109: {
            Collection<VirtualFile> collection = null;
            Label_0074: {
                try {
                    if (a == null) {
                        break Label_0109;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final Project project = psiElement2.getProject();
                    final VirtualFile virtualFile = a;
                    collection = getAccessibleResources(project, virtualFile);
                    if (collection == null) {
                        break Label_0074;
                    }
                    return collection;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final Project project = psiElement2.getProject();
                    final VirtualFile virtualFile = a;
                    collection = getAccessibleResources(project, virtualFile);
                    if (collection == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCResourceFilesProvider", "getAccessibleResources"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return collection;
            try {
                emptyList = Collections.emptyList();
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCResourceFilesProvider", "getAccessibleResources"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return emptyList;
    }
    
    public static boolean isAccessible(@NotNull final Project project, @Nullable final VirtualFile virtualFile, @Nullable final VirtualFile virtualFile2) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/resolve/references/OCResourceFilesProvider", "isAccessible"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final OCResourceFilesProvider ocResourceFilesProvider : (OCResourceFilesProvider[])OCResourceFilesProvider.EP_NAME.getExtensions()) {
            try {
                if (ocResourceFilesProvider.isAccessibleInner(project, virtualFile, virtualFile2)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static boolean isAccessible(@NotNull final PsiElement psiElement, @NotNull final VirtualFile virtualFile) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/references/OCResourceFilesProvider", "isAccessible"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/resolve/references/OCResourceFilesProvider", "isAccessible"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return isAccessible(psiElement.getProject(), a(psiElement), virtualFile);
    }
    
    @Nullable
    private static VirtualFile a(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/references/OCResourceFilesProvider", "getFileFromElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        VirtualFile virtualFile = psiElement.getContainingFile().getVirtualFile();
        if (virtualFile == null) {
            virtualFile = psiElement.getContainingFile().getOriginalFile().getVirtualFile();
        }
        return virtualFile;
    }
    
    @NotNull
    protected abstract Collection<VirtualFile> getAccessibleResourceFiles(@NotNull final Project p0, @NotNull final VirtualFile p1);
    
    protected abstract boolean isAccessibleInner(@NotNull final Project p0, @NotNull final VirtualFile p1, @Nullable final VirtualFile p2);
    
    static {
        EP_NAME = ExtensionPointName.create("cidr.lang.resourceFilesProvider");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
