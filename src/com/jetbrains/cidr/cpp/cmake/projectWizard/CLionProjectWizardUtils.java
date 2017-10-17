// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import com.intellij.openapi.command.WriteCommandAction;
import java.util.Arrays;
import com.intellij.openapi.startup.StartupManager;
import com.jetbrains.cidr.cpp.toolchains.CMake;
import com.jetbrains.cidr.cpp.CPPToolchains;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.CMakeProjectSettings;
import com.intellij.openapi.util.Version;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.lang.formatting.OCFormattingModelBuilder;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VfsUtil;
import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public class CLionProjectWizardUtils
{
    public static void reformatProjectFiles(@NotNull final Project project, @NotNull final VirtualFile virtualFile, final boolean b, final VirtualFile... array) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "reformatProjectFiles"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cMakeFile", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "reformatProjectFiles"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        WriteAction.run(() -> {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "lambda$reformatProjectFiles$3"));
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                if (virtualFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cMakeFile", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "lambda$reformatProjectFiles$3"));
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            try {
                if (project.isDisposed()) {
                    return;
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
            StartupManager.getInstance(project).runWhenProjectIsInitialized((Runnable)(() -> {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "lambda$null$2"));
                    }
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
                try {
                    if (virtualFile == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cMakeFile", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "lambda$null$2"));
                    }
                }
                catch (RuntimeException ex2) {
                    throw b(ex2);
                }
                final IllegalArgumentException ex3;
                final IllegalArgumentException ex5;
                final IllegalArgumentException ex7;
                WriteCommandAction.runWriteCommandAction(project, () -> {
                    try {
                        if (project == null) {
                            new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "lambda$null$1"));
                            throw ex3;
                        }
                    }
                    catch (RuntimeException ex4) {
                        throw b(ex4);
                    }
                    try {
                        if (virtualFile == null) {
                            new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cMakeFile", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "lambda$null$1"));
                            throw ex5;
                        }
                    }
                    catch (RuntimeException ex6) {
                        throw b(ex6);
                    }
                    reformatFile(project, virtualFile, false);
                    Arrays.asList(array).forEach(virtualFile2 -> {
                        try {
                            if (project == null) {
                                new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "lambda$null$0"));
                                throw ex7;
                            }
                        }
                        catch (RuntimeException ex8) {
                            throw b(ex8);
                        }
                        reformatFile(project, virtualFile2, b);
                    });
                });
            }));
        });
    }
    
    public static void refreshProjectDir(final VirtualFile virtualFile) {
        Logger log = null;
        boolean b = false;
        Label_0016: {
            try {
                log = CPPLog.LOG;
                if (virtualFile != null) {
                    b = true;
                    break Label_0016;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            b = false;
        }
        log.assertTrue(b);
        VfsUtil.markDirtyAndRefresh(false, false, true, new VirtualFile[] { virtualFile });
    }
    
    static void reformatFile(@NotNull final Project project, @NotNull final VirtualFile virtualFile, final boolean b) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "reformatFile"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileToFormat", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "reformatFile"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        final PsiFile file = PsiManager.getInstance(project).findFile(virtualFile);
        final CodeStyleManager instance = CodeStyleManager.getInstance(project);
        if (b) {
            final FileDocumentManager instance2 = FileDocumentManager.getInstance();
            final Document document = instance2.getDocument(virtualFile);
            Logger log = null;
            boolean b2 = false;
            Label_0137: {
                try {
                    log = CPPLog.LOG;
                    if (document != null) {
                        b2 = true;
                        break Label_0137;
                    }
                }
                catch (RuntimeException ex3) {
                    throw b(ex3);
                }
                b2 = false;
            }
            log.assertTrue(b2);
            final OCCodeFragment codeFragment = OCElementFactory.codeFragment(document.getText(), project, null, OCTokenTypes.OC_FILE, false, false, OCLanguageKind.CPP);
            codeFragment.putUserData((Key)OCFormattingModelBuilder.ALWAYS_CREATE_FULL_MODEL, (Object)true);
            CodeStyleManager.getInstance(project).reformat((PsiElement)codeFragment);
            document.setText((CharSequence)codeFragment.getText());
            instance2.saveDocument(document);
        }
        else {
            try {
                if (file != null) {
                    instance.reformatText(file, 0, file.getTextLength());
                }
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
        }
    }
    
    @NotNull
    public static String getCMakeMinimumRequiredLine(final String s) {
        Version version = null;
        Label_0016: {
            try {
                if (s == null) {
                    version = null;
                    break Label_0016;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            version = Version.parseVersion(s);
        }
        final Version version2 = version;
        String s2 = null;
        Label_0109: {
            Label_0106: {
                String string = null;
                Label_0077: {
                    Label_0039: {
                        try {
                            if (version2 == null) {
                                break Label_0106;
                            }
                            final Version version3 = version2;
                            final int n = 3;
                            final Integer n2 = n;
                            final boolean b = version3.lessThan(n2);
                            if (b) {
                                break Label_0039;
                            }
                            break Label_0039;
                        }
                        catch (RuntimeException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final Version version3 = version2;
                            final int n = 3;
                            final Integer n2 = n;
                            final boolean b = version3.lessThan(n2);
                            if (b) {
                                string = "2.8.4";
                                break Label_0077;
                            }
                        }
                        catch (RuntimeException ex3) {
                            throw b(ex3);
                        }
                    }
                    string = version2.major + "." + version2.minor;
                }
                final String string2 = "cmake_minimum_required(VERSION " + string + ")\n";
                break Label_0109;
            }
            final String string2 = "# cmake_minimum_required(VERSION <specify CMake version here>)\n";
            try {
                s2 = string2;
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "getCMakeMinimumRequiredLine"));
                }
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
        }
        return s2;
    }
    
    @NotNull
    public static String getCMakeFileHeader(@NotNull final String s, @Nullable final CMakeProjectSettings cMakeProjectSettings) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectName", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "getCMakeFileHeader"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final CMake cMake = CPPToolchains.getInstance().getCMake();
        String languageVersionLineForCMake = "";
        if (cMakeProjectSettings != null) {
            languageVersionLineForCMake = cMakeProjectSettings.getLanguageVersionLineForCMake();
        }
        String string = null;
        Label_0086: {
            StringBuilder sb;
            try {
                sb = new StringBuilder();
                if (cMake == null) {
                    final String version = null;
                    break Label_0086;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            final String version = cMake.readVersion();
            try {
                string = sb.append(getCMakeMinimumRequiredLine(version)).append("project(").append(s).append(")\n\n").append(languageVersionLineForCMake).toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils", "getCMakeFileHeader"));
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
        }
        return string;
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
