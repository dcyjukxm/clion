// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import com.jetbrains.sourceglider.ui.ThreadCallback;
import java.util.Map;

@FunctionalInterface
public interface VisitorRunner
{
    void run(final Map p0, final ThreadCallback p1);
}
