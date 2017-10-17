// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.call;

import com.intellij.ide.util.treeView.AlphaComparator;
import com.intellij.ide.util.treeView.NodeDescriptor;
import java.util.Comparator;
import com.intellij.ide.hierarchy.HierarchyTreeStructure;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import javax.swing.JComponent;
import com.intellij.openapi.actionSystem.ActionManager;
import org.jetbrains.annotations.NotNull;
import javax.swing.JTree;
import java.util.Map;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.ide.hierarchy.CallHierarchyBrowserBase;

public final class OCCallHierarchyBrowser extends CallHierarchyBrowserBase
{
    private static final Logger LOG;
    
    public OCCallHierarchyBrowser(final Project project, final OCCallable ocCallable) {
        super(project, (PsiElement)ocCallable);
    }
    
    @Override
    protected void createTrees(@NotNull final Map<String, JTree> map) {
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type2TreeMap", "com/jetbrains/cidr/lang/hierarchy/call/OCCallHierarchyBrowser", "createTrees"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        final JTree tree = this.createTree(false);
        final BaseOnThisMethodAction baseOnThisMethodAction = new BaseOnThisMethodAction();
        baseOnThisMethodAction.registerCustomShortcutSet(ActionManager.getInstance().getAction("CallHierarchy").getShortcutSet(), (JComponent)tree);
        map.put(OCCallHierarchyBrowser.CALLEE_TYPE, tree);
        final JTree tree2 = this.createTree(false);
        baseOnThisMethodAction.registerCustomShortcutSet(ActionManager.getInstance().getAction("CallHierarchy").getShortcutSet(), (JComponent)tree2);
        map.put(OCCallHierarchyBrowser.CALLER_TYPE, tree2);
    }
    
    @Override
    protected PsiElement getElementFromDescriptor(@NotNull final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        try {
            if (hierarchyNodeDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/hierarchy/call/OCCallHierarchyBrowser", "getElementFromDescriptor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        if (hierarchyNodeDescriptor instanceof OCCallHierarchyNodeDescriptor) {
            return (PsiElement)((OCCallHierarchyNodeDescriptor)hierarchyNodeDescriptor).getEnclosingElement();
        }
        return null;
    }
    
    @Override
    protected PsiElement getOpenFileElementFromDescriptor(@NotNull final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        try {
            if (hierarchyNodeDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/hierarchy/call/OCCallHierarchyBrowser", "getOpenFileElementFromDescriptor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        if (hierarchyNodeDescriptor instanceof OCCallHierarchyNodeDescriptor) {
            return ((OCCallHierarchyNodeDescriptor)hierarchyNodeDescriptor).getTargetElement();
        }
        return null;
    }
    
    @Override
    protected boolean isApplicableElement(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/hierarchy/call/OCCallHierarchyBrowser", "isApplicableElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        Label_0065: {
            try {
                if (!(psiElement instanceof OCCallable)) {
                    return false;
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof NavigatablePsiElement;
                if (b) {
                    break Label_0065;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof NavigatablePsiElement;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
        }
        return false;
    }
    
    @Override
    protected HierarchyTreeStructure createHierarchyTreeStructure(@NotNull final String s, @NotNull final PsiElement psiElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeName", "com/jetbrains/cidr/lang/hierarchy/call/OCCallHierarchyBrowser", "createHierarchyTreeStructure"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/hierarchy/call/OCCallHierarchyBrowser", "createHierarchyTreeStructure"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        try {
            if (OCCallHierarchyBrowser.CALLER_TYPE.equals(s)) {
                return new OCCallerMethodsTreeStructure(this.myProject, (OCCallable)psiElement, this.getCurrentScopeType());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw d(ex3);
        }
        try {
            if (OCCallHierarchyBrowser.CALLEE_TYPE.equals(s)) {
                return new OCCalleeMethodsTreeStructure(this.myProject, (OCCallable)psiElement, this.getCurrentScopeType());
            }
        }
        catch (IllegalArgumentException ex4) {
            throw d(ex4);
        }
        OCCallHierarchyBrowser.LOG.error("unexpected type: " + s);
        return null;
    }
    
    @Override
    protected Comparator<NodeDescriptor> getComparator() {
        return (Comparator<NodeDescriptor>)AlphaComparator.INSTANCE;
    }
    
    static {
        LOG = Logger.getInstance("#com.intellij.ide.hierarchy.call.CallHierarchyBrowser");
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static final class BaseOnThisMethodAction extends CallHierarchyBrowserBase.BaseOnThisMethodAction
    {
    }
}
