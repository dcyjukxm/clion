// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCArrayType;

public class OCArrayToPointerChanger extends OCTypeCloneVisitor
{
    public static OCArrayToPointerChanger INSTANCE;
    
    protected OCArrayToPointerChanger() {
        super(false);
    }
    
    @Override
    public OCType visitArrayType(final OCArrayType ocArrayType) {
        return OCPointerType.to(ocArrayType.getRefType().accept((OCTypeVisitor<OCType>)this), ocArrayType.getARCAttribute(), null, ocArrayType.isConst(), ocArrayType.isVolatile());
    }
    
    static {
        OCArrayToPointerChanger.INSTANCE = new OCArrayToPointerChanger();
    }
}
