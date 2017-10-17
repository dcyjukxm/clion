// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.intellij.util.containers.ContainerUtil;
import java.util.ArrayList;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCParameterInfo;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.List;

public class OCGenerateMethodActionContext extends OCGenerateFromIvarsActionContext
{
    private List<OCMethodSymbol> myBaseMethods;
    private String myMethodSignature;
    private List<OCParameterInfo> myParameters;
    private boolean myDeleteExisting;
    private List<OCInstanceVariableSymbol> myNonReleasedIvars;
    
    public OCGenerateMethodActionContext(final OCClassSymbol ocClassSymbol, final List<OCMethodSymbol> myBaseMethods, final OCObjectType ocObjectType, final PsiElement psiElement) {
        super(ocClassSymbol, ocObjectType, psiElement);
        this.myNonReleasedIvars = new ArrayList<OCInstanceVariableSymbol>();
        this.myBaseMethods = myBaseMethods;
    }
    
    @Override
    public boolean isValid() {
        return super.isValid() && this.myImplementationSymbol != null && !this.myBaseMethods.isEmpty() && !ContainerUtil.exists((Iterable)this.myBaseMethods, ocMethodSymbol -> ocMethodSymbol == null);
    }
    
    public OCMethodSymbol getBaseMethod() {
        return this.myBaseMethods.get(0);
    }
    
    public List<OCMethodSymbol> getBaseMethods() {
        return this.myBaseMethods;
    }
    
    public String getMethodSignature() {
        return this.myMethodSignature;
    }
    
    public void setMethodSignature(final String myMethodSignature) {
        this.myMethodSignature = myMethodSignature;
    }
    
    public List<OCParameterInfo> getParameters() {
        return this.myParameters;
    }
    
    public void setParameters(final List<OCParameterInfo> myParameters) {
        this.myParameters = myParameters;
    }
    
    public boolean isDeleteExisting() {
        return this.myDeleteExisting;
    }
    
    public void setDeleteExisting(final boolean myDeleteExisting) {
        this.myDeleteExisting = myDeleteExisting;
    }
    
    public List<OCInstanceVariableSymbol> getNonReleasedIvars() {
        return this.myNonReleasedIvars;
    }
}
