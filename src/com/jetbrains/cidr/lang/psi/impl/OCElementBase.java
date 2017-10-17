// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import org.jetbrains.annotations.Contract;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.PsiReference;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Iconable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class OCElementBase extends ASTWrapperPsiElement implements OCElement
{
    protected static final String EMPTY_NAME = "";
    private static final TokenSet SYMBOL_NAME_STARTERS;
    private static final TokenSet CPP_OPERATOR_SYMBOL_NAME_PARTS;
    
    public OCElementBase(@NotNull final ASTNode node) {
        if (node == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "<init>"));
        }
        super(node);
    }
    
    @NotNull
    @Override
    public Language getLanguage() {
        OCLanguage instance;
        try {
            instance = OCLanguage.getInstance();
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "getLanguage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return instance;
    }
    
    @Override
    public String toString() {
        return OCElementUtil.getElementDebugName((PsiElement)this);
    }
    
    @Override
    public OCFile getContainingOCFile() {
        return (OCFile)super.getContainingFile();
    }
    
    protected Icon getBaseIcon() {
        final OCSymbol a = this.a();
        try {
            if (a != null) {
                return a.getBaseIcon();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return super.getBaseIcon();
    }
    
    @Nullable
    protected Icon getElementIcon(@Iconable.IconFlags final int n) {
        final OCSymbol a = this.a();
        try {
            if (a != null) {
                return a.computeFullIconNow((PsiElement)this);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return super.getElementIcon(n);
    }
    
    public ItemPresentation getPresentation() {
        final OCSymbol a = this.a();
        try {
            if (a != null) {
                return a.getPresentation();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return super.getPresentation();
    }
    
    @Nullable
    private OCSymbol a() {
        try {
            if (this instanceof OCSymbolDeclarator) {
                return ((OCSymbolDeclarator)this).getSymbol();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    public void accept(@NotNull final PsiElementVisitor psiElementVisitor) {
        try {
            if (psiElementVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiElementVisitor instanceof OCVisitor) {
                this.accept((OCVisitor)psiElementVisitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        psiElementVisitor.visitElement((PsiElement)this);
    }
    
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        ocVisitor.visitOCElement(this);
    }
    
    @NotNull
    @Override
    public String getTextWithMacros() {
        String textWithMacros;
        try {
            textWithMacros = OCElementUtil.getTextWithMacros((PsiElement)this);
            if (textWithMacros == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "getTextWithMacros"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return textWithMacros;
    }
    
    @NotNull
    @Override
    public TextRange getRangeWithMacros() {
        TextRange rangeWithMacros;
        try {
            rangeWithMacros = OCElementUtil.getRangeWithMacros((PsiElement)this);
            if (rangeWithMacros == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "getRangeWithMacros"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return rangeWithMacros;
    }
    
    @Override
    public boolean isEmpty() {
        return OCElementUtil.isElementEmpty((PsiElement)this);
    }
    
    public PsiReference findReferenceAt(final int n) {
        final PsiReference referenceInMacro = OCElementUtil.findReferenceInMacro(this.findElementAt(n));
        try {
            if (referenceInMacro != null) {
                return referenceInMacro;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return super.findReferenceAt(n);
    }
    
    @NotNull
    public SearchScope getUseScope() {
        SearchScope useScope;
        try {
            useScope = OCElementUtil.getUseScope((PsiElement)this);
            if (useScope == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "getUseScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return useScope;
    }
    
    @Override
    public void deleteChildInternal(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "deleteChildInternal"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        OCChangeUtil.delete(astNode.getPsi());
    }
    
    @Override
    public long getComplexOffset() {
        return OCSymbolOffsetUtil.getComplexOffset((PsiElement)this);
    }
    
    @Nullable
    protected PsiElement findReferenceTokenInCall() {
        return this.findChildByType(OCElementBase.SYMBOL_NAME_STARTERS);
    }
    
    @Nullable
    protected PsiElement findNameStartTokenInCall() {
        final PsiElement referenceTokenInCall = this.findReferenceTokenInCall();
        try {
            if (OCElementUtil.getElementType(referenceTokenInCall) == OCTokenTypes.OPERATOR_CPP_KEYWORD) {
                return b(referenceTokenInCall);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return referenceTokenInCall;
    }
    
    protected TextRange getRangeInCallElement() {
        final PsiElement referenceTokenInCall = this.findReferenceTokenInCall();
        if (referenceTokenInCall != null) {
            final TextRange a = a((PsiElement)this);
            try {
                if (TextRange.EMPTY_RANGE.equals((Object)a)) {
                    return TextRange.EMPTY_RANGE;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            final int startOffset = a.getStartOffset();
            if (OCElementUtil.getElementType(referenceTokenInCall) == OCTokenTypes.OPERATOR_CPP_KEYWORD) {
                final PsiElement b = b(referenceTokenInCall);
                final TextRange a2 = a(b);
                try {
                    if (TextRange.EMPTY_RANGE.equals((Object)a2)) {
                        return TextRange.EMPTY_RANGE;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                final PsiElement lastChild = referenceTokenInCall.getParent().getLastChild();
                while (true) {
                    if (b != lastChild) {
                        final TextRange empty_RANGE = TextRange.EMPTY_RANGE;
                        final TextRange a3 = a(lastChild);
                        try {
                            if (empty_RANGE.equals((Object)a3)) {
                                return a2.shiftRight(-startOffset);
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                        return TextRange.create(a2.getStartOffset(), a3.getEndOffset()).shiftRight(-startOffset);
                    }
                    continue;
                }
            }
            else {
                final PsiElement skipSiblingsBackward = PsiTreeUtil.skipSiblingsBackward(referenceTokenInCall, new Class[] { PsiWhiteSpace.class });
                try {
                    if (OCElementUtil.getElementType(skipSiblingsBackward) == OCTokenTypes.TILDE) {
                        return TextRange.create(a(skipSiblingsBackward).getStartOffset(), a(referenceTokenInCall).getEndOffset()).shiftRight(-startOffset);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                final TextRange a4 = a(referenceTokenInCall);
                try {
                    if (!TextRange.EMPTY_RANGE.equals((Object)a4)) {
                        return a4.shiftRight(-startOffset);
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
        }
        return TextRange.EMPTY_RANGE;
    }
    
    @Contract("null -> null")
    private static PsiElement b(@Nullable final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return OCElementUtil.getNextSiblingOfAnyType(psiElement, OCElementBase.CPP_OPERATOR_SYMBOL_NAME_PARTS);
    }
    
    @NotNull
    @Contract(pure = true)
    private static TextRange a(@Nullable PsiElement deepestFirst) {
        Label_0112: {
            TextRange textRange2 = null;
            Label_0077: {
                Label_0053: {
                    TextRange textRange = null;
                    Label_0018: {
                        try {
                            if (deepestFirst != null) {
                                break Label_0053;
                            }
                            textRange = TextRange.EMPTY_RANGE;
                            if (textRange == null) {
                                break Label_0018;
                            }
                            return textRange;
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        try {
                            textRange = TextRange.EMPTY_RANGE;
                            if (textRange == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "getRange"));
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                    }
                    return textRange;
                    try {
                        if (OCElementUtil.isElementEmpty(deepestFirst)) {
                            break Label_0112;
                        }
                        final PsiElement psiElement = deepestFirst;
                        textRange2 = psiElement.getTextRange();
                        if (textRange2 == null) {
                            break Label_0077;
                        }
                        return textRange2;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    final PsiElement psiElement = deepestFirst;
                    textRange2 = psiElement.getTextRange();
                    if (textRange2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "getRange"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            return textRange2;
        }
        if (!(deepestFirst instanceof LeafElement)) {
            deepestFirst = PsiTreeUtil.getDeepestFirst(deepestFirst);
        }
        TextRange empty_RANGE = null;
        Label_0184: {
            TextRange textRange3 = null;
            Label_0149: {
                try {
                    if (!(deepestFirst instanceof OCMacroForeignLeafElement)) {
                        break Label_0184;
                    }
                    final PsiElement psiElement2 = deepestFirst;
                    final OCMacroForeignLeafElement ocMacroForeignLeafElement = (OCMacroForeignLeafElement)psiElement2;
                    textRange3 = ocMacroForeignLeafElement.getRealTextRange();
                    if (textRange3 == null) {
                        break Label_0149;
                    }
                    return textRange3;
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                try {
                    final PsiElement psiElement2 = deepestFirst;
                    final OCMacroForeignLeafElement ocMacroForeignLeafElement = (OCMacroForeignLeafElement)psiElement2;
                    textRange3 = ocMacroForeignLeafElement.getRealTextRange();
                    if (textRange3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "getRange"));
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
            }
            return textRange3;
            try {
                empty_RANGE = TextRange.EMPTY_RANGE;
                if (empty_RANGE == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCElementBase", "getRange"));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
        }
        return empty_RANGE;
    }
    
    static {
        SYMBOL_NAME_STARTERS = TokenSet.orSet(new TokenSet[] { OCTokenTypes.POSSIBLE_ID_NAMES, TokenSet.create(new IElementType[] { OCTokenTypes.OPERATOR_CPP_KEYWORD, OCTokenTypes.THIS_CPP_KEYWORD }) });
        CPP_OPERATOR_SYMBOL_NAME_PARTS = TokenSet.orSet(new TokenSet[] { OCTokenTypes.POSSIBLE_ID_NAMES, OCTokenTypes.OVERLOADED_CPP_OPERATORS, TokenSet.create(new IElementType[] { OCElementTypes.TYPE_ELEMENT }) });
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
