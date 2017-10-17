// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCStructType;
import java.util.function.ToIntFunction;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;

public abstract class OCIntTypeVisitor implements OCTypeVisitor<Integer>
{
    private OCResolveContext myContext;
    
    public OCIntTypeVisitor(final OCResolveContext myContext) {
        this.myContext = myContext;
    }
    
    protected abstract int visitType(final OCType p0);
    
    protected abstract int visitNonTypeArgument(final OCTypeArgument p0);
    
    protected int visitTypeArgument(final OCTypeArgument ocTypeArgument) {
        return (ocTypeArgument instanceof OCType) ? ((OCType)ocTypeArgument).accept((OCTypeVisitor<Integer>)this) : this.visitNonTypeArgument(ocTypeArgument);
    }
    
    @Override
    public Integer visitFunctionType(final OCFunctionType ocFunctionType) {
        return this.visitType(ocFunctionType) + ocFunctionType.getReturnType().accept((OCTypeVisitor<Integer>)this) + ocFunctionType.getParameterTypes().stream().mapToInt(ocType -> ocType.accept((OCTypeVisitor<Integer>)this)).sum();
    }
    
    @Override
    public Integer visitArrayType(final OCArrayType ocArrayType) {
        return this.visitType(ocArrayType) + ocArrayType.getRefType().accept((OCTypeVisitor<Integer>)this);
    }
    
    @Override
    public Integer visitPointerType(final OCPointerType ocPointerType) {
        return this.visitType(ocPointerType) + ocPointerType.getRefType().accept((OCTypeVisitor<Integer>)this);
    }
    
    @Override
    public Integer visitBlockPointerType(final OCBlockPointerType ocBlockPointerType) {
        return this.visitType(ocBlockPointerType) + ocBlockPointerType.getRefType().accept((OCTypeVisitor<Integer>)this);
    }
    
    @Override
    public Integer visitCppReferenceType(final OCCppReferenceType ocCppReferenceType) {
        return this.visitType(ocCppReferenceType) + ocCppReferenceType.getRefType().accept((OCTypeVisitor<Integer>)this);
    }
    
    @Override
    public Integer visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
        return this.visitType(ocExpansionPackType) + ocExpansionPackType.getExpansions().stream().mapToInt((ToIntFunction<? super Object>)this::visitTypeArgument).sum();
    }
    
    @Override
    public Integer visitStructType(final OCStructType ocStructType) {
        return this.visitType(ocStructType) + ocStructType.getSymbol().getTemplateArguments(this.myContext).stream().mapToInt((ToIntFunction<? super Object>)this::visitTypeArgument).sum();
    }
    
    @Override
    public Integer visitObjectType(final OCObjectType ocObjectType) {
        return this.visitType(ocObjectType);
    }
    
    @Override
    public Integer visitIdType(final OCIdType ocIdType) {
        return this.visitType(ocIdType);
    }
    
    @Override
    public Integer visitIntType(final OCIntType ocIntType) {
        return this.visitType(ocIntType);
    }
    
    @Override
    public Integer visitRealType(final OCRealType ocRealType) {
        return this.visitType(ocRealType);
    }
    
    @Override
    public Integer visitReferenceType(final OCReferenceType ocReferenceType) {
        return this.visitType(ocReferenceType);
    }
    
    @Override
    public Integer visitEllipsisReferenceType(final OCEllipsisType ocEllipsisType) {
        return this.visitType(ocEllipsisType);
    }
    
    @Override
    public Integer visitMagicType(final OCMagicType ocMagicType) {
        return this.visitType(ocMagicType);
    }
    
    @Override
    public Integer visitUnknownType(final OCUnknownType ocUnknownType) {
        return this.visitType(ocUnknownType);
    }
    
    @Override
    public Integer visitVoidType(final OCVoidType ocVoidType) {
        return this.visitType(ocVoidType);
    }
    
    @Override
    public Integer visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        return this.visitType(ocTypeParameterType);
    }
    
    @Override
    public Integer visitAutoType(final OCAutoType ocAutoType) {
        return this.visitType(ocAutoType);
    }
    
    @Override
    public Integer visitVariadicType(final OCVariadicType ocVariadicType) {
        return this.visitType(ocVariadicType);
    }
}
