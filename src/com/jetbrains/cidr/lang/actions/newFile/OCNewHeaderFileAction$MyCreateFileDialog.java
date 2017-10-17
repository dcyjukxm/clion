// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;

private class MyCreateFileDialog extends CreateFileDialog
{
    public MyCreateFileDialog(final String s) {
        super(s);
    }
    
    @NotNull
    @Override
    protected String getPresentableName(@NotNull final OCCodeStyleSettings.FileExtensionPair fileExtensionPair) {
        try {
            if (fileExtensionPair == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pair", "com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction$MyCreateFileDialog", "getPresentableName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        String string;
        try {
            string = "." + fileExtensionPair.myHeaderExt;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewHeaderFileAction$MyCreateFileDialog", "getPresentableName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return string;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
