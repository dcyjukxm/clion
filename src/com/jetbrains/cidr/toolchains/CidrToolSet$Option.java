// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

public interface Option
{
    @NotNull
    String getValue();
    
    void write(@NotNull final Element p0);
    
    @NotNull
    String getUniqueID();
}
