// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import java.util.Iterator;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.OCIncludeHelpers;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.intellij.util.containers.ContainerUtil;
import java.util.ArrayList;
import com.intellij.psi.PsiFileSystemItem;
import java.util.Collection;
import com.jetbrains.cidr.lang.OCFileType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceHelper;

public class OCFileReferenceHelper extends FileReferenceHelper
{
    @Override
    public boolean isMine(final Project project, @NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/resolve/OCFileReferenceHelper", "isMine"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile.getFileType() == OCFileType.INSTANCE) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @NotNull
    @Override
    public String trimUrl(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "url", "com/jetbrains/cidr/lang/resolve/OCFileReferenceHelper", "trimUrl"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String trim;
        try {
            trim = s.trim();
            if (trim == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCFileReferenceHelper", "trimUrl"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return trim;
    }
    
    @NotNull
    @Override
    public Collection<PsiFileSystemItem> getContexts(final Project project, @NotNull final VirtualFile file) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/resolve/OCFileReferenceHelper", "getContexts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList<PsiFileSystemItem> list = new ArrayList<PsiFileSystemItem>();
        final PsiFileSystemItem psiFileSystemItem = this.getPsiFileSystemItem(project, file);
        try {
            if (psiFileSystemItem != null) {
                ContainerUtil.addIfNotNull((Collection)list, (Object)psiFileSystemItem.getParent());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCResolveRootAndConfiguration resolveRootAndActiveConfiguration = OCInclusionContextUtil.getResolveRootAndActiveConfiguration(file, project);
        for (final PsiFileSystemItem psiFileSystemItem2 : resolveRootAndActiveConfiguration.getLibraryHeadersRoots().getRoots()) {
            try {
                if (!psiFileSystemItem2.isValid()) {
                    continue;
                }
                list.add(psiFileSystemItem2);
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        ArrayList<PsiFileSystemItem> list2;
        try {
            OCIncludeHelpers.processContainingFramework(project, file, (Processor<PsiFileSystemItem>)new CommonProcessors.CollectProcessor((Collection)list));
            list.addAll((Collection<?>)resolveRootAndActiveConfiguration.getProjectHeadersRoots().getRoots());
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCFileReferenceHelper", "getContexts"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return list2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
