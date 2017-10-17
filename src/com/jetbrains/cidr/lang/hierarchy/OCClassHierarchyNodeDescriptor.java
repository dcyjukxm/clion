// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.editor.markup.EffectType;
import java.awt.Color;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.roots.ui.util.CompositeAppearance;
import javax.swing.Icon;
import com.intellij.icons.AllIcons;
import com.intellij.ui.LayeredIcon;
import com.intellij.ide.IdeBundle;
import com.intellij.psi.PsiElement;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import com.intellij.psi.NavigatablePsiElement;

public class OCClassHierarchyNodeDescriptor<T extends NavigatablePsiElement> extends HierarchyNodeDescriptor
{
    public OCClassHierarchyNodeDescriptor(final Project project, final NodeDescriptor nodeDescriptor, final T t, final boolean b) {
        super(project, nodeDescriptor, (PsiElement)t, b);
    }
    
    public T getType() {
        return (T)this.getPsiElement();
    }
    
    @Override
    public final boolean update() {
        boolean update = super.update();
        final PsiElement psiElement = this.getPsiElement();
        if (psiElement == null) {
            final String message = IdeBundle.message("node.hierarchy.invalid", new Object[0]);
            if (!this.myHighlightedText.getText().startsWith(message)) {
                this.myHighlightedText.getBeginning().addText(message, HierarchyNodeDescriptor.getInvalidPrefixAttributes());
            }
            return true;
        }
        if (update && this.myIsBase) {
            final LayeredIcon icon = new LayeredIcon(2);
            icon.setIcon(this.getIcon(), 0);
            icon.setIcon(AllIcons.Hierarchy.Base, 1, -AllIcons.Hierarchy.Base.getIconWidth() / 2, 0);
            this.setIcon((Icon)icon);
        }
        final NavigatablePsiElement navigatablePsiElement = (NavigatablePsiElement)psiElement;
        final CompositeAppearance myHighlightedText = this.myHighlightedText;
        this.myHighlightedText = new CompositeAppearance();
        TextAttributes textAttributes = null;
        if (this.myColor != null) {
            textAttributes = new TextAttributes(this.myColor, (Color)null, (Color)null, (EffectType)null, 0);
        }
        this.myHighlightedText.getEnding().addText(navigatablePsiElement.getName(), textAttributes);
        final ItemPresentation presentation = navigatablePsiElement.getPresentation();
        final String s = (presentation != null) ? presentation.getLocationString() : null;
        if (s != null) {
            this.myHighlightedText.getEnding().addText(" (" + s + ")", HierarchyNodeDescriptor.getPackageNameAttributes());
        }
        this.myName = this.myHighlightedText.getText();
        if (!Comparing.equal((Object)this.myHighlightedText, (Object)myHighlightedText)) {
            update = true;
        }
        return update;
    }
}
