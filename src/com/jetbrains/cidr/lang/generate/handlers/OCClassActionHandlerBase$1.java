// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.util.Collection;
import java.awt.event.ItemEvent;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.settings.OCOption;
import java.util.List;
import com.jetbrains.cidr.lang.generate.OCMemberChooser;
import java.awt.event.ItemListener;

class OCClassActionHandlerBase$1 implements ItemListener {
    final /* synthetic */ OCMemberChooser val$chooser;
    final /* synthetic */ List val$candidates;
    final /* synthetic */ OCOption val$option;
    final /* synthetic */ OCActionContext val$actionContext;
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        OCClassActionHandlerBase.this.optionValueChanged(this.val$chooser, this.val$candidates, this.val$option, this.val$actionContext);
    }
}