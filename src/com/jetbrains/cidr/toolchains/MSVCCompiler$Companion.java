// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.util.execution.ParametersListUtil;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.JvmStatic;
import kotlin.text.MatchResult;
import kotlin.TypeCastException;
import kotlin.text.Regex;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u0007J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\u000f\u001a\u00020\bH\u0003¨\u0006\u0010" }, d2 = { "Lcom/jetbrains/cidr/toolchains/MSVCCompiler$Companion;", "", "()V", "getMSCVersion", "", "defines", "", "getMSVCLangVersion", "", "is64Bit", "", "parseArguments", "", "lines", "splitArguments", "line", "cidr-common" })
public static final class Companion
{
    @JvmStatic
    public final int getMSCVersion(@NotNull final CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull((Object)charSequence, "defines");
        final MatchResult find$default = Regex.find$default(new Regex("#define\\s+_MSC_VER\\s+(\\d+)"), charSequence, 0, 2, (Object)null);
        if (find$default != null) {
            final MatchResult matchResult = find$default;
            try {
                final Integer value = Integer.valueOf(matchResult.getGroupValues().get(1));
                if (value != null) {
                    return value;
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
        }
        return 0;
    }
    
    @JvmStatic
    @Nullable
    public final String getMSVCLangVersion(@NotNull final CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull((Object)charSequence, "defines");
        final MatchResult find$default = Regex.find$default(new Regex("#define\\s+_MSVC_LANG\\s+(\\d+L?)"), charSequence, 0, 2, (Object)null);
        return (find$default != null) ? ((String)find$default.getGroupValues().get(1)) : null;
    }
    
    @JvmStatic
    public final boolean is64Bit(@NotNull final CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull((Object)charSequence, "defines");
        return Regex.find$default(new Regex("#define\\s+_WIN64\\s+1"), charSequence, 0, 2, (Object)null) != null;
    }
    
    @JvmStatic
    @NotNull
    public final List<String> parseArguments(@NotNull final List<String> list) {
        Intrinsics.checkParameterIsNotNull((Object)list, "lines");
        while (true) {
            for (final String next : list) {
                final String s = next;
                Label_0107: {
                    Label_0091: {
                        Label_0082: {
                            try {
                                if (!StringsKt.startsWith$default(s, "`", false, 2, (Object)null)) {
                                    break Label_0082;
                                }
                                final String s2 = s;
                                final String s3 = "'";
                                final boolean b = false;
                                final int n = 2;
                                final Object o = null;
                                final boolean b2 = StringsKt.endsWith$default(s2, s3, b, n, o);
                                if (b2) {
                                    break Label_0082;
                                }
                                break Label_0082;
                            }
                            catch (TypeCastException ex) {
                                throw b(ex);
                            }
                            try {
                                final String s2 = s;
                                final String s3 = "'";
                                final boolean b = false;
                                final int n = 2;
                                final Object o = null;
                                final boolean b2 = StringsKt.endsWith$default(s2, s3, b, n, o);
                                if (b2) {
                                    final boolean b3 = true;
                                    break Label_0091;
                                }
                            }
                            catch (TypeCastException ex2) {
                                throw b(ex2);
                            }
                        }
                        final boolean b3 = false;
                        try {
                            if (b3) {
                                final String s4 = next;
                                break Label_0107;
                            }
                            continue;
                        }
                        catch (TypeCastException ex3) {
                            throw b(ex3);
                        }
                    }
                    continue;
                    try {
                        final String s4;
                        final String s5 = s4;
                        if (s5 != null) {
                            return this.a(StringsKt.trimEnd(StringsKt.trimStart(s5, new char[] { '`' }), new char[] { '\'' }));
                        }
                    }
                    catch (TypeCastException ex4) {
                        throw b(ex4);
                    }
                }
                return (List<String>)CollectionsKt.emptyList();
            }
            final String s4 = null;
            continue;
        }
    }
    
    @JvmStatic
    private final List<String> a(final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        final List parse = ParametersListUtil.parse(s);
        final StringBuilder sb = new StringBuilder();
        for (final String s2 : parse) {
            if (StringsKt.startsWith$default(s2, "-", false, 2, (Object)null)) {
                final StringBuilder sb2 = sb;
                Label_0091: {
                    try {
                        if (sb2.length() > 0) {
                            final boolean b = true;
                            break Label_0091;
                        }
                    }
                    catch (TypeCastException ex) {
                        throw b(ex);
                    }
                    final boolean b = false;
                    try {
                        if (b) {
                            list.add(sb.toString());
                            sb.setLength(0);
                        }
                    }
                    catch (TypeCastException ex2) {
                        throw b(ex2);
                    }
                }
                Label_0290: {
                    Label_0150: {
                        try {
                            if (StringsKt.startsWith$default(s2, "-Yc", false, 2, (Object)null)) {
                                break Label_0150;
                            }
                            final String s3 = s2;
                            final String s4 = "-Yu";
                            final boolean b2 = false;
                            final int n = 2;
                            final Object o = null;
                            final boolean b3 = StringsKt.startsWith$default(s3, s4, b2, n, o);
                            if (!b3) {
                                break Label_0150;
                            }
                            break Label_0150;
                        }
                        catch (TypeCastException ex3) {
                            throw b(ex3);
                        }
                        try {
                            final String s3 = s2;
                            final String s4 = "-Yu";
                            final boolean b2 = false;
                            final int n = 2;
                            final Object o = null;
                            final boolean b3 = StringsKt.startsWith$default(s3, s4, b2, n, o);
                            if (!b3) {
                                if (!StringsKt.startsWith$default(s2, "-Fp", false, 2, (Object)null)) {
                                    break Label_0290;
                                }
                            }
                        }
                        catch (TypeCastException ex4) {
                            throw b(ex4);
                        }
                    }
                    final ArrayList<String> list2 = list;
                    final String s5 = s2;
                    final int n2 = 0;
                    final int n3 = 3;
                    final ArrayList<String> list3 = list2;
                    String s6;
                    try {
                        s6 = s5;
                        if (s6 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                    }
                    catch (TypeCastException ex5) {
                        throw b(ex5);
                    }
                    final String substring = s6.substring(n2, n3);
                    Intrinsics.checkExpressionValueIsNotNull((Object)substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    list3.add(substring);
                    if (s2.length() <= 3) {
                        continue;
                    }
                    final StringBuilder sb3 = sb;
                    final String s7 = s2;
                    final int n4 = 3;
                    final StringBuilder sb4 = sb3;
                    String s8;
                    try {
                        s8 = s7;
                        if (s8 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                    }
                    catch (TypeCastException ex6) {
                        throw b(ex6);
                    }
                    final String substring2 = s8.substring(n4);
                    Intrinsics.checkExpressionValueIsNotNull((Object)substring2, "(this as java.lang.String).substring(startIndex)");
                    sb4.append(substring2);
                    continue;
                }
                list.add(s2);
            }
            else {
                final StringBuilder sb5 = sb;
                Label_0326: {
                    try {
                        if (sb5.length() > 0) {
                            final boolean b4 = true;
                            break Label_0326;
                        }
                    }
                    catch (TypeCastException ex7) {
                        throw b(ex7);
                    }
                    final boolean b4 = false;
                    try {
                        if (b4) {
                            sb.append(" ");
                        }
                    }
                    catch (TypeCastException ex8) {
                        throw b(ex8);
                    }
                }
                sb.append(s2);
            }
        }
        final StringBuilder sb6 = sb;
        Label_0381: {
            try {
                if (sb6.length() > 0) {
                    final boolean b5 = true;
                    break Label_0381;
                }
            }
            catch (TypeCastException ex9) {
                throw b(ex9);
            }
            final boolean b5 = false;
            try {
                if (b5) {
                    list.add(sb.toString());
                }
            }
            catch (TypeCastException ex10) {
                throw b(ex10);
            }
        }
        return list;
    }
    
    private static TypeCastException b(final TypeCastException ex) {
        return ex;
    }
}
