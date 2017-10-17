// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import com.intellij.openapi.ui.NamedConfigurable;
import com.intellij.openapi.ui.MasterDetailsComponent;
import com.intellij.openapi.actionSystem.AnActionEvent;
import javax.swing.JComponent;
import com.intellij.openapi.actionSystem.CommonShortcuts;
import com.intellij.util.IconUtil;
import com.intellij.ide.IdeBundle;
import com.intellij.openapi.actionSystem.AnAction;

private class MyAddAction extends AnAction
{
    public MyAddAction() {
        super(IdeBundle.message("add.scope.popup.title", new Object[0]), (String)null, IconUtil.getAddIcon());
        this.registerCustomShortcutSet(CommonShortcuts.INSERT, (JComponent)MyMasterDetailsComponent.access$000(MyMasterDetailsComponent.this));
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        final MasterDetailsComponent.MyNode myNode = new MasterDetailsComponent.MyNode((NamedConfigurable)new MyFileExtensionPairConfigurable());
        MyMasterDetailsComponent.access$100(MyMasterDetailsComponent.this).add((MutableTreeNode)myNode);
        ((DefaultTreeModel)MyMasterDetailsComponent.access$300(MyMasterDetailsComponent.this).getModel()).reload((TreeNode)MyMasterDetailsComponent.access$200(MyMasterDetailsComponent.this));
        MyMasterDetailsComponent.this.selectNodeInTree((DefaultMutableTreeNode)myNode);
    }
}
