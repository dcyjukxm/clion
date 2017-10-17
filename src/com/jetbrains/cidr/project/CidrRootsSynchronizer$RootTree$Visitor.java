// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import org.jetbrains.annotations.NotNull;
import java.util.Set;

public interface Visitor
{
    void enter();
    
    boolean visit(@NotNull final Set<RootItem> p0);
    
    void exit();
}
