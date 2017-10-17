// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators;

import java.io.IOException;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.cpp.cmake.projectWizard.CLionProjectWizardUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;

public class CMakeCProjectGenerator extends CMakeAbstractCProjectGenerator
{
    @Nls
    @NotNull
    @Override
    public String getName() {
        String s;
        try {
            s = "C Executable";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCProjectGenerator", "getName"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return s;
    }
    
    @NotNull
    @Override
    protected String getCMakeFileContent(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCProjectGenerator", "getCMakeFileContent"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        String string;
        try {
            string = CLionProjectWizardUtils.getCMakeFileHeader(s, this.getCMakeProjectSettings()) + "\nset(SOURCE_FILES main.c)\nadd_executable(" + s + " ${SOURCE_FILES})";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCProjectGenerator", "getCMakeFileContent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw c(ex2);
        }
        return string;
    }
    
    @NotNull
    @Override
    protected VirtualFile[] createSourceFiles(@NotNull final String s, @NotNull final VirtualFile virtualFile) throws IOException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCProjectGenerator", "createSourceFiles"));
            }
        }
        catch (IOException ex) {
            throw c(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dir", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCProjectGenerator", "createSourceFiles"));
            }
        }
        catch (IOException ex2) {
            throw c(ex2);
        }
        VirtualFile[] array;
        try {
            array = new VirtualFile[] { this.createProjectFileWithContent(virtualFile, "main.c", "#include <stdio.h>\n\nint main( )\n{\n    printf(\"Hello, World!\\n\");\n    return 0;\n}\n") };
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCProjectGenerator", "createSourceFiles"));
            }
        }
        catch (IOException ex3) {
            throw c(ex3);
        }
        return array;
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
