// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import javax.swing.JPanel;
import javax.swing.JComponent;
import com.intellij.openapi.vcs.changes.RefreshablePanel;
import com.intellij.ui.SplitterWithSecondHideable;

private static class ExpandedPanel extends SplitterWithSecondHideable
{
    private final RefreshablePanel myRefreshablePanel;
    
    public ExpandedPanel(final String s, final JComponent component, final JPanel panel) {
        this(s, component, panel, new ExpandedListener());
    }
    
    private ExpandedPanel(final String s, final JComponent component, final JPanel panel, final ExpandedListener expandedListener) {
        super(true, s, component, expandedListener);
        this.myRefreshablePanel = new RefreshablePanel() {
            @Override
            public void refresh() {
            }
            
            @Override
            public JPanel getPanel() {
                return panel;
            }
        };
        this.getComponent().setResizeEnabled(false);
        expandedListener.setExpandedPanel(this);
    }
    
    @Override
    protected RefreshablePanel createDetails() {
        return this.myRefreshablePanel;
    }
    
    @Override
    protected float getSplitterInitialProportion() {
        return 0.7f;
    }
}
