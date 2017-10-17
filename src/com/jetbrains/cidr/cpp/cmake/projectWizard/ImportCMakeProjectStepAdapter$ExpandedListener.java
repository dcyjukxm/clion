// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import com.intellij.ui.SplitterWithSecondHideable;

private static class ExpandedListener implements SplitterWithSecondHideable.OnOffListener<Integer>
{
    private ExpandedPanel myExpandedPanel;
    private float myPreviousProportion;
    
    public void setExpandedPanel(final ExpandedPanel myExpandedPanel) {
        this.myExpandedPanel = myExpandedPanel;
    }
    
    @Override
    public void on(final Integer n) {
        this.myExpandedPanel.getComponent().setProportion((this.myPreviousProportion > 0.0f) ? this.myPreviousProportion : this.myExpandedPanel.getSplitterInitialProportion());
    }
    
    @Override
    public void off(final Integer n) {
        this.myPreviousProportion = this.myExpandedPanel.getUsedProportion();
    }
}
