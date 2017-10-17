// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;
import java.util.function.BiFunction;

public interface MIResponseFilter extends BiFunction<String, String, String>
{
    String apply(@NotNull final String p0, @NotNull final String p1);
}
