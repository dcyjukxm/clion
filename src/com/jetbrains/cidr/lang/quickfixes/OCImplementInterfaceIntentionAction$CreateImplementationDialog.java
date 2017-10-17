// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;

protected class CreateImplementationDialog extends CreateFileDialogBase
{
    public CreateImplementationDialog(final String s) {
        super(OCImplementInterfaceIntentionAction.this.getText(), s, null);
    }
}
