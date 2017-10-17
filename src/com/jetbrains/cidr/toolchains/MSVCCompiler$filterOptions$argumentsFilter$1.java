// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Set;
import kotlin.Metadata;
import java.util.function.BiFunction;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u000b*\u0001\u0000\b\n\u0018\u00002\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001f\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000e" }, d2 = { "com/jetbrains/cidr/toolchains/MSVCCompiler$filterOptions$argumentsFilter$1", "Ljava/util/function/BiFunction;", "", "", "(Ljava/util/Set;)V", "skipOptionValue", "getSkipOptionValue$cidr_common", "()Z", "setSkipOptionValue$cidr_common", "(Z)V", "apply", "parameter", "nextParameter", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Boolean;", "cidr-common" })
public static final class MSVCCompiler$filterOptions$argumentsFilter$1 implements BiFunction<String, String, Boolean> {
    private boolean skipOptionValue;
    
    public final boolean getSkipOptionValue$cidr_common() {
        return this.skipOptionValue;
    }
    
    public final void setSkipOptionValue$cidr_common(final boolean skipOptionValue) {
        this.skipOptionValue = skipOptionValue;
    }
    
    @NotNull
    @Override
    public Boolean apply(@NotNull final String s, @Nullable final String s2) {
        Intrinsics.checkParameterIsNotNull((Object)s, "parameter");
        final boolean startsWith$default = StringsKt.startsWith$default(s, "/", false, 2, (Object)null);
        if (this.skipOptionValue) {
            this.skipOptionValue = false;
            if (!startsWith$default) {
                return false;
            }
        }
        if (this.$skipOptions.contains(s)) {
            this.skipOptionValue = startsWith$default;
            return false;
        }
        if (Intrinsics.areEqual((Object)s, (Object)"/FI") && CollectionsKt.contains((Iterable)this.$skipOptions, (Object)s2)) {
            return false;
        }
        if (StringsKt.startsWith$default(s, "/FI", false, 2, (Object)null)) {
            final Set $skipOptions = this.$skipOptions;
            final int length = "/FI".length();
            final Set set = $skipOptions;
            final String substring = s.substring(length);
            Intrinsics.checkExpressionValueIsNotNull((Object)substring, "(this as java.lang.String).substring(startIndex)");
            if (set.contains(substring)) {
                return false;
            }
        }
        return true;
    }
}