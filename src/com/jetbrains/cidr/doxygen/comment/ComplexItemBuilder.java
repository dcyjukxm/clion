// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.comment;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.documentation.doxygen.api.TagItem;

public interface ComplexItemBuilder
{
    void addTag(@NotNull final TagItem p0);
    
    void addText(@NotNull final String p0);
}
