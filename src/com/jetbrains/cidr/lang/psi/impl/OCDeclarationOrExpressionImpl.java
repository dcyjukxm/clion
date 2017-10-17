// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.intellij.psi.PsiFile;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;

public class OCDeclarationOrExpressionImpl extends OCElementBase implements OCDeclarationOrExpression
{
    public OCDeclarationOrExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationOrExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCType getType() {
        final OCExpression expression = this.getExpression();
        Label_0061: {
            OCType ocType = null;
            Label_0026: {
                try {
                    if (expression == null) {
                        break Label_0061;
                    }
                    final OCExpression ocExpression = expression;
                    ocType = ocExpression.getType();
                    if (ocType == null) {
                        break Label_0026;
                    }
                    return ocType;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCExpression ocExpression = expression;
                    ocType = ocExpression.getType();
                    if (ocType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationOrExpressionImpl", "getType"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return ocType;
        }
        final OCDeclaration declaration = this.getDeclaration();
        OCUnknownType instance = null;
        Label_0147: {
            if (declaration != null) {
                final List<OCDeclarator> declarators = declaration.getDeclarators();
                OCType ocType2 = null;
                Label_0112: {
                    try {
                        if (declarators.isEmpty()) {
                            break Label_0147;
                        }
                        final List<OCDeclarator> list = declarators;
                        final int n = 0;
                        final OCDeclarator ocDeclarator = list.get(n);
                        final OCDeclarator ocDeclarator2 = ocDeclarator;
                        ocType2 = ocDeclarator2.getType();
                        if (ocType2 == null) {
                            break Label_0112;
                        }
                        return ocType2;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final List<OCDeclarator> list = declarators;
                        final int n = 0;
                        final OCDeclarator ocDeclarator = list.get(n);
                        final OCDeclarator ocDeclarator2 = ocDeclarator;
                        ocType2 = ocDeclarator2.getType();
                        if (ocType2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationOrExpressionImpl", "getType"));
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                return ocType2;
            }
            try {
                instance = OCUnknownType.INSTANCE;
                if (instance == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationOrExpressionImpl", "getType"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return instance;
    }
    
    @NotNull
    @Override
    public OCType getResolvedType() {
        OCType resolve;
        try {
            resolve = this.getType().resolve((PsiFile)this.getContainingOCFile());
            if (resolve == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationOrExpressionImpl", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return resolve;
    }
    
    @Override
    public OCDeclaration getDeclaration() {
        final OCDeclarationStatement ocDeclarationStatement = this.findChildByType(OCElementTypes.DECLARATION_STATEMENT);
        try {
            if (ocDeclarationStatement != null) {
                return ocDeclarationStatement.getDeclaration();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public OCExpression getExpression() {
        return this.findChildByType(OCElementTypes.EXPRESSIONS);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationOrExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitDeclarationOrExpression(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
