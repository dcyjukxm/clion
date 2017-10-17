// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiDirectory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

protected static class DisposableState<T extends CreateFileDialogBase>
{
    public final PsiFile selectedFile;
    public final Project project;
    public final PsiDirectory selectedDir;
    @NotNull
    public final T dialogPeer;
    
    public DisposableState(final PsiFile selectedFile, final Project project, final PsiDirectory selectedDir, @NotNull final T dialogPeer) {
        if (dialogPeer == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dialogPeer", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$DisposableState", "<init>"));
        }
        this.selectedFile = selectedFile;
        this.project = project;
        this.selectedDir = selectedDir;
        this.dialogPeer = dialogPeer;
    }
}
