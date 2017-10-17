// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import java.util.Collections;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public static class ResultList<T>
{
    @NotNull
    public final List<T> list;
    public final boolean hasMore;
    
    public ResultList(@NotNull final List<T> list, final boolean hasMore) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ResultList", "<init>"));
        }
        this.list = list;
        this.hasMore = hasMore;
    }
    
    @NotNull
    public static <T> ResultList<T> create(@NotNull final List<T> list, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ResultList", "create"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ResultList list2;
        try {
            list2 = new ResultList<T>(list, b);
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ResultList", "create"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (ResultList<T>)list2;
    }
    
    @NotNull
    public static <T> ResultList<T> empty() {
        ResultList<T> create;
        try {
            create = create(Collections.emptyList(), false);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$ResultList", "empty"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return create;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
