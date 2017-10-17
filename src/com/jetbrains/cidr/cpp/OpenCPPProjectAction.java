// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.ide.impl.ProjectUtil;
import java.util.List;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.cmake.CMakeProjectOpenProcessor;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ide.actions.OpenProjectFileChooserDescriptor;
import com.intellij.openapi.wm.impl.welcomeScreen.NewWelcomeScreen;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.icons.AllIcons;
import com.intellij.ide.IdeBundle;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.actionSystem.AnAction;

public class OpenCPPProjectAction extends AnAction implements DumbAware
{
    public OpenCPPProjectAction() {
        super(IdeBundle.message("title.open.project", new Object[0]), "Open an existing project", AllIcons.General.OpenProject);
    }
    
    public void update(@NotNull final AnActionEvent anActionEvent) {
        try {
            if (anActionEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/cpp/OpenCPPProjectAction", "update"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            super.update(anActionEvent);
            if (NewWelcomeScreen.isNewWelcomeScreen(anActionEvent)) {
                anActionEvent.getPresentation().setIcon(AllIcons.Welcome.OpenProject);
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        FileChooser.chooseFiles((FileChooserDescriptor)new OpenProjectFileChooserDescriptor(true, false) {
            @Override
            public boolean isFileSelectable(@NotNull final VirtualFile virtualFile) {
                try {
                    if (virtualFile == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/OpenCPPProjectAction$1", "isFileSelectable"));
                    }
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
                return (boolean)ReadAction.compute(() -> {
                    try {
                        if (virtualFile == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/OpenCPPProjectAction$1", "lambda$isFileSelectable$0"));
                        }
                    }
                    catch (RuntimeException ex) {
                        throw b(ex);
                    }
                    try {
                        if (CMakeProjectOpenProcessor.findSupportedSubFile(virtualFile) != null) {
                            return true;
                        }
                    }
                    catch (RuntimeException ex2) {
                        throw b(ex2);
                    }
                    return false;
                });
            }
            
            private static RuntimeException b(final RuntimeException ex) {
                return ex;
            }
        }, (Project)anActionEvent.getData(CommonDataKeys.PROJECT), (VirtualFile)null, list -> {
            final VirtualFile virtualFile = (VirtualFile)ReadAction.compute(() -> CMakeProjectOpenProcessor.findSupportedSubFile(list.get(0)));
            try {
                if (virtualFile != null) {
                    ProjectUtil.openOrImport(virtualFile.getPath(), null, false);
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
        });
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
