// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

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
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCEllipsisType;

public interface OCTypeVisitor<T>
{
    T visitEllipsisReferenceType(final OCEllipsisType p0);
    
    T visitFunctionType(final OCFunctionType p0);
    
    T visitMagicType(final OCMagicType p0);
    
    T visitObjectType(final OCObjectType p0);
    
    T visitArrayType(final OCArrayType p0);
    
    T visitPointerType(final OCPointerType p0);
    
    T visitBlockPointerType(final OCBlockPointerType p0);
    
    T visitCppReferenceType(final OCCppReferenceType p0);
    
    T visitIdType(final OCIdType p0);
    
    T visitIntType(final OCIntType p0);
    
    T visitRealType(final OCRealType p0);
    
    T visitReferenceType(final OCReferenceType p0);
    
    T visitStructType(final OCStructType p0);
    
    T visitUnknownType(final OCUnknownType p0);
    
    T visitVoidType(final OCVoidType p0);
    
    T visitTypeParameterType(final OCTypeParameterType p0);
    
    T visitAutoType(final OCAutoType p0);
    
    T visitVariadicType(final OCVariadicType p0);
    
    T visitExpansionPackType(final OCExpansionPackType p0);
}
