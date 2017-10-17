// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.autoImport.OCAutoImportHelper;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiFile;
import java.awt.Color;
import com.intellij.ui.FileColorManager;
import com.intellij.util.ui.UIUtil;
import java.awt.Component;
import com.intellij.openapi.util.Pair;
import com.intellij.ui.SimpleColoredComponent;
import com.intellij.ui.speedSearch.SpeedSearchUtil;
import com.intellij.ui.SimpleTextAttributes;
import org.jetbrains.annotations.NotNull;
import javax.swing.JList;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.intellij.ui.speedSearch.SpeedSearch;
import com.intellij.ui.ColoredListCellRenderer;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

private static class MyRenderer extends JPanel implements ListCellRenderer<AutoImportItem>
{
    final ColoredListCellRenderer<AutoImportItem> myLeft;
    final ColoredListCellRenderer<AutoImportItem> myRight;
    
    public MyRenderer(final SpeedSearch speedSearch) {
        super(new BorderLayout());
        this.myLeft = new ColoredListCellRenderer<AutoImportItem>() {
            protected void customizeCellRenderer(@NotNull final JList<? extends AutoImportItem> list, final AutoImportItem autoImportItem, final int n, final boolean b, final boolean b2) {
                try {
                    if (list == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$MyRenderer$1", "customizeCellRenderer"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                MyRenderer.this.myLeft.clear();
                final Pair<String, String> titleAndLocation = autoImportItem.getTitleAndLocation();
                MyRenderer.this.myLeft.setIcon(autoImportItem.mySymbolToImport.getIcon());
                SpeedSearchUtil.appendColoredFragmentForMatcher((String)titleAndLocation.first, (SimpleColoredComponent)this, SimpleTextAttributes.REGULAR_ATTRIBUTES, speedSearch.getMatcher(), MyRenderer.this.getBackground(), b);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        this.myRight = new ColoredListCellRenderer<AutoImportItem>() {
            protected void customizeCellRenderer(@NotNull final JList<? extends AutoImportItem> list, final AutoImportItem autoImportItem, final int n, final boolean b, final boolean b2) {
                try {
                    if (list == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$MyRenderer$2", "customizeCellRenderer"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                SpeedSearchUtil.appendColoredFragmentForMatcher((String)autoImportItem.getTitleAndLocation().second, (SimpleColoredComponent)this, SimpleTextAttributes.GRAY_ATTRIBUTES, speedSearch.getMatcher(), MyRenderer.this.getBackground(), b);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        this.add((Component)this.myLeft, "Center");
        this.add((Component)this.myRight, "East");
    }
    
    @Override
    public Component getListCellRendererComponent(final JList<? extends AutoImportItem> list, final AutoImportItem autoImportItem, final int n, final boolean b, final boolean b2) {
        Color background = b ? UIUtil.getListSelectionBackground() : UIUtil.getListBackground();
        if (!b && autoImportItem instanceof AutoImportItem) {
            final PsiFile containingPsiFile = autoImportItem.mySymbolToImport.getContainingPsiFile();
            if (containingPsiFile != null) {
                final FileColorManager instance = FileColorManager.getInstance(containingPsiFile.getProject());
                if (instance.isEnabled()) {
                    final Color rendererBackground = instance.getRendererBackground(containingPsiFile);
                    if (rendererBackground != null) {
                        background = rendererBackground;
                    }
                }
            }
        }
        this.setBackground(background);
        this.myLeft.getListCellRendererComponent((JList)list, (Object)autoImportItem, n, b, false);
        this.myRight.getListCellRendererComponent((JList)list, (Object)autoImportItem, n, b, false);
        return this;
    }
}
