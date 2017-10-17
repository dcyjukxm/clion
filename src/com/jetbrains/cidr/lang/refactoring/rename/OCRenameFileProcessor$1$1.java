// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.intellij.refactoring.ui.ConflictsDialog;
import com.intellij.usageView.UsageInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.MultiMap;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.rename.RenameProcessor;

class OCRenameFileProcessor$1$1 extends RenameProcessor {
    @NotNull
    @Override
    protected ConflictsDialog createConflictsDialog(@NotNull final MultiMap<PsiElement, String> multiMap, final UsageInfo[] array) {
        try {
            if (multiMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "conflicts", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameFileProcessor$1$1", "createConflictsDialog"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ConflictsDialog conflictsDialog;
        try {
            conflictsDialog = new ConflictsDialog(this.myProject, multiMap, null, false, false);
            if (conflictsDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameFileProcessor$1$1", "createConflictsDialog"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return conflictsDialog;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}