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
import com.intellij.util.ui.FormBuilder;
import com.jetbrains.cidr.lang.OCBundle;

public class MyCreateClassDialog extends CreateFileDialog
{
    boolean myHeaderOnly;
    
    public MyCreateClassDialog(final String s) {
        super(OCBundle.message("create.cpp.class.dialog.title", new Object[0]), s);
    }
    
    @Override
    public void fillGenericControls(final FormBuilder formBuilder) {
        super.fillGenericControls(formBuilder);
        final NonFocusableCheckBox nonFocusableCheckBox = new NonFocusableCheckBox(OCBundle.message("create.checkbox.cpp.class.header", new Object[0]));
        DialogUtil.registerMnemonic((AbstractButton)nonFocusableCheckBox, '&');
        ((AbstractButton)nonFocusableCheckBox).addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                MyCreateClassDialog.this.myHeaderOnly = ((AbstractButton)nonFocusableCheckBox).isSelected();
                if (MyCreateClassDialog.this.myKindCombo != null) {
                    MyCreateClassDialog.this.reloadExtensions();
                }
                MyCreateClassDialog.this.validateOkAction();
            }
        });
        OCNewCppClassAction.this.addAuxAction(p0 -> {
            try {
                UsageTrigger.trigger("cidr.newFile.cppClass");
                if (this.myHeaderOnly) {
                    UsageTrigger.trigger("cidr.newFile.cppClass.headerOnly");
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pair", "com/jetbrains/cidr/lang/actions/newFile/OCNewCppClassAction$MyCreateClassDialog", "getPresentableName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        String s = null;
        Label_0085: {
            try {
                if (this.myHeaderOnly) {
                    final String s2;
                    s = (s2 = "." + fileExtensionPair.myHeaderExt);
                    break Label_0085;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            String s2;
            s = (s2 = super.getPresentableName(fileExtensionPair));
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewCppClassAction$MyCreateClassDialog", "getPresentableName"));
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
