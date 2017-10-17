// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCTypeArgumentList;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCDeclaration;

public class OCDeclarationImpl extends OCElementBase implements OCDeclaration
{
    public OCDeclarationImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCTypeElement getTypeElement() {
        return this.findChildByType(OCElementTypes.TYPE_ELEMENT);
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
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCType ocType2;
            ocType = (ocType2 = OCUnknownType.INSTANCE);
            try {
                if (ocType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationImpl", "getType"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return ocType;
    }
    
    @NotNull
    @Override
    public final OCType getResolvedType() {
        OCType resolve;
        try {
            resolve = this.getType().resolve(this.getContainingFile());
            if (resolve == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationImpl", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return resolve;
    }
    
    @NotNull
    @Override
    public List<OCDeclarator> getDeclarators() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.DECLARATOR);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationImpl", "getDeclarators"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCDeclarator>)childrenByType;
    }
    
    @Override
    public boolean isTypedef() {
        final OCTypeElement typeElement = this.getTypeElement();
        Label_0033: {
            try {
                if (typeElement == null) {
                    return false;
                }
                final OCTypeElement ocTypeElement = typeElement;
                final ASTNode astNode = ocTypeElement.getNode();
                final OCElementType ocElementType = OCTokenTypes.TYPEDEF_KEYWORD;
                final ASTNode astNode2 = astNode.findChildByType((IElementType)ocElementType);
                if (astNode2 != null) {
                    break Label_0033;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCTypeElement ocTypeElement = typeElement;
                final ASTNode astNode = ocTypeElement.getNode();
                final OCElementType ocElementType = OCTokenTypes.TYPEDEF_KEYWORD;
                final ASTNode astNode2 = astNode.findChildByType((IElementType)ocElementType);
                if (astNode2 != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    public OCTemplateParameterList getTemplateParameterList() {
        return this.findChildByType(OCElementTypes.CPP_TEMPLATE_PARAMETER_LIST);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitDeclaration(this);
    }
    
    @Override
    public boolean isStatic() {
        try {
            if (this.findChildByType(OCTokenTypes.STATIC_KEYWORD) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Nullable
    @Override
    public OCTypeArgumentList getTemplateArgumentList() {
        final List<OCDeclarator> declarators = this.getDeclarators();
        try {
            if (declarators.size() == 1) {
                return declarators.get(0).getTemplateArgumentList();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    @Override
    public Collection<OCSymbol> resolveTemplateDeclarations() {
        final List<OCDeclarator> declarators = this.getDeclarators();
        List<Object> emptyList = null;
        Label_0074: {
            Collection<OCSymbol> collection = null;
            Label_0039: {
                try {
                    if (declarators.size() != 1) {
                        break Label_0074;
                    }
                    final List<OCDeclarator> list = declarators;
                    final int n = 0;
                    final OCDeclarator ocDeclarator = list.get(n);
                    final OCDeclarator ocDeclarator2 = ocDeclarator;
                    collection = OCResolveUtil.resolveTemplateDeclarations(ocDeclarator2);
                    if (collection == null) {
                        break Label_0039;
                    }
                    return collection;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final List<OCDeclarator> list = declarators;
                    final int n = 0;
                    final OCDeclarator ocDeclarator = list.get(n);
                    final OCDeclarator ocDeclarator2 = ocDeclarator;
                    collection = OCResolveUtil.resolveTemplateDeclarations(ocDeclarator2);
                    if (collection == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationImpl", "resolveTemplateDeclarations"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return collection;
            try {
                emptyList = Collections.emptyList();
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclarationImpl", "resolveTemplateDeclarations"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return (Collection<OCSymbol>)emptyList;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
