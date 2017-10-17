// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.jetbrains.cidr.lang.OCBundle;

protected class CreateStringsFileDialog extends CreateFileDialogBase
{
    public CreateStringsFileDialog(final String s) {
        super(OCBundle.message("create.strings.dialog.title", new Object[0]), s, null);
    }
    
    @Override
    protected boolean isNameFieldEnabled() {
        return OCNewStringsFileAction.this.isNameFieldEnabled();
    }
}
