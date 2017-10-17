// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.intellij.codeInsight.generation.MemberChooserObject;
import javax.swing.tree.MutableTreeNode;
import com.intellij.codeInsight.generation.ClassMember;
import com.intellij.openapi.util.Ref;
import com.intellij.ide.util.MemberChooser;
import com.intellij.ui.CheckedTreeNode;

private class MyMemberNode extends CheckedTreeNode implements MemberNode
{
    private MemberNodeImpl myDelegate;
    
    public MyMemberNode(final Ref<Integer> ref, final OCMemberChooserObject ocMemberChooserObject, final ParentNode parentNode) {
        super((Object)null);
        this.myDelegate = new MemberNodeImpl(null, (ClassMember)ocMemberChooserObject, ref);
        super.setChecked(false);
        if (parentNode != null) {
            parentNode.add(this);
        }
    }
    
    public OCMemberChooserObject getDelegate() {
        return (OCMemberChooserObject)this.myDelegate.getDelegate();
    }
    
    public int getOrder() {
        return this.myDelegate.getOrder();
    }
    
    public void setChecked(final boolean checked) {
        if (OCCheckboxMemberChooser.this.isMemberEnabled((OCMemberChooserObject)this.myDelegate.getDelegate())) {
            super.setChecked(checked);
            OCCheckboxMemberChooser.this.refreshChosenMembers();
        }
    }
}
