// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.jetbrains.cidr.lang.symbols.OCBuilderDriver;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.BuilderDriverBase;
import com.intellij.psi.impl.source.tree.ASTStructure;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;

public class OCMethodSelectorPartImpl extends OCElementBase implements OCMethodSelectorPart
{
    public OCMethodSelectorPartImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCMethodSelectorPartImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public String getSelectorPart() {
        final StringBuilder sb = new StringBuilder();
        ASTNode astNode = this.getNode().getFirstChildNode();
        while (true) {
            Label_0041: {
                Label_0059: {
                    try {
                        if (astNode == null || astNode.getElementType() != OCTokenTypes.IDENTIFIER) {
                            break Label_0059;
                        }
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    break Label_0041;
                    while (true) {
                        try {
                            if (astNode == null || !OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(astNode.getElementType())) {
                                break;
                            }
                        }
                        catch (IncorrectOperationException ex2) {
                            throw a(ex2);
                        }
                        astNode = astNode.getTreeNext();
                    }
                }
                Label_0118: {
                    try {
                        if (astNode == null) {
                            return sb.toString();
                        }
                        final ASTNode astNode2 = astNode;
                        final IElementType elementType = astNode2.getElementType();
                        final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COLON;
                        if (elementType == ocPunctuatorElementType) {
                            break Label_0118;
                        }
                        return sb.toString();
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final ASTNode astNode2 = astNode;
                        final IElementType elementType = astNode2.getElementType();
                        final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.COLON;
                        if (elementType == ocPunctuatorElementType) {
                            sb.append(astNode.getText());
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                }
                return sb.toString();
            }
            sb.append(astNode.getText());
            astNode = astNode.getTreeNext();
            continue;
        }
    }
    
    @Override
    public String getParameterName() {
        final ASTNode parameterNode = this.findParameterNode();
        try {
            if (parameterNode != null) {
                return parameterNode.getText();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public String getName() {
        return this.getParameterName();
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCMethodSelectorPartImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final ASTNode parameterNode = this.findParameterNode();
        try {
            if (parameterNode != null) {
                CodeEditUtil.replaceChild(this.getNode(), parameterNode, OCElementFactory.createIdentifier(s, (PsiElement)this).getNode());
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return (PsiElement)this;
    }
    
    @Nullable
    @Override
    public PsiElement getSelectorIdentifier() {
        final ASTNode firstChildNode = this.getNode().getFirstChildNode();
        Label_0033: {
            try {
                if (firstChildNode == null) {
                    return null;
                }
                final ASTNode astNode = firstChildNode;
                final IElementType elementType = astNode.getElementType();
                final OCElementType ocElementType = OCTokenTypes.IDENTIFIER;
                if (elementType == ocElementType) {
                    break Label_0033;
                }
                return null;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final ASTNode astNode = firstChildNode;
                final IElementType elementType = astNode.getElementType();
                final OCElementType ocElementType = OCTokenTypes.IDENTIFIER;
                if (elementType == ocElementType) {
                    return firstChildNode.getPsi();
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    public PsiElement getNameIdentifier() {
        final ASTNode parameterNode = this.findParameterNode();
        try {
            if (parameterNode != null) {
                return parameterNode.getPsi();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public PsiElement getParameter() {
        final PsiElement firstChild = this.getFirstChild();
        final PsiElement lastChildByType = this.findLastChildByType(OCTokenTypes.IDENTIFIER);
        try {
            if (firstChild == lastChildByType) {
                return null;
            }
            final PsiElement psiElement = lastChildByType;
            if (psiElement != null) {
                return lastChildByType;
            }
            return null;
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            final PsiElement psiElement = lastChildByType;
            if (psiElement != null) {
                return lastChildByType;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @Nullable
    @Override
    public ASTNode findParameterNode() {
        final PsiElement parameter = this.getParameter();
        try {
            if (parameter != null) {
                return parameter.getNode();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public int getTextOffset() {
        final ASTNode parameterNode = this.findParameterNode();
        try {
            if (parameterNode != null) {
                return parameterNode.getStartOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return super.getTextOffset();
    }
    
    @Override
    public long getComplexOffset() {
        final PsiElement parameter = this.getParameter();
        try {
            if (parameter != null) {
                return OCSymbolOffsetUtil.getComplexOffset(parameter);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return super.getComplexOffset();
    }
    
    @NotNull
    @Override
    public OCType getType() {
        final OCTypeElement typeElement = this.getTypeElement();
        OCType ocType = null;
        Label_0025: {
            try {
                if (typeElement != null) {
                    final OCType ocType2;
                    ocType = (ocType2 = typeElement.getType());
                    break Label_0025;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            OCType ocType2;
            ocType = (ocType2 = OCUnknownType.INSTANCE);
            try {
                if (ocType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMethodSelectorPartImpl", "getType"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return ocType;
    }
    
    @NotNull
    @Override
    public OCType getRawType() {
        final OCTypeElement typeElement = this.getTypeElement();
        OCType ocType = null;
        Label_0025: {
            try {
                if (typeElement != null) {
                    final OCType ocType2;
                    ocType = (ocType2 = typeElement.getRawType());
                    break Label_0025;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            OCType ocType2;
            ocType = (ocType2 = OCUnknownType.INSTANCE);
            try {
                if (ocType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMethodSelectorPartImpl", "getRawType"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return ocType;
    }
    
    @Nullable
    @Override
    public OCTypeElement getTypeElement() {
        return this.findChildByType(OCElementTypes.TYPE_ELEMENT);
    }
    
    @Nullable
    @Override
    public OCDeclaratorSymbol getLocalSymbol() {
        final OCFile containingOCFile = this.getContainingOCFile();
        final OCMethod ocMethod = (OCMethod)PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCMethod.class });
        TextRange textRange = null;
        if (ocMethod != null) {
            final OCBlockStatement body = ocMethod.getBody();
            TextRange textRange2 = null;
            Label_0061: {
                try {
                    if (body != null) {
                        textRange2 = body.getTextRange();
                        break Label_0061;
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                textRange2 = ocMethod.getTextRange();
            }
            textRange = textRange2;
        }
        final ASTNode node = this.getNode();
        final OCMethodSymbol.SelectorPartSymbol processSelectorPart = new OCBuilderDriver<ASTNode>(containingOCFile, OCInclusionContext.empty(containingOCFile.getKind(), (PsiFile)containingOCFile), (com.intellij.util.diff.FlyweightCapableTreeStructure<ASTNode>)new ASTStructure(node), BuilderDriverBase.AST_NAMED_NODE_STRUCTURE, (Processor<OCSymbol>)CommonProcessors.alwaysTrue()).processSelectorPart(node, new StringBuilder(), textRange, (Ref<Long>)new Ref(), null, false);
        if (processSelectorPart != null) {
            final OCDeclaratorSymbol parameter = processSelectorPart.getParameter();
            Label_0163: {
                try {
                    if (parameter == null) {
                        return parameter;
                    }
                    final OCMethod ocMethod2 = ocMethod;
                    if (ocMethod2 != null) {
                        break Label_0163;
                    }
                    return parameter;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCMethod ocMethod2 = ocMethod;
                    if (ocMethod2 != null) {
                        parameter.setParentMethod(ocMethod.getSymbol());
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            return parameter;
        }
        return null;
    }
    
    public OCDeclaratorSymbol getSymbol() {
        return this.getLocalSymbol();
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCMethodSelectorPartImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitMethodSelectorPart(this);
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
