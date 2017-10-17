// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.projectView;

import java.util.Set;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import gnu.trove.THashSet;
import com.intellij.psi.util.CachedValueProvider;
import javax.swing.Icon;
import java.awt.Graphics;
import java.awt.Composite;
import java.awt.Graphics2D;
import javax.swing.JTree;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.project.Project;
import com.intellij.ide.util.treeView.NodeRenderer;

class CidrFilesViewHelper$2 extends NodeRenderer {
    private boolean myInProject;
    final /* synthetic */ Project val$project;
    
    @NotNull
    private Condition<VirtualFile> a() {
        Condition condition;
        try {
            condition = (Condition)CachedValuesManager.getManager(this.val$project).getCachedValue((UserDataHolder)this.val$project, CidrFilesViewHelper.access$000(), () -> {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/projectView/CidrFilesViewHelper$2", "lambda$getProjectSourcesScope$1"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                final THashSet set = new THashSet();
            Label_0062:
                for (VirtualFile parent : OCSearchScope.getExplicitlySpecifiedProjectSourceFiles(project)) {
                    while (true) {
                        while (((Set<VirtualFile>)set).add(parent)) {
                            if ((parent = parent.getParent()) == null) {
                                continue Label_0062;
                            }
                        }
                        continue;
                    }
                }
                return CachedValueProvider.Result.create((Object)(virtualFile -> ((Set)set).contains(virtualFile)), (Collection)OCSearchScope.getProjectSourcesCacheDependencies(project));
            }, false);
            if (condition == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/projectView/CidrFilesViewHelper$2", "getProjectSourcesScope"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Condition<VirtualFile>)condition;
    }
    
    public void customizeCellRenderer(final JTree tree, final Object o, final boolean mySelected, final boolean b, final boolean b2, final int n, final boolean b3) {
        super.customizeCellRenderer(tree, o, mySelected, b, b2, n, b3);
        this.myInProject = CidrFilesViewHelper.this.isInProjectSources(o, this.a());
        this.mySelected = mySelected;
    }
    
    protected void applyAdditionalHints(@NotNull final Graphics2D graphics2D) {
        try {
            if (graphics2D == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "g", "com/jetbrains/cidr/projectView/CidrFilesViewHelper$2", "applyAdditionalHints"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            super.applyAdditionalHints(graphics2D);
            if (!this.myInProject) {
                graphics2D.setComposite(CidrFilesViewHelper.access$100());
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
    }
    
    protected void paintIcon(@NotNull final Graphics graphics, @NotNull final Icon icon, final int n) {
        try {
            if (graphics == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "g", "com/jetbrains/cidr/projectView/CidrFilesViewHelper$2", "paintIcon"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (icon == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "icon", "com/jetbrains/cidr/projectView/CidrFilesViewHelper$2", "paintIcon"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        Composite composite = null;
        if (!this.myInProject) {
            composite = graphics2D.getComposite();
            graphics2D.setComposite(CidrFilesViewHelper.access$100());
        }
        try {
            super.paintIcon(graphics, icon, n);
            if (composite != null) {
                graphics2D.setComposite(composite);
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}