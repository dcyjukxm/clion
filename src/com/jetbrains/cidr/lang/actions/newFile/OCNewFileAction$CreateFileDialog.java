// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

protected class CreateFileDialog extends CreateFileDialogBase
{
    public CreateFileDialog(final String s) {
        super("New " + OCNewFileAction.this.getTemplatePresentation().getText(), s, null);
    }
}
