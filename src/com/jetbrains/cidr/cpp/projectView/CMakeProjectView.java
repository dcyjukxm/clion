// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.projectView;

import com.intellij.ide.projectView.impl.ProjectViewTree;
import javax.swing.tree.DefaultTreeModel;
import com.intellij.ide.projectView.impl.ProjectAbstractTreeStructureBase;
import org.jetbrains.annotations.NotNull;
import javax.swing.Icon;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.projectView.CidrFilesViewHelper;
import com.jetbrains.cidr.projectView.CidrView;

public class CMakeProjectView extends CidrView
{
    private final CidrFilesViewHelper myHelper;
    
    public CMakeProjectView(final Project project) {
        super(project);
        this.myHelper = this.createHelper();
    }
    
    protected CidrFilesViewHelper createHelper() {
        return new CidrFilesViewHelper(this);
    }
    
    @Override
    public String getTitle() {
        return CidrFilesViewHelper.TITLE;
    }
    
    @Override
    public Icon getIcon() {
        return CidrFilesViewHelper.ICON;
    }
    
    @NotNull
    @Override
    public String getId() {
        String s;
        try {
            s = "ProjectPane";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/projectView/CMakeProjectView", "getId"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return s;
    }
    
    @Override
    public int getWeight() {
        return 1;
    }
    
    @Override
    protected ProjectAbstractTreeStructureBase createStructure() {
        return this.myHelper.createStructure(this.myProject, this.getId());
    }
    
    @Override
    protected ProjectViewTree createTree(final DefaultTreeModel defaultTreeModel) {
        return this.myHelper.createTree(this.myProject, defaultTreeModel);
    }
    
    private static IllegalStateException c(final IllegalStateException ex) {
        return ex;
    }
}
