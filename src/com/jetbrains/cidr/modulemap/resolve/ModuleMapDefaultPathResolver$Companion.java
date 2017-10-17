// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.resolve;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.vfs.newvfs.impl.NullVirtualFile;
import kotlin.text.Regex;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapDefaultPathResolver$Companion;", "", "()V", "regex", "Lkotlin/text/Regex;", "getRegex", "()Lkotlin/text/Regex;", "createUninitialized", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapDefaultPathResolver;", "cidr-lang" })
public static final class Companion
{
    private final Regex a() {
        return ModuleMapDefaultPathResolver.access$getRegex$cp();
    }
    
    @NotNull
    public final ModuleMapDefaultPathResolver createUninitialized() {
        final NullVirtualFile instance = NullVirtualFile.INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull((Object)instance, "NullVirtualFile.INSTANCE");
        return new ModuleMapDefaultPathResolver((VirtualFile)instance);
    }
}
