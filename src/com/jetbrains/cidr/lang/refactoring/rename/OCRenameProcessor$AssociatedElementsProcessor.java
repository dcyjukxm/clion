// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.jetbrains.cidr.lang.symbols.objc.OCCompatibilityAliasSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;

interface AssociatedElementsProcessor
{
    boolean processIvar(final OCInstanceVariableSymbol p0, final OCPropertySymbol p1);
    
    boolean processPropertyAccessors(final OCPropertySymbol p0);
    
    boolean processProperty(final OCPropertySymbol p0, final OCSymbol p1);
    
    boolean processClassAlias(final OCClassSymbol p0, final OCCompatibilityAliasSymbol p1);
    
    boolean processClass(final OCCompatibilityAliasSymbol p0, final OCClassSymbol p1);
}
