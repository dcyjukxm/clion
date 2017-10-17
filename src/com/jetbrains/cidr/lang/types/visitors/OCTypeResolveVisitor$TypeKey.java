// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.types.OCType;

public static class TypeKey
{
    private OCType type;
    private OCTypeSubstitution substitution;
    private final boolean isInSFINAE;
    
    public TypeKey(final OCType type, final OCTypeSubstitution substitution, final boolean isInSFINAE) {
        this.type = type;
        this.substitution = substitution;
        this.isInSFINAE = isInSFINAE;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof TypeKey)) {
            return false;
        }
        final TypeKey typeKey = (TypeKey)o;
        return DeepEqual.equalObjects(this.type, typeKey.type) && DeepEqual.equalObjects(this.substitution, typeKey.substitution) && this.isInSFINAE == typeKey.isInSFINAE;
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * ((this.type != null) ? this.type.hashCode() : 0) + ((this.substitution != null) ? this.substitution.hashCode() : 0)) + (this.isInSFINAE ? 1 : 0);
    }
}
