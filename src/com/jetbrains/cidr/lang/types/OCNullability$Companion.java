// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/lang/types/OCNullability$Companion;", "", "()V", "parseFrom", "Lcom/jetbrains/cidr/lang/types/OCNullability;", "tokenType", "Lcom/intellij/psi/tree/IElementType;", "cidr-lang" })
public static final class Companion
{
    @JvmStatic
    @NotNull
    public final OCNullability parseFrom(@NotNull final IElementType elementType) {
        Intrinsics.checkParameterIsNotNull((Object)elementType, "tokenType");
        final OCNullability[] array = OCNullability.values();
        for (int i = 0; i < array.length; ++i) {
            final OCNullability ocNullability = array[i];
            try {
                if (ocNullability.getMatchingTypes().contains(elementType)) {
                    return ocNullability;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        throw new IllegalArgumentException("Invalid token type provided");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
