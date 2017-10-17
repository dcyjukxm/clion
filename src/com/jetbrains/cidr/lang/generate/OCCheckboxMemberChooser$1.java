// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.intellij.ui.SimpleColoredComponent;
import com.intellij.ide.util.MemberChooser;
import javax.swing.JTree;
import com.intellij.ui.CheckboxTree;

class OCCheckboxMemberChooser$1 extends CheckboxTree.CheckboxTreeCellRenderer {
    public void customizeRenderer(final JTree tree, final Object o, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
        if (o instanceof ElementNode) {
            final boolean memberEnabled = OCCheckboxMemberChooser.this.isMemberEnabled((OCMemberChooserObject)((ElementNode)o).getDelegate());
            if (o instanceof MyMemberNode) {
                this.myCheckbox.setEnabled(memberEnabled);
            }
            this.getTextRenderer().setEnabled(memberEnabled);
            ((ElementNode)o).getDelegate().renderTreeNode((SimpleColoredComponent)this.getTextRenderer(), tree);
        }
    }
}