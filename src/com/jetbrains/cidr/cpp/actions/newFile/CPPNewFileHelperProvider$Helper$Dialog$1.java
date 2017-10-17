// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import org.jetbrains.annotations.Nullable;
import javax.swing.DefaultListModel;
import com.intellij.ui.CheckBoxList;

class CPPNewFileHelperProvider$Helper$Dialog$1 extends CheckBoxList<NewFileTarget> {
    @Nullable
    protected String getSecondaryText(final int n) {
        final NewFileTarget newFileTarget = (NewFileTarget)this.getItemAt(n);
        return (newFileTarget == null) ? "" : newFileTarget.getAdditionalInfo();
    }
}