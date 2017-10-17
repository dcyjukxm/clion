// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.execution.process.ProcessHandler;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/model/CMakeListener;", "", "attachTo", "", "handler", "Lcom/intellij/execution/process/ProcessHandler;", "message", "Lcom/jetbrains/cidr/cpp/cmake/model/CMakeMessage;", "clion" })
public interface CMakeListener
{
    void message(@NotNull final CMakeMessage p0);
    
    void attachTo(@NotNull final ProcessHandler p0);
}
