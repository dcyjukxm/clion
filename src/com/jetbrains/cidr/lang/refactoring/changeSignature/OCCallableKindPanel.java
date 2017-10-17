// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.intellij.util.EventDispatcher;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import javax.swing.AbstractButton;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import com.intellij.ui.IdeBorderFactory;
import java.util.List;
import javax.swing.JRadioButton;
import com.intellij.refactoring.ui.VisibilityPanelBase;

public class OCCallableKindPanel extends VisibilityPanelBase<String>
{
    private final JRadioButton myRbMethod;
    private final JRadioButton myRbFunction;
    private final JRadioButton myRbBlock;
    private List<JRadioButton> myButtons;
    
    public OCCallableKindPanel() {
        this.setBorder((Border)IdeBorderFactory.createTitledBorder("Callable Type", true));
        this.setLayout(new BoxLayout(this, 1));
        final ButtonGroup buttonGroup = new ButtonGroup();
        final ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == 1) {
                    ((ChangeListener)OCCallableKindPanel.this.myEventDispatcher.getMulticaster()).stateChanged(new ChangeEvent(this));
                }
            }
        };
        (this.myRbMethod = new JRadioButton()).setText(OCCallableKind.METHOD.toString());
        (this.myRbFunction = new JRadioButton()).setText(OCCallableKind.FUNCTION.toString());
        (this.myRbBlock = new JRadioButton()).setText(OCCallableKind.BLOCK.toString());
        this.myButtons = Arrays.asList(this.myRbMethod, this.myRbFunction, this.myRbBlock);
        for (final JRadioButton radioButton : this.myButtons) {
            radioButton.addItemListener(itemListener);
            radioButton.setFocusable(false);
            this.add(radioButton);
            buttonGroup.add(radioButton);
        }
    }
    
    @Override
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        final Iterator<JRadioButton> iterator = this.myButtons.iterator();
        while (iterator.hasNext()) {
            iterator.next().setEnabled(b);
        }
    }
    
    @Nullable
    @Override
    public String getVisibility() {
        for (final JRadioButton radioButton : this.myButtons) {
            if (radioButton.isSelected()) {
                return radioButton.getText();
            }
        }
        return null;
    }
    
    @Override
    public void setVisibility(final String s) {
        for (final JRadioButton radioButton : this.myButtons) {
            if (radioButton.getText().equals(s)) {
                radioButton.setSelected(true);
                return;
            }
        }
        assert false : "Unknown callable kind: " + s;
    }
}
