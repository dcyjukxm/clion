// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCNoexceptSpecifier;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;

public class OCBlockExpressionImpl extends OCExpressionBase implements OCBlockExpression
{
    public OCBlockExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCBlockExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCCallableKind getKind() {
        return OCCallableKind.BLOCK;
    }
    
    @Nullable
    @Override
    public OCBlockStatement getBody() {
        return this.findChildByType(OCElementTypes.BLOCK_STATEMENTS);
    }
    
    @Override
    public OCTypeElement getReturnTypeElement() {
        return this.findChildByType(OCElementTypes.TYPE_ELEMENT);
    }
    
    @Override
    public OCParameterList getParameterList() {
        return this.findChildByType(OCElementTypes.PARAMETER_LIST);
    }
    
    @Override
    public List<OCDeclarator> getParameters() {
        final OCParameterList parameterList = this.getParameterList();
        try {
            if (parameterList != null) {
                return parameterList.getParameters();
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCNoexceptSpecifier getNoexceptSpecifier() {
        return null;
    }
    
    @Override
    public OCType calcReturnTypeByBody() {
        return OCExpectedTypeUtil.getReturnTypeOfBlock(this.getBody());
    }
    
    public OCSymbol getSymbol() {
        return new OCBlockSymbol(this);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCBlockExpressionImpl", "accept"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        ocVisitor.visitBlockExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCBlockExpressionImpl", "getType"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        final OCParameterList parameterList = this.getParameterList();
        final ArrayList<OCType> list = new ArrayList<OCType>();
        final ArrayList<String> list2 = new ArrayList<String>();
        if (parameterList != null) {
            final Iterator<OCParameterDeclaration> iterator = parameterList.getParameterDeclarations().iterator();
            while (iterator.hasNext()) {
                final OCDeclarator declarator = iterator.next().getDeclarator();
                try {
                    if (declarator == null) {
                        continue;
                    }
                    list.add(declarator.getType());
                    list2.add(declarator.getName());
                }
                catch (UnsupportedOperationException ex2) {
                    throw b(ex2);
                }
            }
        }
        OCBlockPointerType blockPtr;
        try {
            blockPtr = OCBlockPointerType.blockPtr(new OCFunctionType(this.getReturnType(), list, list2));
            if (blockPtr == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBlockExpressionImpl", "getType"));
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        return blockPtr;
    }
    
    @NotNull
    @Override
    public OCType getReturnType() {
        final OCTypeElement returnTypeElement = this.getReturnTypeElement();
        OCType calcReturnTypeByBody = null;
        Label_0061: {
            OCType ocType = null;
            Label_0026: {
                try {
                    if (returnTypeElement == null) {
                        break Label_0061;
                    }
                    final OCTypeElement ocTypeElement = returnTypeElement;
                    ocType = ocTypeElement.getType();
                    if (ocType == null) {
                        break Label_0026;
                    }
                    return ocType;
                }
                catch (UnsupportedOperationException ex) {
                    throw b(ex);
                }
                try {
                    final OCTypeElement ocTypeElement = returnTypeElement;
                    ocType = ocTypeElement.getType();
                    if (ocType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBlockExpressionImpl", "getReturnType"));
                    }
                }
                catch (UnsupportedOperationException ex2) {
                    throw b(ex2);
                }
            }
            return ocType;
            try {
                calcReturnTypeByBody = this.calcReturnTypeByBody();
                if (calcReturnTypeByBody == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBlockExpressionImpl", "getReturnType"));
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
        }
        return calcReturnTypeByBody;
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCBlockExpressionImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw b((RuntimeException)ex);
        }
        throw new UnsupportedOperationException();
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
    
    public static class OCBlockSymbol extends OCSymbolImpl
    {
        public OCBlockSymbol() {
        }
        
        public OCBlockSymbol(final OCBlockExpression ocBlockExpression) {
            super(ocBlockExpression.getProject(), ocBlockExpression.getContainingFile().getVirtualFile(), ocBlockExpression.getTextOffset(), (ocBlockExpression.getReturnTypeElement() != null) ? ("^" + ocBlockExpression.getReturnTypeElement().getText() + "{...}") : "^{...}", Collections.emptyList());
        }
        
        @NotNull
        @Override
        public OCSymbolKind getKind() {
            OCSymbolKind block;
            try {
                block = OCSymbolKind.BLOCK;
                if (block == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBlockExpressionImpl$OCBlockSymbol", "getKind"));
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            return block;
        }
        
        private static IllegalStateException b(final IllegalStateException ex) {
            return ex;
        }
    }
}
