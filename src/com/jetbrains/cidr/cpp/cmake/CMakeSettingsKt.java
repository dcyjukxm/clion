// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import com.intellij.openapi.application.ApplicationManager;
import java.io.File;
import java.nio.charset.Charset;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import kotlin.text.Charsets;
import kotlin.jvm.functions.Function1;
import java.util.zip.CRC32;
import com.intellij.util.text.StringKt;
import kotlin.text.StringsKt;
import com.intellij.execution.configurations.CommandLineTokenizer;
import com.intellij.util.SmartList;
import kotlin.collections.CollectionsKt;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import java.util.Collections;
import com.intellij.openapi.util.text.StringUtil;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.Intrinsics;
import java.util.Map;
import kotlin.TypeCastException;
import com.jetbrains.cidr.cpp.CPPToolchains;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 2, d1 = { "\u00004\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0003\u001a\u0014\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u001a\u0006\u0010\u000f\u001a\u00020\u0005\u001a\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011\u001a\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u001a\u0010\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u001a&\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0018\u001a\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0005X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0014\u0010\b\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003¨\u0006\u001b" }, d2 = { "DEFAULT_AUTO_RELOAD_ENABLED", "", "getDEFAULT_AUTO_RELOAD_ENABLED", "()Z", "DEFAULT_CONFIG_NAME", "", "getDEFAULT_CONFIG_NAME", "()Ljava/lang/String;", "DEFAULT_PASS_SYSTEM_ENV", "getDEFAULT_PASS_SYSTEM_ENV", "calcConfigurationsHash", "", "configurations", "", "Lcom/jetbrains/cidr/cpp/cmake/CMakeSettings$Configuration;", "getCurrentDefaultBuildOptions", "getDefaultBuildProcesses", "", "cpuCount", "getOptionsList", "options", "normalizeConfigName", "name", "normalizeEnvironment", "", "env", "normalizeOptions", "clion" })
public final class CMakeSettingsKt
{
    private static final boolean DEFAULT_AUTO_RELOAD_ENABLED;
    @NotNull
    private static final String DEFAULT_CONFIG_NAME = "Debug";
    private static final boolean DEFAULT_PASS_SYSTEM_ENV = true;
    
    public static final boolean getDEFAULT_AUTO_RELOAD_ENABLED() {
        return CMakeSettingsKt.DEFAULT_AUTO_RELOAD_ENABLED;
    }
    
    @NotNull
    public static final String getDEFAULT_CONFIG_NAME() {
        return CMakeSettingsKt.DEFAULT_CONFIG_NAME;
    }
    
    public static final boolean getDEFAULT_PASS_SYSTEM_ENV() {
        return CMakeSettingsKt.DEFAULT_PASS_SYSTEM_ENV;
    }
    
    @NotNull
    public static final String getCurrentDefaultBuildOptions() {
        try {
            if (CPPToolchains.getInstance().getMSVC() != null) {
                return "";
            }
        }
        catch (TypeCastException ex) {
            throw b(ex);
        }
        return "-j " + getDefaultBuildProcesses(Runtime.getRuntime().availableProcessors());
    }
    
    public static final int getDefaultBuildProcesses(final int n) {
        return Math.max(1, (int)Math.ceil(n / 2.0));
    }
    
    @NotNull
    public static final Map<String, String> normalizeEnvironment(@NotNull final Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull((Object)map, "env");
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>(map.size());
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            final String s = entry.getKey();
            final String s2 = entry.getValue();
            try {
                if (StringUtil.isEmptyOrSpaces(s)) {
                    continue;
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            linkedHashMap.put(s, StringUtil.notNullize(StringUtil.trim(s2)));
        }
        final Map<Object, Object> unmodifiableMap = (Map<Object, Object>)Collections.unmodifiableMap((Map<? extends String, ? extends String>)linkedHashMap);
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableMap, "Collections.unmodifiableMap(result)");
        return (Map<String, String>)unmodifiableMap;
    }
    
