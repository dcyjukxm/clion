// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.util.ArrayList;
import javax.swing.JRadioButton;
import java.util.List;
import javax.swing.JLabel;

private static class EnumOption<T>
{
    private final T[] myValues;
    private final JLabel myLabel;
    private final List<JRadioButton> myButtons;
    
    public EnumOption(final String s, final T[] myValues, final String... array) {
        this.myButtons = new ArrayList<JRadioButton>();
        assert myValues.length == array.length;
        this.myValues = myValues;
        this.myLabel = new JLabel(s + ":");
        final ButtonGroup buttonGroup = new ButtonGroup();
        for (int length = array.length, i = 0; i < length; ++i) {
            final JRadioButton radioButton = new JRadioButton(array[i]);
            buttonGroup.add(radioButton);
            this.myButtons.add(radioButton);
        }
    }
    
    public List<JRadioButton> getButtons() {
        return this.myButtons;
    }
    
    public JLabel getLabel() {
        return this.myLabel;
    }
    
    public T getSelected() {
        for (int i = 0; i < this.myButtons.size(); ++i) {
            if (this.myButtons.get(i).isSelected()) {
                return (T)this.myValues[i];
            }
        }
        return null;
    }
    
    public void selectedValue(final T t) {
        for (int i = 0; i < this.myValues.length; ++i) {
            if (this.myValues[i] == t) {
                this.myButtons.get(i).setSelected(true);
                break;
            }
        }
    }
}
