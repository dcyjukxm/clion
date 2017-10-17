// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.NotNull;
import java.util.Locale;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Comparator;

public static class VirtualFileComparator implements Comparator<VirtualFile>
{
    private final Project myProject;
    private final String myHeaderNameWithoutExtension;
    
    public VirtualFileComparator(final Project myProject, final String s) {
        this.myProject = myProject;
        this.myHeaderNameWithoutExtension = s.toUpperCase(Locale.getDefault());
    }
    
    @Override
    public int compare(@NotNull final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root1", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil$VirtualFileComparator", "compare"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root2", "com/jetbrains/cidr/lang/preprocessor/OCInclusionContextUtil$VirtualFileComparator", "compare"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int compare = Comparing.compare(OCInclusionContext.isPrecompiledHeader(virtualFile, this.myProject), OCInclusionContext.isPrecompiledHeader(virtualFile2, this.myProject));
        try {
            if (compare != 0) {
                return -compare;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final int compare2 = Comparing.compare(OCFileTypeHelpers.isSourceFile(virtualFile.getName()), OCFileTypeHelpers.isSourceFile(virtualFile2.getName()));
        try {
            if (compare2 != 0) {
                return -compare2;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final String upperCase = virtualFile.getNameWithoutExtension().toUpperCase(Locale.getDefault());
        final String upperCase2 = virtualFile2.getNameWithoutExtension().toUpperCase(Locale.getDefault());
        final int compare3 = Comparing.compare(this.myHeaderNameWithoutExtension.equals(upperCase), this.myHeaderNameWithoutExtension.equals(upperCase2));
        try {
            if (compare3 != 0) {
                return -compare3;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final int compare4 = Comparing.compare((Comparable)upperCase, (Comparable)upperCase2);
        try {
            if (compare4 != 0) {
                return compare4;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return Comparing.compare((Comparable)virtualFile.getPath(), (Comparable)virtualFile2.getPath());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
