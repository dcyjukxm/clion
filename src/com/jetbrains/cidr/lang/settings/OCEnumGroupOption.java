// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import javax.swing.JComponent;
import java.awt.event.ItemListener;
import com.intellij.openapi.util.Ref;
import com.intellij.util.Processor;
import javax.swing.Box;
import javax.swing.JLabel;
import com.intellij.openapi.ui.VerticalFlowLayout;
import javax.swing.border.Border;
import com.intellij.ui.IdeBorderFactory;
import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.JRadioButton;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public class OCEnumGroupOption<T> implements OCOption<T, JPanel>
{
    private T[] myValues;
    private String[] myChoices;
    private String myGroupName;
    private Presentation myPresentation;
    
    public OCEnumGroupOption(final String myGroupName, final Presentation myPresentation, final T[] myValues, final String... myChoices) {
        this.myGroupName = myGroupName;
        this.myPresentation = myPresentation;
        this.myChoices = myChoices;
        this.myValues = myValues;
    }
    
    @Override
    public JPanel createComponent() {
        return this.createComponent(this.myGroupName != null && this.myGroupName.length() > 0);
    }
    
    public JPanel createComponent(final boolean b) {
        final ButtonGroup buttonGroup = new ButtonGroup();
        switch (this.myPresentation) {
            case Section: {
                final JPanel panel = new JPanel(new FlowLayout(0, 0, 0));
                final String[] myChoices = this.myChoices;
                for (int length = myChoices.length, i = 0; i < length; ++i) {
                    final JRadioButton radioButton = new JRadioButton(myChoices[i]);
                    buttonGroup.add(radioButton);
                    panel.add(radioButton);
                }
                if (b) {
                    panel.setBorder((Border)IdeBorderFactory.createTitledBorder(this.myGroupName, true));
                }
                return panel;
            }
            case Line: {
                final JPanel panel2 = new JPanel(new FlowLayout(0, 6, 10));
                final JPanel panel3 = new JPanel((LayoutManager)new VerticalFlowLayout(2, 0, 0, true, false));
                final JLabel label = new JLabel(this.myGroupName + ":\t");
                panel3.add(Box.createVerticalStrut(2));
                panel3.add(label);
                panel2.add(panel3);
                final String[] myChoices2 = this.myChoices;
                for (int length2 = myChoices2.length, j = 0; j < length2; ++j) {
                    final JRadioButton radioButton2 = new JRadioButton(myChoices2[j]);
                    buttonGroup.add(radioButton2);
                    final JPanel panel4 = new JPanel((LayoutManager)new VerticalFlowLayout(2, 0, 0, true, false));
                    panel4.add(radioButton2);
                    panel2.add(panel4);
                }
                return panel2;
            }
            default: {
                return null;
            }
        }
    }
    
    private static boolean a(final JPanel panel, final Processor<JRadioButton> processor) {
        for (final Component component : panel.getComponents()) {
            if ((component instanceof JPanel && !a((JPanel)component, processor)) || (component instanceof JRadioButton && !processor.process((Object)component))) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public T getSelectedValue(final JPanel panel) {
        final Ref create = Ref.create((Object)0);
        a(panel, (Processor<JRadioButton>)(radioButton -> {
            if (radioButton.isSelected()) {
                return false;
            }
            create.set((Object)((int)create.get() + 1));
            return true;
        }));
        return (T)this.myValues[(int)create.get()];
    }
    
    @Override
    public void selectValue(final JPanel panel, final T t) {
        a(panel, (Processor<JRadioButton>)(radioButton -> {
            if (this.myValues[(int)ref.get()] == t) {
                radioButton.setSelected(true);
                return false;
            }
            ref.set((Object)((int)ref.get() + 1));
            return true;
        }));
    }
    
    @Override
    public void addItemListener(final JPanel panel, final ItemListener itemListener) {
        a(panel, (Processor<JRadioButton>)(radioButton -> {
            radioButton.addItemListener(itemListener);
            return true;
        }));
    }
    
    public enum Presentation
    {
        Section, 
        Line;
    }
}
