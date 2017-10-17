// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jetbrains.annotations.NotNull;

public final class BuildTargetData
{
    @NotNull
    public final String projectName;
    @NotNull
    public final String targetName;
    
    public BuildTargetData(@NotNull final String projectName, @NotNull final String targetName) {
        if (projectName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectName", "com/jetbrains/cidr/execution/BuildTargetData", "<init>"));
        }
        if (targetName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetName", "com/jetbrains/cidr/execution/BuildTargetData", "<init>"));
        }
        this.projectName = projectName;
        this.targetName = targetName;
    }
    
    public BuildTargetData(@NotNull final CidrBuildTarget cidrBuildTarget) {
        if (cidrBuildTarget == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/execution/BuildTargetData", "<init>"));
        }
        this(cidrBuildTarget.getProjectName(), cidrBuildTarget.getName());
    }
    
    @Override
    public String toString() {
        return this.getDisplayString();
    }
    
    @NotNull
    public String getDisplayString() {
        String formatDisplayString;
        try {
            formatDisplayString = BuildTargetAndConfigurationData.formatDisplayString(this.projectName, this.targetName);
            if (formatDisplayString == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/BuildTargetData", "getDisplayString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return formatDisplayString;
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final BuildTargetData buildTargetData = this;
                final Class<? extends BuildTargetData> clazz = buildTargetData.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final BuildTargetData buildTargetData = this;
                final Class<? extends BuildTargetData> clazz = buildTargetData.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final BuildTargetData buildTargetData2 = (BuildTargetData)o;
        try {
            if (!this.projectName.equals(buildTargetData2.projectName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.targetName.equals(buildTargetData2.targetName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * this.projectName.hashCode() + this.targetName.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
