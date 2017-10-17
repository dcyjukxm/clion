// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import javax.swing.JList;
import com.intellij.ui.ListCellRendererWrapper;

class OCDeclareMembersHandler$MemberChooserHeaderPanel$1 extends ListCellRendererWrapper<OCDeclareActionContext.Target> {
    public void customize(final JList list, final OCDeclareActionContext.Target target, final int n, final boolean b, final boolean b2) {
        this.setText(target.getName());
        this.setIcon(target.getIcon());
    }
}