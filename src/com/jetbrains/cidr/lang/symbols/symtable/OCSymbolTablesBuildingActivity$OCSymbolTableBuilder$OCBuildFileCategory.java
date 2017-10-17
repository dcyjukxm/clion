// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;

private static final class OCBuildFileCategory
{
    private final Object myConfigurationCluster;
    private final OCLanguageKind myKind;
    
    private OCBuildFileCategory(@Nullable final Object myConfigurationCluster, @Nullable final OCLanguageKind myKind) {
        this.myConfigurationCluster = myConfigurationCluster;
        this.myKind = myKind;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final OCBuildFileCategory ocBuildFileCategory = (OCBuildFileCategory)o;
        if (this.myKind != ocBuildFileCategory.myKind) {
            return false;
        }
        if (this.myConfigurationCluster != null) {
            if (this.myConfigurationCluster.equals(ocBuildFileCategory.myConfigurationCluster)) {
                return true;
            }
        }
        else if (ocBuildFileCategory.myConfigurationCluster == null) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return 31 * ((this.myConfigurationCluster != null) ? this.myConfigurationCluster.hashCode() : 0) + ((this.myKind != null) ? this.myKind.hashCode() : 0);
    }
}
