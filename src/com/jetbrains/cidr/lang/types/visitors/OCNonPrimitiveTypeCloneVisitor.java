// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCIntType;

public class OCNonPrimitiveTypeCloneVisitor extends OCTypeCloneVisitor
{
    public OCNonPrimitiveTypeCloneVisitor() {
        super(false);
    }
    
    @Override
    public OCType visitIntType(final OCIntType ocIntType) {
        return ocIntType;
    }
    
    @Override
    public OCType visitRealType(final OCRealType ocRealType) {
        return ocRealType;
    }
    
    @Override
    public OCType visitVoidType(final OCVoidType ocVoidType) {
        return ocVoidType;
    }
    
    @Override
    public OCType visitMagicType(final OCMagicType ocMagicType) {
        return ocMagicType;
    }
    
    @Override
    public OCType visitObjectType(final OCObjectType ocObjectType) {
        return ocObjectType;
    }
    
    @Override
    public OCType visitIdType(final OCIdType ocIdType) {
        return ocIdType;
    }
    
    @Override
    public OCType visitUnknownType(final OCUnknownType ocUnknownType) {
        return ocUnknownType;
    }
}
