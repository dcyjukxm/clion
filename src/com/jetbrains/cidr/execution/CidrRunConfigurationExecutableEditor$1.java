// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.vfs.LocalFileSystem;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CidrRunConfigurationExecutableEditor$1 implements ActionListener {
    Object lastSelection = CidrRunConfigurationExecutableEditor.access$000(CidrRunConfigurationExecutableEditor.this).getSelectedItem();
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object selectedItem = CidrRunConfigurationExecutableEditor.access$000(CidrRunConfigurationExecutableEditor.this).getSelectedItem();
        try {
            if (selectedItem != CidrRunConfigurationExecutableEditor.access$100(CidrRunConfigurationExecutableEditor.this)) {
                this.lastSelection = selectedItem;
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Object lastSelection = this.lastSelection;
        CidrRunConfigurationExecutableEditor.access$000(CidrRunConfigurationExecutableEditor.this).setSelectedItem(this.lastSelection);
        final FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(true, false, false, false, false, false) {
            public boolean isFileSelectable(@NotNull final VirtualFile virtualFile) {
                try {
                    if (virtualFile == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/CidrRunConfigurationExecutableEditor$1$1", "isFileSelectable"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return ActionListener.this.a(virtualFile);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        VirtualFile fileByIoFile = null;
        if (lastSelection instanceof File) {
            fileByIoFile = LocalFileSystem.getInstance().findFileByIoFile((File)lastSelection);
        }
        FileChooser.chooseFiles((FileChooserDescriptor)fileChooserDescriptor, CidrRunConfigurationExecutableEditor.access$300(CidrRunConfigurationExecutableEditor.this), fileByIoFile, list -> {
            final VirtualFile virtualFile = (VirtualFile)ContainerUtil.getFirstItem(list);
            Object o = null;
            Label_0046: {
                Label_0027: {
                    try {
                        if (virtualFile == null) {
                            break Label_0027;
                        }
                        final ActionListener actionListener = this;
                        final VirtualFile virtualFile2 = virtualFile;
                        final boolean b = actionListener.a(virtualFile2);
                        if (!b) {
                            break Label_0027;
                        }
                        break Label_0027;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final ActionListener actionListener = this;
                        final VirtualFile virtualFile2 = virtualFile;
                        final boolean b = actionListener.a(virtualFile2);
                        if (!b) {
                            o = null;
                            break Label_0046;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                o = new File(virtualFile.getPath());
            }
            CidrRunConfigurationExecutableEditor.access$400(CidrRunConfigurationExecutableEditor.this, true, o, null);
        });
    }
    
    private boolean a(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/CidrRunConfigurationExecutableEditor$1", "isSelectable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile.isDirectory()) {
                return "app".equals(virtualFile.getExtension());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return new File(virtualFile.getPath()).canExecute();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}