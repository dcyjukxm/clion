// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import org.jetbrains.annotations.NotNull;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

private class NameFocusListener implements FocusListener
{
    private int mySelectionStart;
    
    public NameFocusListener(final int mySelectionStart) {
        this.mySelectionStart = mySelectionStart;
    }
    
    @Override
    public void focusGained(@NotNull final FocusEvent focusEvent) {
        try {
            if (focusEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase$NameFocusListener", "focusGained"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CreateFileDialogBase.this.myNameField.setCaretPosition(this.mySelectionStart);
        CreateFileDialogBase.this.myNameField.moveCaretPosition(CreateFileDialogBase.this.myNameField.getText().length());
    }
    
    @Override
    public void focusLost(@NotNull final FocusEvent focusEvent) {
        try {
            if (focusEvent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase$NameFocusListener", "focusLost"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    public void resetSelectionStart() {
        this.mySelectionStart = 0;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
