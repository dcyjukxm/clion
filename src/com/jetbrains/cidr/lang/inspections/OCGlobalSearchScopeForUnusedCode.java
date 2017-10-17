// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;

public class OCGlobalSearchScopeForUnusedCode extends GlobalSearchScope
{
    private final GlobalSearchScope myScope;
    
    public OCGlobalSearchScopeForUnusedCode(final GlobalSearchScope myScope) {
        this.myScope = myScope;
    }
    
    public boolean contains(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/inspections/OCGlobalSearchScopeForUnusedCode", "contains"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myScope.contains(virtualFile);
    }
    
    public int compare(@NotNull final VirtualFile virtualFile, @NotNull final VirtualFile virtualFile2) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file1", "com/jetbrains/cidr/lang/inspections/OCGlobalSearchScopeForUnusedCode", "compare"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file2", "com/jetbrains/cidr/lang/inspections/OCGlobalSearchScopeForUnusedCode", "compare"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.myScope.compare(virtualFile, virtualFile2);
    }
    
    public boolean isSearchInModuleContent(@NotNull final Module module) {
        try {
            if (module == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "aModule", "com/jetbrains/cidr/lang/inspections/OCGlobalSearchScopeForUnusedCode", "isSearchInModuleContent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myScope.isSearchInModuleContent(module);
    }
    
    public boolean isSearchInLibraries() {
        return this.myScope.isSearchInLibraries();
    }
    
    public Project getProject() {
        return this.myScope.getProject();
    }
    
    public boolean isSearchInModuleContent(@NotNull final Module module, final boolean b) {
        try {
            if (module == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "aModule", "com/jetbrains/cidr/lang/inspections/OCGlobalSearchScopeForUnusedCode", "isSearchInModuleContent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myScope.isSearchInModuleContent(module, b);
    }
    
    public boolean isSearchOutsideRootModel() {
        return this.myScope.isSearchOutsideRootModel();
    }
    
    @NotNull
    public GlobalSearchScope intersectWith(@NotNull final GlobalSearchScope globalSearchScope) {
        try {
            if (globalSearchScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/lang/inspections/OCGlobalSearchScopeForUnusedCode", "intersectWith"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCGlobalSearchScopeForUnusedCode ocGlobalSearchScopeForUnusedCode;
        try {
            ocGlobalSearchScopeForUnusedCode = new OCGlobalSearchScopeForUnusedCode(super.intersectWith(globalSearchScope));
            if (ocGlobalSearchScopeForUnusedCode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCGlobalSearchScopeForUnusedCode", "intersectWith"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocGlobalSearchScopeForUnusedCode;
    }
    
    @NotNull
    public GlobalSearchScope uniteWith(@NotNull final GlobalSearchScope globalSearchScope) {
        try {
            if (globalSearchScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/lang/inspections/OCGlobalSearchScopeForUnusedCode", "uniteWith"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCGlobalSearchScopeForUnusedCode ocGlobalSearchScopeForUnusedCode;
        try {
            ocGlobalSearchScopeForUnusedCode = new OCGlobalSearchScopeForUnusedCode(super.uniteWith(globalSearchScope));
            if (ocGlobalSearchScopeForUnusedCode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCGlobalSearchScopeForUnusedCode", "uniteWith"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocGlobalSearchScopeForUnusedCode;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
