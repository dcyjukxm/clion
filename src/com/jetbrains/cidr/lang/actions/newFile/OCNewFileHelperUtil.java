// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.ui.mac.foundation.Foundation;
import com.intellij.openapi.util.SystemInfo;
import org.jetbrains.annotations.Nullable;
import java.util.Properties;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTable;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Function;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiDirectory;

public class OCNewFileHelperUtil
{
    public static void addCreatedFiles(@NotNull final PsiDirectory psiDirectory, @NotNull final String[] array, @NotNull final PsiFile[] array2, @NotNull final Project project, @NotNull final Function<VirtualFile, Void> function) {
        try {
            if (psiDirectory == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "directory", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileHelperUtil", "addCreatedFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileNames", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileHelperUtil", "addCreatedFiles"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (array2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resultElements", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileHelperUtil", "addCreatedFiles"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileHelperUtil", "addCreatedFiles"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (function == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileHandler", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileHelperUtil", "addCreatedFiles"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        for (int i = 0; i < array2.length; ++i) {
            final PsiFile psiFile = array2[i];
            PsiFile file;
            if (psiFile == null) {
                file = psiDirectory.createFile(array[i]);
            }
            else {
                file = (PsiFile)psiDirectory.add((PsiElement)psiFile);
            }
            final VirtualFile virtualFile = file.getVirtualFile();
            try {
                if (virtualFile != null) {
                    CommandProcessor.getInstance().addAffectedFiles(project, new VirtualFile[] { virtualFile });
                    function.fun((Object)virtualFile);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                array2[i] = file;
                CodeStyleManager.getInstance(project).reformat((PsiElement)file);
                if (file instanceof OCFile) {
                    FileSymbolTable.forFile(file, OCInclusionContextUtil.headerContext(file).derive());
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
    }
    
    public static void fillCommonTemplateProperties(@NotNull final Properties properties, @Nullable final String s) {
        try {
            if (properties == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "properties", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileHelperUtil", "fillCommonTemplateProperties"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String s2 = null;
        Label_0066: {
            try {
                if (SystemInfo.isMac) {
                    s2 = Foundation.fullUserName();
                    break Label_0066;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            s2 = properties.getProperty("USER");
        }
        properties.setProperty("USER_NAME", s2);
        properties.setProperty("PROJECT_NAME", s);
        properties.setProperty("PROJECTNAME", s);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
