// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.jetbrains.cidr.lang.psi.OCFile;
import java.awt.event.ActionEvent;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.project.Project;
import java.awt.event.ActionListener;

static final class OCFileReferenceEditor$1 implements ActionListener {
    final /* synthetic */ Project val$project;
    final /* synthetic */ Ref val$result;
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final OCFileChooserDialog ocFileChooserDialog = new OCFileChooserDialog("Choose Containing Class", this.val$project);
        ocFileChooserDialog.showDialog();
        if (ocFileChooserDialog.isOK()) {
            final OCFile ocFile = ocFileChooserDialog.getSelected();
            OCFileReferenceEditor.access$002((OCFileReferenceEditor)this.val$result.get(), ocFile);
            ((OCFileReferenceEditor)this.val$result.get()).setText(ocFile.getName());
        }
    }
}