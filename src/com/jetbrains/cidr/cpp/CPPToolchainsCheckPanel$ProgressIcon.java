// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.icons.AllIcons;
import java.awt.Graphics;
import java.awt.Component;
import java.util.Iterator;
import com.intellij.openapi.util.Disposer;
import java.util.LinkedHashSet;
import com.intellij.openapi.Disposable;
import com.intellij.ui.SimpleColoredComponent;
import java.util.Set;
import javax.swing.Icon;
import com.intellij.util.ui.Animator;

private static class ProgressIcon extends Animator implements Icon
{
    private static final Icon[] ICONS;
    private final Set<SimpleColoredComponent> myComponents;
    private int myIndex;
    
    public ProgressIcon(final Disposable disposable) {
        super((String)null, ProgressIcon.ICONS.length, 800, true);
        this.myComponents = new LinkedHashSet<SimpleColoredComponent>();
        Disposer.register(disposable, (Disposable)this);
    }
    
    public void addComponent(final SimpleColoredComponent simpleColoredComponent) {
        this.myComponents.add(simpleColoredComponent);
        if (this.myComponents.size() == 1) {
            this.resume();
        }
    }
    
    public void removeComponent(final SimpleColoredComponent simpleColoredComponent) {
        this.myComponents.remove(simpleColoredComponent);
        if (this.myComponents.isEmpty()) {
            this.suspend();
        }
    }
    
    public void paintNow(final int n, final int n2, final int n3) {
        this.myIndex = ((n < 0) ? 0 : ((n >= ProgressIcon.ICONS.length) ? (ProgressIcon.ICONS.length - 1) : n));
        final int iconWidth = this.getIconWidth();
        final int iconHeight = this.getIconHeight();
        for (final SimpleColoredComponent simpleColoredComponent : this.myComponents) {
            simpleColoredComponent.paintImmediately(simpleColoredComponent.getIpad().left, 0, iconWidth, iconHeight);
        }
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        ProgressIcon.ICONS[this.myIndex].paintIcon(component, graphics, n, n2);
    }
    
    public int getIconWidth() {
        return AllIcons.Process.Step_passive.getIconWidth();
    }
    
    public int getIconHeight() {
        return AllIcons.Process.Step_passive.getIconHeight();
    }
    
    static {
        ICONS = new Icon[] { AllIcons.Process.Step_1, AllIcons.Process.Step_2, AllIcons.Process.Step_3, AllIcons.Process.Step_4, AllIcons.Process.Step_5, AllIcons.Process.Step_6, AllIcons.Process.Step_7, AllIcons.Process.Step_8, AllIcons.Process.Step_9, AllIcons.Process.Step_10, AllIcons.Process.Step_11, AllIcons.Process.Step_12 };
    }
}
