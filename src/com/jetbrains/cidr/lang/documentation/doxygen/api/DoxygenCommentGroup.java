// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation.doxygen.api;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface DoxygenCommentGroup
{
    @NotNull
    String getOptions();
    
    @NotNull
    List<InfoItem> getContent();
    
    boolean isEmpty();
}
