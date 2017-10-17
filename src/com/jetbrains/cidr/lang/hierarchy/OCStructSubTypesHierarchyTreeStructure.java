// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import java.util.List;
import com.intellij.psi.NavigatablePsiElement;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.search.OCDirectStructInheritorsSearch;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.intellij.openapi.project.Project;
import com.intellij.ide.hierarchy.HierarchyTreeStructure;

public class OCStructSubTypesHierarchyTreeStructure extends HierarchyTreeStructure
{
    public OCStructSubTypesHierarchyTreeStructure(final Project project, final OCStruct ocStruct) {
        super(project, new OCClassHierarchyNodeDescriptor<Object>(project, null, ocStruct, true));
    }
    
    @NotNull
    @Override
    protected Object[] buildChildren(@NotNull final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        try {
            if (hierarchyNodeDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/hierarchy/OCStructSubTypesHierarchyTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCStruct ocStruct = ((OCClassHierarchyNodeDescriptor)hierarchyNodeDescriptor).getType();
        final ArrayList<Object> list = new ArrayList<Object>();
        OCDirectStructInheritorsSearch.search(ocStruct).forEach(ocStructSymbol -> {
            final OCStruct ocStruct = ((OCSymbolBase<OCStruct>)ocStructSymbol).locateDefinition();
            try {
                if (ocStruct != null) {
                    list.add(ocStruct);
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/OCStructSubTypesHierarchyTreeStructure", "buildChildren"));
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
