// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.icons.AllIcons;
import icons.CLionIcons;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import javax.swing.Icon;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import java.io.Serializable;

public class CMakeTarget implements Serializable, CidrBuildTarget<CMakeConfiguration>
{
    public static final Icon EXECUTABLE_ICON;
    public static final Icon LIBRARY_ICON;
    public static final Icon UTILITY_ICON;
    public static final Icon META_TARGET_ICON;
    @NotNull
    private final String myProjectName;
    @NotNull
    private final String myName;
    @NotNull
    private final List<CMakeConfiguration> myConfigurations;
    
    public CMakeTarget(@NotNull final String myProjectName, @NotNull final String myName, @NotNull final List<CMakeConfiguration> list) {
        if (myProjectName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectName", "com/jetbrains/cidr/cpp/cmake/model/CMakeTarget", "<init>"));
        }
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/model/CMakeTarget", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/cmake/model/CMakeTarget", "<init>"));
        }
        this.myProjectName = myProjectName;
        this.myName = myName;
        this.myConfigurations = Collections.unmodifiableList((List<? extends CMakeConfiguration>)list);
    }
    
    @NotNull
    @Override
    public String getProjectName() {
        String myProjectName;
        try {
            myProjectName = this.myProjectName;
            if (myProjectName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeTarget", "getProjectName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myProjectName;
    }
    
    @Nullable
    @Override
    public Icon getIcon() {
        final CMakeConfiguration cMakeConfiguration = (CMakeConfiguration)ContainerUtil.getFirstItem((List)this.getBuildConfigurations());
        try {
            if (cMakeConfiguration == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            switch (cMakeConfiguration.getTargetType()) {
                case EXECUTABLE: {
                    return CMakeTarget.EXECUTABLE_ICON;
                }
                case SHARED_LIBRARY:
                case STATIC_LIBRARY: {
                    break;
                }
                case UTILITY: {
                    return CMakeTarget.UTILITY_ICON;
                }
                default: {
                    return null;
                }
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return CMakeTarget.LIBRARY_ICON;
    }
    
    @Override
    public boolean isExecutable() {
        return ContainerUtil.exists((Iterable)this.getBuildConfigurations(), cMakeConfiguration -> {
            try {
                if (cMakeConfiguration.getTargetType() == CMakeConfiguration.TargetType.EXECUTABLE) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return false;
        });
    }
    
    public boolean projectNameEquals(@Nullable final String s) {
        return this.myProjectName.equalsIgnoreCase(s);
    }
    
    @NotNull
    @Override
    public String getName() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeTarget", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    @NotNull
    @Override
    public List<CMakeConfiguration> getBuildConfigurations() {
        List<CMakeConfiguration> myConfigurations;
        try {
            myConfigurations = this.myConfigurations;
            if (myConfigurations == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeTarget", "getBuildConfigurations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myConfigurations;
    }
    
    @Override
    public String toString() {
        return this.myName + " [" + this.myConfigurations.size() + " configs]";
    }
    
    static {
        EXECUTABLE_ICON = CLionIcons.CMakeTarget_Executable;
        LIBRARY_ICON = AllIcons.Modules.Library;
        UTILITY_ICON = CLionIcons.CMakeTarget_Utility;
        META_TARGET_ICON = CLionIcons.CMakeTarget_Meta;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
