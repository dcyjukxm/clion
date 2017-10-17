// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.psi.PsiElement;

class 1AddMember
{
    PsiElement anchor;
    PsiElement member;
    OCMemberSymbol symbol;
    
    1AddMember(final PsiElement anchor, final PsiElement member, final OCMemberSymbol symbol) {
        this.anchor = anchor;
        this.member = member;
        this.symbol = symbol;
    }
}
