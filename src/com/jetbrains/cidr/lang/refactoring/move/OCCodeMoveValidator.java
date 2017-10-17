// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

public class OCCodeMoveValidator extends OCRecursiveVisitor
{
    private PsiElement myTargetContext;
    private String myOutOfScopeMessage;
    
    public OCCodeMoveValidator(final PsiElement myTargetContext) {
        this.myTargetContext = myTargetContext;
    }
    
    public boolean isOutOfScope() {
        return this.myOutOfScopeMessage != null;
    }
    
    public String getOutOfScopeMessage() {
        return this.myOutOfScopeMessage;
    }
    
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        super.visitReferenceExpression(ocReferenceExpression);
        final OCElementTypes.SelfSuperToken selfSuperToken = ocReferenceExpression.getSelfSuperToken();
        if (selfSuperToken != null) {
            if (this.myTargetContext == null) {
                this.myOutOfScopeMessage = "Using self/super";
                return;
            }
            final OCMethod ocMethod = (OCMethod)PsiTreeUtil.getParentOfType((PsiElement)ocReferenceExpression, (Class)OCMethod.class);
            final OCMethod ocMethod2 = (OCMethod)PsiTreeUtil.getParentOfType(this.myTargetContext, (Class)OCMethod.class);
            final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getParentOfType((PsiElement)ocMethod, (Class)OCClassDeclaration.class);
            final OCClassDeclaration ocClassDeclaration2 = (OCClassDeclaration)PsiTreeUtil.getParentOfType(this.myTargetContext, (Class)OCClassDeclaration.class);
            if (ocMethod == null || ocMethod2 == null || ocMethod.isInstanceMethod() != ocMethod2.isInstanceMethod()) {
                this.myOutOfScopeMessage = "using \"" + StringUtil.toLowerCase(selfSuperToken.name()) + "\" from " + ((ocMethod != null && ocMethod.isInstanceMethod()) ? "instance" : "class") + " method";
            }
            else if (ocClassDeclaration == null || ocClassDeclaration2 == null || !Comparing.equal(ocClassDeclaration.getName(), ocClassDeclaration2.getName())) {
                this.myOutOfScopeMessage = "using \"" + StringUtil.toLowerCase(selfSuperToken.name()) + "\" from another class";
            }
        }
        else if (ocReferenceExpression.isCppThis()) {
            final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)PsiTreeUtil.getContextOfType((PsiElement)ocReferenceExpression, false, new Class[] { OCFunctionDefinition.class });
            final OCFunctionDefinition ocFunctionDefinition2 = (OCFunctionDefinition)PsiTreeUtil.getContextOfType(this.myTargetContext, false, new Class[] { OCFunctionDefinition.class });
            final OCFunctionSymbol ocFunctionSymbol = (ocFunctionDefinition != null) ? ocFunctionDefinition.getSymbol() : null;
            final OCFunctionSymbol ocFunctionSymbol2 = (ocFunctionDefinition2 != null) ? ocFunctionDefinition2.getSymbol() : null;
            final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName = (ocFunctionSymbol != null) ? ocFunctionSymbol.getResolvedOwner() : null;
            final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName2 = (ocFunctionSymbol2 != null) ? ocFunctionSymbol2.getResolvedOwner() : null;
            if (ocSymbolWithQualifiedName != null && !ocSymbolWithQualifiedName.equals(ocSymbolWithQualifiedName2)) {
                this.myOutOfScopeMessage = "using \"this\" from another class";
            }
        }
        else {
            final OCSymbol resolveToSymbol = ocReferenceExpression.resolveToSymbol();
            if (resolveToSymbol != null && !isInScope(resolveToSymbol, this.myTargetContext)) {
                this.myOutOfScopeMessage = "using " + resolveToSymbol.getNameWithKindLowercase() + " which is out of scope";
            }
        }
    }
    
    public static boolean isInScope(final OCSymbol ocSymbol, final PsiElement psiElement) {
        if (ocSymbol instanceof OCInstanceVariableSymbol) {
            return isInAncestorClass(((OCSymbolWithParent<T, OCClassSymbol>)ocSymbol).getParent(), psiElement);
        }
        if (ocSymbol instanceof OCDeclaratorSymbol && !ocSymbol.isGlobal() && psiElement != null) {
            final TextRange scope = ocSymbol.getScope();
            final VirtualFile virtualFile = psiElement.getContainingFile().getVirtualFile();
            return scope != null && Comparing.equal((Object)virtualFile, (Object)ocSymbol.getContainingFile()) && scope.contains(psiElement.getTextOffset());
        }
        if (ocSymbol instanceof OCSymbolWithQualifiedName) {
            final OCSymbolWithQualifiedName resolvedOwner = ((OCSymbolWithQualifiedName)ocSymbol).getResolvedOwner();
            return resolvedOwner == null || isInAncestorClass(resolvedOwner, psiElement);
        }
        return true;
    }
    
    public static boolean isInAncestorClass(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        final PsiElement parentOfType = PsiTreeUtil.getParentOfType(psiElement, new Class[] { OCMethod.class, OCSynthesizeProperty.class });
        if (parentOfType == null || (parentOfType instanceof OCMethod && !((OCMethod)parentOfType).isInstanceMethod())) {
            return false;
        }
        final PsiElement parentOfType2 = PsiTreeUtil.getParentOfType(parentOfType, (Class)OCClassDeclaration.class);
        final OCClassSymbol ocClassSymbol2 = (parentOfType2 != null) ? ((OCClassDeclaration)parentOfType2).getSymbol() : null;
        return ocClassSymbol2 != null && ocClassSymbol2.isSubclass(ocClassSymbol);
    }
    
    public static boolean isInAncestorClass(final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final PsiElement psiElement) {
        final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)PsiTreeUtil.getParentOfType(psiElement, (Class)OCFunctionDefinition.class);
        final OCFunctionSymbol ocFunctionSymbol = (ocFunctionDefinition != null) ? ocFunctionDefinition.getSymbol() : null;
        for (OCSymbolWithParent<T, OCSymbolWithQualifiedName<T>> resolvedOwner = (OCSymbolWithParent<T, OCSymbolWithQualifiedName<T>>)((ocFunctionSymbol != null) ? ocFunctionSymbol.getResolvedOwner() : null); resolvedOwner != null; resolvedOwner = (OCSymbolWithParent<T, OCSymbolWithQualifiedName<T>>)((OCSymbolWithQualifiedName<PsiElement>)resolvedOwner).getResolvedOwner()) {
            if (((OCSymbolImpl)resolvedOwner).equals(ocSymbolWithQualifiedName)) {
                return true;
            }
            if (resolvedOwner instanceof OCStructSymbol && ocSymbolWithQualifiedName instanceof OCStructSymbol && ((OCStructSymbol)ocSymbolWithQualifiedName).isAncestor((OCStructSymbol)resolvedOwner)) {
                return true;
            }
        }
        return false;
    }
}
