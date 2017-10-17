// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.visitors;

import com.jetbrains.cidr.lang.psi.OCCppTypeidExpression;
import com.jetbrains.cidr.lang.psi.OCVariadicPackExpression;
import com.jetbrains.cidr.lang.psi.OCGenericArgumentsList;
import com.jetbrains.cidr.lang.psi.OCGenericArgument;
import com.jetbrains.cidr.lang.psi.OCGenericParametersList;
import com.jetbrains.cidr.lang.psi.OCGenericParameter;
import com.jetbrains.cidr.lang.psi.OCPragma;
import com.jetbrains.cidr.lang.psi.OCCppStaticAssert;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCCppDeleteExpression;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.psi.OCCppQualifiedPointer;
import com.jetbrains.cidr.lang.psi.impl.OCMacroParameterImpl;
import com.jetbrains.cidr.lang.psi.OCDirective;
import com.jetbrains.cidr.lang.psi.impl.OCDefineDirectiveImpl;
import com.jetbrains.cidr.lang.psi.impl.OCAsmStatementImpl;
import com.jetbrains.cidr.lang.psi.impl.OCAsmStatementPartImpl;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.jetbrains.cidr.lang.psi.OCStatementExpression;
import com.jetbrains.cidr.lang.psi.OCAttribute;
import com.jetbrains.cidr.lang.psi.OCAttributesList;
import com.jetbrains.cidr.lang.psi.OCLambdaIntroducer;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.psi.OCProtocolList;
import com.jetbrains.cidr.lang.psi.OCSuperClassRef;
import com.jetbrains.cidr.lang.psi.OCCategoryName;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCQualifiedDesignator;
import com.jetbrains.cidr.lang.psi.OCDesignatedInitializer;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.psi.OCPropertyAttribute;
import com.jetbrains.cidr.lang.psi.OCPropertyAttributesList;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.psi.OCSynthesizePropertiesList;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCEnum;
import com.jetbrains.cidr.lang.psi.OCUnion;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceAlias;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCInstanceVariablesList;
import com.jetbrains.cidr.lang.psi.OCArgumentSelector;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCClassPredeclaration;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;
import com.jetbrains.cidr.lang.psi.impl.OCTemplateParameterListImpl;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.psi.OCProtocolExpression;
import com.jetbrains.cidr.lang.psi.OCEncodeExpression;
import com.jetbrains.cidr.lang.psi.OCAvailabilityExpression;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCNSDictionaryLiteral;
import com.jetbrains.cidr.lang.psi.OCNSArrayLiteral;
import com.jetbrains.cidr.lang.psi.OCGenericSelectionExpression;
import com.jetbrains.cidr.lang.psi.OCBoxedExpression;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentsOwner;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCArraySelectionExpression;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.psi.OCSizeofExpression;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCCommaExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCCatchSection;
import com.jetbrains.cidr.lang.psi.OCFinallySection;
import com.jetbrains.cidr.lang.psi.OCThrowExpression;
import com.jetbrains.cidr.lang.psi.OCTryStatement;
import com.jetbrains.cidr.lang.psi.OCAutoReleasePoolStatement;
import com.jetbrains.cidr.lang.psi.OCSynchronizedStatement;
import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.psi.OCDoWhileStatement;
import com.jetbrains.cidr.lang.psi.OCWhileStatement;
import com.jetbrains.cidr.lang.psi.OCLoopStatement;
import com.jetbrains.cidr.lang.psi.OCContinueStatement;
import com.jetbrains.cidr.lang.psi.OCBreakStatement;
import com.jetbrains.cidr.lang.psi.OCGotoStatement;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import com.jetbrains.cidr.lang.psi.OCEmptyStatement;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.psi.OCLocalSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCLabeledStatement;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.PsiElementVisitor;

public class OCVisitor extends PsiElementVisitor
{
    public void visitOCElement(final OCElement ocElement) {
        this.visitElement((PsiElement)ocElement);
    }
    
    public void visitOCFile(final OCFile ocFile) {
        this.visitFile((PsiFile)ocFile);
    }
    
    public void visitExpression(final OCExpression ocExpression) {
        this.visitOCElement(ocExpression);
    }
    
    public void visitStatement(final OCStatement ocStatement) {
        this.visitOCElement(ocStatement);
    }
    
    public void visitIfStatement(final OCIfStatement ocIfStatement) {
        this.visitStatement(ocIfStatement);
    }
    
    public void visitBlockStatement(final OCBlockStatement ocBlockStatement) {
        this.visitStatement(ocBlockStatement);
    }
    
