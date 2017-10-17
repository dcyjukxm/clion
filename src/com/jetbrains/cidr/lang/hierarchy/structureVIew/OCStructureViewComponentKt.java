// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import javax.swing.JTree;
import org.jetbrains.annotations.Nullable;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import javax.swing.tree.TreePath;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 2, d1 = { "\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u0004*\u00020\b2\u0006\u0010\t\u001a\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000\"\u0017\u0010\u0002\u001a\u0004\u0018\u00010\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n" }, d2 = { "SEPARATOR_INDENT", "", "value", "Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMarkedStructureViewElement;", "Ljavax/swing/tree/TreePath;", "getValue", "(Ljavax/swing/tree/TreePath;)Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMarkedStructureViewElement;", "previousPath", "Ljavax/swing/JTree;", "path", "cidr-lang" })
public final class OCStructureViewComponentKt
{
    private static final int SEPARATOR_INDENT = 20;
    
    @Nullable
    public static final OCMarkedStructureViewElement getValue(@NotNull final TreePath treePath) {
        Intrinsics.checkParameterIsNotNull((Object)treePath, "$receiver");
        Object lastPathComponent;
        if (!((lastPathComponent = treePath.getLastPathComponent()) instanceof DefaultMutableTreeNode)) {
            lastPathComponent = null;
        }
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)lastPathComponent;
        Object o = (defaultMutableTreeNode != null) ? defaultMutableTreeNode.getUserObject() : null;
        if (!(o instanceof AbstractTreeNode)) {
            o = null;
        }
        final AbstractTreeNode abstractTreeNode = (AbstractTreeNode)o;
        Object o2 = (abstractTreeNode != null) ? abstractTreeNode.getValue() : null;
        if (!(o2 instanceof OCMarkedStructureViewElement)) {
            o2 = null;
        }
        return (OCMarkedStructureViewElement)o2;
    }
    
    @Nullable
    public static final TreePath previousPath(@NotNull final JTree tree, @NotNull final TreePath treePath) {
        Intrinsics.checkParameterIsNotNull((Object)tree, "$receiver");
        Intrinsics.checkParameterIsNotNull((Object)treePath, "path");
        final int rowForPath = tree.getRowForPath(treePath);
        return (rowForPath > 0) ? tree.getPathForRow(rowForPath - 1) : null;
    }
}
