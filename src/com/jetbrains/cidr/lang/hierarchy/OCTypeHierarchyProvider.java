// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.project.Project;
import com.intellij.ui.content.Content;
import javax.swing.JLabel;
import javax.swing.JComponent;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.ide.hierarchy.HierarchyBrowser;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCClassPredeclaration;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.ide.hierarchy.HierarchyProvider;

public class OCTypeHierarchyProvider implements HierarchyProvider
{
    public PsiElement getTarget(@NotNull final DataContext dataContext) {
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyProvider", "getTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement psiElement = (PsiElement)CommonDataKeys.PSI_ELEMENT.getData(dataContext);
        if (psiElement instanceof OCClassPredeclaration) {
            final OCClassSymbol symbol = ((OCClassPredeclaration)psiElement).getSymbol();
            if (symbol != null) {
                final OCClassSymbol definitionSymbol = symbol.getDefinitionSymbol();
                try {
                    if (definitionSymbol != null) {
                        return ((OCSymbol<PsiElement>)definitionSymbol).locateDefinition();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return null;
        }
        try {
            if (a(psiElement)) {
                return psiElement;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    private static boolean a(final PsiElement psiElement) {
        Label_0021: {
            try {
                if (psiElement instanceof OCClassDeclaration) {
                    break Label_0021;
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCStruct;
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCStruct;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    public HierarchyBrowser createHierarchyBrowser(final PsiElement psiElement) {
        HierarchyBrowser hierarchyBrowser = null;
        Label_0070: {
            OCTypeHierarchyBrowser ocTypeHierarchyBrowser = null;
            Label_0035: {
                try {
                    if (!a(psiElement)) {
                        break Label_0070;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final Project project = psiElement2.getProject();
                    final PsiElement psiElement3 = psiElement;
                    final OCElement ocElement = (OCElement)psiElement3;
                    ocTypeHierarchyBrowser = new OCTypeHierarchyBrowser(project, ocElement);
                    if (ocTypeHierarchyBrowser == null) {
                        break Label_0035;
                    }
                    return (HierarchyBrowser)ocTypeHierarchyBrowser;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final Project project = psiElement2.getProject();
                    final PsiElement psiElement3 = psiElement;
                    final OCElement ocElement = (OCElement)psiElement3;
                    ocTypeHierarchyBrowser = new OCTypeHierarchyBrowser(project, ocElement);
                    if (ocTypeHierarchyBrowser == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyProvider", "createHierarchyBrowser"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return (HierarchyBrowser)ocTypeHierarchyBrowser;
            try {
                hierarchyBrowser = new HierarchyBrowser() {
                    public JComponent getComponent() {
                        return new JLabel("Not implemented. Target: " + psiElement);
                    }
                    
                    public void setContent(final Content content) {
                    }
                };
                if (hierarchyBrowser == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyProvider", "createHierarchyBrowser"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return (HierarchyBrowser)hierarchyBrowser;
    }
    
    public void browserActivated(@NotNull final HierarchyBrowser hierarchyBrowser) {
        try {
            if (hierarchyBrowser == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "hierarchyBrowser", "com/jetbrains/cidr/lang/hierarchy/OCTypeHierarchyProvider", "browserActivated"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCTypeHierarchyBrowser ocTypeHierarchyBrowser = (OCTypeHierarchyBrowser)hierarchyBrowser;
        String s = null;
        Label_0086: {
            try {
                if (ocTypeHierarchyBrowser.isImplementation()) {
                    s = OCTypeHierarchyBrowser.SUPERTYPES_HIERARCHY_TYPE;
                    break Label_0086;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (ocTypeHierarchyBrowser.isInterface()) {
                    s = OCTypeHierarchyBrowser.SUBTYPES_HIERARCHY_TYPE;
                    break Label_0086;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            s = OCTypeHierarchyBrowser.TYPE_HIERARCHY_TYPE;
        }
        ocTypeHierarchyBrowser.changeView(s);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
