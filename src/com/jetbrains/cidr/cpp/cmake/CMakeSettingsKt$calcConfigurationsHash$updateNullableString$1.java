// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.Nullable;
import java.util.zip.CRC32;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005" }, d2 = { "<anonymous>", "", "s", "", "invoke", "(Ljava/lang/String;)Lkotlin/Unit;" })
static final class CMakeSettingsKt$calcConfigurationsHash$updateNullableString$1 extends Lambda implements Function1<String, Unit> {
    @Nullable
    public final Unit invoke(@Nullable final String s) {
        this.$crc.update((s != null) ? 1 : 0);
        Unit instance;
        if (s != null) {
            final CRC32 $crc = this.$crc;
            final byte[] bytes = s.getBytes(Charsets.UTF_8);
            Intrinsics.checkExpressionValueIsNotNull((Object)bytes, "(this as java.lang.String).getBytes(charset)");
            $crc.update(bytes);
            instance = Unit.INSTANCE;
        }
        else {
            instance = null;
        }
        return instance;
    }
}