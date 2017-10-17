// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import java.util.Collections;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import java.io.File;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0011\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005BO\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010BW\b\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0002\u0010\u0012J\u0013\u0010!\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u0004H\u0016J\u0010\u0010&\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0010\u0010'\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\"\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\nJ\u0010\u0010*\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\f8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR-\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\r \u001f*\u0012\u0012\f\u0012\n \u001f*\u0004\u0018\u00010\r0\r\u0018\u00010\f0\f¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001c¨\u0006+" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/CMakeSettings$Configuration;", "", "()V", "configName", "", "(Ljava/lang/String;)V", "generationOptions", "generationPassSystemEnvironment", "", "additionalGenerationEnvironment", "", "toolSetOptions", "", "Lcom/jetbrains/cidr/toolchains/CidrToolSet$Option;", "generationDir", "Ljava/io/File;", "(Ljava/lang/String;Ljava/lang/String;ZLjava/util/Map;Ljava/util/List;Ljava/io/File;)V", "safe", "(Ljava/lang/String;Ljava/lang/String;ZLjava/util/Map;Ljava/util/List;Ljava/io/File;Z)V", "getAdditionalGenerationEnvironment", "()Ljava/util/Map;", "getConfigName", "()Ljava/lang/String;", "getGenerationDir", "()Ljava/io/File;", "getGenerationOptions", "generationOptionsList", "getGenerationOptionsList", "()Ljava/util/List;", "getGenerationPassSystemEnvironment", "()Z", "kotlin.jvm.PlatformType", "getToolSetOptions", "equals", "other", "hashCode", "", "toString", "withConfigName", "withGenerationDir", "withGenerationEnvironment", "passSystemEnvironment", "withGenerationOptions", "clion" })
public static final class Configuration
{
    @NotNull
    private final String configName;
    @Nullable
    private final String generationOptions;
    @NotNull
    private final Map<String, String> additionalGenerationEnvironment;
    private final List<CidrToolSet.Option> toolSetOptions;
    private final boolean generationPassSystemEnvironment;
    @Nullable
    private final File generationDir;
    
    @NotNull
    public final String getConfigName() {
        return this.configName;
    }
    
    @Nullable
    public final String getGenerationOptions() {
        return this.generationOptions;
    }
    
    @NotNull
    public final List<String> getGenerationOptionsList() {
        return CMakeSettingsKt.getOptionsList(this.generationOptions);
    }
    
    @NotNull
    public final Map<String, String> getAdditionalGenerationEnvironment() {
        return this.additionalGenerationEnvironment;
    }
    
    public final List<CidrToolSet.Option> getToolSetOptions() {
        return this.toolSetOptions;
    }
    
    @NotNull
    public final Configuration withConfigName(@Nullable final String s) {
        final String generationOptions = this.generationOptions;
        final boolean generationPassSystemEnvironment = this.generationPassSystemEnvironment;
        final Map<String, String> additionalGenerationEnvironment = this.additionalGenerationEnvironment;
        final List<CidrToolSet.Option> toolSetOptions = this.toolSetOptions;
        Intrinsics.checkExpressionValueIsNotNull((Object)toolSetOptions, "this.toolSetOptions");
        return new Configuration(s, generationOptions, generationPassSystemEnvironment, additionalGenerationEnvironment, toolSetOptions, this.generationDir);
    }
    
    @NotNull
    public final Configuration withGenerationOptions(@Nullable final String s) {
        final String configName = this.configName;
        final boolean generationPassSystemEnvironment = this.generationPassSystemEnvironment;
        final Map<String, String> additionalGenerationEnvironment = this.additionalGenerationEnvironment;
        final List<CidrToolSet.Option> toolSetOptions = this.toolSetOptions;
        Intrinsics.checkExpressionValueIsNotNull((Object)toolSetOptions, "this.toolSetOptions");
        return new Configuration(configName, s, generationPassSystemEnvironment, additionalGenerationEnvironment, toolSetOptions, this.generationDir);
    }
    
    @NotNull
    public final Configuration withGenerationEnvironment(final boolean b, @NotNull final Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull((Object)map, "additionalGenerationEnvironment");
        final String configName = this.configName;
        final String generationOptions = this.generationOptions;
        final List<CidrToolSet.Option> toolSetOptions = this.toolSetOptions;
        Intrinsics.checkExpressionValueIsNotNull((Object)toolSetOptions, "this.toolSetOptions");
        return new Configuration(configName, generationOptions, b, map, toolSetOptions, this.generationDir);
    }
    
