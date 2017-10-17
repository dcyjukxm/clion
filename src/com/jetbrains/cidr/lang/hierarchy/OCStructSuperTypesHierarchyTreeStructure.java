// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.NavigatablePsiElement;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.intellij.openapi.project.Project;
import com.intellij.ide.hierarchy.HierarchyTreeStructure;

public class OCStructSuperTypesHierarchyTreeStructure extends HierarchyTreeStructure
{
    public OCStructSuperTypesHierarchyTreeStructure(final Project project, final OCStruct ocStruct) {
        super(project, new OCClassHierarchyNodeDescriptor<Object>(project, null, ocStruct, true));
    }
    
    @NotNull
    @Override
    protected Object[] buildChildren(@NotNull final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        try {
            if (hierarchyNodeDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/hierarchy/OCStructSuperTypesHierarchyTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCStruct ocStruct = ((OCClassHierarchyNodeDescriptor)hierarchyNodeDescriptor).getType();
        final ArrayList<OCStruct> list = new ArrayList<OCStruct>();
        final OCStructSymbol ocStructSymbol = ocStruct.getSymbol();
        if (ocStructSymbol != null) {
            final OCFile containingOCFile = ocStructSymbol.getContainingOCFile();
            final Iterator<OCType> iterator = ocStructSymbol.getBaseCppClasses((PsiElement)containingOCFile).iterator();
            while (iterator.hasNext()) {
                final OCType resolve = iterator.next().resolve((PsiFile)containingOCFile);
                if (resolve instanceof OCStructType) {
                    for (OCStructSymbol ocStructSymbol2 : ((OCStructType)resolve).getStructs()) {
                        if (ocStructSymbol2.isPredeclaration()) {
                            final OCSymbol definitionSymbol = ocStructSymbol2.getDefinitionSymbol();
                            if (definitionSymbol != null) {
                                ocStructSymbol2 = (OCStructSymbol)definitionSymbol;
                            }
                        }
                        final OCStruct locateDefinition = ((OCSymbolBase<OCStruct>)ocStructSymbol2).locateDefinition();
                        try {
                            if (!(locateDefinition instanceof OCStruct)) {
                                continue;
                            }
                            list.add(locateDefinition);
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                }
            }
        }
        final HierarchyNodeDescriptor[] array = new HierarchyNodeDescriptor[list.size()];
        int i = 0;
        try {
            while (i < array.length) {
                array[i] = new OCClassHierarchyNodeDescriptor<Object>(this.myProject, hierarchyNodeDescriptor, list.get(i), false);
                ++i;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        HierarchyNodeDescriptor[] array2;
        try {
            array2 = array;
            if (array2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/OCStructSuperTypesHierarchyTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return array2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
