// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceAlias;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.jetbrains.cidr.lang.psi.OCEnum;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.impl.OCTemplateParameterListImpl;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCLabeledStatement;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.jetbrains.cidr.lang.psi.OCWhileStatement;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCCatchSection;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCLambdaIntroducer;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCLocalSymbolDeclarator;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

class OCLocalDeclarationsVisitor extends OCVisitor
{
    private Processor<OCLocalSymbolDeclarator> myProcessor;
    private PsiElement myLastParent;
    private boolean myStepIntoBlocks;
    private boolean myResult;
    
    OCLocalDeclarationsVisitor(final Processor<OCLocalSymbolDeclarator> myProcessor, final PsiElement myLastParent, final boolean myStepIntoBlocks) {
        this.myResult = true;
        this.myProcessor = myProcessor;
        this.myLastParent = myLastParent;
        this.myStepIntoBlocks = myStepIntoBlocks;
    }
    
    public boolean getResult() {
        return this.myResult;
    }
    
    private void a(final PsiElement myLastParent) {
        final PsiElement myLastParent2 = this.myLastParent;
        this.myLastParent = myLastParent;
        for (PsiElement psiElement = myLastParent.getFirstChild(); psiElement != null; psiElement = psiElement.getNextSibling()) {
            if (psiElement != myLastParent2) {
                psiElement.accept((PsiElementVisitor)this);
                if (!this.myResult) {
                    break;
                }
            }
        }
        this.myLastParent = myLastParent2;
    }
    
    @Override
    public void visitExpression(final OCExpression ocExpression) {
        if (this.myStepIntoBlocks) {
            this.a((PsiElement)ocExpression);
        }
    }
    
    @Override
    public void visitStatement(final OCStatement ocStatement) {
        if (this.myStepIntoBlocks) {
            this.a((PsiElement)ocStatement);
        }
    }
    
    @Override
    public void visitCaseStatement(final OCCaseStatement ocCaseStatement) {
        this.a((PsiElement)ocCaseStatement);
    }
    
