// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.intellij.openapi.project.Project;

public interface OCContextChange
{
    int getOffset();
    
    void apply(final Project p0, final OCInclusionContext p1);
}
