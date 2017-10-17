// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCFunctionType;

public class OCBooleanTypeVisitor implements OCTypeVisitor<Boolean>
{
    @Override
    public Boolean visitFunctionType(final OCFunctionType ocFunctionType) {
        if (ocFunctionType.getReturnType().accept((OCTypeVisitor<Boolean>)this)) {
            return true;
        }
        final Iterator<OCType> iterator = ocFunctionType.getParameterTypes().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().accept((OCTypeVisitor<Boolean>)this)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Boolean visitArrayType(final OCArrayType ocArrayType) {
        return ocArrayType.getRefType().accept((OCTypeVisitor<Boolean>)this);
    }
    
    @Override
    public Boolean visitPointerType(final OCPointerType ocPointerType) {
        return ocPointerType.getRefType().accept((OCTypeVisitor<Boolean>)this);
    }
    
    @Override
    public Boolean visitBlockPointerType(final OCBlockPointerType ocBlockPointerType) {
        return ocBlockPointerType.getRefType().accept((OCTypeVisitor<Boolean>)this);
    }
    
    @Override
    public Boolean visitCppReferenceType(final OCCppReferenceType ocCppReferenceType) {
        return ocCppReferenceType.getRefType().accept((OCTypeVisitor<Boolean>)this);
    }
    
    @Override
    public Boolean visitEllipsisReferenceType(final OCEllipsisType ocEllipsisType) {
        return false;
    }
    
    @Override
    public Boolean visitMagicType(final OCMagicType ocMagicType) {
        return false;
    }
    
    @Override
    public Boolean visitObjectType(final OCObjectType ocObjectType) {
        return false;
    }
    
    @Override
    public Boolean visitIdType(final OCIdType ocIdType) {
        return false;
    }
    
    @Override
    public Boolean visitIntType(final OCIntType ocIntType) {
        return false;
    }
    
    @Override
    public Boolean visitRealType(final OCRealType ocRealType) {
        return false;
    }
    
    @Override
    public Boolean visitReferenceType(final OCReferenceType ocReferenceType) {
        return false;
    }
    
    @Override
    public Boolean visitStructType(final OCStructType ocStructType) {
        return false;
    }
    
    @Override
    public Boolean visitUnknownType(final OCUnknownType ocUnknownType) {
        return false;
    }
    
    @Override
    public Boolean visitVoidType(final OCVoidType ocVoidType) {
        return false;
    }
    
    @Override
    public Boolean visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        return false;
    }
    
    @Override
    public Boolean visitAutoType(final OCAutoType ocAutoType) {
        return false;
    }
    
    @Override
    public Boolean visitVariadicType(final OCVariadicType ocVariadicType) {
        return ocVariadicType.getUnderlyingType().accept((OCTypeVisitor<Boolean>)this);
    }
    
    @Override
    public Boolean visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
        for (final OCTypeArgument ocTypeArgument : ocExpansionPackType.getExpansions()) {
            if (ocTypeArgument instanceof OCType && ((OCType)ocTypeArgument).accept((OCTypeVisitor<Boolean>)this)) {
                return true;
            }
        }
        return false;
    }
}
