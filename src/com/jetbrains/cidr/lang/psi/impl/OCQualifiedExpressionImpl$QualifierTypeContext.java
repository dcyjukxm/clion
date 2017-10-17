// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.types.OCType;

private static class QualifierTypeContext
{
    OCType qualifierType;
    OCType containerType;
    
    public QualifierTypeContext(final OCType qualifierType, final OCType containerType) {
        this.qualifierType = qualifierType;
        this.containerType = containerType;
    }
}
