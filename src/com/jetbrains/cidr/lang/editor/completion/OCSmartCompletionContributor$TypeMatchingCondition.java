// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.types.OCTypeGuesser;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.cpp.OCThisSelfSuperSymbol;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public static class TypeMatchingCondition<T extends OCSymbol> implements Condition<T>
{
    private final OCFile myFile;
    private final PsiElement myContext;
    private final OCType myExpectedType;
    private final boolean myStrictTypeMatch;
    private final OCSymbol myBannedSymbol;
    private final boolean myAllowPointersForBooleans;
    private boolean myAvoidNSObjectMethods;
    private boolean myAcceptTypeDeclarations;
    private OCObjectTypeContext myReceiverContext;
    private OCType myQualifierType;
    
    public TypeMatchingCondition(final OCFile ocFile, final PsiElement psiElement, final OCType ocType) {
        this(ocFile, psiElement, ocType, false, null);
    }
    
    public TypeMatchingCondition(final OCFile ocFile, final PsiElement psiElement, final OCType ocType, final boolean b, final OCSymbol ocSymbol) {
        this(ocFile, psiElement, ocType, b, ocSymbol, false);
    }
    
    public TypeMatchingCondition(final OCFile myFile, final PsiElement myContext, final OCType myExpectedType, final boolean myStrictTypeMatch, final OCSymbol myBannedSymbol, final boolean myAllowPointersForBooleans) {
        this.myFile = myFile;
        this.myContext = myContext;
        this.myExpectedType = myExpectedType;
        this.myStrictTypeMatch = myStrictTypeMatch;
        this.myBannedSymbol = myBannedSymbol;
        this.myAllowPointersForBooleans = myAllowPointersForBooleans;
        this.myAvoidNSObjectMethods = true;
    }
    
    public void acceptTypeDeclarations(final boolean myAcceptTypeDeclarations) {
        this.myAcceptTypeDeclarations = myAcceptTypeDeclarations;
    }
    
    public void avoidNSObjectMethods(final boolean myAvoidNSObjectMethods) {
        this.myAvoidNSObjectMethods = myAvoidNSObjectMethods;
    }
    
    public void setReceiverContext(final OCObjectTypeContext myReceiverContext) {
        this.myReceiverContext = myReceiverContext;
    }
    
    public void setQualifierType(final OCType myQualifierType) {
        this.myQualifierType = myQualifierType;
    }
    
    public boolean value(final OCSymbol ocSymbol) {
        if (Comparing.equal((Object)ocSymbol, (Object)this.myBannedSymbol)) {
            return false;
        }
        final OCType symbolType = this.getSymbolType(ocSymbol, this.myContext);
        if (symbolType != null) {
            if (symbolType.isUnknown()) {
                return false;
            }
            if (symbolType.isPointerToID() && (!ocSymbol.getKind().isLocal() || ocSymbol instanceof OCThisSelfSuperSymbol)) {
                if (!this.myExpectedType.isPointerToID()) {
                    return false;
                }
                final OCType terminalType = this.myExpectedType.getTerminalType();
                final OCType terminalType2 = symbolType.getTerminalType();
                if (terminalType instanceof OCObjectType && terminalType2 instanceof OCObjectType && ((OCObjectType)terminalType2).getAllProtocols().isEmpty() && !((OCObjectType)terminalType).getAllProtocols().isEmpty()) {
                    return false;
                }
            }
            if (this.myExpectedType.isCompatible(symbolType, (PsiElement)this.myFile)) {
                return (!this.myAvoidNSObjectMethods || !(ocSymbol instanceof OCMethodSymbol) || !"NSObject".equals(((OCSymbolWithParent<T, OCClassSymbol>)ocSymbol).getParent().getName()) || !symbolType.equals(ocSymbol.getEffectiveType().resolve((PsiFile)this.myFile), this.myContext)) && (!symbolType.isPointerToVoid() || this.myExpectedType.isPointerToVoid()) && (this.myAllowPointersForBooleans || symbolType.isPointer() == this.myExpectedType.isPointer()) && OCVisibility.isVisible(ocSymbol, this.myContext, this.myQualifierType) && (!this.myStrictTypeMatch || Comparing.equal(this.myExpectedType.getAliasName(), symbolType.getAliasName()));
            }
        }
        return false;
    }
    
    @Nullable
    protected OCType getSymbolType(final OCSymbol ocSymbol, final PsiElement psiElement) {
        if (ocSymbol instanceof OCMethodSymbol && this.myReceiverContext != null) {
            final OCType methodGuessedReturnType = OCTypeGuesser.getMethodGuessedReturnType((OCMethodSymbol)ocSymbol, this.myReceiverContext, null, psiElement);
            if (methodGuessedReturnType != null) {
                return methodGuessedReturnType.resolve((PsiFile)this.myFile);
            }
        }
        OCType ocType;
        if (this.myAcceptTypeDeclarations) {
            if (ocSymbol instanceof OCStructSymbol) {
                ocType = ocSymbol.getType();
            }
            else {
                ocType = ocSymbol.getEffectiveType();
            }
        }
        else if ((ocSymbol instanceof OCDeclaratorSymbol && ocSymbol.getKind() != OCSymbolKind.TYPEDEF) || ocSymbol instanceof OCMemberSymbol || ocSymbol instanceof OCFunctionSymbol) {
            ocType = ocSymbol.getEffectiveType();
        }
        else {
            ocType = null;
        }
        return (ocType != null) ? ocType.resolve((PsiFile)this.myFile) : null;
    }
}
