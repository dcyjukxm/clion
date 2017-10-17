// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import java.util.Locale;
import com.intellij.util.PathUtil;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import com.jetbrains.cidr.execution.CidrBuildConfiguration;
import com.jetbrains.cidr.execution.BuildTargetAndConfigurationData;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VfsUtilCore;
import java.io.File;
import com.intellij.openapi.vfs.LocalFileSystem;
import java.io.IOException;
import com.jetbrains.cidr.execution.testing.tcatch.CidrCatchTestUtil;
import java.util.Arrays;
import com.intellij.util.containers.HashSet;
import com.jetbrains.cidr.cpp.execution.testing.tcatch.CMakeCatchTestRunConfigurationType;
import com.jetbrains.cidr.cpp.execution.testing.google.CMakeGoogleTestRunConfigurationType;
import java.util.Collection;
import org.jetbrains.annotations.Contract;
import java.util.Iterator;
import com.jetbrains.cidr.execution.BuildTargetData;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.jetbrains.cidr.cpp.cmake.model.CMakeModel;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.execution.CidrBuildConfigurationHelper;

public class CMakeBuildConfigurationHelper extends CidrBuildConfigurationHelper<CMakeConfiguration, CMakeTarget>
{
    @NotNull
    private final Project myProject;
    private static final String[] GOOGLE_TEST_STANDARD_LIBS;
    
