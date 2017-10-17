// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import kotlin.collections.MapsKt;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.collections.CollectionsKt;
import java.util.Collections;
import com.intellij.util.messages.Topic;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.cpp.toolchains.MSVC;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.io.File;
import java.util.Iterator;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import kotlin.Metadata;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.State;
import org.jdom.Element;
import com.intellij.openapi.components.PersistentStateComponent;

@State(name = "CMakeSettings", storages = { @Storage("$WORKSPACE_FILE$") })
@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002%&B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001a\u0010\u001d\u001a\u00020\u00162\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001e\u001a\u00020\u0016H\u0002J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0002H\u0016J\u0018\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0016H\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010!\u001a\u00020\u0002H\u0016R*\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00078F@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR0\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\r8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0006\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0012\u0010\u001b\u001a\u00020\u00168\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/CMakeSettings;", "Lcom/intellij/openapi/components/PersistentStateComponent;", "Lorg/jdom/Element;", "myProject", "Lcom/intellij/openapi/project/Project;", "(Lcom/intellij/openapi/project/Project;)V", "value", "", "buildOptions", "getBuildOptions", "()Ljava/lang/String;", "setBuildOptions", "(Ljava/lang/String;)V", "", "Lcom/jetbrains/cidr/cpp/cmake/CMakeSettings$Configuration;", "configurations", "getConfigurations", "()Ljava/util/List;", "setConfigurations", "(Ljava/util/List;)V", "effectiveBuildOptionsList", "getEffectiveBuildOptionsList", "", "isAutoReloadEnabled", "()Z", "setAutoReloadEnabled", "(Z)V", "myAutoReloadEnabled", "myConfigurations", "getBoolean", "def", "getState", "loadConfig", "element", "migrate", "loadState", "", "Companion", "Configuration", "clion" })
public final class CMakeSettings implements PersistentStateComponent<Element>
{
    private volatile boolean myAutoReloadEnabled;
    private volatile List<Configuration> myConfigurations;
    @Nullable
    private volatile String buildOptions;
    private final Project myProject;
    public static final Companion Companion;
    
    @Nullable
    public final String getBuildOptions() {
        return this.buildOptions;
    }
    
    public final void setBuildOptions(@Nullable final String s) {
        this.buildOptions = CMakeSettingsKt.normalizeOptions(s);
    }
    
    @NotNull
    public final List<String> getEffectiveBuildOptionsList() {
        String s;
        if ((s = this.getBuildOptions()) == null) {
            s = CMakeSettingsKt.getCurrentDefaultBuildOptions();
        }
        return CMakeSettingsKt.getOptionsList(s);
    }
    
    @Nullable
    public Element getState() {
        final Element element = new Element("state");
        if (this.myAutoReloadEnabled != CMakeSettingsKt.getDEFAULT_AUTO_RELOAD_ENABLED()) {
            element.setAttribute("AUTO_RELOAD", String.valueOf(this.myAutoReloadEnabled));
        }
        final Element element2 = new Element("configurations");
        for (final Configuration configuration : this.myConfigurations) {
            final Element element3 = new Element("configuration");
            if (Intrinsics.areEqual((Object)configuration.getConfigName(), (Object)"Default") ^ true) {
                element3.setAttribute("CONFIG_NAME", configuration.getConfigName());
            }
            final String generationOptions = configuration.getGenerationOptions();
            if (generationOptions != null) {
                element3.setAttribute("GENERATION_OPTIONS", generationOptions);
            }
            if (configuration.getGenerationPassSystemEnvironment() != CMakeSettingsKt.getDEFAULT_PASS_SYSTEM_ENV()) {
                element3.setAttribute("GENERATION_PASS_SYSTEM_ENVIRONMENT", String.valueOf(configuration.getGenerationPassSystemEnvironment()));
            }
            if (!configuration.getAdditionalGenerationEnvironment().isEmpty()) {
                final Element element4 = new Element("ADDITIONAL_GENERATION_ENVIRONMENT");
                EnvironmentVariablesComponent.writeExternal(element4, configuration.getAdditionalGenerationEnvironment());
                element3.addContent(element4);
            }
            for (final CidrToolSet.Option option : configuration.getToolSetOptions()) {
                final Element element5 = new Element("toolsetOption");
                option.write(element5);
                element3.addContent(element5);
            }
            final File generationDir = configuration.getGenerationDir();
            if (generationDir != null) {
                element3.setAttribute("GENERATION_DIR", FileUtil.toSystemIndependentName(generationDir.getPath()));
            }
            element2.addContent(element3);
        }
        element.addContent(element2);
        final String buildOptions = this.getBuildOptions();
        if (buildOptions != null) {
            element.setAttribute("ADDITIONAL_BUILD_OPTIONS", buildOptions);
        }
        return element;
    }
    
    public void loadState(@NotNull final Element element) {
        Intrinsics.checkParameterIsNotNull((Object)element, "element");
        this.setAutoReloadEnabled(this.a(element.getAttributeValue("AUTO_RELOAD"), CMakeSettingsKt.getDEFAULT_AUTO_RELOAD_ENABLED()));
        final ArrayList<Configuration> list = new ArrayList<Configuration>();
        if (element.getAttributeValue("GENERATION_PASS_SYSTEM_ENVIRONMENT") != null) {
            list.add(this.a(element, true));
        }
        else {
            final Element child = element.getChild("configurations");
            if (child != null) {
                for (final Element element2 : child.getChildren("configuration")) {
                    final ArrayList<Configuration> list2 = list;
                    final Element element3 = element2;
                    Intrinsics.checkExpressionValueIsNotNull((Object)element3, "configElement");
                    list2.add(this.a(element3, false));
                }
            }
        }
        this.setConfigurations(list);
        this.setBuildOptions(element.getAttributeValue("ADDITIONAL_BUILD_OPTIONS"));
    }
    
