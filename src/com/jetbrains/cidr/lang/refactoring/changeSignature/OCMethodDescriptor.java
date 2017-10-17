// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import java.util.Iterator;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.refactoring.changeSignature.MethodDescriptor;

public class OCMethodDescriptor implements MethodDescriptor<OCParameterInfo, Object>
{
    private OCCallable myCallable;
    private OCSymbol mySymbol;
    private String myName;
    private boolean mySureIsConstructor;
    private boolean myChangeCallableKindPossible;
    private List<OCParameterInfo> myParameters;
    private OCClassDeclaration myContainerClass;
    private List<OCReferenceExpression> mySelfReferences;
    
    public OCMethodDescriptor(final OCBlockExpression ocBlockExpression) {
        this(ocBlockExpression, "", ocBlockExpression.getParameterList());
    }
    
    public OCMethodDescriptor(final OCFunctionDeclaration ocFunctionDeclaration) {
        this(ocFunctionDeclaration, ocFunctionDeclaration.getSymbolName(), ocFunctionDeclaration.getParameterList());
    }
    
    public OCMethodDescriptor(final OCMethod myCallable) {
        this.myParameters = new ArrayList<OCParameterInfo>();
        this.mySelfReferences = new ArrayList<OCReferenceExpression>();
        this.myCallable = myCallable;
        this.mySymbol = myCallable.getSymbol();
        this.myName = myCallable.getSelector();
        this.myContainerClass = (OCClassDeclaration)PsiTreeUtil.getParentOfType((PsiElement)myCallable, (Class)OCClassDeclaration.class);
        int n = 0;
        for (final OCMethodSelectorPart ocMethodSelectorPart : myCallable.getParameters()) {
            final OCTypeElement typeElement = ocMethodSelectorPart.getTypeElement();
            final OCParameterInfo ocParameterInfo = new OCParameterInfo(ocMethodSelectorPart.getSelectorPart(), ocMethodSelectorPart.getParameterName(), (typeElement != null) ? typeElement.getType() : null, n++, (PsiElement)myCallable);
            if (typeElement != null) {
                ocParameterInfo.setTypeText(typeElement.getTextWithMacros());
            }
            this.myParameters.add(ocParameterInfo);
        }
        final OCBlockStatement body = myCallable.getBody();
        if (body != null) {
            body.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                @Override
                public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
                    if (ocReferenceExpression.getSelfSuperToken() != null) {
                        OCMethodDescriptor.this.mySelfReferences.add(ocReferenceExpression);
                    }
                }
            });
        }
    }
    
    private OCMethodDescriptor(final OCCallable myCallable, final String myName, final OCParameterList list) {
        this.myParameters = new ArrayList<OCParameterInfo>();
        this.mySelfReferences = new ArrayList<OCReferenceExpression>();
        this.myCallable = myCallable;
        this.mySymbol = myCallable.getSymbol();
        this.myName = myName;
        this.myContainerClass = (OCClassDeclaration)PsiTreeUtil.getParentOfType((PsiElement)myCallable, (Class)OCClassDeclaration.class);
        if (list == null) {
            return;
        }
        int n = 0;
        for (final OCParameterDeclaration ocParameterDeclaration : list.getParameterDeclarations()) {
            final OCDeclarator declarator = ocParameterDeclaration.getDeclarator();
            if (declarator == null) {
                continue;
            }
            final PsiElement nameIdentifier = declarator.getNameIdentifier();
            final OCParameterInfo ocParameterInfo = new OCParameterInfo("", (nameIdentifier != null) ? nameIdentifier.getText() : "", declarator.getType(), n++, (PsiElement)myCallable);
            final String typeTextWithModifiers = OCElementUtil.getTypeTextWithModifiers(ocParameterDeclaration);
            if (typeTextWithModifiers != null) {
                ocParameterInfo.setTypeText(typeTextWithModifiers);
            }
            ocParameterInfo.setEllipsisType(ocParameterDeclaration.isEllipsis());
            this.myParameters.add(ocParameterInfo);
        }
    }
    
    public static OCMethodDescriptor createMethodDescriptor(final OCCallable ocCallable) {
        if (ocCallable instanceof OCFunctionDeclaration) {
            return new OCMethodDescriptor((OCFunctionDeclaration)ocCallable);
        }
        if (ocCallable instanceof OCBlockExpression) {
            return new OCMethodDescriptor((OCBlockExpression)ocCallable);
        }
        if (ocCallable instanceof OCMethod) {
            return new OCMethodDescriptor((OCMethod)ocCallable);
        }
        assert false;
        return null;
    }
    
    @Override
    public String getName() {
        return this.myName;
    }
    
    @Override
    public List<OCParameterInfo> getParameters() {
        final ArrayList<OCParameterInfo> list = new ArrayList<OCParameterInfo>(this.myParameters.size());
        final Iterator<OCParameterInfo> iterator = this.myParameters.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().clone());
        }
        return list;
    }
    
    public OCParameterInfo getParameter(final int n) {
        return this.myParameters.get(n).clone();
    }
    
    @Override
    public int getParametersCount() {
        return this.myParameters.size();
    }
    
    public OCType getReturnType() {
        return this.myCallable.getReturnType();
    }
    
    public OCSymbol getMethodSymbol() {
        return this.mySymbol;
    }
    
    public boolean isConstructor() {
        return this.mySureIsConstructor || (this.mySymbol != null && this.mySymbol.getKind().isConstructorOrDestructor());
    }
    
    public boolean isChangeCallableKindPossible() {
        return this.myChangeCallableKindPossible;
    }
    
    public void setChangeCallableKindPossible(final boolean myChangeCallableKindPossible) {
        this.myChangeCallableKindPossible = myChangeCallableKindPossible;
    }
    
    public void setSureIsConstructor(final boolean mySureIsConstructor) {
        this.mySureIsConstructor = mySureIsConstructor;
    }
    
    public String getReturnTypeText(final PsiElement psiElement) {
        if (this.mySymbol instanceof OCMemberSymbol && this.myCallable.getReturnType().isVoid() && this.mySymbol.hasAttribute("ibaction")) {
            return "IBAction";
        }
        final OCTypeElement returnTypeElement = this.myCallable.getReturnTypeElement();
        if (returnTypeElement == null) {
            return this.myCallable.getReturnType().getBestNameInContext(psiElement);
        }
        if (this.myCallable instanceof OCFunctionDeclaration) {
            return OCElementUtil.getTypeTextWithModifiers((OCDeclaration)this.myCallable);
        }
        return returnTypeElement.getTextWithMacros();
    }
    
    @Override
    public Object getVisibility() {
        return this.getCallableKind();
    }
    
    public List<OCReferenceExpression> getSelfReferences() {
        return this.mySelfReferences;
    }
    
    public OCCallableKind getCallableKind() {
        if (this.myCallable instanceof OCMethod) {
            return OCCallableKind.METHOD;
        }
        if (this.myCallable instanceof OCFunctionDeclaration) {
            return OCCallableKind.FUNCTION;
        }
        if (this.myCallable instanceof OCBlockExpression) {
            return OCCallableKind.BLOCK;
        }
        if (this.myCallable instanceof OCLambdaExpression) {
            return OCCallableKind.LAMBDA;
        }
        assert false;
        return null;
    }
    
    public OCClassDeclaration getContainerClass() {
        return this.myContainerClass;
    }
    
    public OCCallable getMethod() {
        return this.myCallable;
    }
    
    @Override
    public boolean canChangeVisibility() {
        return true;
    }
    
    @Override
    public boolean canChangeParameters() {
        return true;
    }
    
    @Override
    public ReadWriteOption canChangeReturnType() {
        return this.isConstructor() ? ReadWriteOption.None : ReadWriteOption.ReadWrite;
    }
    
    @Override
    public boolean canChangeName() {
        return true;
    }
}