    public CMakeBuildConfigurationHelper(@NotNull final Project myProject) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper", "<init>"));
        }
        this.myProject = myProject;
    }
    
    @NotNull
    public String getProjectName() {
        final CMakeModel model = CMakeWorkspace.getInstance(this.myProject).getModel();
        String s = null;
        Label_0028: {
            try {
                if (model == null) {
                    final String projectName;
                    s = (projectName = "Project");
                    break Label_0028;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            String projectName;
            s = (projectName = model.getProjectName());
            try {
                if (projectName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper", "getProjectName"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return s;
    }
    
    @NotNull
    @Override
    public List<CMakeTarget> getTargets() {
        List<CMakeTarget> modelTargets;
        try {
            modelTargets = CMakeWorkspace.getInstance(this.myProject).getModelTargets();
            if (modelTargets == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper", "getTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return modelTargets;
    }
    
    @Nullable
    @Override
    public CMakeConfiguration getDefaultConfiguration(@Nullable final CMakeTarget cMakeTarget) {
        return (CMakeConfiguration)ContainerUtil.getFirstItem((List)((CidrBuildConfigurationHelper<CidrBuildConfiguration, CMakeTarget>)this).getConfigurations(cMakeTarget));
    }
    
    @Nullable
    @Override
    public CMakeTarget findTarget(@Nullable final BuildTargetData buildTargetData) {
        return findTarget(this.getTargets(), buildTargetData);
    }
    
    @Contract("_, null -> null")
    public static CMakeTarget findTarget(@NotNull final List<? extends CMakeTarget> list, @Nullable final BuildTargetData buildTargetData) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targets", "com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper", "findTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (buildTargetData == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        for (final CMakeTarget cMakeTarget : list) {
            try {
                if (!cMakeTarget.projectNameEquals(buildTargetData.projectName)) {
                    continue;
                }
                final CMakeTarget cMakeTarget2 = cMakeTarget;
                final String s = cMakeTarget2.getName();
                final BuildTargetData buildTargetData2 = buildTargetData;
                final String s2 = buildTargetData2.targetName;
                final boolean b = s.equals(s2);
                if (b) {
                    return cMakeTarget;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final CMakeTarget cMakeTarget2 = cMakeTarget;
                final String s = cMakeTarget2.getName();
                final BuildTargetData buildTargetData2 = buildTargetData;
                final String s2 = buildTargetData2.targetName;
                final boolean b = s.equals(s2);
                if (b) {
                    return cMakeTarget;
                }
                continue;
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    public List<CMakeTarget> getRunTargets() {
        List filter;
        try {
            filter = ContainerUtil.filter((Collection)this.getTargets(), cMakeTarget -> cMakeTarget.isExecutable());
            if (filter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper", "getRunTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (List<CMakeTarget>)filter;
    }
    
    @Nullable
    @Override
    public CMakeTarget findRunTarget(@Nullable final BuildTargetData buildTargetData) {
        return findTarget(this.getRunTargets(), buildTargetData);
    }
    
    @Nullable
    @Override
    public CMakeConfiguration findConfiguration(@Nullable final CMakeTarget cMakeTarget, @Nullable final String s) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        for (final CMakeConfiguration cMakeConfiguration : this.getConfigurations(cMakeTarget)) {
            try {
                if (cMakeConfiguration.getName().equals(s)) {
                    return cMakeConfiguration;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return null;
    }
    
    @NotNull
    public CMakeRunConfigurationType getDefaultTargetType(@NotNull final CMakeTarget cMakeTarget) {
        try {
            if (cMakeTarget == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper", "getDefaultTargetType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        CMakeAppRunConfigurationType instance = null;
        Label_0158: {
            CMakeCatchTestRunConfigurationType cMakeCatchTestRunConfigurationType = null;
            Label_0123: {
                Label_0101: {
                    CMakeGoogleTestRunConfigurationType cMakeGoogleTestRunConfigurationType = null;
                    Label_0066: {
                        try {
                            if (!this.b(cMakeTarget)) {
                                break Label_0101;
                            }
                            cMakeGoogleTestRunConfigurationType = CMakeGoogleTestRunConfigurationType.getInstance();
                            if (cMakeGoogleTestRunConfigurationType == null) {
                                break Label_0066;
                            }
                            return cMakeGoogleTestRunConfigurationType;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        try {
                            cMakeGoogleTestRunConfigurationType = CMakeGoogleTestRunConfigurationType.getInstance();
                            if (cMakeGoogleTestRunConfigurationType == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper", "getDefaultTargetType"));
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    return cMakeGoogleTestRunConfigurationType;
                    try {
                        if (!this.c(cMakeTarget)) {
                            break Label_0158;
                        }
                        cMakeCatchTestRunConfigurationType = CMakeCatchTestRunConfigurationType.getInstance();
                        if (cMakeCatchTestRunConfigurationType == null) {
                            break Label_0123;
                        }
                        return cMakeCatchTestRunConfigurationType;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                try {
                    cMakeCatchTestRunConfigurationType = CMakeCatchTestRunConfigurationType.getInstance();
                    if (cMakeCatchTestRunConfigurationType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper", "getDefaultTargetType"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
            return cMakeCatchTestRunConfigurationType;
            try {
                instance = CMakeAppRunConfigurationType.getInstance();
                if (instance == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper", "getDefaultTargetType"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
        }
        return instance;
    }
    
    private boolean b(@NotNull final CMakeTarget cMakeTarget) {
        try {
            if (cMakeTarget == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper", "isGoogleTestTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (cMakeTarget.isExecutable()) {
            final CMakeConfiguration defaultConfiguration = this.getDefaultConfiguration(cMakeTarget);
            if (defaultConfiguration != null) {
                final HashSet set = new HashSet();
                try {
                    final HashSet set2;
                    Arrays.asList(CMakeBuildConfigurationHelper.GOOGLE_TEST_STANDARD_LIBS).forEach(s -> {
                        set2.add((Object)("lib" + s + ".a"));
                        set2.add((Object)(s + ".lib"));
                        return;
                    });
                    if (null != ContainerUtil.find((Iterable)defaultConfiguration.getLinkerFlags(), s -> set.contains((Object)PathUtil.getFileName(s).toLowerCase(Locale.ENGLISH)))) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                return false;
            }
        }
        return false;
    }
    
    private boolean c(final CMakeTarget cMakeTarget) {
        try {
            if (!cMakeTarget.isExecutable() || !CidrCatchTestUtil.isTargetDetectionEnabled()) {
                return false;
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        final CMakeConfiguration defaultConfiguration = this.getDefaultConfiguration(cMakeTarget);
        if (defaultConfiguration != null) {
            final Collection<File> sources = defaultConfiguration.getSources();
            final LocalFileSystem instance = LocalFileSystem.getInstance();
            int n = 10;
            for (final File file : sources) {
                try {
                    if (--n < 0) {
                        break;
                    }
                }
                catch (IOException ex2) {
                    throw b(ex2);
                }
                final VirtualFile fileByIoFile = instance.findFileByIoFile(file);
                if (fileByIoFile != null) {
                    try {
                        final String loadText = VfsUtilCore.loadText(fileByIoFile);
                        final String substring = loadText.substring(0, Math.min(loadText.length(), 1024));
                        try {
                            if (!substring.contains("catch.hpp\"")) {
                                if (!substring.contains("catch.hpp>")) {
                                    continue;
                                }
                            }
                        }
                        catch (IOException ex3) {
                            throw b(ex3);
                        }
                        final Iterator<String> iterator2 = CidrCatchTestUtil.CATCH_TEST_MACRO_NAMES.iterator();
                        while (iterator2.hasNext()) {
                            if (substring.contains(iterator2.next())) {
                                return true;
                            }
                        }
                    }
                    catch (IOException ex4) {}
                }
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    public BuildTargetAndConfigurationData findSimilarValidInTargets(@Nullable CMakeConfiguration cMakeConfiguration, @Nullable CMakeTarget target, @NotNull final List<CMakeTarget> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetsWithContext", "com/jetbrains/cidr/cpp/execution/CMakeBuildConfigurationHelper", "findSimilarValidInTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (cMakeConfiguration == null || list.contains(target)) {
                return CidrBuildConfigurationHelper.createIfSuitable(cMakeConfiguration, target, list);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final String name = cMakeConfiguration.getName();
        final Iterator<CMakeTarget> iterator = list.iterator();
        while (iterator.hasNext()) {
            final CMakeConfiguration configuration = this.findConfiguration(iterator.next(), name);
            if (configuration != null) {
                cMakeConfiguration = configuration;
                target = configuration.getTarget();
                break;
            }
        }
        return CidrBuildConfigurationHelper.createIfSuitable(cMakeConfiguration, target, list);
    }
    
    static {
        GOOGLE_TEST_STANDARD_LIBS = new String[] { "gtest", "gtest_main", "gmock", "gmock_main" };
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
