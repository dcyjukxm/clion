// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.openapi.ui.TextComponentAccessor;
import java.io.File;
import java.awt.Component;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import com.intellij.util.Function;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import java.awt.event.ActionListener;

static final class CPPToolchainsPanel$5 implements ActionListener {
    final /* synthetic */ TextFieldWithBrowseButton val$field;
    final /* synthetic */ boolean val$executable;
    final /* synthetic */ Function val$isAppBundle;
    final /* synthetic */ Function val$getFileFromAppBundle;
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final JFileChooser fileChooser = new JFileChooser(this.val$field.getText());
        fileChooser.setFileSelectionMode(this.val$executable ? 0 : 2);
        fileChooser.setFileHidingEnabled(!CPPToolchainsPanel.access$1200());
        if (fileChooser.showOpenDialog((Component)this.val$field) == 0) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                if (this.val$isAppBundle.fun((Object)selectedFile)) {
                    selectedFile = (File)this.val$getFileFromAppBundle.fun((Object)selectedFile);
                }
                TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT.setText((Component)this.val$field.getChildComponent(), selectedFile.getAbsolutePath());
            }
        }
    }
}