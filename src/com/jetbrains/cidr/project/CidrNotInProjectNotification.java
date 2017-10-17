// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import com.intellij.openapi.extensions.AreaInstance;
import com.intellij.openapi.extensions.Extensions;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.ui.EditorNotificationPanel;
import com.jetbrains.cidr.lang.daemon.OCAnnotator;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiManager;
import javax.swing.JComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.roots.ModuleRootEvent;
import com.intellij.openapi.roots.ModuleRootListener;
import com.intellij.ProjectTopics;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.project.DumbAware;
import com.intellij.ui.EditorNotifications;

public class CidrNotInProjectNotification extends EditorNotifications.Provider implements DumbAware
{
    private static final Key MY_KEY;
    private final Project myProject;
    private volatile boolean myEnabled;
    
    public CidrNotInProjectNotification(final Project myProject) {
        this.myProject = myProject;
        this.myProject.getMessageBus().connect().subscribe(ProjectTopics.PROJECT_ROOTS, (Object)new ModuleRootListener() {
            public void rootsChanged(final ModuleRootEvent moduleRootEvent) {
                EditorNotifications.getInstance(myProject).updateAllNotifications();
            }
        });
    }
    
    @NotNull
    public Key getKey() {
        Key my_KEY;
        try {
            my_KEY = CidrNotInProjectNotification.MY_KEY;
            if (my_KEY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/project/CidrNotInProjectNotification", "getKey"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return my_KEY;
    }
    
    @Nullable
    public JComponent createNotificationPanel(@NotNull final VirtualFile virtualFile, @NotNull final FileEditor fileEditor) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/project/CidrNotInProjectNotification", "createNotificationPanel"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (fileEditor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileEditor", "com/jetbrains/cidr/project/CidrNotInProjectNotification", "createNotificationPanel"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (!this.myEnabled) {
                return null;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final PsiFile file = PsiManager.getInstance(this.myProject).findFile(virtualFile);
        Label_0137: {
            try {
                if (!(file instanceof OCFile)) {
                    break Label_0137;
                }
                final PsiFile psiFile = file;
                final OCFile ocFile = (OCFile)psiFile;
                final boolean b = OCAnnotator.isAnnotated(ocFile);
                if (b) {
                    break Label_0137;
                }
                break Label_0137;
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            try {
                final PsiFile psiFile = file;
                final OCFile ocFile = (OCFile)psiFile;
                final boolean b = OCAnnotator.isAnnotated(ocFile);
                if (b) {
                    return null;
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        final EditorNotificationPanel editorNotificationPanel = new EditorNotificationPanel();
        editorNotificationPanel.setText("This file does not belong to any project target, code insight features might not work properly.");
        return (JComponent)editorNotificationPanel;
    }
    
    public static void setEnabled(@NotNull final Project project, final boolean myEnabled) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/project/CidrNotInProjectNotification", "setEnabled"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        ((CidrNotInProjectNotification)Extensions.findExtension(EditorNotifications.EXTENSION_POINT_NAME, (AreaInstance)project, (Class)CidrNotInProjectNotification.class)).myEnabled = myEnabled;
        EditorNotifications.getInstance(project).updateAllNotifications();
    }
    
    static {
        MY_KEY = Key.create("CidrNotInProjectNotification");
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
