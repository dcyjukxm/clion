// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.projectView;

import com.intellij.ide.projectView.impl.nodes.ProjectViewProjectNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.ProjectViewSettings;
import com.intellij.ide.projectView.impl.ProjectTreeStructure;
import com.intellij.icons.AllIcons;
import com.intellij.ide.IdeBundle;
import com.intellij.ide.projectView.impl.nodes.BasePsiNode;
import javax.swing.tree.DefaultMutableTreeNode;
import com.jetbrains.cidr.CidrBundle;
import javax.swing.tree.TreeCellRenderer;
import java.util.Set;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import gnu.trove.THashSet;
import com.intellij.psi.util.CachedValueProvider;
import java.awt.Graphics;
import java.awt.Composite;
import java.awt.Graphics2D;
import javax.swing.JTree;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.ide.util.treeView.NodeRenderer;
import javax.swing.tree.TreeModel;
import com.intellij.ide.projectView.impl.ProjectViewTree;
import javax.swing.tree.DefaultTreeModel;
import com.intellij.ide.projectView.impl.ProjectAbstractTreeStructureBase;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import java.awt.AlphaComposite;
import javax.swing.Icon;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.Key;

public class CidrFilesViewHelper
{
    private static final Key<CachedValue<Condition<VirtualFile>>> SCOPE_FILES_KEY;
    public static final String TITLE;
    public static final Icon ICON;
    public static final int WEIGHT = 1;
    private static final AlphaComposite COMPOSITE;
    @NotNull
    private final CidrView myView;
    
    public CidrFilesViewHelper(@NotNull final CidrView myView) {
        if (myView == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "view", "com/jetbrains/cidr/projectView/CidrFilesViewHelper", "<init>"));
        }
        this.myView = myView;
    }
    
    @NotNull
    public CidrView getView() {
        CidrView myView;
        try {
            myView = this.myView;
            if (myView == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/projectView/CidrFilesViewHelper", "getView"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myView;
    }
    
    @NotNull
    public ProjectAbstractTreeStructureBase createStructure(@NotNull final Project project, @NotNull final String s) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/projectView/CidrFilesViewHelper", "createStructure"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ID", "com/jetbrains/cidr/projectView/CidrFilesViewHelper", "createStructure"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        MyProjectTreeStructure myProjectTreeStructure;
        try {
            myProjectTreeStructure = new MyProjectTreeStructure(project, s);
            if (myProjectTreeStructure == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/projectView/CidrFilesViewHelper", "createStructure"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return myProjectTreeStructure;
    }
    
    @NotNull
    public ProjectViewTree createTree(@NotNull final Project project, final DefaultTreeModel defaultTreeModel) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/projectView/CidrFilesViewHelper", "createTree"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ProjectViewTree projectViewTree = new ProjectViewTree(project, defaultTreeModel) {
            public String toString() {
                return CidrFilesViewHelper.TITLE + " " + super.toString();
            }
        };
        final NodeRenderer cellRenderer = new NodeRenderer() {
            private boolean myInProject;
            
            @NotNull
            private Condition<VirtualFile> a() {
                Condition condition;
                try {
                    condition = (Condition)CachedValuesManager.getManager(project).getCachedValue((UserDataHolder)project, CidrFilesViewHelper.SCOPE_FILES_KEY, () -> {
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
                        graphics2D.setComposite(CidrFilesViewHelper.COMPOSITE);
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
                    graphics2D.setComposite(CidrFilesViewHelper.COMPOSITE);
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
        };
        ProjectViewTree projectViewTree2;
        try {
            cellRenderer.setOpaque(false);
            cellRenderer.setIconOpaque(false);
            projectViewTree.setCellRenderer((TreeCellRenderer)cellRenderer);
            projectViewTree.getEmptyText().setText(CidrBundle.message("project.view.empty", new Object[0]));
            projectViewTree2 = projectViewTree;
            if (projectViewTree2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/projectView/CidrFilesViewHelper", "createTree"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return projectViewTree2;
    }
    
    public boolean isInProjectSources(final Object o, final Condition<VirtualFile> condition) {
        if (o instanceof DefaultMutableTreeNode) {
            final Object userObject = ((DefaultMutableTreeNode)o).getUserObject();
            if (userObject instanceof BasePsiNode) {
                final VirtualFile virtualFile = ((BasePsiNode)userObject).getVirtualFile();
                try {
                    if (virtualFile != null) {
                        return condition.value((Object)virtualFile);
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
        }
        return true;
    }
    
    static {
        SCOPE_FILES_KEY = Key.create("SCOPE_FILES_KEY");
        TITLE = IdeBundle.message("title.project", new Object[0]);
        ICON = AllIcons.General.ProjectTab;
        COMPOSITE = AlphaComposite.getInstance(3, 0.6f);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class MyProjectTreeStructure extends ProjectTreeStructure implements ProjectViewSettings
    {
        public MyProjectTreeStructure(final Project project, final String s) {
            super(project, s);
        }
        
        @Override
        public boolean isShowExcludedFiles() {
            return true;
        }
        
        @Override
        protected AbstractTreeNode createRoot(final Project project, final ViewSettings viewSettings) {
            return (AbstractTreeNode)new ProjectViewProjectNode(project, viewSettings) {
                @Override
                public boolean contains(@NotNull final VirtualFile virtualFile) {
                    try {
                        if (virtualFile == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/projectView/CidrFilesViewHelper$MyProjectTreeStructure$1", "contains"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    return true;
                }
                
                private static IllegalArgumentException b(final IllegalArgumentException ex) {
                    return ex;
                }
            };
        }
    }
}
