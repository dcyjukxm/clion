// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.jetbrains.cidr.lang.settings.OCNewClassSettings;
import javax.swing.JComponent;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.util.ui.FormBuilder;
import com.jetbrains.cidr.lang.OCBundle;
import javax.swing.JComboBox;

protected class CreateClassDialog extends CreateFileDialogBase
{
    private JComboBox myLanguageCombo;
    
    public CreateClassDialog(final String s) {
        super(OCBundle.message("create.class.dialog.title", new Object[0]), s, null);
    }
    
    public CreateClassDialog(final String s, final String s2, final String s3) {
        super(s, s2, s3, null);
    }
    
    @Override
    public void fillGenericControls(final FormBuilder formBuilder) {
        super.fillGenericControls(formBuilder);
        this.myLanguageCombo = (JComboBox)new ComboBox((Object[])new String[] { "Objective-C (.m)", "Objective-C++ (.mm)" });
        formBuilder.addLabeledComponent(OCBundle.message("create.field.language", new Object[0]), (JComponent)this.myLanguageCombo);
        this.myLanguageCombo.setSelectedIndex(OCNewClassSettings.getInstance().myLanguageIndex);
        this.myLanguageCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                OCNewClassSettings.getInstance().myLanguageIndex = CreateClassDialog.this.myLanguageCombo.getSelectedIndex();
                CreateClassDialog.this.validateOkAction();
            }
        });
    }
    
    protected String getImplementationExtension() {
        return (this.myLanguageCombo.getSelectedIndex() == 0) ? ".m" : ".mm";
    }
}
