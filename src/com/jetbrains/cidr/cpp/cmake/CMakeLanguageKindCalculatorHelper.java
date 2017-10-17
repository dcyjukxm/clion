// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import com.jetbrains.cidr.cpp.cmake.model.CMakeModel;
import com.intellij.openapi.util.io.FileUtilRt;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.workspace.OCLanguageKindCalculatorHelper;

public class CMakeLanguageKindCalculatorHelper implements OCLanguageKindCalculatorHelper
{
    @Nullable
    @Override
    public OCLanguageKind getSpecifiedLanguage(@NotNull final Project project, @NotNull final VirtualFile virtualFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/CMakeLanguageKindCalculatorHelper", "getSpecifiedLanguage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/CMakeLanguageKindCalculatorHelper", "getSpecifiedLanguage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCLanguageKind getLanguageByExtension(@NotNull final Project project, @NotNull final String s) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/CMakeLanguageKindCalculatorHelper", "getLanguageByExtension"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/CMakeLanguageKindCalculatorHelper", "getLanguageByExtension"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final CMakeModel model = CMakeWorkspace.getInstance(project).getModel();
        try {
            if (model == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return model.getFileExtensions().get(FileUtilRt.getExtension(s));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
