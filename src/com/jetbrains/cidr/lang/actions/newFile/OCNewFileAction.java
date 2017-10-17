// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.openapi.vfs.StandardFileSystems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.icons.AllIcons;
import com.jetbrains.cidr.lang.OCBundle;

public class OCNewFileAction extends OCNewFileActionBase
{
    public OCNewFileAction() {
        super(null, OCBundle.message("create.file.title", new Object[0]), OCBundle.message("create.file.description", new Object[0]), AllIcons.FileTypes.Text);
    }
    
    @Nullable
    @Override
    protected String getFileTemplate(final String s) {
        return null;
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s) {
        NewFileNames newFileNames;
        try {
            newFileNames = new NewFileNames(s, null);
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileAction", "getNewFileNames"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return newFileNames;
    }
    
    @Override
    protected boolean isValidName(final String s) {
        return StandardFileSystems.local().isValidName(s);
    }
    
    @NotNull
    @Override
    protected CreateFileDialogBase createDialog(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultName", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        CreateFileDialog createFileDialog;
        try {
            createFileDialog = new CreateFileDialog(s);
            if (createFileDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return createFileDialog;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    protected class CreateFileDialog extends CreateFileDialogBase
    {
        public CreateFileDialog(final String s) {
            super("New " + OCNewFileAction.this.getTemplatePresentation().getText(), s, null);
        }
    }
}
