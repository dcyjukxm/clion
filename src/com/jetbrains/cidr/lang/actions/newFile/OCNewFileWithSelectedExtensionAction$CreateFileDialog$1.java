// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.jetbrains.cidr.lang.settings.OCFileExtensionsSettingsListener;

class OCNewFileWithSelectedExtensionAction$CreateFileDialog$1 implements OCFileExtensionsSettingsListener {
    @Override
    public void settingsUpdated() {
        CreateFileDialog.this.reloadExtensions();
    }
}