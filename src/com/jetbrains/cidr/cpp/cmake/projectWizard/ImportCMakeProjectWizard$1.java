// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import javax.swing.Icon;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ide.actions.OpenProjectFileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;

static final class ImportCMakeProjectWizard$1 extends FileChooserDescriptor {
    FileChooserDescriptor myDelegate = new OpenProjectFileChooserDescriptor(true);
    
    public Icon getIcon(final VirtualFile virtualFile) {
        final Icon icon = this.myDelegate.getIcon(virtualFile);
        return (icon == null) ? super.getIcon(virtualFile) : icon;
    }
    
    public boolean isFileSelectable(final VirtualFile virtualFile) {
        return virtualFile != null && virtualFile.isDirectory() && virtualFile.getChildren().length > 0;
    }
}