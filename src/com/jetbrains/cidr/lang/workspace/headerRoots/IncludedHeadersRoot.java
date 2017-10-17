// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.search.PsiElementProcessor;
import java.io.File;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapDefaultPathResolver;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapPathResolver;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;

public class IncludedHeadersRoot extends HeadersSearchRoot
{
    @NotNull
    private final VirtualFile myIncludedDir;
    private final boolean myRecursive;
    private final boolean myUserHeaders;
    
    public IncludedHeadersRoot(@NotNull final Project project, @NotNull final VirtualFile myIncludedDir, final boolean myRecursive, final boolean myUserHeaders) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot", "<init>"));
        }
        if (myIncludedDir == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "includedDir", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot", "<init>"));
        }
        super(project);
        this.myIncludedDir = myIncludedDir;
        this.myRecursive = myRecursive;
        this.myUserHeaders = myUserHeaders;
    }
    
    @NotNull
    @Override
    public String getName() {
        String s;
        try {
            s = "Included Headers";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public VirtualFile getVirtualFile() {
        VirtualFile myIncludedDir;
        try {
            myIncludedDir = this.myIncludedDir;
            if (myIncludedDir == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot", "getVirtualFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myIncludedDir;
    }
    
    @NotNull
    @Override
    protected ModuleMapPathResolver createModuleMapPathResolver() {
        ModuleMapDefaultPathResolver moduleMapDefaultPathResolver;
        try {
            moduleMapDefaultPathResolver = new ModuleMapDefaultPathResolver(this.myIncludedDir);
            if (moduleMapDefaultPathResolver == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot", "createModuleMapPathResolver"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return moduleMapDefaultPathResolver;
    }
    
    @NotNull
    @Override
    protected File findModuleMapFile() {
        File file = new File(this.myIncludedDir.getPath(), "module.modulemap");
        if (!file.exists()) {
            file = new File(this.myIncludedDir.getPath(), "module.map");
        }
        File file2;
        try {
            file2 = file;
            if (file2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/IncludedHeadersRoot", "findModuleMapFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return file2;
    }
    
    public boolean isRecursive() {
        return this.myRecursive;
    }
    
    @Override
    public boolean isUserHeaders() {
        return this.myUserHeaders;
    }
    
    public boolean processChildren(final PsiElementProcessor<PsiFileSystemItem> psiElementProcessor) {
        return this.processUnder(psiElementProcessor, this.myIncludedDir, this.myRecursive, null);
    }
    
    public String toString() {
        StringBuilder append2 = null;
        Label_0049: {
            StringBuilder append;
            try {
                append = new StringBuilder().append(this.getName()).append(" @ ").append(this.myIncludedDir).append(": ");
                if (this.myUserHeaders) {
                    final String s = "user";
                    break Label_0049;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final String s = "system";
            try {
                append2 = append.append(s);
                if (this.myRecursive) {
                    final String s2 = " [recursive]";
                    return append2.append(s2).toString();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final String s2 = "";
        return append2.append(s2).toString();
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
                final IncludedHeadersRoot includedHeadersRoot = this;
                final Class<? extends IncludedHeadersRoot> clazz = includedHeadersRoot.getClass();
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
                final IncludedHeadersRoot includedHeadersRoot = this;
                final Class<? extends IncludedHeadersRoot> clazz = includedHeadersRoot.getClass();
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
        final IncludedHeadersRoot includedHeadersRoot2 = (IncludedHeadersRoot)o;
        try {
            if (this.myRecursive != includedHeadersRoot2.myRecursive) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (this.myUserHeaders != includedHeadersRoot2.myUserHeaders) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!this.myIncludedDir.equals(includedHeadersRoot2.myIncludedDir)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return true;
    }
    
    public int hashCode() {
        final int hashCode = this.myIncludedDir.hashCode();
        int n = 0;
        int n2 = 0;
        Label_0028: {
            try {
                n = 31 * hashCode;
                if (this.myRecursive) {
                    n2 = 1;
                    break Label_0028;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            n2 = 0;
        }
        final int n3 = n + n2;
        int n4;
        try {
            n4 = 31 * n3;
            if (this.myUserHeaders) {
                final int n5 = 1;
                return n4 + n5;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int n5 = 0;
        return n4 + n5;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
