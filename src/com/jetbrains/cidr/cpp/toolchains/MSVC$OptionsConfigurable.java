// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import com.jetbrains.cidr.toolchains.CidrToolSet;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.ui.GridBag;
import javax.swing.JPanel;

public interface OptionsConfigurable
{
    void createComponents(final JPanel p0, @NotNull final GridBag p1);
    
    boolean isModified(@NotNull final List<Option> p0);
    
    @NotNull
    List<Option> apply();
    
    void reset(@NotNull final List<Option> p0);
    
    default void disposeUIResources() {
    }
}
