// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import com.intellij.psi.PsiElement;
import kotlin.jvm.internal.Intrinsics;
import javax.swing.tree.TreePath;
import java.awt.Insets;
import java.awt.Rectangle;
import org.jetbrains.annotations.NotNull;
import java.awt.Graphics;
import kotlin.Metadata;
import com.intellij.util.ui.tree.WideSelectionTreeUI;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JP\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0014¨\u0006\u0014" }, d2 = { "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewComponent$1", "Lcom/intellij/util/ui/tree/WideSelectionTreeUI;", "(Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewComponent;)V", "paintRow", "", "g", "Ljava/awt/Graphics;", "clipBounds", "Ljava/awt/Rectangle;", "insets", "Ljava/awt/Insets;", "bounds", "path", "Ljavax/swing/tree/TreePath;", "row", "", "isExpanded", "", "hasBeenExpanded", "isLeaf", "cidr-lang" })
public static final class OCStructureViewComponent$1 extends WideSelectionTreeUI {
    final /* synthetic */ OCStructureViewComponent this$0;
    
    protected void paintRow(@NotNull final Graphics graphics, @NotNull final Rectangle rectangle, @NotNull final Insets insets, @NotNull final Rectangle rectangle2, @NotNull final TreePath treePath, final int n, final boolean b, final boolean b2, final boolean b3) {
        Intrinsics.checkParameterIsNotNull((Object)graphics, "g");
        Intrinsics.checkParameterIsNotNull((Object)rectangle, "clipBounds");
        Intrinsics.checkParameterIsNotNull((Object)insets, "insets");
        Intrinsics.checkParameterIsNotNull((Object)rectangle2, "bounds");
        Intrinsics.checkParameterIsNotNull((Object)treePath, "path");
        super.paintRow(graphics, rectangle, insets, rectangle2, treePath, n, b, b2, b3);
        final OCMarkedStructureViewElement value = OCStructureViewComponentKt.getValue(treePath);
        final TreePath previousPath = OCStructureViewComponentKt.previousPath(this.tree, treePath);
        final OCMarkedStructureViewElement ocMarkedStructureViewElement = (previousPath != null) ? OCStructureViewComponentKt.getValue(previousPath) : null;
        final OCMarkedStructureViewElement ocMarkedStructureViewElement2 = value;
        final PsiElement psiElement = (ocMarkedStructureViewElement2 != null) ? ocMarkedStructureViewElement2.getParentMarkElement() : null;
        final OCMarkedStructureViewElement ocMarkedStructureViewElement3 = ocMarkedStructureViewElement;
        if (Intrinsics.areEqual((Object)psiElement, (Object)((ocMarkedStructureViewElement3 != null) ? ocMarkedStructureViewElement3.getParentMarkElement() : null)) ^ true) {
            final OCMarkedStructureViewElement ocMarkedStructureViewElement4 = value;
            if (ocMarkedStructureViewElement4 != null) {
                final OCMark parentMark = ocMarkedStructureViewElement4.getParentMark();
                if (parentMark != null) {
                    final OCMark ocMark = parentMark;
                    final OCStructureViewComponent this$0 = this.this$0;
                    final OCMark ocMark2 = ocMark;
                    Intrinsics.checkExpressionValueIsNotNull((Object)ocMark2, "it");
                    OCStructureViewComponent.access$paintSeparator(this$0, graphics, rectangle, rectangle2, ocMark2);
                }
            }
        }
    }
}