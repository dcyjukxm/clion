// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;

protected class CreateInterfaceDialog extends CreateFileDialogBase
{
    public CreateInterfaceDialog(final String s) {
        super(OCCreateInterfaceIntentionAction.this.getText(), s, null);
    }
}
