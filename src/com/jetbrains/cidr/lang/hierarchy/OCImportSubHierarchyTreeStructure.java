// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy;

import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import java.util.Set;
import java.util.Iterator;
import com.intellij.psi.NavigatablePsiElement;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.hash.HashSet;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.project.Project;
import com.intellij.ide.hierarchy.HierarchyTreeStructure;

public class OCImportSubHierarchyTreeStructure extends HierarchyTreeStructure
{
    public OCImportSubHierarchyTreeStructure(final Project project, final OCFile ocFile) {
        super(project, new OCClassHierarchyNodeDescriptor<Object>(project, null, ocFile, true));
    }
    
    @NotNull
    @Override
    protected Object[] buildChildren(@NotNull final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        try {
            if (hierarchyNodeDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/hierarchy/OCImportSubHierarchyTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiFile psiFile = ((OCClassHierarchyNodeDescriptor)hierarchyNodeDescriptor).getType();
        final HashSet set = new HashSet();
        try {
            if (psiFile instanceof OCFile) {
                ContainerUtil.process((List)((OCFile)psiFile).findIncludeDirectives(), ocIncludeDirective -> {
                    final PsiFile includedFile = ocIncludeDirective.getIncludedFile();
                    try {
                        if (includedFile != null) {
                            ((Set<PsiFile>)set).add(includedFile);
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    return true;
                });
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final HierarchyNodeDescriptor[] array = new HierarchyNodeDescriptor[((Set)set).size()];
        int n = 0;
        final Iterator<PsiFile> iterator = ((Set<PsiFile>)set).iterator();
        while (iterator.hasNext()) {
            array[n] = new OCClassHierarchyNodeDescriptor<Object>(this.myProject, hierarchyNodeDescriptor, (NavigatablePsiElement)iterator.next(), false);
            ++n;
        }
        HierarchyNodeDescriptor[] array2;
        try {
            array2 = array;
            if (array2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/OCImportSubHierarchyTreeStructure", "buildChildren"));
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
