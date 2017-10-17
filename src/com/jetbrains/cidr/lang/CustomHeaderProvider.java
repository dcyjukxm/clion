// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import com.intellij.util.containers.ContainerUtil;
import java.util.Map;
import com.intellij.openapi.extensions.ExtensionPointName;

public abstract class CustomHeaderProvider
{
    public static ExtensionPointName<CustomHeaderProvider> EP_NAME;
    private final Map<HeaderSearchStage, StageProvider> myStageProviders;
    
    public CustomHeaderProvider() {
        this.myStageProviders = (Map<HeaderSearchStage, StageProvider>)ContainerUtil.newEnumMap((Class)HeaderSearchStage.class);
    }
    
    public abstract boolean accepts(@Nullable final OCResolveRootAndConfiguration p0);
    
    @Nullable
    public abstract String provideSerializationPath(@NotNull final VirtualFile p0);
    
    @Nullable
    public abstract VirtualFile getCustomSerializedHeaderFile(@NotNull final String p0, @NotNull final Project p1, @NotNull final VirtualFile p2);
    
    @Nullable
    public VirtualFile getCustomHeaderFile(@NotNull final String s, @NotNull final HeaderSearchStage headerSearchStage, @Nullable final OCResolveConfiguration ocResolveConfiguration) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerName", "com/jetbrains/cidr/lang/CustomHeaderProvider", "getCustomHeaderFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (headerSearchStage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stage", "com/jetbrains/cidr/lang/CustomHeaderProvider", "getCustomHeaderFile"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final StageProvider stageProvider = this.myStageProviders.get(headerSearchStage);
        try {
            if (stageProvider != null) {
                return stageProvider.getHeader(s, ocResolveConfiguration);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    protected void registerProvider(@NotNull final HeaderSearchStage headerSearchStage, @NotNull final StageProvider stageProvider) {
        try {
            if (headerSearchStage == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stage", "com/jetbrains/cidr/lang/CustomHeaderProvider", "registerProvider"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (stageProvider == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "provider", "com/jetbrains/cidr/lang/CustomHeaderProvider", "registerProvider"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myStageProviders.put(headerSearchStage, stageProvider);
    }
    
    @NotNull
    public static Helper getProviders(@NotNull final String s, @Nullable final OCResolveRootAndConfiguration ocResolveRootAndConfiguration) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerName", "com/jetbrains/cidr/lang/CustomHeaderProvider", "getProviders"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList arrayList = ContainerUtil.newArrayList();
        for (final CustomHeaderProvider customHeaderProvider : (CustomHeaderProvider[])CustomHeaderProvider.EP_NAME.getExtensions()) {
            try {
                if (customHeaderProvider.accepts(ocResolveRootAndConfiguration)) {
                    arrayList.add(customHeaderProvider);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        List trimToSize = null;
        OCResolveConfiguration configuration = null;
        Label_0132: {
            try {
                trimToSize = ContainerUtil.trimToSize((List)arrayList);
                if (ocResolveRootAndConfiguration != null) {
                    configuration = ocResolveRootAndConfiguration.getConfiguration();
                    break Label_0132;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            configuration = null;
        }
        final Helper helper = new Helper(trimToSize, s, configuration);
        if (helper == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/CustomHeaderProvider", "getProviders"));
        }
        return helper;
    }
    
    @Nullable
    public static String provideSerializationPathForFile(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/CustomHeaderProvider", "provideSerializationPathForFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CustomHeaderProvider[] array = (CustomHeaderProvider[])CustomHeaderProvider.EP_NAME.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            final String provideSerializationPath = array[i].provideSerializationPath(virtualFile);
            try {
                if (provideSerializationPath != null) {
                    return provideSerializationPath;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    @Nullable
    public static VirtualFile getCustomHeaderFile(final String s, final Project project, final VirtualFile virtualFile) {
        final CustomHeaderProvider[] array = (CustomHeaderProvider[])CustomHeaderProvider.EP_NAME.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            final VirtualFile customSerializedHeaderFile = array[i].getCustomSerializedHeaderFile(s, project, virtualFile);
            try {
                if (customSerializedHeaderFile != null) {
                    return customSerializedHeaderFile;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    static {
        CustomHeaderProvider.EP_NAME = (ExtensionPointName<CustomHeaderProvider>)ExtensionPointName.create("cidr.lang.customHeaderProvider");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum HeaderSearchStage
    {
        BEFORE_START, 
        BEFORE_CURRENT, 
        BEFORE_LIBRARIES, 
        BEFORE_FRAMEWORKS, 
        AFTER_END;
    }
    
    public static class Helper
    {
        @NotNull
        private List<CustomHeaderProvider> myProviders;
        @NotNull
        private String myHeaderName;
        @Nullable
        private OCResolveConfiguration myConfiguration;
        
        public Helper(@NotNull final List<CustomHeaderProvider> myProviders, @NotNull final String myHeaderName, @Nullable final OCResolveConfiguration myConfiguration) {
            if (myProviders == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "providers", "com/jetbrains/cidr/lang/CustomHeaderProvider$Helper", "<init>"));
            }
            if (myHeaderName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerName", "com/jetbrains/cidr/lang/CustomHeaderProvider$Helper", "<init>"));
            }
            this.myProviders = myProviders;
            this.myHeaderName = myHeaderName;
            this.myConfiguration = myConfiguration;
        }
        
        @Nullable
        VirtualFile getCustomHeader(final HeaderSearchStage headerSearchStage) {
            final Iterator<CustomHeaderProvider> iterator = this.myProviders.iterator();
            while (iterator.hasNext()) {
                final VirtualFile customHeaderFile = iterator.next().getCustomHeaderFile(this.myHeaderName, headerSearchStage, this.myConfiguration);
                try {
                    if (customHeaderFile != null) {
                        return customHeaderFile;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            return null;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    protected interface StageProvider
    {
        VirtualFile getHeader(@NotNull final String p0, @Nullable final OCResolveConfiguration p1);
    }
}