    public void visitLabeledStatement(final OCLabeledStatement ocLabeledStatement) {
        this.visitStatement(ocLabeledStatement);
        this.visitLocalSymbolDeclarator(ocLabeledStatement);
    }
    
    public void visitExpressionStatement(final OCExpressionStatement ocExpressionStatement) {
        this.visitStatement(ocExpressionStatement);
    }
    
    public void visitEmptyStatement(final OCEmptyStatement ocEmptyStatement) {
        this.visitStatement(ocEmptyStatement);
    }
    
    public void visitReturnStatement(final OCReturnStatement ocReturnStatement) {
        this.visitStatement(ocReturnStatement);
    }
    
    public void visitGotoStatement(final OCGotoStatement ocGotoStatement) {
        this.visitStatement(ocGotoStatement);
    }
    
    public void visitBreakStatement(final OCBreakStatement ocBreakStatement) {
        this.visitStatement(ocBreakStatement);
    }
    
    public void visitContinueStatement(final OCContinueStatement ocContinueStatement) {
        this.visitStatement(ocContinueStatement);
    }
    
    public void visitLoopStatement(final OCLoopStatement ocLoopStatement) {
        this.visitStatement(ocLoopStatement);
    }
    
    public void visitWhileStatement(final OCWhileStatement ocWhileStatement) {
        this.visitLoopStatement(ocWhileStatement);
    }
    
    public void visitDoWhileStatement(final OCDoWhileStatement ocDoWhileStatement) {
        this.visitLoopStatement(ocDoWhileStatement);
    }
    
    public void visitForStatement(final OCForStatement ocForStatement) {
        this.visitLoopStatement(ocForStatement);
    }
    
    public void visitForeachStatement(final OCForeachStatement ocForeachStatement) {
        this.visitLoopStatement(ocForeachStatement);
    }
    
    public void visitCaseStatement(final OCCaseStatement ocCaseStatement) {
        this.visitStatement(ocCaseStatement);
    }
    
    public void visitSwitchStatement(final OCSwitchStatement ocSwitchStatement) {
        this.visitStatement(ocSwitchStatement);
    }
    
    public void visitSynchronizedStatement(final OCSynchronizedStatement ocSynchronizedStatement) {
        this.visitStatement(ocSynchronizedStatement);
    }
    
    public void visitAutoReleasePoolStatement(final OCAutoReleasePoolStatement ocAutoReleasePoolStatement) {
        this.visitStatement(ocAutoReleasePoolStatement);
    }
    
    public void visitTryStatement(final OCTryStatement ocTryStatement) {
        this.visitStatement(ocTryStatement);
    }
    
    public void visitThrowExpression(final OCThrowExpression ocThrowExpression) {
        this.visitExpression(ocThrowExpression);
    }
    
    public void visitFinallySection(final OCFinallySection ocFinallySection) {
        this.visitOCElement(ocFinallySection);
    }
    
    public void visitCatchSection(final OCCatchSection ocCatchSection) {
        this.visitOCElement(ocCatchSection);
    }
    
    public void visitDeclarationStatement(final OCDeclarationStatement ocDeclarationStatement) {
        this.visitStatement(ocDeclarationStatement);
    }
    
    public void visitDeclarationOrExpression(final OCDeclarationOrExpression ocDeclarationOrExpression) {
        this.visitOCElement(ocDeclarationOrExpression);
    }
    
    public void visitCommaExpression(final OCCommaExpression ocCommaExpression) {
        this.visitExpression(ocCommaExpression);
    }
    
