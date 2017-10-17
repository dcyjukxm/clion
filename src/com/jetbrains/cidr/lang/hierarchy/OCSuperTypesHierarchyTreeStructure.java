// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import java.util.List;
import com.intellij.psi.NavigatablePsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.openapi.project.Project;
import com.intellij.ide.hierarchy.HierarchyTreeStructure;

public class OCSuperTypesHierarchyTreeStructure extends HierarchyTreeStructure
{
    public OCSuperTypesHierarchyTreeStructure(final Project project, final OCClassDeclaration ocClassDeclaration) {
        super(project, new OCClassHierarchyNodeDescriptor<Object>(project, null, ocClassDeclaration, true));
    }
    
    @NotNull
    @Override
    protected Object[] buildChildren(@NotNull final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        try {
            if (hierarchyNodeDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/hierarchy/OCSuperTypesHierarchyTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List superTypes = ((OCClassHierarchyNodeDescriptor)hierarchyNodeDescriptor).getType().getSuperTypes();
        final HierarchyNodeDescriptor[] array = new HierarchyNodeDescriptor[superTypes.size()];
        int i = 0;
        try {
            while (i < array.length) {
                array[i] = new OCClassHierarchyNodeDescriptor<Object>(this.myProject, hierarchyNodeDescriptor, superTypes.get(i), false);
                ++i;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        HierarchyNodeDescriptor[] array2;
        try {
            array2 = array;
            if (array2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/OCSuperTypesHierarchyTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return array2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
