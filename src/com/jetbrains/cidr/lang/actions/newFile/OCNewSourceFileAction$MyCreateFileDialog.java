// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import javax.swing.JComponent;
import com.intellij.internal.statistic.UsageTrigger;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import com.intellij.util.ui.DialogUtil;
import com.intellij.ui.NonFocusableCheckBox;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.util.ui.FormBuilder;

private class MyCreateFileDialog extends CreateFileDialog
{
    private boolean myShouldAddHeader;
    
    public MyCreateFileDialog(final String s) {
        super(s);
    }
    
    @Override
    public void fillGenericControls(final FormBuilder formBuilder) {
        super.fillGenericControls(formBuilder);
        final NonFocusableCheckBox nonFocusableCheckBox = new NonFocusableCheckBox(OCBundle.message("create.checkbox.source.associated.header", new Object[0]));
        DialogUtil.registerMnemonic((AbstractButton)nonFocusableCheckBox, '&');
        ((AbstractButton)nonFocusableCheckBox).addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                MyCreateFileDialog.this.myShouldAddHeader = ((AbstractButton)nonFocusableCheckBox).isSelected();
                if (MyCreateFileDialog.this.myKindCombo != null) {
                    MyCreateFileDialog.this.reloadExtensions();
                }
                MyCreateFileDialog.this.validateOkAction();
            }
        });
        OCNewSourceFileAction.this.addAuxAction(p0 -> {
            try {
                UsageTrigger.trigger("cidr.newFile.source");
                if (this.myShouldAddHeader) {
                    UsageTrigger.trigger("cidr.newFile.source.withHeader");
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            return;
        });
        formBuilder.addComponent((JComponent)nonFocusableCheckBox);
    }
    
    @NotNull
    @Override
    protected String getPresentableName(@NotNull final OCCodeStyleSettings.FileExtensionPair fileExtensionPair) {
        try {
            if (fileExtensionPair == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pair", "com/jetbrains/cidr/lang/actions/newFile/OCNewSourceFileAction$MyCreateFileDialog", "getPresentableName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        String s = null;
        Label_0085: {
            try {
                if (this.myShouldAddHeader) {
                    final String s2;
                    s = (s2 = super.getPresentableName(fileExtensionPair));
                    break Label_0085;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            String s2;
            s = (s2 = "." + fileExtensionPair.mySourceExt);
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewSourceFileAction$MyCreateFileDialog", "getPresentableName"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return s;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
