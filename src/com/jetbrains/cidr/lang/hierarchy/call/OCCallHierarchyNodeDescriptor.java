// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.call;

import com.intellij.openapi.util.TextRange;
import java.util.Iterator;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import java.util.Collection;
import com.intellij.openapi.editor.colors.EditorColors;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.codeInsight.highlighting.HighlightManager;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.navigation.ItemPresentation;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.util.Comparing;
import com.intellij.psi.NavigatablePsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.openapi.editor.markup.EffectType;
import java.awt.Color;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.roots.ui.util.CompositeAppearance;
import javax.swing.Icon;
import com.intellij.icons.AllIcons;
import com.intellij.ui.LayeredIcon;
import com.intellij.ide.IdeBundle;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCCallable;
import java.util.HashSet;
import java.util.ArrayList;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Set;
import com.intellij.psi.PsiReference;
import java.util.List;
import com.intellij.pom.Navigatable;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;

public class OCCallHierarchyNodeDescriptor extends HierarchyNodeDescriptor implements Navigatable
{
    private int myUsageCount;
    private final List<PsiReference> myReferences;
    private final boolean myNavigateToReference;
    private final Set<OCSymbol> myPossibleResponders;
    
    public OCCallHierarchyNodeDescriptor(final Project project, final HierarchyNodeDescriptor hierarchyNodeDescriptor, final PsiElement psiElement, final boolean b, final boolean myNavigateToReference) {
        super(project, hierarchyNodeDescriptor, psiElement, b);
        this.myUsageCount = 1;
        this.myReferences = new ArrayList<PsiReference>();
        this.myNavigateToReference = myNavigateToReference;
        this.myPossibleResponders = new HashSet<OCSymbol>();
    }
    
    @Nullable
    public final OCCallable getEnclosingElement() {
        final PsiElement psiElement = this.getPsiElement();
        return (psiElement == null) ? null : getEnclosingElement(psiElement);
    }
    
    public Set<OCSymbol> getPossibleResponders() {
        return this.myPossibleResponders;
    }
    
    public void addPossibleResponder(final OCSymbol ocSymbol) {
        this.myPossibleResponders.add(ocSymbol);
    }
    
    @Nullable
    static OCCallable getEnclosingElement(final PsiElement psiElement) {
        return (OCCallable)PsiTreeUtil.getNonStrictParentOfType(psiElement, new Class[] { OCCallable.class });
    }
    
    public final void incrementUsageCount() {
        ++this.myUsageCount;
    }
    
    public final PsiElement getTargetElement() {
        return this.getPsiElement();
    }
    
    @Override
    public final boolean isValid() {
        return this.getEnclosingElement() != null;
    }
    
