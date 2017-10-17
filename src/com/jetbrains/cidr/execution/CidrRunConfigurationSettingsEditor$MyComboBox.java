// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.openapi.ui.ComboBox;

protected static class MyComboBox extends ComboBox<String>
{
    public MyComboBox(final int n) {
        super(n);
    }
    
    public void fireSelectedItemChanged() {
        this.selectedItemChanged();
    }
}