    @NotNull
    public static final List<String> getOptionsList(@Nullable final String s) {
        try {
            if (s == null) {
                return (List<String>)CollectionsKt.emptyList();
            }
        }
        catch (TypeCastException ex) {
            throw b(ex);
        }
        final SmartList list = new SmartList();
        final CommandLineTokenizer commandLineTokenizer = new CommandLineTokenizer(s, true);
        try {
            while (commandLineTokenizer.hasMoreTokens()) {
                list.add((Object)commandLineTokenizer.nextToken());
            }
        }
        catch (TypeCastException ex2) {
            throw b(ex2);
        }
        final List<Object> unmodifiableList = (List<Object>)Collections.unmodifiableList((List<? extends String>)list);
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableList, "Collections.unmodifiableList(result)");
        return (List<String>)unmodifiableList;
    }
    
    @NotNull
    public static final String normalizeConfigName(@Nullable final String s) {
        String string;
        if (s != null) {
            try {
                if (s == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            string = StringsKt.trim((CharSequence)s).toString();
        }
        else {
            string = null;
        }
        try {
            final String nullize$default;
            if ((nullize$default = StringKt.nullize$default(string, false, 1, (Object)null)) != null) {
                return nullize$default;
            }
        }
        catch (TypeCastException ex2) {
            throw b(ex2);
        }
        return "Default";
    }
    
    @Nullable
    public static final String normalizeOptions(@Nullable final String s) {
        return StringUtil.nullize(StringUtil.trim(s));
    }
    
    public static final long calcConfigurationsHash(@NotNull final List<CMakeSettings.Configuration> list) {
        Intrinsics.checkParameterIsNotNull((Object)list, "configurations");
        final CRC32 crc32 = new CRC32();
        final Function1 function1 = (Function1)new CMakeSettingsKt$calcConfigurationsHash$updateNullableString.CMakeSettingsKt$calcConfigurationsHash$updateNullableString$1(crc32);
        for (final CMakeSettings.Configuration configuration : list) {
            final CRC32 crc33 = crc32;
            final String configName = configuration.getConfigName();
            final CRC32 crc34 = crc33;
            final Charset utf_8 = Charsets.UTF_8;
            String s;
            try {
                s = configName;
                if (s == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            final byte[] bytes = s.getBytes(utf_8);
            Intrinsics.checkExpressionValueIsNotNull((Object)bytes, "(this as java.lang.String).getBytes(charset)");
            final byte[] array = bytes;
            CRC32 crc35 = null;
            int n = 0;
            Label_0150: {
                try {
                    crc34.update(array);
                    function1.invoke((Object)configuration.getGenerationOptions());
                    crc35 = crc32;
                    if (configuration.getGenerationPassSystemEnvironment()) {
                        n = 1;
                        break Label_0150;
                    }
                }
                catch (TypeCastException ex2) {
                    throw b(ex2);
                }
                n = 0;
            }
            crc35.update(n);
            crc32.update(configuration.getAdditionalGenerationEnvironment().size());
            for (final Map.Entry<String, String> entry : configuration.getAdditionalGenerationEnvironment().entrySet()) {
                function1.invoke((Object)entry.getKey());
                function1.invoke((Object)entry.getValue());
            }
            crc32.update(configuration.getToolSetOptions().size());
            final Iterator<Object> iterator3 = configuration.getToolSetOptions().iterator();
            while (iterator3.hasNext()) {
                function1.invoke((Object)((CidrToolSet.Option)iterator3.next()).getUniqueID());
            }
            Function1 function2 = null;
            Object path = null;
            Label_0351: {
                try {
                    function2 = function1;
                    final File generationDir = configuration.getGenerationDir();
                    if (generationDir != null) {
                        path = generationDir.getPath();
                        break Label_0351;
                    }
                }
                catch (TypeCastException ex3) {
                    throw b(ex3);
                }
                path = null;
            }
            function2.invoke(path);
        }
        return crc32.getValue();
    }
    
    static {
        DEFAULT_AUTO_RELOAD_ENABLED = ApplicationManager.getApplication().isUnitTestMode();
    }
    
    private static TypeCastException b(final TypeCastException ex) {
        return ex;
    }
}
