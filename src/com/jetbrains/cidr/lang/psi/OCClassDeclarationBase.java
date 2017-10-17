// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;

public interface OCClassDeclarationBase extends PsiElement, NavigatablePsiElement, PsiNameIdentifierOwner, OCSymbolDeclarator<OCClassSymbol>
{
}
