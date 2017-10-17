// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCReferenceTypeBuilder;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCObjectType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;

public class OCProtocolImpl extends OCClassDeclarationBaseImpl<OCProtocolSymbol> implements OCProtocol
{
    public OCProtocolImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCProtocolImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCObjectType getType() {
        return this.getType(false);
    }
    
    @Override
    protected Class<? extends OCClassSymbol> getSymbolClass() {
        return OCProtocolSymbol.class;
    }
    
    @Override
    public OCObjectType getType(final boolean b) {
        final String name = this.getName();
        final OCReferenceTypeBuilder ocReferenceTypeBuilder = new OCReferenceTypeBuilder("id");
        try {
            if (name != null) {
                ocReferenceTypeBuilder.setSingleProtocolName(name);
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final OCType resolve = ocReferenceTypeBuilder.build().resolve((PsiFile)this.getContainingOCFile(), b);
        try {
            if (resolve.isPointerToObject()) {
                return (OCObjectType)((OCPointerType)resolve).getRefType();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return null;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCProtocolImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        ocVisitor.visitProtocol(this);
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
