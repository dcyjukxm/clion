// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import java.util.Iterator;
import java.util.Collection;
import com.intellij.psi.NavigatablePsiElement;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import org.jetbrains.annotations.NotNull;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.project.Project;
import com.intellij.ide.hierarchy.HierarchyTreeStructure;

public class OCImportSuperHierarchyTreeStructure extends HierarchyTreeStructure
{
    public OCImportSuperHierarchyTreeStructure(final Project project, final OCFile ocFile) {
        super(project, new OCClassHierarchyNodeDescriptor<Object>(project, null, ocFile, true));
    }
    
    @NotNull
    @Override
    protected Object[] buildChildren(@NotNull final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        try {
            if (hierarchyNodeDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/hierarchy/OCImportSuperHierarchyTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCFile ocFile = ((OCClassHierarchyNodeDescriptor)hierarchyNodeDescriptor).getType();
        FileSymbolTablesCache.getInstance(this.myProject).ensurePendingFilesProcessed();
        final Collection<OCFile> includingFiles = ocFile.getIncludingFiles();
        final HierarchyNodeDescriptor[] array = new HierarchyNodeDescriptor[includingFiles.size()];
        int n = 0;
        final Iterator<OCFile> iterator = includingFiles.iterator();
        while (iterator.hasNext()) {
            array[n] = new OCClassHierarchyNodeDescriptor<Object>(this.myProject, hierarchyNodeDescriptor, (NavigatablePsiElement)iterator.next(), false);
            ++n;
        }
        HierarchyNodeDescriptor[] array2;
        try {
            array2 = array;
            if (array2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/OCImportSuperHierarchyTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return array2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
