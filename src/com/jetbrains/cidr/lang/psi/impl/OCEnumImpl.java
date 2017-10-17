// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCEnum;

public class OCEnumImpl extends OCStructLikeImpl implements OCEnum
{
    public OCEnumImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCEnumImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCEnumImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitEnum(this);
    }
    
    @Override
    public OCSymbolKind getKind() {
        return OCSymbolKind.ENUM;
    }
    
    @Override
    public boolean isEnumClass() {
        try {
            if (this.findChildByType(TokenSet.create(new IElementType[] { OCTokenTypes.CLASS_KEYWORD, OCTokenTypes.STRUCT_KEYWORD })) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public void processEnumConsts(final Processor<OCSymbol> processor) {
        this.processSymbols((Processor<OCSymbol>)(ocSymbol -> {
            try {
                if (!(ocSymbol instanceof OCStructSymbol)) {
                    return processor.process((Object)ocSymbol);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return true;
        }));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
