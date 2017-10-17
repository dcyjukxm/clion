// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import com.intellij.util.ui.tree.TreeUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import javax.swing.Icon;
import com.intellij.openapi.actionSystem.AnAction;

private class MyMoveAction extends AnAction
{
    private final int myDirection;
    
    protected MyMoveAction(final String s, final Icon icon, final int myDirection) {
        super(s, s, icon);
        this.myDirection = myDirection;
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        TreeUtil.moveSelectedRow((JTree)MyMasterDetailsComponent.access$400(MyMasterDetailsComponent.this), this.myDirection);
    }
    
    public void update(final AnActionEvent anActionEvent) {
        boolean enabled = false;
        final TreePath selectionPath = MyMasterDetailsComponent.access$500(MyMasterDetailsComponent.this).getSelectionPath();
        if (selectionPath != null) {
            final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)selectionPath.getLastPathComponent();
            enabled = (((this.myDirection < 0) ? defaultMutableTreeNode.getPreviousSibling() : defaultMutableTreeNode.getNextSibling()) != null);
        }
        anActionEvent.getPresentation().setEnabled(enabled);
    }
}
