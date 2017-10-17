// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.CheckedTreeNode;
import javax.swing.JTree;
import com.intellij.ui.CheckboxTree;

private class MyTreeCellRenderer extends CheckboxTree.CheckboxTreeCellRenderer
{
    private final boolean myRootFullName;
    
    public MyTreeCellRenderer(final boolean myRootFullName) {
        super(true, false);
        this.myRootFullName = myRootFullName;
    }
    
    public void customizeRenderer(final JTree tree, final Object o, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
        if (o instanceof CheckedTreeNode) {
            final Object userObject = ((CheckedTreeNode)o).getUserObject();
            if (userObject instanceof VirtualFile) {
                final VirtualFile virtualFile = (VirtualFile)userObject;
                final ColoredTreeCellRenderer textRenderer = this.getTextRenderer();
                textRenderer.append((this.myRootFullName && ImportCMakeProjectStepAdapter.access$100(ImportCMakeProjectStepAdapter.this).equals(virtualFile)) ? virtualFile.getPresentableUrl() : virtualFile.getPresentableName());
                textRenderer.setIcon(ImportCMakeProjectStepAdapter.access$900(ImportCMakeProjectStepAdapter.this).getIcon(virtualFile));
            }
        }
    }
}
