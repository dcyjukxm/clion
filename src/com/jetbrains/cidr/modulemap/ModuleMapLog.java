// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.JvmField;
import com.intellij.openapi.diagnostic.Logger;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004" }, d2 = { "Lcom/jetbrains/cidr/modulemap/ModuleMapLog;", "", "()V", "Companion", "cidr-lang" })
public final class ModuleMapLog
{
    @JvmField
    @NotNull
    public static final Logger LOG;
    public static final Companion Companion;
    
    static {
        Companion = new Companion(null);
        final Logger instance = Logger.getInstance('#' + ModuleMapLog.class.getPackage().getName());
        Intrinsics.checkExpressionValueIsNotNull((Object)instance, "Logger.getInstance(\"#${M\u2026ss.java.`package`.name}\")");
        LOG = instance;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005" }, d2 = { "Lcom/jetbrains/cidr/modulemap/ModuleMapLog$Companion;", "", "()V", "LOG", "Lcom/intellij/openapi/diagnostic/Logger;", "cidr-lang" })
    public static final class Companion
    {
    }
}
