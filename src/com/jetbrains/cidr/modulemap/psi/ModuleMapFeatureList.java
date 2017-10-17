// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface ModuleMapFeatureList extends ModuleMapPsiElement
{
    @NotNull
    List<ModuleMapFeature> getFeatureList();
}
