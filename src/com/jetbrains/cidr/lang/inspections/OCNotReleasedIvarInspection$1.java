// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.codeInspection.ProblemsHolder;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCNotReleasedIvarInspection$1 extends OCRecursiveVisitor {
    private OCMethodSymbol curMethod;
    final /* synthetic */ ProblemsHolder val$holder;
    final /* synthetic */ IvarsInfo val$ivarsInfo;
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        final OCInstanceVariableSymbol receiverIvar = OCNotReleasedIvarInspection.getReceiverIvar(ocSendMessageExpression.getReceiverExpression());
        if ("retain".equals(ocSendMessageExpression.getMessageSelector()) && receiverIvar != null && !OCNotReleasedIvarInspection.access$700(receiverIvar, (PsiElement)ocSendMessageExpression, this.curMethod, this.val$holder.getFile(), this.val$ivarsInfo) && !(ocSendMessageExpression.getParent() instanceof OCAssignmentExpression)) {
            OCNotReleasedIvarInspection.this.reportWarning(receiverIvar, (PsiElement)ocSendMessageExpression.getReceiverExpression(), this.val$holder);
        }
    }
    
    @Override
    public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
        final OCInstanceVariableSymbol receiverIvar = OCNotReleasedIvarInspection.getReceiverIvar(ocAssignmentExpression.getReceiverExpression());
        final OCExpression sourceExpression = ocAssignmentExpression.getSourceExpression();
        if (OCElementUtil.isRetainCall((PsiElement)sourceExpression, true) && receiverIvar != null && !OCNotReleasedIvarInspection.access$700(receiverIvar, (PsiElement)ocAssignmentExpression, this.curMethod, this.val$holder.getFile(), this.val$ivarsInfo)) {
            if (sourceExpression instanceof OCSendMessageExpression) {
                final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)sourceExpression;
                final OCInstanceVariableSymbol receiverIvar2 = OCNotReleasedIvarInspection.getReceiverIvar(ocSendMessageExpression.getReceiverExpression());
                if ("retain".equals(ocSendMessageExpression.getMessageSelector()) && receiverIvar2 != null && OCNotReleasedIvarInspection.access$700(receiverIvar2, (PsiElement)ocSendMessageExpression, this.curMethod, this.val$holder.getFile(), this.val$ivarsInfo)) {
                    return;
                }
            }
            OCNotReleasedIvarInspection.this.reportWarning(receiverIvar, (PsiElement)ocAssignmentExpression.getReceiverExpression(), this.val$holder);
        }
    }
    
    @Override
    public void visitSynthesizeProperty(final OCSynthesizeProperty ocSynthesizeProperty) {
        final OCReferenceElement propertyRef = ocSynthesizeProperty.getPropertyRef();
        final OCReferenceElement instanceVariableRef = ocSynthesizeProperty.getInstanceVariableRef();
        if (propertyRef == null) {
            return;
        }
        OCPropertySymbol associatedPropertyInPrivateCategory = (OCPropertySymbol)propertyRef.resolveToSymbol();
        if (associatedPropertyInPrivateCategory != null && !associatedPropertyInPrivateCategory.isRetained()) {
            associatedPropertyInPrivateCategory = associatedPropertyInPrivateCategory.getAssociatedPropertyInPrivateCategory();
        }
        OCInstanceVariableSymbol ocInstanceVariableSymbol = (associatedPropertyInPrivateCategory != null) ? associatedPropertyInPrivateCategory.getAssociatedIvar() : null;
        if (ocInstanceVariableSymbol == null) {
            final OCSynthesizeSymbol ocSynthesizeSymbol = ocSynthesizeProperty.getSymbol();
            ocInstanceVariableSymbol = ((ocSynthesizeSymbol != null) ? ocSynthesizeSymbol.getIvarSymbol() : null);
        }
        if (associatedPropertyInPrivateCategory != null && ocInstanceVariableSymbol != null && associatedPropertyInPrivateCategory.isRetained() && !OCNotReleasedIvarInspection.isIvarReleased(ocInstanceVariableSymbol, this.val$holder.getFile(), this.val$ivarsInfo)) {
            OCNotReleasedIvarInspection.this.reportWarning(ocInstanceVariableSymbol, (PsiElement)((instanceVariableRef != null) ? instanceVariableRef : propertyRef), this.val$holder);
        }
    }
    
    @Override
    public void visitMethod(final OCMethod ocMethod) {
        this.curMethod = ocMethod.getSymbol();
        super.visitMethod(ocMethod);
        this.curMethod = null;
    }
}