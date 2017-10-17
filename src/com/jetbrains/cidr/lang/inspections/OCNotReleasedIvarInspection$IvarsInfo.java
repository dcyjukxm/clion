// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import com.intellij.openapi.util.Pair;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import java.util.Set;

public static class IvarsInfo
{
    private Set<OCInstanceVariableSymbol> myReleasedIvars;
    private Map<OCInstanceVariableSymbol, Map<OCMethodSymbol, PsiElement>> myLocalReleases;
    private Set<OCSymbol> myTraversedCallables;
    private Map<OCMethodSymbol, PsiElement> myDeallocs;
    private List<Pair<OCInstanceVariableSymbol, PsiElement>> myLocalRetainedIvars;
    
    public IvarsInfo() {
        this.myReleasedIvars = new HashSet<OCInstanceVariableSymbol>();
        this.myLocalReleases = new HashMap<OCInstanceVariableSymbol, Map<OCMethodSymbol, PsiElement>>();
        this.myTraversedCallables = new HashSet<OCSymbol>();
        this.myDeallocs = new HashMap<OCMethodSymbol, PsiElement>();
        this.myLocalRetainedIvars = new ArrayList<Pair<OCInstanceVariableSymbol, PsiElement>>();
    }
}
