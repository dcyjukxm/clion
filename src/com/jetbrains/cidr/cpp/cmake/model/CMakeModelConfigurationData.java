// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import java.util.Collections;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.cpp.cmake.CMakeException;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import kotlin.Metadata;
import java.io.Serializable;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b¢\u0006\u0002\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/model/CMakeModelConfigurationData;", "Ljava/io/Serializable;", "configId", "", "configurationName", "", "generationDir", "Ljava/io/File;", "cacheFile", "cacheFileEncoding", "registeredConfigurationTypes", "", "(ILjava/lang/String;Ljava/io/File;Ljava/io/File;Ljava/lang/String;Ljava/util/List;)V", "cacheConfigurator", "Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfigurator;", "getCacheConfigurator", "()Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfigurator;", "getCacheFile", "()Ljava/io/File;", "getConfigId", "()I", "setConfigId", "(I)V", "getConfigurationName", "()Ljava/lang/String;", "getGenerationDir", "getRegisteredConfigurationTypes", "()Ljava/util/List;", "clion" })
public final class CMakeModelConfigurationData implements Serializable
{
    @NotNull
    private final List<String> registeredConfigurationTypes;
    private int configId;
    @NotNull
    private final String configurationName;
    @NotNull
    private final File generationDir;
    @NotNull
    private final File cacheFile;
    private final String cacheFileEncoding;
    
    @NotNull
    public final List<String> getRegisteredConfigurationTypes() {
        return this.registeredConfigurationTypes;
    }
    
    @NotNull
    public final CMakeConfigurator getCacheConfigurator() throws CMakeException {
        return new CMakeConfigurator(this.cacheFile, this.cacheFileEncoding);
    }
    
    public final int getConfigId() {
        return this.configId;
    }
    
    public final void setConfigId(final int configId) {
        this.configId = configId;
    }
    
    @NotNull
    public final String getConfigurationName() {
        return this.configurationName;
    }
    
    @NotNull
    public final File getGenerationDir() {
        return this.generationDir;
    }
    
    @NotNull
    public final File getCacheFile() {
        return this.cacheFile;
    }
    
    public CMakeModelConfigurationData(final int configId, @NotNull final String configurationName, @NotNull final File generationDir, @NotNull final File cacheFile, @NotNull final String cacheFileEncoding, @NotNull final List<String> list) {
        Intrinsics.checkParameterIsNotNull((Object)configurationName, "configurationName");
        Intrinsics.checkParameterIsNotNull((Object)generationDir, "generationDir");
        Intrinsics.checkParameterIsNotNull((Object)cacheFile, "cacheFile");
        Intrinsics.checkParameterIsNotNull((Object)cacheFileEncoding, "cacheFileEncoding");
        Intrinsics.checkParameterIsNotNull((Object)list, "registeredConfigurationTypes");
        this.configId = configId;
        this.configurationName = configurationName;
        this.generationDir = generationDir;
        this.cacheFile = cacheFile;
        this.cacheFileEncoding = cacheFileEncoding;
        final List<String> unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableList, "Collections.unmodifiable\u2026steredConfigurationTypes)");
        this.registeredConfigurationTypes = unmodifiableList;
    }
}
