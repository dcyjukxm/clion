// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import java.util.List;
import com.intellij.ui.AddEditRemovePanel$TableModel;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.vfs.LocalFileSystem;
import java.awt.Component;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import org.jetbrains.annotations.Nullable;
import kotlin.Metadata;
import com.intellij.ui.AddEditRemovePanel;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0014J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0014¨\u0006\n" }, d2 = { "com/jetbrains/cidr/cpp/execution/remote/EditDebuggersDialog$createCenterPanel$panel$1", "Lcom/intellij/ui/AddEditRemovePanel;", "", "(Lcom/jetbrains/cidr/cpp/execution/remote/EditDebuggersDialog;Lcom/jetbrains/cidr/cpp/execution/remote/EditDebuggersDialog$createCenterPanel$model$1;Lcom/intellij/ui/AddEditRemovePanel$TableModel;Ljava/util/List;)V", "addItem", "editItem", "o", "isUpDownSupported", "", "removeItem", "clion" })
public static final class EditDebuggersDialog$createCenterPanel$panel$1 extends AddEditRemovePanel<String> {
    public boolean isUpDownSupported() {
        return true;
    }
    
    @Nullable
    protected String addItem() {
        return this.editItem(null);
    }
    
    protected boolean removeItem(@Nullable final String s) {
        return true;
    }
    
    @Nullable
    protected String editItem(@Nullable final String s) {
        final VirtualFile chooseFile = FileChooser.chooseFile(new FileChooserDescriptor(true, false, false, false, false, false), (Component)this, (Project)null, (s != null) ? LocalFileSystem.getInstance().findFileByPath(s) : null);
        return (chooseFile != null) ? chooseFile.getPath() : null;
    }
}