// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.intellij.ui.content.Content;
import javax.swing.JLabel;
import javax.swing.JComponent;
import com.intellij.psi.PsiElement;
import com.intellij.ide.hierarchy.HierarchyBrowser;

class OCTypeHierarchyProvider$1 implements HierarchyBrowser {
    final /* synthetic */ PsiElement val$target;
    
    public JComponent getComponent() {
        return new JLabel("Not implemented. Target: " + this.val$target);
    }
    
    public void setContent(final Content content) {
    }
}