    @NotNull
    public final Configuration withGenerationDir(@Nullable final File file) {
        final String configName = this.configName;
        final String generationOptions = this.generationOptions;
        final boolean generationPassSystemEnvironment = this.generationPassSystemEnvironment;
        final Map<String, String> additionalGenerationEnvironment = this.additionalGenerationEnvironment;
        final List<CidrToolSet.Option> toolSetOptions = this.toolSetOptions;
        Intrinsics.checkExpressionValueIsNotNull((Object)toolSetOptions, "this.toolSetOptions");
        return new Configuration(configName, generationOptions, generationPassSystemEnvironment, additionalGenerationEnvironment, toolSetOptions, file);
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (TypeCastException ex) {
            throw b(ex);
        }
        Label_0031: {
            try {
                if (o != null) {
                    final Class<?> class1 = o.getClass();
                    break Label_0031;
                }
            }
            catch (TypeCastException ex2) {
                throw b(ex2);
            }
            final Class<?> class1 = null;
            try {
                if (Intrinsics.areEqual((Object)class1, (Object)this.getClass()) ^ true) {
                    return false;
                }
            }
            catch (TypeCastException ex3) {
                throw b(ex3);
            }
        }
        try {
            if (o == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.jetbrains.cidr.cpp.cmake.CMakeSettings.Configuration");
            }
        }
        catch (TypeCastException ex4) {
            throw b(ex4);
        }
        try {
            final Configuration configuration = (Configuration)o;
            if (this.generationPassSystemEnvironment != ((Configuration)o).generationPassSystemEnvironment) {
                return false;
            }
        }
        catch (TypeCastException ex5) {
            throw b(ex5);
        }
        try {
            if (Intrinsics.areEqual((Object)this.configName, (Object)((Configuration)o).configName) ^ true) {
                return false;
            }
        }
        catch (TypeCastException ex6) {
            throw b(ex6);
        }
        try {
            if (Intrinsics.areEqual((Object)this.generationDir, (Object)((Configuration)o).generationDir) ^ true) {
                return false;
            }
        }
        catch (TypeCastException ex7) {
            throw b(ex7);
        }
        try {
            if (Intrinsics.areEqual((Object)this.generationOptions, (Object)((Configuration)o).generationOptions) ^ true) {
                return false;
            }
        }
        catch (TypeCastException ex8) {
            throw b(ex8);
        }
        try {
            if (Intrinsics.areEqual((Object)this.additionalGenerationEnvironment, (Object)((Configuration)o).additionalGenerationEnvironment) ^ true) {
                return false;
            }
        }
        catch (TypeCastException ex9) {
            throw b(ex9);
        }
        try {
            if (Intrinsics.areEqual((Object)this.toolSetOptions, (Object)((Configuration)o).toolSetOptions) ^ true) {
                return false;
            }
        }
        catch (TypeCastException ex10) {
            throw b(ex10);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * Boolean.hashCode(this.generationPassSystemEnvironment) + this.configName.hashCode();
        int n2 = 0;
        int hashCode = 0;
        Label_0045: {
            try {
                n2 = 31 * n;
                final File generationDir = this.generationDir;
                if (generationDir != null) {
                    hashCode = generationDir.hashCode();
                    break Label_0045;
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            hashCode = 0;
        }
        final int n3 = n2 + hashCode;
        int n4;
        try {
            n4 = 31 * n3;
            final String generationOptions = this.generationOptions;
            if (generationOptions != null) {
                final int hashCode2 = generationOptions.hashCode();
                return 31 * (31 * (n4 + hashCode2) + this.additionalGenerationEnvironment.hashCode()) + this.toolSetOptions.hashCode();
            }
        }
        catch (TypeCastException ex2) {
            throw b(ex2);
        }
        final int hashCode2 = 0;
        return 31 * (31 * (n4 + hashCode2) + this.additionalGenerationEnvironment.hashCode()) + this.toolSetOptions.hashCode();
    }
    
    @NotNull
    @Override
    public String toString() {
        return "Configuration(configName=" + this.configName + ", generationPassSystemEnvironment=" + this.generationPassSystemEnvironment + ", generationOptions=" + this.generationOptions + ", additionalGenerationEnvironment=" + this.additionalGenerationEnvironment + ", generationDir=" + this.generationDir + ", toolSetOptions=" + this.toolSetOptions + ')';
    }
    
    public final boolean getGenerationPassSystemEnvironment() {
        return this.generationPassSystemEnvironment;
    }
    
    @Nullable
    public final File getGenerationDir() {
        return this.generationDir;
    }
    
    private Configuration(final String s, final String s2, final boolean generationPassSystemEnvironment, final Map<String, String> map, final List<? extends CidrToolSet.Option> list, final File generationDir, final boolean b) {
        this.generationPassSystemEnvironment = generationPassSystemEnvironment;
        this.generationDir = generationDir;
        this.configName = CMakeSettingsKt.normalizeConfigName(s);
        this.generationOptions = CMakeSettingsKt.normalizeOptions(s2);
        Map<String, String> unmodifiableMap;
        if (b) {
            unmodifiableMap = map;
        }
        else {
            Intrinsics.checkExpressionValueIsNotNull((Object)(unmodifiableMap = Collections.unmodifiableMap((Map<? extends String, ? extends String>)CMakeSettingsKt.normalizeEnvironment(map))), "Collections.unmodifiable\u2026alGenerationEnvironment))");
        }
        List<? extends CidrToolSet.Option> unmodifiableList = null;
        Label_0079: {
            try {
                this.additionalGenerationEnvironment = unmodifiableMap;
                if (b) {
                    unmodifiableList = list;
                    break Label_0079;
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        this.toolSetOptions = (List<CidrToolSet.Option>)unmodifiableList;
    }
    
    public Configuration() {
        this(CMakeSettingsKt.getDEFAULT_CONFIG_NAME());
    }
    
    public Configuration(@Nullable final String s) {
        this(s, null, CMakeSettingsKt.getDEFAULT_PASS_SYSTEM_ENV(), MapsKt.emptyMap(), CollectionsKt.emptyList(), null, true);
    }
    
    public Configuration(@Nullable final String s, @Nullable final String s2, final boolean b, @NotNull final Map<String, String> map, @NotNull final List<? extends CidrToolSet.Option> list, @Nullable final File file) {
        Intrinsics.checkParameterIsNotNull((Object)map, "additionalGenerationEnvironment");
        Intrinsics.checkParameterIsNotNull((Object)list, "toolSetOptions");
        this(s, s2, b, map, list, file, false);
    }
    
    private static TypeCastException b(final TypeCastException ex) {
        return ex;
    }
}
