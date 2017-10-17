// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import javax.swing.JComponent;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;

public interface NewFileHandler<T extends NewFileTarget>
{
    @NotNull
    List<T> bestTargets(@Nullable final PsiFile p0, @Nullable final String p1);
    
    void handleNewFiles(@NotNull final List<Integer> p0, @NotNull final List<VirtualFile> p1);
    
    List<T> getTargets();
    
    @Nullable
    JComponent createPreviewComponent();
    
    void updatePreviewComponent(@NotNull final JComponent p0, @Nullable final T p1);
}
