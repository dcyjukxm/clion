// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.projectView;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import com.intellij.psi.PsiDirectory;
import com.intellij.ide.impl.ProjectViewSelectInTarget;
import com.intellij.ide.SelectInTarget;
import com.intellij.ide.util.treeView.AbstractTreeUpdater;
import com.intellij.ide.util.treeView.AbstractTreeBuilder;
import com.intellij.util.messages.Topic;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationListener;
import com.intellij.openapi.project.Project;
import com.intellij.ide.projectView.impl.AbstractProjectViewPSIPane;

public abstract class CidrView extends AbstractProjectViewPSIPane
{
    public CidrView(final Project project) {
        super(project);
        project.getMessageBus().connect().subscribe((Topic)OCWorkspaceModificationListener.TOPIC, (Object)new OCWorkspaceModificationListener() {
            @Override
            public void projectsChanged() {
                this.somethingChanged();
            }
            
            @Override
            public void projectFilesChanged() {
                this.somethingChanged();
            }
            
            @Override
            public void sourceFilesChanged() {
                this.somethingChanged();
            }
            
            @Override
            public void buildSettingsChanged() {
                this.somethingChanged();
            }
            
            @Override
            public void selectedResolveConfigurationChanged() {
                this.somethingChanged();
            }
            
            @Override
            public void buildFinished() {
                this.somethingChanged();
            }
            
            void somethingChanged() {
                CidrView.this.queueUpdate();
            }
        });
    }
    
    protected void queueUpdate() {
        final AbstractTreeBuilder treeBuilder = this.getTreeBuilder();
        try {
            if (treeBuilder != null) {
                treeBuilder.queueUpdate();
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
    }
    
    @Override
    protected AbstractTreeUpdater createTreeUpdater(final AbstractTreeBuilder abstractTreeBuilder) {
        return new AbstractTreeUpdater(abstractTreeBuilder);
    }
    
    @Override
    public SelectInTarget createSelectInTarget() {
        return (SelectInTarget)new ProjectViewSelectInTarget(this.myProject) {
            @Override
            public String toString() {
                return CidrView.this.getTitle();
            }
            
            public String getMinorViewId() {
                return CidrView.this.getId();
            }
            
            public float getWeight() {
                return CidrView.this.getWeight();
            }
        };
    }
    
    @NotNull
    @Override
    public PsiDirectory[] getSelectedDirectories() {
        final PsiDirectory[] selectedDirectories = super.getSelectedDirectories();
        Label_0057: {
            PsiDirectory[] array = null;
            Label_0022: {
                try {
                    if (selectedDirectories.length <= 0) {
                        break Label_0057;
                    }
                    array = selectedDirectories;
                    if (array == null) {
                        break Label_0022;
                    }
                    return array;
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                try {
                    array = selectedDirectories;
                    if (array == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/projectView/CidrView", "getSelectedDirectories"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
            }
            return array;
        }
        final PsiElement[] selectedPSIElements = this.getSelectedPSIElements();
        final ArrayList<PsiDirectory> list = new ArrayList<PsiDirectory>();
        for (final PsiElement psiElement : selectedPSIElements) {
            Label_0166: {
                try {
                    if (psiElement instanceof PsiDirectory) {
                        list.add((PsiDirectory)psiElement);
                        break Label_0166;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw b(ex3);
                }
                final PsiFile containingFile = psiElement.getContainingFile();
                if (containingFile != null) {
                    final PsiDirectory containingDirectory = containingFile.getContainingDirectory();
                    try {
                        if (containingDirectory != null) {
                            list.add(containingDirectory);
                        }
                    }
                    catch (IllegalStateException ex4) {
                        throw b(ex4);
                    }
                }
            }
        }
        PsiDirectory[] array3;
        try {
            array3 = list.toArray(new PsiDirectory[list.size()]);
            if (array3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/projectView/CidrView", "getSelectedDirectories"));
            }
        }
        catch (IllegalStateException ex5) {
            throw b(ex5);
        }
        return array3;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
