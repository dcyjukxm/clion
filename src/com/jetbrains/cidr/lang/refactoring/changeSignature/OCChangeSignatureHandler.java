// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCType;

public interface OCChangeSignatureHandler
{
    void invoke();
    
    void invokeSynchronously();
    
    void setTitle(final String p0);
    
    void setHelpId(final String p0);
    
    void setCallableKind(final OCCallableKind p0);
    
    void setName(final String p0);
    
    void setReturnType(final OCType p0);
    
    void setParentClass(final OCSymbol p0, final boolean p1, final List<? extends OCClassSymbol> p2);
    
    OCParameterInfo addParameter(final String p0, final OCType p1, final int p2);
    
    OCParameterInfo addParameter(final String p0, final String p1, final OCType p2, final int p3, final boolean p4);
    
    OCParameterInfo insertParameter(final String p0, final OCType p1, final int p2);
    
    OCParameterInfo insertParameter(final String p0, final String p1, final OCType p2, final int p3, final int p4, final boolean p5);
    
    OCParameterInfo insertParameter(final String p0, final String p1, final OCType p2, final String p3, final int p4, final int p5, final boolean p6);
    
    void addSelfParameter(final String p0);
    
    void removeParameter(final int p0);
    
    void removeParameter(final String p0, final boolean p1);
    
    void exchangeParameters(final int p0, final int p1);
    
    void renameSelector(final int p0, final String p1);
    
    void setChangeParentClassPossible(final boolean p0);
    
    void setNameVisible(final boolean p0);
    
    void setRefactorButtonText(final String p0);
    
    void setChangeUsages(final boolean p0);
    
    void setChangeAncestors(final boolean p0);
    
    OCGeneratedInfo getGeneratedInfo();
    
    OCMethodDescriptor getMethodDescriptor();
    
    String getNewSignature();
    
    List<OCParameterInfo> getParameters();
    
    List<OCCallable> getNewCallables();
    
    OCChangeInfo getChangeInfo();
}
