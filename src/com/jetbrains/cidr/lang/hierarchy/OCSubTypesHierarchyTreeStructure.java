// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.List;
import com.intellij.psi.NavigatablePsiElement;
import com.jetbrains.cidr.lang.search.OCDirectInheritorsSearch;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.ide.hierarchy.HierarchyTreeStructure;

public class OCSubTypesHierarchyTreeStructure extends HierarchyTreeStructure
{
    public OCSubTypesHierarchyTreeStructure(final Project project, final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        super(project, hierarchyNodeDescriptor);
    }
    
    public OCSubTypesHierarchyTreeStructure(final Project project, final OCClassDeclaration ocClassDeclaration) {
        super(project, new OCClassHierarchyNodeDescriptor<Object>(project, null, ocClassDeclaration, true));
    }
    
    @NotNull
    @Override
    protected Object[] buildChildren(@NotNull final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        try {
            if (hierarchyNodeDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/hierarchy/OCSubTypesHierarchyTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCClassDeclaration ocClassDeclaration = ((OCClassHierarchyNodeDescriptor)hierarchyNodeDescriptor).getType();
        final ArrayList<Object> list = new ArrayList<Object>();
        OCDirectInheritorsSearch.search(ocClassDeclaration).forEach(ocClassSymbol -> {
            final OCClassDeclarationBase ocClassDeclarationBase = ocClassSymbol.locateDefinition();
            try {
                if (ocClassDeclarationBase instanceof OCClassDeclaration) {
                    list.add(ocClassDeclarationBase);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return true;
        });
        final HierarchyNodeDescriptor[] array = new HierarchyNodeDescriptor[list.size()];
        int i = 0;
        try {
            while (i < array.length) {
                array[i] = new OCClassHierarchyNodeDescriptor<Object>(this.myProject, hierarchyNodeDescriptor, list.get(i), false);
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/OCSubTypesHierarchyTreeStructure", "buildChildren"));
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
