// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.internal.statistic.beans.GroupDescriptor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.toolchains.CMake;
import com.jetbrains.cidr.cpp.toolchains.Cygwin;
import com.jetbrains.cidr.cpp.toolchains.MinGW;
import com.intellij.internal.statistic.CollectUsagesException;
import com.intellij.openapi.util.SystemInfo;
import java.util.HashSet;
import com.intellij.internal.statistic.beans.UsageDescriptor;
import java.util.Set;
import com.intellij.internal.statistic.UsagesCollector;

public class CPPToolchainUsageCollector extends UsagesCollector
{
    @NotNull
    @Override
    public Set<UsageDescriptor> getUsages() throws CollectUsagesException {
        final HashSet<UsageDescriptor> set = new HashSet<UsageDescriptor>();
        final CPPToolchains instance = CPPToolchains.getInstance();
        if (SystemInfo.isWindows) {
            final MinGW minGW = instance.getMinGW();
            if (minGW != null) {
                final String version = minGW.readVersion();
                try {
                    if (version != null) {
                        set.add(new UsageDescriptor("MinGW " + version, 1));
                    }
                }
                catch (CollectUsagesException ex) {
                    throw c(ex);
                }
            }
            final Cygwin cygwin = instance.getCygwin();
            if (cygwin != null) {
                final String version2 = cygwin.readVersion();
                try {
                    if (version2 != null) {
                        set.add(new UsageDescriptor("Cygwin " + version2, 1));
                    }
                }
                catch (CollectUsagesException ex2) {
                    throw c(ex2);
                }
            }
        }
        HashSet<UsageDescriptor> set2 = null;
        Label_0241: {
            try {
                if (instance.getState().isUseBundledCMake()) {
                    set.add(new UsageDescriptor("CMake bundled", 1));
                    break Label_0241;
                }
            }
            catch (CollectUsagesException ex3) {
                throw c(ex3);
            }
            final CMake cMake = instance.getCMake();
            if (cMake != null) {
                final String version3 = cMake.readVersion();
                try {
                    if (version3 != null) {
                        set.add(new UsageDescriptor("CMake specified " + version3, 1));
                    }
                }
                catch (CollectUsagesException ex4) {
                    throw c(ex4);
                }
            }
            try {
                set2 = set;
                if (set2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchainUsageCollector", "getUsages"));
                }
            }
            catch (CollectUsagesException ex5) {
                throw c(ex5);
            }
        }
        return set2;
    }
    
    @NotNull
    @Override
    public GroupDescriptor getGroupId() {
        GroupDescriptor create;
        try {
            create = GroupDescriptor.create("Toolchains");
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPToolchainUsageCollector", "getGroupId"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return create;
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
