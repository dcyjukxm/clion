// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCNoexceptSpecifier;
import java.util.Collections;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;

public class OCFunctionDeclarationImpl extends OCDeclarationImpl implements OCFunctionDeclaration
{
    public OCFunctionDeclarationImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDeclarationImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCCallableKind getKind() {
        return OCCallableKind.FUNCTION;
    }
    
    @Nullable
    @Override
    public OCDeclarator getDeclarator() {
        return this.findChildByType(OCElementTypes.DECLARATOR);
    }
    
    @NotNull
    @Override
    public OCType getReturnType() {
        final OCDeclarator declarator = this.getDeclarator();
        OCType ocType = null;
        Label_0025: {
            try {
                if (declarator != null) {
                    ocType = declarator.getType();
                    break Label_0025;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            ocType = OCUnknownType.INSTANCE;
        }
        OCType refType = ocType;
        if (refType instanceof OCPointerType) {
            refType = ((OCPointerType)refType).getRefType();
        }
        OCType ocType2 = null;
        Label_0065: {
            try {
                if (refType instanceof OCFunctionType) {
                    final OCType ocType3;
                    ocType2 = (ocType3 = ((OCFunctionType)refType).getReturnType());
                    break Label_0065;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            OCType ocType3;
            ocType2 = (ocType3 = OCUnknownType.INSTANCE);
            try {
                if (ocType3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDeclarationImpl", "getReturnType"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return ocType2;
    }
    
    @Nullable
    @Override
    public OCTypeElement getTrailingReturnTypeElement() {
        final OCDeclaratorImpl ocDeclaratorImpl = (OCDeclaratorImpl)this.getDeclarator();
        if (ocDeclaratorImpl != null) {
            boolean b = false;
            for (final ASTNode astNode : ocDeclaratorImpl.getNode().getChildren((TokenSet)null)) {
                final IElementType elementType = astNode.getElementType();
                Label_0101: {
                    if (elementType == OCTokenTypes.DEREF) {
                        b = true;
                    }
                    else {
                        Label_0086: {
                            try {
                                if (elementType != OCElementTypes.TYPE_ELEMENT) {
                                    break Label_0101;
                                }
                                final boolean b2 = b;
                                if (b2) {
                                    break Label_0086;
                                }
                                break Label_0101;
                            }
                            catch (IncorrectOperationException ex) {
                                throw a(ex);
                            }
                            try {
                                final boolean b2 = b;
                                if (b2) {
                                    return (OCTypeElement)astNode.getPsi();
                                }
                            }
                            catch (IncorrectOperationException ex2) {
                                throw a(ex2);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    public OCTypeElement getReturnTypeElement() {
        final OCTypeElement trailingReturnTypeElement = this.getTrailingReturnTypeElement();
        OCTypeElement ocTypeElement = null;
        Label_0027: {
            try {
                if (trailingReturnTypeElement != null) {
                    final OCTypeElement ocTypeElement2;
                    ocTypeElement = (ocTypeElement2 = trailingReturnTypeElement);
                    break Label_0027;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            OCTypeElement ocTypeElement2;
            ocTypeElement = (ocTypeElement2 = this.findNotNullChildByType(OCElementTypes.TYPE_ELEMENT));
            try {
                if (ocTypeElement2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDeclarationImpl", "getReturnTypeElement"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return ocTypeElement;
    }
    
    @Nullable
    @Override
    public OCParameterList getParameterList() {
        final OCDeclaratorImpl ocDeclaratorImpl = (OCDeclaratorImpl)this.getDeclarator();
        try {
            if (ocDeclaratorImpl != null) {
                return (OCParameterList)ocDeclaratorImpl.findChildByType(OCElementTypes.PARAMETER_LIST);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public List<OCDeclarator> getParameters() {
        final OCParameterList parameterList = this.getParameterList();
        try {
            if (parameterList != null) {
                return parameterList.getParameters();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public OCBlockStatement getBody() {
        return null;
    }
    
    @Override
    public boolean isPossibleStructMember() {
        final OCDeclarator declarator = this.getDeclarator();
        Label_0025: {
            try {
                if (declarator == null) {
                    return false;
                }
                final OCDeclarator ocDeclarator = declarator;
                final boolean b = ocDeclarator.isPossibleStructMember();
                if (b) {
                    break Label_0025;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final OCDeclarator ocDeclarator = declarator;
                final boolean b = ocDeclarator.isPossibleStructMember();
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isStatic() {
        try {
            if (this.findChildByType(OCTokenTypes.STATIC_KEYWORD) != null) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public boolean isOperator() {
        final OCDeclarator declarator = this.getDeclarator();
        try {
            if (declarator == null) {
                return false;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (declarator.getNode().findChildByType((IElementType)OCTokenTypes.OPERATOR_CPP_KEYWORD) != null) {
                return true;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @NotNull
    @Override
    public CVQualifiers getCVQualifiers() {
        final OCDeclaratorImpl ocDeclaratorImpl = (OCDeclaratorImpl)this.getDeclarator();
        boolean b = false;
        boolean b2 = false;
        CVQualifiers value = null;
        Label_0063: {
            boolean b3 = false;
            Label_0042: {
                Label_0033: {
                    try {
                        if (ocDeclaratorImpl == null) {
                            break Label_0063;
                        }
                        final OCDeclaratorImpl ocDeclaratorImpl2 = ocDeclaratorImpl;
                        final OCElementType ocElementType = OCTokenTypes.CONST_KEYWORD;
                        final PsiElement psiElement = ocDeclaratorImpl2.findChildByType(ocElementType);
                        if (psiElement != null) {
                            break Label_0033;
                        }
                        break Label_0033;
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCDeclaratorImpl ocDeclaratorImpl2 = ocDeclaratorImpl;
                        final OCElementType ocElementType = OCTokenTypes.CONST_KEYWORD;
                        final PsiElement psiElement = ocDeclaratorImpl2.findChildByType(ocElementType);
                        if (psiElement != null) {
                            b3 = true;
                            break Label_0042;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                b3 = false;
            }
            b = b3;
            boolean b4 = false;
            Label_0062: {
                try {
                    if (ocDeclaratorImpl.findChildByType(OCTokenTypes.VOLATILE_KEYWORD) != null) {
                        b4 = true;
                        break Label_0062;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                b4 = false;
            }
            b2 = b4;
            try {
                value = CVQualifiers.get(b, b2);
                if (value == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDeclarationImpl", "getCVQualifiers"));
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return value;
    }
    
    public OCSymbolWithQualifiedName getSymbol() {
        return this.getContainingOCFile().findSymbol(this, OCSymbolWithQualifiedName.class);
    }
    
    @NotNull
    public String getName() {
        final OCDeclarator declarator = this.getDeclarator();
        String s = null;
        Label_0024: {
            try {
                if (declarator != null) {
                    final String name;
                    s = (name = declarator.getName());
                    break Label_0024;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            String name;
            s = (name = "");
            try {
                if (name == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDeclarationImpl", "getName"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    @NotNull
    public String getSymbolName() {
        final OCDeclarator declarator = this.getDeclarator();
        String s = null;
        Label_0024: {
            try {
                if (declarator != null) {
                    final String symbolName;
                    s = (symbolName = declarator.getSymbolName());
                    break Label_0024;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            String symbolName;
            s = (symbolName = "");
            try {
                if (symbolName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDeclarationImpl", "getSymbolName"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    @Override
    public PsiElement getNameIdentifier() {
        final OCDeclarator declarator = this.getDeclarator();
        try {
            if (declarator != null) {
                return declarator.getNameIdentifier();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public OCCppNamespaceQualifier getNamespaceQualifier() {
        final OCDeclarator declarator = this.getDeclarator();
        try {
            if (declarator != null) {
                return declarator.getNamespaceQualifier();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public PsiElement setName(@NonNls @NotNull final String name) throws IncorrectOperationException {
        try {
            if (name == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDeclarationImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCDeclarator declarator = this.getDeclarator();
        try {
            if (declarator != null) {
                final Object setName = declarator.setName(name);
                return (PsiElement)setName;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final Object setName = this;
        return (PsiElement)setName;
    }
    
    public int getTextOffset() {
        final OCDeclarator declarator = this.getDeclarator();
        try {
            if (declarator != null) {
                return declarator.getTextOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return 0;
    }
    
    @Override
    public long getComplexOffset() {
        final OCDeclarator declarator = this.getDeclarator();
        try {
            if (declarator != null) {
                return declarator.getComplexOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return 0L;
    }
    
    @Nullable
    @Override
    public OCElementsRange getHeaderRange() {
        final OCBlockStatement body = this.getBody();
        PsiElement firstChild;
        try {
            firstChild = this.getFirstChild();
            if (body != null) {
                final PsiElement psiElement = body.getPrevSibling();
                return new OCElementsRange(firstChild, psiElement).trim(OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiElement psiElement = this.getLastChild();
        return new OCElementsRange(firstChild, psiElement).trim(OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDeclarationImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitFunctionDeclaration(this);
    }
    
    @NotNull
    @Override
    public List<PsiElement> getVirtualSpecifiers() {
        final OCDeclarator declarator = this.getDeclarator();
        PsiElement firstChild = null;
        Label_0023: {
            try {
                if (declarator != null) {
                    firstChild = declarator.getFirstChild();
                    break Label_0023;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            firstChild = null;
        }
        PsiElement nextSibling = firstChild;
        List<PsiElement> list = null;
        while (nextSibling != null) {
            final IElementType elementType = OCElementUtil.getElementType(nextSibling);
            Label_0074: {
                Label_0066: {
                    try {
                        if (!OCTokenTypes.CPP_VIRTUAL_SPECIFIERS.contains(elementType)) {
                            break Label_0074;
                        }
                        if (list != null) {
                            break Label_0066;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    list = new ArrayList<PsiElement>();
                }
                list.add(nextSibling);
            }
            nextSibling = nextSibling.getNextSibling();
        }
        List<PsiElement> list2 = null;
        Label_0099: {
            try {
                if (list == null) {
                    final List<PsiElement> emptyList;
                    list2 = (emptyList = Collections.emptyList());
                    break Label_0099;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            List<PsiElement> emptyList;
            list2 = (emptyList = list);
            try {
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDeclarationImpl", "getVirtualSpecifiers"));
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return list2;
    }
    
    @Nullable
    @Override
    public OCNoexceptSpecifier getNoexceptSpecifier() {
        final OCDeclaratorImpl ocDeclaratorImpl = (OCDeclaratorImpl)this.getDeclarator();
        try {
            if (ocDeclaratorImpl != null) {
                return (OCNoexceptSpecifier)ocDeclaratorImpl.findChildByType(OCElementTypes.CPP_NOEXCEPT_SPECIFIER);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
