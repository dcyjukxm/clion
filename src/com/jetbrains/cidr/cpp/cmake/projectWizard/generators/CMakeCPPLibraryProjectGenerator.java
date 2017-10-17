// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators;

import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import java.io.IOException;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.CMakeProjectSettings;
import com.jetbrains.cidr.cpp.cmake.projectWizard.CLionProjectWizardUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;

public class CMakeCPPLibraryProjectGenerator extends CMakeAbstractCPPProjectGenerator
{
    @Nls
    @NotNull
    @Override
    public String getName() {
        String s;
        try {
            s = "C++ Library";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCPPLibraryProjectGenerator", "getName"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectName", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCPPLibraryProjectGenerator", "getCMakeFileContent"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        final CMakeProjectSettings cMakeProjectSettings = this.getCMakeProjectSettings();
        String string = null;
        Label_0090: {
            StringBuilder append;
            try {
                append = new StringBuilder().append(CLionProjectWizardUtils.getCMakeFileHeader(s, cMakeProjectSettings)).append("\nset(SOURCE_FILES library.cpp library.h)\nadd_library(").append(s);
                if (cMakeProjectSettings != null) {
                    final String libraryTypeForCMake = cMakeProjectSettings.getLibraryTypeForCMake();
                    break Label_0090;
                }
            }
            catch (IllegalStateException ex2) {
                throw c(ex2);
            }
            final String libraryTypeForCMake = "";
            try {
                string = append.append(libraryTypeForCMake).append(" ${SOURCE_FILES})").toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCPPLibraryProjectGenerator", "getCMakeFileContent"));
                }
            }
            catch (IllegalStateException ex3) {
                throw c(ex3);
            }
        }
        return string;
    }
    
    @Override
    public boolean addLibrarySettingsPanel() {
        return true;
    }
    
    @NotNull
    @Override
    protected VirtualFile[] createSourceFiles(@NotNull final String s, @NotNull final VirtualFile virtualFile) throws IOException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCPPLibraryProjectGenerator", "createSourceFiles"));
            }
        }
        catch (IOException ex) {
            throw c(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dir", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCPPLibraryProjectGenerator", "createSourceFiles"));
            }
        }
        catch (IOException ex2) {
            throw c(ex2);
        }
        final String s2 = "library.h";
        final String includeGuard = OCNewFileActionBase.includeGuard(s, s2);
        VirtualFile[] array;
        try {
            array = new VirtualFile[] { this.createProjectFileWithContent(virtualFile, s2, "#ifndef " + includeGuard + "\n#define " + includeGuard + "\nvoid hello();\n#endif\n"), this.createProjectFileWithContent(virtualFile, "library.cpp", "#include \"library.h\"\n\n#include <iostream>\n\nvoid hello()\n{\n    std::cout<<\"Hello, World!\"<<std::endl;\n}\n") };
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeCPPLibraryProjectGenerator", "createSourceFiles"));
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
