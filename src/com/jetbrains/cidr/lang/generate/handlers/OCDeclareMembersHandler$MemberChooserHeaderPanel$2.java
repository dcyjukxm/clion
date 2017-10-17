// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import java.awt.event.ItemEvent;
import com.intellij.util.Processor;
import java.awt.event.ItemListener;

class OCDeclareMembersHandler$MemberChooserHeaderPanel$2 implements ItemListener {
    final /* synthetic */ Processor val$listener;
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.val$listener.process((Object)MemberChooserHeaderPanel.access$000(MemberChooserHeaderPanel.this).getSelectedItem());
    }
}