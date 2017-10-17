// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import java.awt.event.ItemListener;
import javax.swing.JComponent;

public interface OCOption<T, Comp extends JComponent>
{
    Comp createComponent();
    
    T getSelectedValue(final Comp p0);
    
    void selectValue(final Comp p0, final T p1);
    
    void addItemListener(final Comp p0, final ItemListener p1);
}
