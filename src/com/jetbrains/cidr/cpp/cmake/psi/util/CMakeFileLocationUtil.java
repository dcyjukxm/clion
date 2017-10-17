// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi.util;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.psi.PsiDocumentManager;
import java.io.File;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class CMakeFileLocationUtil
{
    @NotNull
    public static String getLocationInFile(@NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementInCMakeFile", "com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFileLocationUtil", "getLocationInFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CMakeWorkspace instance = CMakeWorkspace.getInstance(psiElement.getProject());
        final PsiFile containingFile = psiElement.getContainingFile();
        VirtualFile virtualFile = containingFile.getVirtualFile();
        if (virtualFile == null) {
            virtualFile = containingFile.getOriginalFile().getVirtualFile();
        }
        String name = containingFile.getName();
        if (virtualFile != null) {
            final String path = virtualFile.getPath();
            final VirtualFile effectiveContentRoot = instance.getEffectiveContentRoot();
            if (effectiveContentRoot != null) {
                final String relativePath = FileUtil.getRelativePath(new File(FileUtil.toCanonicalPath(effectiveContentRoot.getPath())), new File(path));
                String s = null;
                Label_0163: {
                    try {
                        if (relativePath == null) {
                            s = path;
                            break Label_0163;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    s = relativePath;
                }
                name = s;
            }
        }
        final String systemDependentName = FileUtil.toSystemDependentName(name);
        if (b) {
            Document document = PsiDocumentManager.getInstance(psiElement.getProject()).getDocument(containingFile);
            if (document == null) {
                document = EditorFactory.getInstance().createDocument((CharSequence)containingFile.getText());
            }
            final int n = document.getLineNumber(psiElement.getTextOffset()) + 1;
            String string;
            try {
                string = systemDependentName + ":" + n;
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFileLocationUtil", "getLocationInFile"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return string;
        }
        String s2;
        try {
            s2 = systemDependentName;
            if (s2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/util/CMakeFileLocationUtil", "getLocationInFile"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return s2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
