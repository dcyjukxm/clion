// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import kotlin.text.StringsKt;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.toolchains.DefaultCidrToolEnvironment;
import kotlin.TypeCastException;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 2, d1 = { "\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002¨\u0006\u0005" }, d2 = { "toCanonicalIncludeName", "", "includePath", "delimiters", "", "clion" })
public final class PrecompiledIncludeMapperKt
{
    private static final String a(final String s, final char[] array) {
        char[] windows_UNIX_PATH_SEPARATORS = null;
        Label_0016: {
            try {
                windows_UNIX_PATH_SEPARATORS = array;
                if (array != null) {
                    break Label_0016;
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            windows_UNIX_PATH_SEPARATORS = DefaultCidrToolEnvironment.WINDOWS_UNIX_PATH_SEPARATORS;
        }
        final char[] array2 = windows_UNIX_PATH_SEPARATORS;
        final String s2 = s;
        final char[] array3 = array2;
        Intrinsics.checkExpressionValueIsNotNull((Object)array3, "separators");
        final String join = StringUtil.join((Collection)StringsKt.split$default((CharSequence)s2, Arrays.copyOf(array3, array3.length), false, 0, 6, (Object)null), "/");
        String s3;
        try {
            s3 = join;
            if (s3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        catch (TypeCastException ex2) {
            throw b(ex2);
        }
        final String lowerCase = s3.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull((Object)lowerCase, "(this as java.lang.String).toLowerCase()");
        return lowerCase;
    }
    
    private static TypeCastException b(final TypeCastException ex) {
        return ex;
    }
}
