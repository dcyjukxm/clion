// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;

public static class SearchParameters<T extends OCMemberSymbol>
{
    private GlobalSearchScope myScope;
    private Project myProject;
    private String mySelectorName;
    private OCObjectTypeContext.StaticMode myStaticMode;
    private OCClassSymbol myReceiverClass;
    private boolean myIncludeInterfaceAndProtocolResponders;
    private boolean myIncludeSelfImplementation;
    private boolean myInterfacesThenImplementations;
    private boolean myImplementationsThenInterfaces;
    private boolean myIncludeFromID;
    private boolean myInheritors;
    private boolean myAncestors;
    private Class<? extends T> myMemberClass;
    
    public Project getProject() {
        return this.myProject;
    }
    
    public GlobalSearchScope getScope() {
        return this.myScope;
    }
    
    public boolean isIncludeInterfaceAndProtocolResponders() {
        return this.myIncludeInterfaceAndProtocolResponders;
    }
    
    public boolean isIncludeSelfImplementation() {
        return this.myIncludeSelfImplementation;
    }
    
    public boolean isImplementationsThenInterfaces() {
        return this.myImplementationsThenInterfaces;
    }
    
    public boolean isInterfacesThenImplementations() {
        return this.myInterfacesThenImplementations;
    }
    
    public boolean isIncludeFromID() {
        return this.myIncludeFromID;
    }
    
    public boolean isAncestors() {
        return this.myAncestors;
    }
    
    public boolean isInheritors() {
        return this.myInheritors;
    }
    
    public String getSelectorName() {
        return this.mySelectorName;
    }
    
    public OCClassSymbol getReceiverClass() {
        return this.myReceiverClass;
    }
    
    public void setAncestors(final boolean myAncestors) {
        this.myAncestors = myAncestors;
    }
    
    public void setImplementationsThenInterfaces(final boolean myImplementationsThenInterfaces) {
        this.myImplementationsThenInterfaces = myImplementationsThenInterfaces;
    }
    
    public void setIncludeInterfaceAndProtocolResponders(final boolean myIncludeInterfaceAndProtocolResponders) {
        this.myIncludeInterfaceAndProtocolResponders = myIncludeInterfaceAndProtocolResponders;
    }
    
    public void setIncludeSelfImplementation(final boolean myIncludeSelfImplementation) {
        this.myIncludeSelfImplementation = myIncludeSelfImplementation;
    }
    
    public void setInheritors(final boolean myInheritors) {
        this.myInheritors = myInheritors;
    }
    
    public void setInterfacesThenImplementations(final boolean myInterfacesThenImplementations) {
        this.myInterfacesThenImplementations = myInterfacesThenImplementations;
    }
    
    public void setIncludeFromID(final boolean myIncludeFromID) {
        this.myIncludeFromID = myIncludeFromID;
    }
    
    public Class<? extends OCMemberSymbol> getMemberClass() {
        return this.myMemberClass;
    }
    
    public OCObjectTypeContext.StaticMode getStaticMode() {
        return this.myStaticMode;
    }
    
    private SearchParameters(final Project myProject, final GlobalSearchScope myScope, final String mySelectorName, final OCClassSymbol myReceiverClass, final Class<? extends T> myMemberClass, final OCObjectTypeContext.StaticMode myStaticMode) {
        this.myIncludeInterfaceAndProtocolResponders = true;
        this.myInheritors = true;
        this.myProject = myProject;
        this.myScope = myScope;
        this.mySelectorName = mySelectorName;
        this.myReceiverClass = myReceiverClass;
        this.myMemberClass = myMemberClass;
        this.myStaticMode = myStaticMode;
    }
    
    private SearchParameters() {
        this.myIncludeInterfaceAndProtocolResponders = true;
        this.myInheritors = true;
    }
}