    public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
        this.visitExpression(ocAssignmentExpression);
    }
    
    public void visitConditionalExpression(final OCConditionalExpression ocConditionalExpression) {
        this.visitExpression(ocConditionalExpression);
    }
    
    public void visitBinaryExpression(final OCBinaryExpression ocBinaryExpression) {
        this.visitExpression(ocBinaryExpression);
    }
    
    public void visitUnaryExpression(final OCUnaryExpression ocUnaryExpression) {
        this.visitExpression(ocUnaryExpression);
    }
    
    public void visitPrefixExpression(final OCPrefixExpression ocPrefixExpression) {
        this.visitExpression(ocPrefixExpression);
    }
    
    public void visitCastExpression(final OCCastExpression ocCastExpression) {
        this.visitExpression(ocCastExpression);
    }
    
    public void visitSizeofExpression(final OCSizeofExpression ocSizeofExpression) {
        this.visitExpression(ocSizeofExpression);
    }
    
    public void visitPostfixExpression(final OCPostfixExpression ocPostfixExpression) {
        this.visitExpression(ocPostfixExpression);
    }
    
    public void visitArraySelectionExpression(final OCArraySelectionExpression ocArraySelectionExpression) {
        this.visitExpression(ocArraySelectionExpression);
    }
    
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        this.visitExpression(ocCallExpression);
    }
    
    public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
        this.visitExpression(ocQualifiedExpression);
        this.visitTemplateArgumentsOwner(ocQualifiedExpression);
    }
    
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        this.visitExpression(ocReferenceExpression);
    }
    
    public void visitParenthesizedExpression(final OCParenthesizedExpression ocParenthesizedExpression) {
        this.visitExpression(ocParenthesizedExpression);
    }
    
    public void visitLiteralExpression(final OCLiteralExpression ocLiteralExpression) {
        this.visitExpression(ocLiteralExpression);
    }
    
    public void visitBoxedExpression(final OCBoxedExpression ocBoxedExpression) {
        this.visitExpression(ocBoxedExpression);
    }
    
    public void visitGenericSelectionExpression(final OCGenericSelectionExpression ocGenericSelectionExpression) {
        this.visitExpression(ocGenericSelectionExpression);
    }
    
    public void visitArrayLiteral(final OCNSArrayLiteral ocnsArrayLiteral) {
        this.visitOCElement(ocnsArrayLiteral);
    }
    
    public void visitDictionaryLiteral(final OCNSDictionaryLiteral ocnsDictionaryLiteral) {
        this.visitOCElement(ocnsDictionaryLiteral);
    }
    
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        this.visitExpression(ocSendMessageExpression);
    }
    
    public void visitSelectorExpression(final OCSelectorExpression ocSelectorExpression) {
        this.visitExpression(ocSelectorExpression);
    }
    
    public void visitAvailabilityExpression(final OCAvailabilityExpression ocAvailabilityExpression) {
        this.visitExpression(ocAvailabilityExpression);
    }
    
    public void visitEncodeExpression(final OCEncodeExpression ocEncodeExpression) {
        this.visitExpression(ocEncodeExpression);
    }
    
    public void visitProtocolExpression(final OCProtocolExpression ocProtocolExpression) {
        this.visitExpression(ocProtocolExpression);
    }
    
    public void visitMessageArgument(final OCMessageArgument ocMessageArgument) {
        this.visitOCElement(ocMessageArgument);
    }
    
    public void visitArgumentList(final OCArgumentList list) {
        this.visitOCElement(list);
    }
    
    public void visitDeclaration(final OCDeclaration ocDeclaration) {
        this.visitOCElement(ocDeclaration);
        this.visitTemplateArgumentsOwner(ocDeclaration);
    }
    
    public void visitSymbolDeclarator(final OCSymbolDeclarator ocSymbolDeclarator) {
    }
    
    public void visitLocalSymbolDeclarator(final OCLocalSymbolDeclarator ocLocalSymbolDeclarator) {
        this.visitSymbolDeclarator(ocLocalSymbolDeclarator);
    }
    
    public void visitDeclarator(final OCDeclarator ocDeclarator) {
        this.visitOCElement(ocDeclarator);
        this.visitLocalSymbolDeclarator(ocDeclarator);
    }
    
    public void visitParameterDeclaration(final OCParameterDeclaration ocParameterDeclaration) {
        this.visitDeclaration(ocParameterDeclaration);
    }
    
    public void visitParameterList(final OCParameterList list) {
        this.visitOCElement(list);
    }
    
    public void visitTemplateParameterList(final OCTemplateParameterListImpl ocTemplateParameterListImpl) {
        this.visitOCElement(ocTemplateParameterListImpl);
    }
    
    public void visitTypeParameterDeclaration(final OCTypeParameterDeclaration ocTypeParameterDeclaration) {
        this.visitOCElement(ocTypeParameterDeclaration);
        this.visitLocalSymbolDeclarator(ocTypeParameterDeclaration);
    }
    
    public void visitCallable(final OCCallable ocCallable) {
        this.visitSymbolDeclarator(ocCallable);
    }
    
    public void visitFunctionDefinition(final OCFunctionDefinition ocFunctionDefinition) {
        this.visitFunctionDeclaration(ocFunctionDefinition);
        this.visitCallable(ocFunctionDefinition);
    }
    
    public void visitClassDeclaration(final OCClassDeclaration ocClassDeclaration) {
        this.visitOCElement(ocClassDeclaration);
        this.visitSymbolDeclarator(ocClassDeclaration);
    }
    
    public void visitProtocol(final OCProtocol ocProtocol) {
        this.visitClassDeclaration(ocProtocol);
    }
    
    public void visitInterface(final OCInterface ocInterface) {
        this.visitClassDeclaration(ocInterface);
    }
    
    public void visitImplementation(final OCImplementation ocImplementation) {
        this.visitClassDeclaration(ocImplementation);
    }
    
    public void visitClassPredeclaration(final OCClassPredeclaration ocClassPredeclaration) {
        this.visitClassDeclaration(ocClassPredeclaration);
    }
    
    public void visitMethod(final OCMethod ocMethod) {
        this.visitOCElement(ocMethod);
        this.visitCallable(ocMethod);
    }
    
    public void visitMethodSelectorPart(final OCMethodSelectorPart ocMethodSelectorPart) {
        this.visitOCElement(ocMethodSelectorPart);
        this.visitLocalSymbolDeclarator(ocMethodSelectorPart);
    }
    
    public void visitArgumentSelector(final OCArgumentSelector ocArgumentSelector) {
        this.visitOCElement(ocArgumentSelector);
    }
    
    public void visitInstanceVariableList(final OCInstanceVariablesList list) {
        this.visitOCElement(list);
    }
    
    public void visitNamespace(final OCCppNamespace ocCppNamespace) {
        this.visitOCElement(ocCppNamespace);
        this.visitSymbolDeclarator(ocCppNamespace);
    }
    
    public void visitNamespaceAlias(final OCCppNamespaceAlias ocCppNamespaceAlias) {
        this.visitOCElement(ocCppNamespaceAlias);
        this.visitLocalSymbolDeclarator(ocCppNamespaceAlias);
    }
    
    public void visitTemplateArgumentsOwner(final OCTemplateArgumentsOwner ocTemplateArgumentsOwner) {
    }
    
    public void visitNamespaceQualifier(final OCCppNamespaceQualifier ocCppNamespaceQualifier) {
        this.visitOCElement(ocCppNamespaceQualifier);
        this.visitTemplateArgumentsOwner(ocCppNamespaceQualifier);
    }
    
    public void visitStructLike(final OCStructLike ocStructLike) {
        this.visitOCElement(ocStructLike);
        this.visitLocalSymbolDeclarator(ocStructLike);
        this.visitTemplateArgumentsOwner(ocStructLike);
    }
    
    public void visitStruct(final OCStruct ocStruct) {
        this.visitStructLike(ocStruct);
    }
    
    public void visitUnion(final OCUnion ocUnion) {
        this.visitStructLike(ocUnion);
    }
    
    public void visitEnum(final OCEnum ocEnum) {
        this.visitStructLike(ocEnum);
    }
    
    public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
        this.visitOCElement(ocReferenceElement);
        this.visitTemplateArgumentsOwner(ocReferenceElement);
    }
    
    public void visitMacroCall(final OCMacroCall ocMacroCall) {
        this.visitOCElement(ocMacroCall);
    }
    
    public void visitMacroCallArgument(final OCMacroCallArgument ocMacroCallArgument) {
        this.visitOCElement(ocMacroCallArgument);
    }
    
    public void visitProperty(final OCProperty ocProperty) {
        this.visitOCElement(ocProperty);
    }
    
    public void visitSynthesizePropertiesList(final OCSynthesizePropertiesList list) {
        this.visitOCElement(list);
    }
    
    public void visitSynthesizeProperty(final OCSynthesizeProperty ocSynthesizeProperty) {
        this.visitOCElement(ocSynthesizeProperty);
        this.visitSymbolDeclarator(ocSynthesizeProperty);
    }
    
    public void visitPropertyAttributesList(final OCPropertyAttributesList list) {
        this.visitOCElement(list);
    }
    
    public void visitPropertyAttribute(final OCPropertyAttribute ocPropertyAttribute) {
        this.visitOCElement(ocPropertyAttribute);
    }
    
    public void visitCompoundInitializer(final OCCompoundInitializer ocCompoundInitializer) {
        this.visitExpression(ocCompoundInitializer);
    }
    
    public void visitDesignatedInitializer(final OCDesignatedInitializer ocDesignatedInitializer) {
        this.visitOCElement(ocDesignatedInitializer);
    }
    
    public void visitQualifiedDesignator(final OCQualifiedDesignator ocQualifiedDesignator) {
        this.visitOCElement(ocQualifiedDesignator);
    }
    
    public void visitTypeElement(final OCTypeElement ocTypeElement) {
        this.visitOCElement(ocTypeElement);
    }
    
    public void visitCategoryName(final OCCategoryName ocCategoryName) {
        this.visitOCElement(ocCategoryName);
    }
    
    public void visitSuperClassRef(final OCSuperClassRef ocSuperClassRef) {
        this.visitOCElement(ocSuperClassRef);
    }
    
    public void visitProtocolsList(final OCProtocolList list) {
        this.visitOCElement(list);
    }
    
    public void visitImportDirective(final OCIncludeDirective ocIncludeDirective) {
        this.visitOCElement(ocIncludeDirective);
    }
    
    public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
        this.visitExpression(ocBlockExpression);
        this.visitCallable(ocBlockExpression);
    }
    
    public void visitLambdaExpression(final OCLambdaExpression ocLambdaExpression) {
        this.visitExpression(ocLambdaExpression);
        this.visitCallable(ocLambdaExpression);
    }
    
    public void visitLambdaIntroducer(final OCLambdaIntroducer ocLambdaIntroducer) {
        this.visitOCElement(ocLambdaIntroducer);
    }
    
    public void visitAttributeList(final OCAttributesList list) {
        this.visitOCElement(list);
    }
    
    public void visitAttribute(final OCAttribute ocAttribute) {
        this.visitOCElement(ocAttribute);
    }
    
    public void visitStatementExpression(final OCStatementExpression ocStatementExpression) {
        this.visitExpression(ocStatementExpression);
    }
    
    public void visitUsingStatement(final OCCppUsingStatement ocCppUsingStatement) {
        this.visitOCElement(ocCppUsingStatement);
        this.visitLocalSymbolDeclarator(ocCppUsingStatement);
    }
    
    public void visitFunctionDeclaration(final OCFunctionDeclaration ocFunctionDeclaration) {
        this.visitDeclaration(ocFunctionDeclaration);
    }
    
    public void visitAsmStatementPart(final OCAsmStatementPartImpl ocAsmStatementPartImpl) {
        this.visitOCElement(ocAsmStatementPartImpl);
    }
    
    public void visitAsmStatement(final OCAsmStatementImpl ocAsmStatementImpl) {
        this.visitStatement(ocAsmStatementImpl);
    }
    
    public void visitDefineDirective(final OCDefineDirectiveImpl ocDefineDirectiveImpl) {
        this.visitDirective(ocDefineDirectiveImpl);
        this.visitSymbolDeclarator(ocDefineDirectiveImpl);
    }
    
    public void visitMacroParameter(final OCMacroParameterImpl ocMacroParameterImpl) {
        this.visitSymbolDeclarator(ocMacroParameterImpl);
    }
    
    public void visitDirective(final OCDirective ocDirective) {
        this.visitOCElement(ocDirective);
    }
    
    public void visitCppQualifiedPointer(final OCCppQualifiedPointer ocCppQualifiedPointer) {
        this.visitOCElement(ocCppQualifiedPointer);
    }
    
    public void visitCppNewExpression(final OCCppNewExpression ocCppNewExpression) {
        this.visitExpression(ocCppNewExpression);
    }
    
    public void visitCppDeleteExpression(final OCCppDeleteExpression ocCppDeleteExpression) {
        this.visitExpression(ocCppDeleteExpression);
    }
    
    public void visitConstructorFieldInitializer(final OCConstructorFieldInitializer ocConstructorFieldInitializer) {
        this.visitOCElement(ocConstructorFieldInitializer);
    }
    
    public void visitStaticAssert(final OCCppStaticAssert ocCppStaticAssert) {
        this.visitOCElement(ocCppStaticAssert);
    }
    
    public void visitPragma(final OCPragma ocPragma) {
        this.visitDirective(ocPragma);
    }
    
    public void visitGenericParameter(final OCGenericParameter ocGenericParameter) {
        this.visitOCElement(ocGenericParameter);
    }
    
    public void visitGenericParametersList(final OCGenericParametersList list) {
        this.visitOCElement(list);
    }
    
    public void visitGenericArgument(final OCGenericArgument ocGenericArgument) {
        this.visitOCElement(ocGenericArgument);
    }
    
    public void visitGenericArgumentsList(final OCGenericArgumentsList list) {
        this.visitOCElement(list);
    }
    
    public void visitVariadicPackExpression(final OCVariadicPackExpression ocVariadicPackExpression) {
        this.visitExpression(ocVariadicPackExpression);
    }
    
    public void visitCppTypeIdExpression(final OCCppTypeidExpression ocCppTypeidExpression) {
        this.visitExpression(ocCppTypeidExpression);
    }
}
