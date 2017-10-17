// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.jetbrains.cidr.lang.psi.OCInterface;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.openapi.project.Project;

public final class OCTypeHierarchyTreeStructure extends OCSubTypesHierarchyTreeStructure
{
    public OCTypeHierarchyTreeStructure(final Project project, final OCClassDeclaration ocClassDeclaration) {
        super(project, a(project, ocClassDeclaration));
        this.setBaseElement(this.myBaseDescriptor);
    }
    
    private static HierarchyNodeDescriptor a(final Project project, final OCClassDeclaration ocClassDeclaration) {
        HierarchyNodeDescriptor hierarchyNodeDescriptor = null;
        final Iterator<OCClassDeclaration> iterator = a(ocClassDeclaration).iterator();
        while (iterator.hasNext()) {
            final OCClassHierarchyNodeDescriptor ocClassHierarchyNodeDescriptor = new OCClassHierarchyNodeDescriptor<Object>(project, hierarchyNodeDescriptor, (NavigatablePsiElement)iterator.next(), false);
            if (hierarchyNodeDescriptor != null) {
                hierarchyNodeDescriptor.setCachedChildren(new HierarchyNodeDescriptor[] { ocClassHierarchyNodeDescriptor });
            }
            hierarchyNodeDescriptor = ocClassHierarchyNodeDescriptor;
        }
        final OCClassHierarchyNodeDescriptor ocClassHierarchyNodeDescriptor2 = new OCClassHierarchyNodeDescriptor<Object>(project, hierarchyNodeDescriptor, (NavigatablePsiElement)ocClassDeclaration, true);
        if (hierarchyNodeDescriptor != null) {
            hierarchyNodeDescriptor.setCachedChildren(new HierarchyNodeDescriptor[] { ocClassHierarchyNodeDescriptor2 });
        }
        return ocClassHierarchyNodeDescriptor2;
    }
    
    private static List<OCClassDeclaration> a(OCClassDeclaration ocInterface) {
        if (!ocInterface.isValid()) {
            return (List<OCClassDeclaration>)Collections.emptyList();
        }
        if (ocInterface instanceof OCProtocol) {
            return (List<OCClassDeclaration>)Collections.emptyList();
        }
        final ArrayList<OCInterface> list = (ArrayList<OCInterface>)new ArrayList<OCClassDeclaration>();
        while (!"NSObject".equals(ocInterface.getName())) {
            final OCInterface superClass = ocInterface.getSuperClass();
            if (superClass == null) {
                break;
            }
            if (list.contains(superClass)) {
                break;
            }
            list.add(0, superClass);
            ocInterface = superClass;
        }
        return (List<OCClassDeclaration>)list;
    }
}
