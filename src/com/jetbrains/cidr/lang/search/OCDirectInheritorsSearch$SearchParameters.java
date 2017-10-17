// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;

public static class SearchParameters
{
    private final GlobalSearchScope myScope;
    private final String myClassName;
    private final boolean isProtocol;
    private final Project myProject;
    private final boolean myPreferImplementations;
    
    public SearchParameters(final GlobalSearchScope myScope, final String myClassName, final boolean isProtocol, final Project myProject, final boolean myPreferImplementations) {
        this.myScope = myScope;
        this.myClassName = myClassName;
        this.isProtocol = isProtocol;
        this.myProject = myProject;
        this.myPreferImplementations = myPreferImplementations;
    }
    
    public GlobalSearchScope getScope() {
        return this.myScope;
    }
    
    public String getClassName() {
        return this.myClassName;
    }
    
    public Project getProject() {
        return this.myProject;
    }
    
    public boolean isProtocol() {
        return this.isProtocol;
    }
    
    public boolean isPreferImplementations() {
        return this.myPreferImplementations;
    }
}
