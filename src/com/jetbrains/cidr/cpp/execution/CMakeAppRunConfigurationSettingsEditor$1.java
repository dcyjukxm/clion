// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import org.jetbrains.annotations.Nullable;
import javax.swing.Icon;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import java.util.List;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;

class CMakeAppRunConfigurationSettingsEditor$1 extends CMakeTarget {
    @Override
    public boolean isExecutable() {
        return false;
    }
    
    @Nullable
    @Override
    public Icon getIcon() {
        return CMakeTarget.META_TARGET_ICON;
    }
}