    private final Configuration a(final Element element, final boolean b) {
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        final Element child = element.getChild("ADDITIONAL_GENERATION_ENVIRONMENT");
        if (child != null) {
            EnvironmentVariablesComponent.readExternal(child, linkedHashMap);
        }
        final List list = element.getChildren("toolsetOption");
        final ArrayList<CidrToolSet.Option> list2 = new ArrayList<CidrToolSet.Option>();
        final Iterator<Element> iterator = list.iterator();
        while (iterator.hasNext()) {
            final CidrToolSet.Option loadOption = MSVC.loadOption(iterator.next());
            if (loadOption != null) {
                list2.add(loadOption);
            }
        }
        final ArrayList<CidrToolSet.Option> list3 = list2;
        final Configuration configuration = new(com.jetbrains.cidr.cpp.cmake.CMakeSettings.Configuration.class);
        Configuration configuration2 = configuration;
        Configuration configuration3 = configuration;
        final String s2;
        String s = s2 = (b ? CMakeSettingsKt.getDEFAULT_CONFIG_NAME() : element.getAttributeValue("CONFIG_NAME"));
        final String attributeValue = element.getAttributeValue("GENERATION_OPTIONS");
        final boolean a = this.a(element.getAttributeValue("GENERATION_PASS_SYSTEM_ENVIRONMENT"), CMakeSettingsKt.getDEFAULT_PASS_SYSTEM_ENV());
        final LinkedHashMap<String, String> linkedHashMap2 = linkedHashMap;
        final ArrayList<CidrToolSet.Option> list4 = list3;
        final String attributeValue2 = element.getAttributeValue("GENERATION_DIR");
        File file2;
        if (attributeValue2 != null) {
            final String s3 = attributeValue2;
            final String s4 = s2;
            final Configuration configuration4 = configuration;
            final Configuration configuration5 = configuration;
            final File file = new File(s3);
            configuration2 = configuration5;
            configuration3 = configuration4;
            s = s4;
            file2 = file;
        }
        else {
            file2 = null;
        }
        new Configuration(s, attributeValue, a, linkedHashMap2, list4, file2);
        return configuration2;
    }
    
    private final boolean a(final String s, final boolean b) {
        boolean boolean1;
        if (StringUtil.isEmptyOrSpaces(s)) {
            boolean1 = b;
        }
        else {
            if (s == null) {
                Intrinsics.throwNpe();
            }
            boolean1 = Boolean.parseBoolean(s);
        }
        return boolean1;
    }
    
    public final boolean isAutoReloadEnabled() {
        return this.myAutoReloadEnabled;
    }
    
    public final void setAutoReloadEnabled(final boolean myAutoReloadEnabled) {
        final boolean myAutoReloadEnabled2 = this.myAutoReloadEnabled;
        this.myAutoReloadEnabled = myAutoReloadEnabled;
        if (myAutoReloadEnabled2 != this.myAutoReloadEnabled) {
            ((CMakeSettingsListener)this.myProject.getMessageBus().syncPublisher((Topic)CMakeSettingsListener.Companion.getTOPIC())).autoReloadChanged();
        }
    }
    
    @NotNull
    public final List<Configuration> getConfigurations() {
        return this.myConfigurations;
    }
    
    public final void setConfigurations(@NotNull final List<Configuration> list) {
        Intrinsics.checkParameterIsNotNull((Object)list, "value");
        final List<Configuration> myConfigurations = this.myConfigurations;
        final List<Configuration> unmodifiableList = Collections.unmodifiableList((List<? extends Configuration>)list);
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableList, "Collections.unmodifiableList(value)");
        this.myConfigurations = unmodifiableList;
        if (Intrinsics.areEqual((Object)myConfigurations, (Object)this.myConfigurations) ^ true) {
            ((CMakeSettingsListener)this.myProject.getMessageBus().syncPublisher((Topic)CMakeSettingsListener.Companion.getTOPIC())).configurationsChanged();
        }
    }
    
    public CMakeSettings(@NotNull final Project myProject) {
        Intrinsics.checkParameterIsNotNull((Object)myProject, "myProject");
        this.myProject = myProject;
        this.myAutoReloadEnabled = CMakeSettingsKt.getDEFAULT_AUTO_RELOAD_ENABLED();
        this.myConfigurations = (List<Configuration>)CollectionsKt.listOf((Object)new Configuration());
    }
    
    static {
        Companion = new Companion(null);
    }
    
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
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/CMakeSettings$Companion;", "", "()V", "getInstance", "Lcom/jetbrains/cidr/cpp/cmake/CMakeSettings;", "project", "Lcom/intellij/openapi/project/Project;", "clion" })
    public static final class Companion
    {
        @NotNull
        public final CMakeSettings getInstance(@NotNull final Project project) {
            Intrinsics.checkParameterIsNotNull((Object)project, "project");
            final Object component = project.getComponent((Class)CMakeSettings.class);
            if (component == null) {
                Intrinsics.throwNpe();
            }
            return (CMakeSettings)component;
        }
    }
}
