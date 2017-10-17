// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.Set;

class OCFindUsagesHandler$2 implements AssociatedElementProcessor {
    final /* synthetic */ OCFindUsagesOptions val$options;
    final /* synthetic */ Set val$result;
    
    @Override
    public boolean proceedProperty(final OCPropertySymbol ocPropertySymbol) {
        return this.val$options.isSearchForProperties;
    }
    
    @Override
    public boolean proceedIvar(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
        return this.val$options.isSearchForIvars;
    }
    
    @Override
    public boolean proceedDerivedClasses() {
        return this.val$options.isSearchForDerivedClasses;
    }
    
    public boolean process(final PsiElement psiElement) {
        this.val$result.add(psiElement);
        return true;
    }
}