// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

protected static class Member
{
    private OCSymbol mySymbol;
    private PsiElement myElement;
    private OCMemberInfo myMemberInfo;
    private boolean isInnerClassMember;
    
    public Member(final OCSymbol mySymbol, @Nullable final PsiElement myElement, final OCMemberInfo myMemberInfo) {
        this.mySymbol = mySymbol;
        this.myElement = myElement;
        this.myMemberInfo = myMemberInfo;
    }
    
    public OCSymbol getSymbol() {
        return this.mySymbol;
    }
    
    @Nullable
    public PsiElement getElement() {
        return this.myElement;
    }
    
    public OCMemberInfo getMemberInfo() {
        return this.myMemberInfo;
    }
    
    @Nullable
    public OCVisibility getVisibility() {
        return this.myMemberInfo.getVisibility();
    }
    
    public boolean isInnerClassMember() {
        return this.isInnerClassMember;
    }
    
    public void setInnerClassMember(final boolean isInnerClassMember) {
        this.isInnerClassMember = isInnerClassMember;
    }
}
