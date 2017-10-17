// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.intellij.refactoring.classMembers.MemberInfoBase;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.classMembers.DependentMembersCollectorBase;
import com.intellij.refactoring.classMembers.ClassMembersRefactoringSupport;

public class OCClassMembersRefactoringSupport implements ClassMembersRefactoringSupport
{
    public DependentMembersCollectorBase createDependentMembersCollector(final Object o, final Object o2) {
        return new OCDependentMembersCollector((PsiElement)o, (PsiElement)o2);
    }
    
    public boolean isProperMember(final MemberInfoBase memberInfoBase) {
        return true;
    }
}
