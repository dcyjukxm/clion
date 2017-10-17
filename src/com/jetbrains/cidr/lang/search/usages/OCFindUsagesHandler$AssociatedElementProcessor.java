// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.psi.PsiElement;
import com.intellij.util.Processor;

interface AssociatedElementProcessor extends Processor<PsiElement>
{
    boolean proceedProperty(final OCPropertySymbol p0);
    
    boolean proceedIvar(final OCInstanceVariableSymbol p0);
    
    boolean proceedDerivedClasses();
}
