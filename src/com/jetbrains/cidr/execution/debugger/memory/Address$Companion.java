// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.memory;

import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0015B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014H\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\u0004*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0015\u0010\u000b\u001a\u00020\f*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/memory/Address$Companion;", "", "()V", "MAX_VALUE", "Lcom/jetbrains/cidr/execution/debugger/memory/Address;", "MIN_VALUE", "NULL", "asAddress", "", "getAsAddress", "(Ljava/lang/Number;)Lcom/jetbrains/cidr/execution/debugger/memory/Address;", "coercing", "Lcom/jetbrains/cidr/execution/debugger/memory/Address$Companion$CoercingSize;", "getCoercing", "(Ljava/lang/Number;)Lcom/jetbrains/cidr/execution/debugger/memory/Address$Companion$CoercingSize;", "fromUnsignedLong", "l", "", "parseHexString", "s", "", "CoercingSize", "cidr-debugger" })
public static final class Companion
{
    @JvmStatic
    @NotNull
    public final Address fromUnsignedLong(final long n) {
        return new Address(n, null);
    }
    
    @JvmStatic
    @NotNull
    public final Address parseHexString(@NotNull final String s) throws NumberFormatException {
        Intrinsics.checkParameterIsNotNull((Object)s, "s");
        String substring;
        if (StringsKt.startsWith(s, "0x", true)) {
            Intrinsics.checkExpressionValueIsNotNull((Object)(substring = s.substring(2)), "(this as java.lang.String).substring(startIndex)");
        }
        else {
            substring = s;
        }
        final String s2 = substring;
        try {
            final String s3 = s2;
            Label_0091: {
                Label_0062: {
                    try {
                        if (s3.length() > 0) {
                            final boolean b = true;
                            break Label_0062;
                        }
                    }
                    catch (NumberFormatException ex) {
                        throw b(ex);
                    }
                    final boolean b = false;
                    try {
                        if (!b) {
                            break Label_0091;
                        }
                        final String s4 = "+-";
                        final String s5 = s4;
                        final String s6 = s2;
                        final int n = 0;
                        final char c = s6.charAt(n);
                        final boolean b2 = false;
                        final int n2 = 2;
                        final Object o = null;
                        final boolean b3 = StringsKt.contains$default((CharSequence)s5, c, b2, n2, o);
                        if (b3) {
                            break Label_0091;
                        }
                        break Label_0091;
                    }
                    catch (NumberFormatException ex2) {
                        throw b(ex2);
                    }
                }
                try {
                    final String s4 = "+-";
                    final String s5 = s4;
                    final String s6 = s2;
                    final int n = 0;
                    final char c = s6.charAt(n);
                    final boolean b2 = false;
                    final int n2 = 2;
                    final Object o = null;
                    final boolean b3 = StringsKt.contains$default((CharSequence)s5, c, b2, n2, o);
                    if (b3) {
                        throw new NumberFormatException("Illegal leading sign: " + s);
                    }
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
            }
            final long unsignedLong = Long.parseUnsignedLong(s2, 16);
            if (s2.length() > 16) {
                final String s7 = s2;
                final Regex regex = new Regex("^0+");
                final String s8 = "";
                try {
                    if (regex.replaceFirst((CharSequence)s7, s8).length() > 16) {
                        throw new NumberFormatException("String value " + s + " exceeds range of unsigned long.");
                    }
                }
                catch (NumberFormatException ex4) {
                    throw b(ex4);
                }
            }
            return Address.Companion.fromUnsignedLong(unsignedLong);
        }
        catch (NumberFormatException ex5) {
            throw new NumberFormatException("Invalid address: " + ex5.getMessage());
        }
    }
    
    @NotNull
    public final Address getAsAddress(@NotNull final Number n) {
        Intrinsics.checkParameterIsNotNull((Object)n, "$receiver");
        return Address.Companion.fromUnsignedLong(n.longValue());
    }
    
    @NotNull
    public final CoercingSize getCoercing(@NotNull final Number n) {
        Intrinsics.checkParameterIsNotNull((Object)n, "$receiver");
        return new CoercingSize(n.longValue());
    }
    
    private static NumberFormatException b(final NumberFormatException ex) {
        return ex;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/memory/Address$Companion$CoercingSize;", "", "unsignedLongValue", "", "(J)V", "getUnsignedLongValue", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "cidr-debugger" })
    public static final class CoercingSize
    {
        private final long unsignedLongValue;
        
        public final long getUnsignedLongValue() {
            return this.unsignedLongValue;
        }
        
        public CoercingSize(final long unsignedLongValue) {
            this.unsignedLongValue = unsignedLongValue;
        }
        
        public final long component1() {
            return this.unsignedLongValue;
        }
        
        @NotNull
        public final CoercingSize copy(final long n) {
            return new CoercingSize(n);
        }
        
        @Override
        public String toString() {
            return "CoercingSize(unsignedLongValue=" + this.unsignedLongValue + ")";
        }
        
        @Override
        public int hashCode() {
            return Long.hashCode(this.unsignedLongValue);
        }
        
        @Override
        public boolean equals(final Object o) {
            return this == o || (o instanceof CoercingSize && this.unsignedLongValue == ((CoercingSize)o).unsignedLongValue);
        }
    }
}
