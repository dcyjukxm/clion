// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.ide.hierarchy.HierarchyBrowser;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.ide.hierarchy.HierarchyProvider;

public class OCIncludeHierarchyProvider implements HierarchyProvider
{
    public PsiElement getTarget(@NotNull final DataContext dataContext) {
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/lang/hierarchy/OCIncludeHierarchyProvider", "getTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement psiElement = (PsiElement)CommonDataKeys.PSI_ELEMENT.getData(dataContext);
        try {
            if (psiElement instanceof OCIncludeDirective) {
                return (PsiElement)((OCIncludeDirective)psiElement).getIncludedFile();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiElement psiElement2 = (PsiElement)CommonDataKeys.PSI_FILE.getData(dataContext);
        Label_0105: {
            try {
                if (psiElement2 == null) {
                    return null;
                }
                final PsiElement psiElement3 = psiElement2;
                final boolean b = psiElement3 instanceof OCFile;
                if (b) {
                    break Label_0105;
                }
                return null;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final PsiElement psiElement3 = psiElement2;
                final boolean b = psiElement3 instanceof OCFile;
                if (b) {
                    return psiElement2;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return null;
    }
    
    @NotNull
    public HierarchyBrowser createHierarchyBrowser(final PsiElement psiElement) {
        OCTypeHierarchyBrowser ocTypeHierarchyBrowser;
        try {
            ocTypeHierarchyBrowser = new OCTypeHierarchyBrowser(psiElement.getProject(), (OCElement)psiElement);
            if (ocTypeHierarchyBrowser == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/OCIncludeHierarchyProvider", "createHierarchyBrowser"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (HierarchyBrowser)ocTypeHierarchyBrowser;
    }
    
    public void browserActivated(@NotNull final HierarchyBrowser hierarchyBrowser) {
        try {
            if (hierarchyBrowser == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "hierarchyBrowser", "com/jetbrains/cidr/lang/hierarchy/OCIncludeHierarchyProvider", "browserActivated"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCTypeHierarchyBrowser ocTypeHierarchyBrowser = (OCTypeHierarchyBrowser)hierarchyBrowser;
        String s = null;
        Label_0069: {
            try {
                if (ocTypeHierarchyBrowser.isImplementation()) {
                    s = OCTypeHierarchyBrowser.SUPERTYPES_HIERARCHY_TYPE;
                    break Label_0069;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            s = OCTypeHierarchyBrowser.SUBTYPES_HIERARCHY_TYPE;
        }
        ocTypeHierarchyBrowser.changeView(s);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
