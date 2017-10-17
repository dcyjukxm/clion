// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.intellij.util.Processor;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import com.intellij.ui.ListCellRendererWrapper;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public static class MemberChooserHeaderPanel extends JPanel
{
    private final JComboBox comboBox;
    
    public MemberChooserHeaderPanel(final OCDeclareActionContext.Target[] array, final OCDeclareActionContext.Target selectedItem) {
        super(new BorderLayout());
        this.add(this.comboBox = new JComboBox((E[])array), "Center");
        this.comboBox.setRenderer((ListCellRenderer)new ListCellRendererWrapper<OCDeclareActionContext.Target>() {
            public void customize(final JList list, final OCDeclareActionContext.Target target, final int n, final boolean b, final boolean b2) {
                this.setText(target.getName());
                this.setIcon(target.getIcon());
            }
        });
        this.comboBox.setSelectedItem(selectedItem);
        final JLabel label = new JLabel("Declare in: ");
        label.setDisplayedMnemonic('T');
        label.setLabelFor(this.comboBox);
        this.add(label, "West");
    }
    
    protected void addTargetChangeListener(final Processor<OCDeclareActionContext.Target> processor) {
        this.comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                processor.process((Object)MemberChooserHeaderPanel.this.comboBox.getSelectedItem());
            }
        });
    }
}