    public final boolean update() {
        final CompositeAppearance myHighlightedText = this.myHighlightedText;
        final Icon icon = this.getIcon();
        int n = 1;
        if (this.isMarkReadOnly()) {
            n |= 0x2;
        }
        boolean update = super.update();
        final OCCallable enclosingElement = this.getEnclosingElement();
        if (enclosingElement == null) {
            final String message = IdeBundle.message("node.hierarchy.invalid", new Object[0]);
            if (!this.myHighlightedText.getText().startsWith(message)) {
                this.myHighlightedText.getBeginning().addText(message, HierarchyNodeDescriptor.getInvalidPrefixAttributes());
            }
            return true;
        }
        this.setIcon(((PsiElement)enclosingElement).getIcon(n));
        if (update && this.myIsBase) {
            final LayeredIcon icon2 = new LayeredIcon(2);
            icon2.setIcon(this.getIcon(), 0);
            icon2.setIcon(AllIcons.Hierarchy.Base, 1, -AllIcons.Hierarchy.Base.getIconWidth() / 2, 0);
            this.setIcon((Icon)icon2);
        }
        this.myHighlightedText = new CompositeAppearance();
        TextAttributes textAttributes = null;
        if (this.myColor != null) {
            textAttributes = new TextAttributes(this.myColor, (Color)null, (Color)null, (EffectType)null, 0);
        }
        String nameWithParent = null;
        if (enclosingElement instanceof OCMethod) {
            final OCMethodSymbol ocMethodSymbol = ((OCMethod)enclosingElement).getSymbol();
            nameWithParent = ((ocMethodSymbol != null) ? ocMethodSymbol.getNameWithParent() : ((OCMethod)enclosingElement).getName());
        }
        else if (enclosingElement instanceof OCFunctionDeclaration) {
            final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = ((OCFunctionDeclaration)enclosingElement).getSymbol();
            if (ocSymbolWithQualifiedName != null) {
                final OCQualifiedName resolvedQualifiedName = ocSymbolWithQualifiedName.getResolvedQualifiedName();
                if (resolvedQualifiedName != null) {
                    nameWithParent = resolvedQualifiedName.getNameWithParent();
                }
            }
        }
        if (nameWithParent == null) {
            final ItemPresentation presentation = ((NavigatablePsiElement)enclosingElement).getPresentation();
            nameWithParent = ((presentation != null) ? presentation.getPresentableText() : ((NavigatablePsiElement)enclosingElement).getName());
        }
        this.myHighlightedText.getEnding().addText(nameWithParent, textAttributes);
        if (this.myPossibleResponders.size() > 1) {
            this.myHighlightedText.getEnding().addText(" (polymorphic call) ", HierarchyNodeDescriptor.getPackageNameAttributes());
        }
        if (this.myUsageCount > 1) {
            this.myHighlightedText.getEnding().addText(IdeBundle.message("node.call.hierarchy.N.usages", new Object[] { this.myUsageCount }), HierarchyNodeDescriptor.getUsageCountPrefixAttributes());
        }
        this.myName = this.myHighlightedText.getText();
        if (!Comparing.equal((Object)this.myHighlightedText, (Object)myHighlightedText) || !Comparing.equal((Object)this.getIcon(), (Object)icon)) {
            update = true;
        }
        return update;
    }
    
    public void addReference(final PsiReference psiReference) {
        this.myReferences.add(psiReference);
    }
    
    public void navigate(final boolean b) {
        if (!this.myNavigateToReference) {
            final PsiElement psiElement = this.getPsiElement();
            if (psiElement instanceof Navigatable && ((Navigatable)psiElement).canNavigate()) {
                ((Navigatable)psiElement).navigate(b);
            }
            return;
        }
        final PsiElement element = this.myReferences.get(0).getElement();
        if (element == null) {
            return;
        }
        final PsiElement parent = element.getParent();
        if (parent instanceof Navigatable && ((Navigatable)parent).canNavigate()) {
            ((Navigatable)parent).navigate(b);
        }
        else {
            final PsiFile containingFile = parent.getContainingFile();
            if (containingFile == null || containingFile.getVirtualFile() == null) {
                return;
            }
            FileEditorManager.getInstance(this.myProject).openFile(containingFile.getVirtualFile(), b);
        }
        final Editor editor = PsiUtilBase.findEditor(parent);
        if (editor != null) {
            final HighlightManager instance = HighlightManager.getInstance(this.myProject);
            final TextAttributes attributes = EditorColorsManager.getInstance().getGlobalScheme().getAttributes(EditorColors.SEARCH_RESULT_ATTRIBUTES);
            final ArrayList list = new ArrayList();
            final Iterator<PsiReference> iterator = this.myReferences.iterator();
            while (iterator.hasNext()) {
                final PsiElement element2 = iterator.next().getElement();
                if (element2 != null) {
                    final PsiElement parent2 = element2.getParent();
                    if (parent2 == null) {
                        continue;
                    }
                    final TextRange textRange = parent2.getTextRange();
                    instance.addRangeHighlight(editor, textRange.getStartOffset(), textRange.getEndOffset(), attributes, false, (Collection)list);
                }
            }
        }
    }
    
    public boolean canNavigate() {
        if (!this.myNavigateToReference) {
            final PsiElement psiElement = this.getPsiElement();
            return psiElement instanceof Navigatable && ((Navigatable)psiElement).canNavigate();
        }
        if (this.myReferences.isEmpty()) {
            return false;
        }
        final PsiElement parent = this.myReferences.get(0).getElement().getParent();
        return parent != null && parent.isValid() && ((parent instanceof Navigatable && ((Navigatable)parent).canNavigate()) || parent.getContainingFile() != null);
    }
    
    public boolean canNavigateToSource() {
        return this.canNavigate();
    }
}
