// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.actions;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jetbrains.cidr.cpp.cmake.CMakeCacheFileType;
import com.jetbrains.cidr.cpp.CPPBundle;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import com.intellij.openapi.project.DumbAwareAction;

public class EditCMakeCacheAction extends DumbAwareAction
{
    @Nullable
    private final File myCacheFile;
    
    public EditCMakeCacheAction(@Nullable final File file) {
        super(CPPBundle.message("cmake.action.editCmakeCache", new Object[0]), (String)null, CMakeCacheFileType.INSTANCE.getIcon());
        this.myCacheFile = ((file == null) ? null : new File(file, "CMakeCache.txt"));
    }
    
    public void update(final AnActionEvent anActionEvent) {
        super.update(anActionEvent);
        anActionEvent.getPresentation().setEnabled(getEventProject(anActionEvent) != null && this.myCacheFile != null && this.myCacheFile.exists());
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        final Project eventProject = getEventProject(anActionEvent);
        if (eventProject == null || this.myCacheFile == null) {
            return;
        }
        final VirtualFile fileByIoFile = LocalFileSystem.getInstance().findFileByIoFile(this.myCacheFile);
        if (fileByIoFile != null) {
            new OpenFileDescriptor(eventProject, fileByIoFile, -1).navigate(true);
        }
    }
}
