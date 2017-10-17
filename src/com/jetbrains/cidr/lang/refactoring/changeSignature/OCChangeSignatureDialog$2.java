// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.intellij.util.ArrayUtil;
import javax.swing.event.ChangeEvent;
import com.intellij.refactoring.ui.ComboBoxVisibilityPanel;
import javax.swing.event.ChangeListener;

class OCChangeSignatureDialog$2 implements ChangeListener {
    final /* synthetic */ ComboBoxVisibilityPanel val$panel;
    final /* synthetic */ Object[] val$places;
    
    @Override
    public void stateChanged(final ChangeEvent changeEvent) {
        OCChangeSignatureDialog.access$200(OCChangeSignatureDialog.this).getGeneratedInfo().setTargetSymbolsMode("below".equals(this.val$panel.getVisibility()) ? OCTargetSymbolPanel.TargetSymbolsMode.INTERFACE : OCTargetSymbolPanel.TargetSymbolsMode.IMPLEMENTATION);
        OCChangeSignatureDialog.access$400(OCChangeSignatureDialog.this).putUserData(OCChangeSignatureDialog.access$300(), (Object)ArrayUtil.find(this.val$places, this.val$panel.getVisibility()));
    }
}