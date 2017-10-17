// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 2, d1 = { "\u0000$\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\u001a0\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0002X\u0082D¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0002X\u0082D¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0002X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000f" }, d2 = { "BUILT_IN_MACROS", "", "", "CANT_OPEN_FILE_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "DEFINES_END", "DEFINES_START", "DEFINE_PREFIX", "collectMSVCSkipOptions", "", "lines", "skipOptions", "", "warnLog", "cidr-common" })
public final class MSVCCompilerKt
{
    private static final Pattern CANT_OPEN_FILE_PATTERN;
    private static final String DEFINES_START = "__cidr_defines_start__\n";
    private static final String DEFINES_END = "__cidr_defines_end__\n";
    private static final String DEFINE_PREFIX = "__cidr_define_";
    private static final List<String> BUILT_IN_MACROS;
    
    public static final boolean collectMSVCSkipOptions(@NotNull final List<String> list, @NotNull final Set<String> set, @NotNull final List<String> list2) {
        Intrinsics.checkParameterIsNotNull((Object)list, "lines");
        Intrinsics.checkParameterIsNotNull((Object)set, "skipOptions");
        Intrinsics.checkParameterIsNotNull((Object)list2, "warnLog");
        return CidrCompilerBase.collectOptionsToSkip(list, set, list2, MSVCCompilerKt.CANT_OPEN_FILE_PATTERN);
    }
    
    static {
        CANT_OPEN_FILE_PATTERN = Pattern.compile(".+fatal error (?:\\w+): Cannot open (?:source|include) file: '(.+)':.+");
        BUILT_IN_MACROS = CollectionsKt.listOf((Object[])new String[] { "__cplusplus", "__STDC__", "__STDC_HOSTED__", "__STDCPP_THREADS__", "__ATOM__", "__AVX__", "__AVX2__", "_CHAR_UNSIGNED", "__CLR_VER", "_CONTROL_FLOW_GUARD", "__cplusplus_cli", "__cplusplus_winrt", "_CPPRTTI", "_CPPUNWIND", "_DEBUG", "_DLL", "_INTEGRAL_MAX_BITS", "_ISO_VOLATILE", "_KERNEL_MODE", "_M_AMD64", "_M_ARM", "_M_ARM_ARMV7VE", "_M_ARM_FP", "_M_ARM64", "_M_CEE", "_M_CEE_PURE", "_M_CEE_SAFE", "_M_FP_EXCEPT", "_M_FP_FAST", "_M_FP_PRECISE", "_M_FP_STRICT", "_M_IX86", "_M_IX86_FP", "_M_X64", "_MANAGED", "_MSC_BUILD", "_MSC_EXTENSIONS", "_MSC_FULL_VER", "_MSC_VER", "_MSVC_LANG", "__MSVC_RUNTIME_CHECKS", "_MT", "_NATIVE_WCHAR_T_DEFINED", "_OPENMP", "_PREFAST_", "_VC_NODEFAULTLIB", "_WCHAR_T_DEFINED", "_WIN32", "_WIN64", "_WINRT_DLL", "_M_IA64", "_NATIVE_NULLPTR_SUPPORTED", "_RVALUE_REFERENCES_SUPPORTED", "_RVALUE_REFERENCES_V2_SUPPORTED" });
    }
}