    @Override
    public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
        if (this.myLastParent != ocBlockExpression.getParent()) {
            final OCParameterList parameterList = ocBlockExpression.getParameterList();
            if (parameterList != null) {
                parameterList.accept((PsiElementVisitor)this);
            }
        }
    }
    
    @Override
    public void visitLambdaExpression(final OCLambdaExpression ocLambdaExpression) {
        if (this.myLastParent != ocLambdaExpression.getParent()) {
            final OCParameterList parameterList = ocLambdaExpression.getParameterList();
            final OCLambdaIntroducer lambdaIntroducer = ocLambdaExpression.getLambdaIntroducer();
            if (this.myLastParent != lambdaIntroducer) {
                lambdaIntroducer.accept((PsiElementVisitor)this);
                if (parameterList != null) {
                    parameterList.accept((PsiElementVisitor)this);
                }
            }
        }
    }
    
    @Override
    public void visitBlockStatement(final OCBlockStatement ocBlockStatement) {
        if (this.myStepIntoBlocks || this.myLastParent != ocBlockStatement.getParent()) {
            this.a((PsiElement)ocBlockStatement);
        }
    }
    
    @Override
    public void visitCatchSection(final OCCatchSection ocCatchSection) {
        this.a((PsiElement)ocCatchSection);
    }
    
    @Override
    public void visitDeclaration(final OCDeclaration ocDeclaration) {
        this.a((PsiElement)ocDeclaration);
    }
    
    @Override
    public void visitDeclarationStatement(final OCDeclarationStatement ocDeclarationStatement) {
        this.a((PsiElement)ocDeclarationStatement);
    }
    
    @Override
    public void visitDeclarationOrExpression(final OCDeclarationOrExpression ocDeclarationOrExpression) {
        this.a((PsiElement)ocDeclarationOrExpression);
    }
    
    @Override
    public void visitDeclarator(final OCDeclarator ocDeclarator) {
        if (this.myResult && !(ocDeclarator.getParent() instanceof OCFunctionDefinition) && (!(ocDeclarator.getParent().getParent() instanceof OCLambdaIntroducer) || this.myLastParent != ocDeclarator.getInitializer())) {
            this.myResult = this.myProcessor.process((Object)ocDeclarator);
        }
        if (this.myStepIntoBlocks || this.myLastParent != ocDeclarator.getParent()) {
            this.a((PsiElement)ocDeclarator);
        }
    }
    
    @Override
    public void visitLocalSymbolDeclarator(final OCLocalSymbolDeclarator ocLocalSymbolDeclarator) {
    }
    
    @Override
    public void visitMethodSelectorPart(final OCMethodSelectorPart ocMethodSelectorPart) {
        if (this.myResult) {
            this.myResult = this.myProcessor.process((Object)ocMethodSelectorPart);
        }
    }
    
    @Override
    public void visitForStatement(final OCForStatement ocForStatement) {
        if (this.myStepIntoBlocks || this.myLastParent != ocForStatement.getParent()) {
            this.a((PsiElement)ocForStatement);
        }
    }
    
    @Override
    public void visitForeachStatement(final OCForeachStatement ocForeachStatement) {
        if (this.myStepIntoBlocks || this.myLastParent != ocForeachStatement.getParent()) {
            this.a((PsiElement)ocForeachStatement);
        }
    }
    
    @Override
    public void visitIfStatement(final OCIfStatement ocIfStatement) {
        if (this.myStepIntoBlocks || this.myLastParent != ocIfStatement.getParent()) {
            this.a((PsiElement)ocIfStatement);
        }
    }
    
    @Override
    public void visitWhileStatement(final OCWhileStatement ocWhileStatement) {
        if (this.myStepIntoBlocks || this.myLastParent != ocWhileStatement.getParent()) {
            this.a((PsiElement)ocWhileStatement);
        }
    }
    
    @Override
    public void visitSwitchStatement(final OCSwitchStatement ocSwitchStatement) {
        if (this.myStepIntoBlocks || this.myLastParent != ocSwitchStatement.getParent()) {
            this.a((PsiElement)ocSwitchStatement);
        }
    }
    
    @Override
    public void visitFunctionDeclaration(final OCFunctionDeclaration ocFunctionDeclaration) {
        this.a((PsiElement)ocFunctionDeclaration);
        if (!this.myResult) {
            return;
        }
        if (this.myLastParent != ocFunctionDeclaration.getParent()) {
            final OCParameterList parameterList = ocFunctionDeclaration.getParameterList();
            if (parameterList != null) {
                parameterList.accept((PsiElementVisitor)this);
            }
        }
    }
    
    @Override
    public void visitFunctionDefinition(final OCFunctionDefinition ocFunctionDefinition) {
        this.visitFunctionDeclaration(ocFunctionDefinition);
    }
    
    @Override
    public void visitLabeledStatement(final OCLabeledStatement ocLabeledStatement) {
        if (this.myResult) {
            this.myResult = this.myProcessor.process((Object)ocLabeledStatement);
        }
        if (this.myStepIntoBlocks || this.myLastParent != ocLabeledStatement.getParent()) {
            this.a((PsiElement)ocLabeledStatement);
        }
    }
    
    @Override
    public void visitMethod(final OCMethod ocMethod) {
        this.a((PsiElement)ocMethod);
    }
    
    @Override
    public void visitParameterDeclaration(final OCParameterDeclaration ocParameterDeclaration) {
        this.a((PsiElement)ocParameterDeclaration);
    }
    
    @Override
    public void visitParameterList(final OCParameterList list) {
        this.a((PsiElement)list);
    }
    
    @Override
    public void visitLambdaIntroducer(final OCLambdaIntroducer ocLambdaIntroducer) {
        this.a((PsiElement)ocLambdaIntroducer);
    }
    
    @Override
    public void visitTemplateParameterList(final OCTemplateParameterListImpl ocTemplateParameterListImpl) {
        this.a((PsiElement)ocTemplateParameterListImpl);
    }
    
    @Override
    public void visitStructLike(final OCStructLike ocStructLike) {
        if (this.myResult) {
            this.myResult = this.myProcessor.process((Object)ocStructLike);
        }
        if (ocStructLike instanceof OCEnum || this.myLastParent != ocStructLike.getParent()) {
            this.a((PsiElement)ocStructLike);
        }
    }
    
    @Override
    public void visitUsingStatement(final OCCppUsingStatement ocCppUsingStatement) {
        if (this.myResult) {
            this.myResult = this.myProcessor.process((Object)ocCppUsingStatement);
        }
    }
    
    @Override
    public void visitNamespaceAlias(final OCCppNamespaceAlias ocCppNamespaceAlias) {
        if (this.myResult) {
            this.myResult = this.myProcessor.process((Object)ocCppNamespaceAlias);
        }
    }
    
    @Override
    public void visitTypeElement(final OCTypeElement ocTypeElement) {
        this.a((PsiElement)ocTypeElement);
    }
}
