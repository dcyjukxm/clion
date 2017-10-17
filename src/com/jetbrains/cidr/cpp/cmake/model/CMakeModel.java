// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.util.containers.ContainerUtil;
import java.io.ObjectOutputStream;
import org.jetbrains.annotations.Nullable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.Map;
import java.io.File;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.io.Serializable;

public class CMakeModel implements Serializable
{
    public static final String CMAKE_LISTS_FILE = "CMakeLists.txt";
    public static final String CMAKE_CACHE_FILE = "CMakeCache.txt";
    public static final String DEFAULT_TYPE = "Default";
    public static final List<String> DEFAULT_CONFIGURATION_TYPES;
    public static final int SERIALIZATION_VERSION = 105;
    @NotNull
    private final String myProjectName;
    @NotNull
    private final Set<File> myCMakeFiles;
    @NotNull
    private final Set<File> myHeaderAndResourceFiles;
    @NotNull
    private final List<CMakeTarget> myTargets;
    @NotNull
    private final Map<String, OCLanguageKind> myFileExtensions;
    @NotNull
    private final List<CMakeModelConfigurationData> myConfigurationData;
    
    public CMakeModel(@NotNull final String myProjectName, @NotNull final Set<File> set, @NotNull final Set<File> set2, @NotNull final List<CMakeTarget> list, @NotNull final Map<String, OCLanguageKind> map, @NotNull final List<CMakeModelConfigurationData> list2) {
        if (myProjectName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectName", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "<init>"));
        }
        if (set == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeFiles", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "<init>"));
        }
        if (set2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerAndResourceFiles", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targets", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "<init>"));
        }
        if (map == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileExtensions", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "<init>"));
        }
        if (list2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurationData", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "<init>"));
        }
        this.myProjectName = myProjectName;
        this.myCMakeFiles = Collections.unmodifiableSet((Set<? extends File>)set);
        this.myHeaderAndResourceFiles = Collections.unmodifiableSet((Set<? extends File>)set2);
        this.myTargets = Collections.unmodifiableList((List<? extends CMakeTarget>)list);
        this.myFileExtensions = Collections.unmodifiableMap((Map<? extends String, ? extends OCLanguageKind>)map);
        this.myConfigurationData = Collections.unmodifiableList((List<? extends CMakeModelConfigurationData>)list2);
    }
    
    @Nullable
    public static CMakeModel load(@NotNull final ObjectInputStream objectInputStream) throws IOException {
        try {
            if (objectInputStream == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "out", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "load"));
            }
        }
        catch (ClassNotFoundException ex) {
            throw b(ex);
        }
        try {
            try {
                if (objectInputStream.readInt() != 105) {
                    return null;
                }
            }
            catch (IOException ex2) {
                throw b(ex2);
            }
            final Object object = objectInputStream.readObject();
            try {
                if (object instanceof CMakeModel) {
                    return (CMakeModel)object;
                }
            }
            catch (ClassNotFoundException ex3) {
                throw b(ex3);
            }
            return null;
        }
        catch (ClassNotFoundException ex4) {
            return null;
        }
    }
    
    public void save(@NotNull final ObjectOutputStream objectOutputStream) throws IOException {
        try {
            if (objectOutputStream == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "out", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "save"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        objectOutputStream.writeInt(105);
        objectOutputStream.writeObject(this);
    }
    
    @NotNull
    public String getProjectName() {
        String myProjectName;
        try {
            myProjectName = this.myProjectName;
            if (myProjectName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "getProjectName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myProjectName;
    }
    
    @NotNull
    public Set<File> getCMakeFiles() {
        Set<File> myCMakeFiles;
        try {
            myCMakeFiles = this.myCMakeFiles;
            if (myCMakeFiles == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "getCMakeFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myCMakeFiles;
    }
    
    @NotNull
    public Set<File> getHeaderAndResourceFiles() {
        Set<File> myHeaderAndResourceFiles;
        try {
            myHeaderAndResourceFiles = this.myHeaderAndResourceFiles;
            if (myHeaderAndResourceFiles == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "getHeaderAndResourceFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myHeaderAndResourceFiles;
    }
    
    @NotNull
    public List<CMakeTarget> getTargets() {
        List<CMakeTarget> myTargets;
        try {
            myTargets = this.myTargets;
            if (myTargets == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "getTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myTargets;
    }
    
    @NotNull
    public Map<String, OCLanguageKind> getFileExtensions() {
        Map<String, OCLanguageKind> myFileExtensions;
        try {
            myFileExtensions = this.myFileExtensions;
            if (myFileExtensions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "getFileExtensions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myFileExtensions;
    }
    
    @NotNull
    public List<CMakeModelConfigurationData> getConfigurationData() {
        List<CMakeModelConfigurationData> myConfigurationData;
        try {
            myConfigurationData = this.myConfigurationData;
            if (myConfigurationData == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeModel", "getConfigurationData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myConfigurationData;
    }
    
    @Override
    public String toString() {
        return this.myProjectName + " [" + this.myTargets.size() + " targets]";
    }
    
    static {
        DEFAULT_CONFIGURATION_TYPES = (List)ContainerUtil.immutableList((Object[])new String[] { "Default", "Debug", "Release", "RelWithDebInfo", "MinSizeRel" });
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
