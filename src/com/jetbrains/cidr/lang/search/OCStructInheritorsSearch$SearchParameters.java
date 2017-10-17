// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.psi.search.GlobalSearchScope;

public static class SearchParameters
{
    private GlobalSearchScope myScope;
    private OCQualifiedName myStructName;
    private PsiFile myFile;
    private Project myProject;
    
    public SearchParameters(final GlobalSearchScope myScope, final OCQualifiedName myStructName, final PsiFile myFile, final Project myProject) {
        this.myScope = myScope;
        this.myStructName = myStructName;
        this.myFile = myFile;
        this.myProject = myProject;
    }
    
    public GlobalSearchScope getScope() {
        return this.myScope;
    }
    
    public OCQualifiedName getStructName() {
        return this.myStructName;
    }
    
    public PsiFile getFile() {
        return this.myFile;
    }
    
    public Project getProject() {
        return this.myProject;
    }
}
