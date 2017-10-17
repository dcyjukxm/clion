// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Trinity;
import javax.swing.event.ChangeEvent;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import javax.swing.event.ChangeListener;

class OCTargetSymbolPanel$2 implements ChangeListener {
    final /* synthetic */ OCClassSymbol val$parent;
    final /* synthetic */ OCGeneratedInfo val$info;
    
    @Override
    public void stateChanged(final ChangeEvent changeEvent) {
        final Object selectedItem = OCTargetSymbolPanel.access$000(OCTargetSymbolPanel.this).getSelectedItem();
        if (selectedItem instanceof Trinity) {
            final TargetSymbolsMode targetSymbolsMode = (TargetSymbolsMode)((Trinity)selectedItem).getSecond();
            this.val$parent.getProject().putUserData((Key)OCTargetSymbolPanel.VISIBILITY_KEY, (Object)targetSymbolsMode);
            this.val$info.setTargetSymbolsMode(targetSymbolsMode);
        }
        else if (selectedItem.toString().startsWith("New category")) {
            OCTargetSymbolPanel.this.createNewCategory(this.val$info);
        }
        else if (selectedItem instanceof OCClassSymbol) {
            this.val$info.setMethodParent((OCSymbol)selectedItem);
            this.val$info.setTargetSymbolsMode(TargetSymbolsMode.INTERFACE);
        }
    }
}