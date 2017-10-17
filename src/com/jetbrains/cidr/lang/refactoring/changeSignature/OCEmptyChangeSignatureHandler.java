// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCType;

public class OCEmptyChangeSignatureHandler implements OCChangeSignatureHandler
{
    public static OCEmptyChangeSignatureHandler INSTANCE;
    
    @Override
    public void invoke() {
    }
    
    @Override
    public void invokeSynchronously() {
    }
    
    @Override
    public void setTitle(final String s) {
    }
    
    @Override
    public void setHelpId(final String s) {
    }
    
    @Override
    public void setCallableKind(final OCCallableKind ocCallableKind) {
    }
    
    @Override
    public void setName(final String s) {
    }
    
    @Override
    public void setReturnType(final OCType ocType) {
    }
    
    @Override
    public void setParentClass(final OCSymbol ocSymbol, final boolean b, final List<? extends OCClassSymbol> list) {
    }
    
    @Override
    public OCParameterInfo addParameter(final String s, final OCType ocType, final int n) {
        return null;
    }
    
    @Override
    public OCParameterInfo addParameter(final String s, final String s2, final OCType ocType, final int n, final boolean b) {
        return null;
    }
    
    @Override
    public OCParameterInfo insertParameter(final String s, final String s2, final OCType ocType, final int n, final int n2, final boolean b) {
        return null;
    }
    
    @Override
    public OCParameterInfo insertParameter(final String s, final String s2, final OCType ocType, final String s3, final int n, final int n2, final boolean b) {
        return null;
    }
    
    @Override
    public OCParameterInfo insertParameter(final String s, final OCType ocType, final int n) {
        return null;
    }
    
    @Override
    public void addSelfParameter(final String s) {
    }
    
    @Override
    public void removeParameter(final int n) {
    }
    
    @Override
    public void removeParameter(final String s, final boolean b) {
    }
    
    @Override
    public void exchangeParameters(final int n, final int n2) {
    }
    
    @Override
    public void setChangeParentClassPossible(final boolean b) {
    }
    
    @Override
    public void setNameVisible(final boolean b) {
    }
    
    @Override
    public OCGeneratedInfo getGeneratedInfo() {
        return new OCGeneratedInfo(null);
    }
    
    @Override
    public void setChangeUsages(final boolean b) {
    }
    
    @Override
    public void setChangeAncestors(final boolean b) {
    }
    
    @Override
    public String getNewSignature() {
        return "";
    }
    
    @Override
    public List<OCCallable> getNewCallables() {
        return null;
    }
    
    @Override
    public List<OCParameterInfo> getParameters() {
        return Collections.emptyList();
    }
    
    @Override
    public OCMethodDescriptor getMethodDescriptor() {
        return null;
    }
    
    @Override
    public OCChangeInfo getChangeInfo() {
        return null;
    }
    
    @Override
    public void setRefactorButtonText(final String s) {
    }
    
    @Override
    public void renameSelector(final int n, final String s) {
    }
    
    static {
        OCEmptyChangeSignatureHandler.INSTANCE = new OCEmptyChangeSignatureHandler();
    }
}
