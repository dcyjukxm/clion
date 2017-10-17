// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import java.util.Set;
import gnu.trove.THashSet;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;

public class FrameworksSearchRoot extends HeadersSearchRoot
{
    @NotNull
    private final VirtualFile myRoot;
    
    public FrameworksSearchRoot(@NotNull final Project project, @NotNull final VirtualFile myRoot) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/headerRoots/FrameworksSearchRoot", "<init>"));
        }
        if (myRoot == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root", "com/jetbrains/cidr/lang/workspace/headerRoots/FrameworksSearchRoot", "<init>"));
        }
        super(project);
        this.myRoot = myRoot;
    }
    
    public VirtualFile getVirtualFile() {
        return this.myRoot;
    }
    
    public boolean processChildren(final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor) {
        return AppleFramework.processFrameworksUnder((PsiFileSystemItem)this, this.myRoot, psiElementProcessor, (Set<String>)new THashSet());
    }
    
    public String toString() {
        return "FrameworksSearchRoot: " + this.myRoot;
    }
    
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final FrameworksSearchRoot frameworksSearchRoot = this;
                final Class<? extends FrameworksSearchRoot> clazz = frameworksSearchRoot.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final FrameworksSearchRoot frameworksSearchRoot = this;
                final Class<? extends FrameworksSearchRoot> clazz = frameworksSearchRoot.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final FrameworksSearchRoot frameworksSearchRoot2 = (FrameworksSearchRoot)o;
        try {
            if (!this.myRoot.equals(frameworksSearchRoot2.myRoot)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return true;
    }
    
    public int hashCode() {
        return this.myRoot.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
