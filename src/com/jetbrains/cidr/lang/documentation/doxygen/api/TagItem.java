// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation.doxygen.api;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface TagItem extends InfoItem
{
    @NotNull
    String getName();
    
    @NotNull
    String getOptions();
    
    @NotNull
    List<String> getParameters();
    
    @NotNull
    String getDescription();
    
    @NotNull
    List<InfoItem> getContent();
}
