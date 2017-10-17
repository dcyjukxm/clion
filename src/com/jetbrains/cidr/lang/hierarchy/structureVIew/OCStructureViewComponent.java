// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import javax.swing.JTree;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import com.intellij.ui.Gray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.psi.PsiElement;
import javax.swing.tree.TreePath;
import java.awt.Insets;
import com.intellij.util.ui.tree.WideSelectionTreeUI;
import javax.swing.plaf.TreeUI;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.project.Project;
import com.intellij.ide.structureView.StructureViewModel;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.fileEditor.FileEditor;
import javax.swing.border.MatteBorder;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import org.jetbrains.annotations.NotNull;
import com.intellij.ui.JBColor;
import kotlin.Metadata;
import com.intellij.ide.structureView.newStructureView.StructureViewComponent;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ(\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¨\u0006\u0015" }, d2 = { "Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewComponent;", "Lcom/intellij/ide/structureView/newStructureView/StructureViewComponent;", "editor", "Lcom/intellij/openapi/fileEditor/FileEditor;", "structureViewModel", "Lcom/intellij/ide/structureView/StructureViewModel;", "project", "Lcom/intellij/openapi/project/Project;", "showRootNode", "", "(Lcom/intellij/openapi/fileEditor/FileEditor;Lcom/intellij/ide/structureView/StructureViewModel;Lcom/intellij/openapi/project/Project;Z)V", "paintSeparator", "", "g", "Ljava/awt/Graphics;", "clipBounds", "Ljava/awt/Rectangle;", "bounds", "mark", "Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark;", "Companion", "cidr-lang" })
public final class OCStructureViewComponent extends StructureViewComponent
{
    @NotNull
    private static final JBColor COLOR;
    public static final Companion Companion;
    
    private final void a(final Graphics graphics, final Rectangle rectangle, final Rectangle rectangle2, final OCMark ocMark) {
        final MatteBorder matteBorder = BorderFactory.createMatteBorder(ocMark.isSeparatorBefore() ? 1 : 0, 0, ocMark.isSeparatorAfter() ? 1 : 0, 0, (Color)OCStructureViewComponent.Companion.getCOLOR());
        final int n = rectangle2.getLocation().x + OCStructureViewComponentKt.access$getSEPARATOR_INDENT$p();
        matteBorder.paintBorder(null, graphics, n, rectangle2.getLocation().y, rectangle.width + rectangle.x - n, rectangle2.height + 1);
    }
    
    public OCStructureViewComponent(@Nullable final FileEditor fileEditor, @NotNull final StructureViewModel structureViewModel, @NotNull final Project project, final boolean b) {
        Intrinsics.checkParameterIsNotNull((Object)structureViewModel, "structureViewModel");
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        super(fileEditor, structureViewModel, project, b);
        this.getTree().setUI((TreeUI)new WideSelectionTreeUI() {
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
                            this$0.a(graphics, rectangle, rectangle2, ocMark2);
                        }
                    }
                }
            }
        });
    }
    
    static {
        Companion = new Companion(null);
        COLOR = new JBColor((Color)Gray._192, (Color)Gray._128);
    }
    
    @NotNull
    public static final /* synthetic */ JBColor access$getCOLOR$cp() {
        return OCStructureViewComponent.COLOR;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewComponent$Companion;", "", "()V", "COLOR", "Lcom/intellij/ui/JBColor;", "getCOLOR", "()Lcom/intellij/ui/JBColor;", "cidr-lang" })
    public static final class Companion
    {
        @NotNull
        public final JBColor getCOLOR() {
            return OCStructureViewComponent.access$getCOLOR$cp();
        }
    }
}
