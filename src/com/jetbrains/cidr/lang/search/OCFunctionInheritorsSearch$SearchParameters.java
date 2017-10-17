// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;

public static class SearchParameters
{
    private GlobalSearchScope myScope;
    private Project myProject;
    private String myFunctionName;
    private OCType myType;
    private OCFile myContext;
    private OCQualifiedName myParentStruct;
    private boolean myIncludeSameSymbols;
    private boolean myIsFriend;
    private boolean myIsFriendOrStatic;
    private boolean myIncludeOnlyDirectInheritors;
    private boolean myPredefinitionsThenImplementations;
    private boolean myImplementationsThenPredefinitions;
    private MultiMap<OCQualifiedName, OCQualifiedName> myInheritenceCache;
    
    public Project getProject() {
        return this.myProject;
    }
    
    public GlobalSearchScope getScope() {
        return this.myScope;
    }
    
    public String getFunctionName() {
        return this.myFunctionName;
    }
    
    public OCType getType() {
        return this.myType;
    }
    
    public OCQualifiedName getParentStruct() {
        return this.myParentStruct;
    }
    
    public boolean isFriend() {
        return this.myIsFriend;
    }
    
    public void setIncludeSameSymbols(final boolean myIncludeSameSymbols) {
        this.myIncludeSameSymbols = myIncludeSameSymbols;
    }
    
    public void setIncludeOnlyDirectInheritors(final boolean myIncludeOnlyDirectInheritors) {
        this.myIncludeOnlyDirectInheritors = myIncludeOnlyDirectInheritors;
    }
    
    public void setPredeclarationsThenImplementations(final boolean myPredefinitionsThenImplementations) {
        this.myPredefinitionsThenImplementations = myPredefinitionsThenImplementations;
    }
    
    public void setImplementationsThenPredeclarations(final boolean myImplementationsThenPredefinitions) {
        this.myImplementationsThenPredefinitions = myImplementationsThenPredefinitions;
    }
    
    public static SearchParameters getSearchFunctionByNameParameters(final OCFile myContext, final GlobalSearchScope myScope, final String myFunctionName, final OCQualifiedName myParentStruct) {
        final SearchParameters searchParameters = new SearchParameters();
        searchParameters.myProject = myContext.getProject();
        searchParameters.myContext = myContext;
        searchParameters.myScope = myScope;
        searchParameters.myFunctionName = myFunctionName;
        searchParameters.myParentStruct = myParentStruct;
        return searchParameters;
    }
    
    public static SearchParameters getSearchFunctionBySignatureParameters(final OCFile myContext, final GlobalSearchScope myScope, final String myFunctionName, final OCType myType, final OCQualifiedName myParentStruct) {
        final SearchParameters searchParameters = new SearchParameters();
        searchParameters.myProject = myContext.getProject();
        searchParameters.myContext = myContext;
        searchParameters.myScope = myScope;
        searchParameters.myFunctionName = myFunctionName;
        searchParameters.myType = myType;
        searchParameters.myParentStruct = myParentStruct;
        return searchParameters;
    }
    
    @Override
    public String toString() {
        return "SearchParameters{myFunctionName='" + this.myFunctionName + '\'' + ", myType=" + this.myType + ", myContext=" + this.myContext + ", myIncludeSameSymbols=" + this.myIncludeSameSymbols + ", myIsFriend=" + this.myIsFriend + ", myIsFriendOrStatic=" + this.myIsFriendOrStatic + '}';
    }
}
