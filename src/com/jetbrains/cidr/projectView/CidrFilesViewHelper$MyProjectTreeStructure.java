// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.projectView;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ide.projectView.impl.nodes.ProjectViewProjectNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.openapi.project.Project;
import com.intellij.ide.projectView.ProjectViewSettings;
import com.intellij.ide.projectView.impl.ProjectTreeStructure;

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
