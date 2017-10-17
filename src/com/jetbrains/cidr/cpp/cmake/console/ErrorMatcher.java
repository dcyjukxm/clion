// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.console;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005H&¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/console/ErrorMatcher;", "", "isPotentialMatch", "", "multilineFragment", "", "match", "Lcom/jetbrains/cidr/cpp/cmake/console/CMakeConsoleMessageType;", "line", "clion" })
public interface ErrorMatcher
{
    boolean isPotentialMatch(@NotNull final CharSequence p0);
    
    @Nullable
    CMakeConsoleMessageType match(@NotNull final CharSequence p0);
}
