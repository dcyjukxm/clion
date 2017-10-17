// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.call;

import com.intellij.ide.hierarchy.CallHierarchyBrowserBase;
import com.intellij.ide.hierarchy.HierarchyBrowser;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.ide.hierarchy.HierarchyProvider;

public class OCCallHierarchyProvider implements HierarchyProvider
{
    public PsiElement getTarget(@NotNull final DataContext dataContext) {
        try {
            if (dataContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/lang/hierarchy/call/OCCallHierarchyProvider", "getTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Project project = (Project)CommonDataKeys.PROJECT.getData(dataContext);
        try {
            if (project == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return PsiTreeUtil.getParentOfType((PsiElement)CommonDataKeys.PSI_ELEMENT.getData(dataContext), (Class)OCCallable.class, false);
    }
    
    @NotNull
    public HierarchyBrowser createHierarchyBrowser(final PsiElement psiElement) {
        OCCallHierarchyBrowser ocCallHierarchyBrowser;
        try {
            ocCallHierarchyBrowser = new OCCallHierarchyBrowser(psiElement.getProject(), (OCCallable)psiElement);
            if (ocCallHierarchyBrowser == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/call/OCCallHierarchyProvider", "createHierarchyBrowser"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (HierarchyBrowser)ocCallHierarchyBrowser;
    }
    
    public void browserActivated(@NotNull final HierarchyBrowser hierarchyBrowser) {
        try {
            if (hierarchyBrowser == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "hierarchyBrowser", "com/jetbrains/cidr/lang/hierarchy/call/OCCallHierarchyProvider", "browserActivated"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ((OCCallHierarchyBrowser)hierarchyBrowser).changeView(CallHierarchyBrowserBase.CALLER_TYPE);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
