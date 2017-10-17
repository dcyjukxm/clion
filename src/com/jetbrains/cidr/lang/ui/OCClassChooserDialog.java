// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.navigation.OCGotoClassContributor;
import java.util.List;
import com.intellij.psi.search.GlobalSearchScope;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import javax.swing.tree.DefaultMutableTreeNode;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.Nullable;
import com.intellij.ide.util.TreeChooser;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.ide.util.AbstractTreeClassChooserDialog;

public class OCClassChooserDialog extends AbstractTreeClassChooserDialog<OCSymbolHolderVirtualPsiElement>
{
    private Condition<OCSymbol> mySymbolCondition;
    
    public OCClassChooserDialog(final String s, final Project project, final TreeChooser.Filter<OCSymbolHolderVirtualPsiElement> filter, @Nullable final OCSymbolHolderVirtualPsiElement ocSymbolHolderVirtualPsiElement, final Condition<OCSymbol> mySymbolCondition) {
        super(s, project, OCSearchScope.getProjectSourcesScope(project), (Class<PsiNamedElement>)OCSymbolHolderVirtualPsiElement.class, (TreeChooser.Filter<PsiNamedElement>)filter, null, (PsiNamedElement)ocSymbolHolderVirtualPsiElement, false, false);
        this.mySymbolCondition = mySymbolCondition;
    }
    
    @Nullable
    @Override
    protected OCSymbolHolderVirtualPsiElement getSelectedFromTreeUserObject(final DefaultMutableTreeNode defaultMutableTreeNode) {
        final Object userObject = defaultMutableTreeNode.getUserObject();
        try {
            if (!(userObject instanceof PsiFileNode)) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        final PsiFile psiFile = (PsiFile)((PsiFileNode)userObject).getValue();
        OCSymbolDeclarator<OCSymbol> firstClass = null;
        Label_0053: {
            try {
                if (psiFile instanceof OCFile) {
                    firstClass = (OCSymbolDeclarator<OCSymbol>)((OCFile)psiFile).findFirstClass();
                    break Label_0053;
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            firstClass = null;
        }
        final OCSymbolDeclarator<OCSymbol> ocSymbolDeclarator = firstClass;
        OCSymbol symbol = null;
        Label_0075: {
            try {
                if (ocSymbolDeclarator != null) {
                    symbol = ocSymbolDeclarator.getSymbol();
                    break Label_0075;
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
            symbol = null;
        }
        final OCSymbol ocSymbol = symbol;
        Label_0103: {
            try {
                if (ocSymbol == null) {
                    return null;
                }
                final OCClassChooserDialog ocClassChooserDialog = this;
                final Condition<OCSymbol> condition = ocClassChooserDialog.mySymbolCondition;
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = condition.value((Object)ocSymbol2);
                if (b) {
                    break Label_0103;
                }
                return null;
            }
            catch (IllegalStateException ex4) {
                throw b(ex4);
            }
            try {
                final OCClassChooserDialog ocClassChooserDialog = this;
                final Condition<OCSymbol> condition = ocClassChooserDialog.mySymbolCondition;
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = condition.value((Object)ocSymbol2);
                if (b) {
                    return new OCSymbolHolderVirtualPsiElement(ocSymbol);
                }
            }
            catch (IllegalStateException ex5) {
                throw b(ex5);
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    protected List<OCSymbolHolderVirtualPsiElement> getClassesByName(final String s, final boolean b, final String s2, final GlobalSearchScope globalSearchScope) {
        final OCSymbol[] symbolsByName = new OCGotoClassContributor().getSymbolsByName(s, this.getProject(), globalSearchScope.isSearchInLibraries(), this.mySymbolCondition, true);
        final ArrayList<OCSymbolHolderVirtualPsiElement> list = new ArrayList<OCSymbolHolderVirtualPsiElement>();
        for (final OCSymbol ocSymbol : symbolsByName) {
            try {
                if (globalSearchScope.contains(ocSymbol.getContainingFile())) {
                    list.add(new OCSymbolHolderVirtualPsiElement(ocSymbol));
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
        }
        ArrayList<OCSymbolHolderVirtualPsiElement> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCClassChooserDialog", "getClassesByName"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return list2;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
