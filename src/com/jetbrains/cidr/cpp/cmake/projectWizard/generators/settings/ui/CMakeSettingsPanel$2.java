// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.ui;

import java.awt.event.ItemEvent;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.CMakeProjectGenerator;
import java.awt.event.ItemListener;

class CMakeSettingsPanel$2 implements ItemListener {
    final /* synthetic */ CMakeProjectGenerator val$cMakeProjectGenerator;
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            this.val$cMakeProjectGenerator.setLibraryType((String)itemEvent.getItem());
        }
    }
}