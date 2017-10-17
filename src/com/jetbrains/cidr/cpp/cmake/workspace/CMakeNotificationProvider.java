// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.ui.EditorNotificationPanel;
import com.intellij.openapi.vfs.VfsUtilCore;
import javax.swing.JComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.DumbAware;
import com.intellij.ui.EditorNotifications;

public class CMakeNotificationProvider extends EditorNotifications.Provider implements DumbAware
{
    private final Project myProject;
    private final CMakeWorkspace myWorkspace;
    static final Key KEY;
    
    public CMakeNotificationProvider(@NotNull final Project myProject) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeNotificationProvider", "<init>"));
        }
        this.myProject = myProject;
        this.myWorkspace = CMakeWorkspace.getInstance(myProject);
    }
    
    @NotNull
    public Key getKey() {
        Key key;
        try {
            key = CMakeNotificationProvider.KEY;
            if (key == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeNotificationProvider", "getKey"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return key;
    }
    
    @Nullable
    public JComponent createNotificationPanel(@NotNull final VirtualFile virtualFile, @NotNull final FileEditor fileEditor) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeNotificationProvider", "createNotificationPanel"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (fileEditor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileEditor", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeNotificationProvider", "createNotificationPanel"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0122: {
            try {
                if (this.myProject.isDisposed()) {
                    return null;
                }
                final CMakeNotificationProvider cMakeNotificationProvider = this;
                final Project project = cMakeNotificationProvider.myProject;
                final Key<Boolean> key = CMakeWorkspaceWatcher.NEEDS_RELOAD;
                final Object o = project.getUserData((Key)key);
                if (o != null) {
                    break Label_0122;
                }
                return null;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final CMakeNotificationProvider cMakeNotificationProvider = this;
                final Project project = cMakeNotificationProvider.myProject;
                final Key<Boolean> key = CMakeWorkspaceWatcher.NEEDS_RELOAD;
                final Object o = project.getUserData((Key)key);
                if (o == null) {
                    return null;
                }
                if (!this.myWorkspace.getCMakeFiles().contains(VfsUtilCore.virtualToIoFile(virtualFile))) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final EditorNotificationPanel editorNotificationPanel = new EditorNotificationPanel();
        editorNotificationPanel.setText(CPPBundle.message("cmake.autoReloadNotification.text", new Object[0]));
        editorNotificationPanel.createActionLabel(CPPBundle.message("cmake.autoReloadNotification.reloadChanges", new Object[0]), "CMake.ReloadCMakeProject");
        editorNotificationPanel.createActionLabel(CPPBundle.message("cmake.autoReloadNotification.enableAutoReload", new Object[0]), "CMake.ToggleCMakeAutoReload");
        return (JComponent)editorNotificationPanel;
    }
    
    static {
        KEY = Key.create("CMakeNotificationProvider");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
