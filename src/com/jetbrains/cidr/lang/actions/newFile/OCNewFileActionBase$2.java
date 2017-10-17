// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

static final class OCNewFileActionBase$2 implements FileFactory {
    final /* synthetic */ Project val$project;
    
    @Override
    public PsiFile createFileFromText(@NotNull final String s, @Nullable final FileType fileType, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileName", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$2", "createFileFromText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$2", "createFileFromText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiFileFactory instance = PsiFileFactory.getInstance(this.val$project);
        try {
            if (fileType == null) {
                return instance.createFileFromText(s, s2);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return instance.createFileFromText(s, fileType, (CharSequence)s2);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}