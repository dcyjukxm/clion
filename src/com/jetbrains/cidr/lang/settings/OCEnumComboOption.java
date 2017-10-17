// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import javax.swing.JComponent;
import java.awt.event.ItemListener;
import com.intellij.util.ArrayUtil;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class OCEnumComboOption<T> implements OCOption<T, JPanel>
{
    private T[] myValues;
    private String[] myChoices;
    private String myGroupName;
    
    public OCEnumComboOption(final String myGroupName, final T[] myValues, final String... myChoices) {
        this.myGroupName = myGroupName;
        this.myChoices = myChoices;
        this.myValues = myValues;
    }
    
    @Override
    public JPanel createComponent() {
        final JPanel panel = new JPanel(new FlowLayout(0, 0, 0));
        panel.add(Box.createHorizontalStrut(7));
        panel.add(new JLabel(this.myGroupName + ":"));
        panel.add(new JComboBox<Object>((Object[])this.myChoices));
        return panel;
    }
    
    @Override
    public T getSelectedValue(final JPanel panel) {
        return (T)this.myValues[((JComboBox)panel.getComponent(2)).getSelectedIndex()];
    }
    
    @Override
    public void selectValue(final JPanel panel, final T t) {
        ((JComboBox)panel.getComponent(2)).setSelectedIndex(ArrayUtil.indexOf(this.myValues, (Object)t));
    }
    
    @Override
    public void addItemListener(final JPanel panel, final ItemListener itemListener) {
        ((JComboBox)panel.getComponent(2)).addItemListener(itemListener);
    }
}
