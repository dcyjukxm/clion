// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapRealFrameworkPathResolver$Companion;", "", "()V", "createUninitialized", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapRealFrameworkPathResolver;", "cidr-lang" })
public static final class Companion
{
    @NotNull
    public final ModuleMapRealFrameworkPathResolver createUninitialized() {
        return new ModuleMapRealFrameworkPathResolver((VirtualFile)new LightVirtualFile());
    }
}
