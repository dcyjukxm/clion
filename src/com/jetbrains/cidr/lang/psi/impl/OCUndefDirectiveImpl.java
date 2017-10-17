// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.intellij.util.text.CharArrayUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.symbols.cpp.OCUndefMacroSymbol;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCUndefDirective;

public class OCUndefDirectiveImpl extends OCDirectiveImpl implements OCUndefDirective
{
    public OCUndefDirectiveImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCUndefDirectiveImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    public String getName() {
        final OCUndefMacroSymbol symbol = this.getSymbol();
        String symbolName;
        try {
            symbolName = OCSymbolBase.getSymbolName(symbol);
            if (symbolName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUndefDirectiveImpl", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return symbolName;
    }
    
    public OCUndefMacroSymbol getSymbol() {
        if (this.isValid()) {
            final ASTNode a = this.a();
            try {
                if (a != null) {
                    return OCUndefMacroSymbol.parseFromDirectiveContent(a.getText(), this.getContainingOCFile(), a.getStartOffset());
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    @Nullable
    private ASTNode a() {
        return this.getNode().findChildByType((IElementType)OCElementTypes.MACRO_REF);
    }
    
    public int getTextOffset() {
        final ASTNode a = this.a();
        try {
            if (a == null) {
                return super.getTextOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return a.getStartOffset() + CharArrayUtil.shiftForward((CharSequence)a.getText(), 0, " \t");
    }
    
    @Override
    public long getComplexOffset() {
        final ASTNode a = this.a();
        try {
            if (a == null) {
                return super.getComplexOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return OCSymbolOffsetUtil.getComplexOffset(a.getPsi()) + CharArrayUtil.shiftForward((CharSequence)a.getText(), 0, " \t");
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCUndefDirectiveImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final ASTNode a = this.a();
        CodeEditUtil.replaceChild(this.getNode(), a, ((OCUndefDirectiveImpl)OCElementFactory.topLevelDeclarationFromText("#undef " + a.getText().replaceFirst(this.getName(), s), (PsiElement)this)).a());
        return (PsiElement)this;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
