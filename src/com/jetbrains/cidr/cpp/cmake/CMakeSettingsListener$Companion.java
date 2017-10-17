// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.messages.Topic;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001f\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/CMakeSettingsListener$Companion;", "", "()V", "TOPIC", "Lcom/intellij/util/messages/Topic;", "Lcom/jetbrains/cidr/cpp/cmake/CMakeSettingsListener;", "kotlin.jvm.PlatformType", "getTOPIC", "()Lcom/intellij/util/messages/Topic;", "clion" })
public static final class Companion
{
    @NotNull
    private static final Topic<CMakeSettingsListener> TOPIC;
    
    @NotNull
    public final Topic<CMakeSettingsListener> getTOPIC() {
        return Companion.TOPIC;
    }
    
    private Companion() {
        TOPIC = new Topic(CMakeSettingsListener.class.getSimpleName(), (Class)CMakeSettingsListener.class);
    }
}
