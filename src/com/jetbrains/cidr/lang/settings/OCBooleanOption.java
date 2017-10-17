// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import javax.swing.JComponent;
import com.jetbrains.cidr.lang.OCBundle;
import java.awt.event.ItemListener;
import com.intellij.ui.NonFocusableCheckBox;
import javax.swing.JCheckBox;

public class OCBooleanOption implements OCOption<Boolean, JCheckBox>
{
    private String myCaption;
    
    public OCBooleanOption(final String myCaption) {
        this.myCaption = myCaption;
    }
    
    @Override
    public JCheckBox createComponent() {
        final NonFocusableCheckBox nonFocusableCheckBox = new NonFocusableCheckBox(this.myCaption);
        if (this.myCaption.startsWith("Show ")) {
            nonFocusableCheckBox.setMnemonic(this.myCaption.charAt("Show ".length()));
        }
        else {
            nonFocusableCheckBox.setMnemonic(this.myCaption.charAt(0));
        }
        return (JCheckBox)nonFocusableCheckBox;
    }
    
    @Override
    public Boolean getSelectedValue(final JCheckBox checkBox) {
        return checkBox.isSelected();
    }
    
    @Override
    public void selectValue(final JCheckBox checkBox, final Boolean b) {
        checkBox.setSelected(b);
    }
    
    @Override
    public void addItemListener(final JCheckBox checkBox, final ItemListener itemListener) {
        checkBox.addItemListener(itemListener);
    }
    
    public static void setStates(final JCheckBox checkBox, final boolean b, final String s, final boolean b2, final String s2) {
        checkBox.setEnabled(!b && !b2);
        if (b) {
            checkBox.setSelected(false);
            checkBox.setText(OCBundle.message(s, new Object[0]));
        }
        else if (b2) {
            checkBox.setSelected(true);
            checkBox.setText(OCBundle.message(s2, new Object[0]));
        }
    }
}
