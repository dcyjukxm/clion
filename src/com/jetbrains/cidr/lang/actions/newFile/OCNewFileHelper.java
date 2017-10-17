// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.openapi.project.Project;
import java.util.Properties;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.DataContext;

public interface OCNewFileHelper
{
    boolean initFromDataContext(@NotNull final DataContext p0);
    
    boolean initFromFile(@NotNull final PsiFile p0);
    
    @NotNull
    String getDefaultClassPrefix();
    
    boolean canChangeDir();
    
    @NotNull
    DialogWrapper createDialog(@NotNull final OCNewFileActionBase.CreateFileDialogBase p0, @Nullable final PsiDirectory p1, @Nullable final DataContext p2);
    
    void setProperties(@Nullable final DialogWrapper p0, final Properties p1, @Nullable final PsiFile p2, final Project p3);
    
    void doCreateFiles(@NotNull final Project p0, @NotNull final PsiDirectory p1, @NotNull final String[] p2, @NotNull final PsiFile[] p3, @Nullable final DialogWrapper p4, @Nullable final PsiFile p5);
}
