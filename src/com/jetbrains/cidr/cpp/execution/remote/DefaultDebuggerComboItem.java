// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b" }, d2 = { "Lcom/jetbrains/cidr/cpp/execution/remote/DefaultDebuggerComboItem;", "Lcom/jetbrains/cidr/cpp/execution/remote/DebuggerComboItem;", "title", "", "(Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "toString", "clion" })
public final class DefaultDebuggerComboItem implements DebuggerComboItem
{
    @NotNull
    private final String title;
    
    @NotNull
    @Override
    public String toString() {
        return this.title;
    }
    
    @NotNull
    public final String getTitle() {
        return this.title;
    }
    
    public DefaultDebuggerComboItem(@NotNull final String title) {
        Intrinsics.checkParameterIsNotNull((Object)title, "title");
        this.title = title;
    }
}
