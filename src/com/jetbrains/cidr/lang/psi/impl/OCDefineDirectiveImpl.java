// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCMacroParameterList;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;

public class OCDefineDirectiveImpl extends OCDirectiveImpl implements OCDefineDirective
{
    public OCDefineDirectiveImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCDefineDirectiveImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    public String getName() {
        final OCMacroSymbol symbol = this.getSymbol();
        String symbolName;
        try {
            symbolName = OCSymbolBase.getSymbolName(symbol);
            if (symbolName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDefineDirectiveImpl", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return symbolName;
    }
    
    public OCMacroSymbol getSymbol() {
        if (this.isValid()) {
            final PsiElement nameIdentifier = this.getNameIdentifier();
            if (nameIdentifier != null) {
                final int n = nameIdentifier.getTextOffset() - super.getTextOffset();
                final String text = this.getText();
                Label_0052: {
                    try {
                        if (n <= 0) {
                            return null;
                        }
                        final int n2 = n;
                        final String s = text;
                        final int n3 = s.length();
                        if (n2 < n3) {
                            break Label_0052;
                        }
                        return null;
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final int n2 = n;
                        final String s = text;
                        final int n3 = s.length();
                        if (n2 < n3) {
                            return OCMacroSymbol.parseFromDirectiveContent(text.substring(n), this.getContainingOCFile(), this.getTextOffset());
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
        return null;
    }
    
    public PsiElement getNameIdentifier() {
        return this.findChildByType(OCTokenTypes.IDENTIFIER);
    }
    
    @Override
    public OCMacroParameterList getMacroParameters() {
        return this.findChildByType(OCElementTypes.MACRO_PARAMETER_LIST);
    }
    
    public int getTextOffset() {
        final PsiElement nameIdentifier = this.getNameIdentifier();
        try {
            if (nameIdentifier == null) {
                return super.getTextOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return nameIdentifier.getNode().getStartOffset();
    }
    
    @Override
    public long getComplexOffset() {
        final PsiElement nameIdentifier = this.getNameIdentifier();
        try {
            if (nameIdentifier == null) {
                return super.getComplexOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return OCSymbolOffsetUtil.getComplexOffset(nameIdentifier);
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCDefineDirectiveImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
        return (PsiElement)this;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCDefineDirectiveImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitDefineDirective(this);
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
