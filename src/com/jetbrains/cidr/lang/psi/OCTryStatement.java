// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface OCTryStatement extends OCStatement
{
    @Nullable
    OCStatement getBody();
    
    @NotNull
    List<OCCatchSection> getCatchSections();
    
    @Nullable
    OCFinallySection getFinallySection();
    
    boolean isCppStatement();
}
