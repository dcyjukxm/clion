// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.build;

import com.jetbrains.cidr.CidrBundle;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.impl.EditConfigurationsDialog;
import com.jetbrains.cidr.cpp.execution.CMakeAppRunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.build.BaseBuildAction;

public abstract class CMakeTargetAction extends BaseBuildAction
{
    protected CMakeTargetAction(@NotNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/execution/build/CMakeTargetAction", "<init>"));
        }
        super(s);
    }
    
    protected abstract void doBuild(@NotNull final Project p0, @NotNull final CMakeAppRunConfiguration.BuildAndRunConfigurations p1);
    
    @Override
    protected void doBuild(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeTargetAction", "doBuild"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final RunConfiguration a = a(project);
        try {
            if (!(a instanceof CMakeAppRunConfiguration)) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations = ((CMakeAppRunConfiguration)a).getBuildAndRunConfigurations();
        try {
            if (buildAndRunConfigurations == null) {
                new EditConfigurationsDialog(project).show();
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        this.doBuild(project, buildAndRunConfigurations);
    }
    
    @Override
    protected boolean isAvailable(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeTargetAction", "isAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0069: {
            try {
                if (!super.isAvailable(project)) {
                    return false;
                }
                final Project project2 = project;
                final RunConfiguration runConfiguration = a(project2);
                final boolean b = runConfiguration instanceof CMakeAppRunConfiguration;
                if (b) {
                    break Label_0069;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final Project project2 = project;
                final RunConfiguration runConfiguration = a(project2);
                final boolean b = runConfiguration instanceof CMakeAppRunConfiguration;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    private static RunConfiguration a(final Project project) {
        final RunnerAndConfigurationSettings selectedConfiguration = RunManager.getInstance(project).getSelectedConfiguration();
        try {
            if (selectedConfiguration == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return selectedConfiguration.getConfiguration();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class Clean extends CMakeTargetAction
    {
        public Clean() {
            super(CidrBundle.message("build.clean", new Object[0]));
        }
        
        @Override
        protected void doBuild(@NotNull final Project project, @NotNull final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations) {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeTargetAction$Clean", "doBuild"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                if (buildAndRunConfigurations == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/execution/build/CMakeTargetAction$Clean", "doBuild"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            CMakeBuild.clean(project, buildAndRunConfigurations);
        }
        
        private static IllegalArgumentException c(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class Build extends CMakeTargetAction
    {
        public Build() {
            super(CidrBundle.message("build", new Object[0]));
        }
        
        @Override
        protected void doBuild(@NotNull final Project project, @NotNull final CMakeAppRunConfiguration.BuildAndRunConfigurations buildAndRunConfigurations) {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/build/CMakeTargetAction$Build", "doBuild"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                if (buildAndRunConfigurations == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurations", "com/jetbrains/cidr/cpp/execution/build/CMakeTargetAction$Build", "doBuild"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            CMakeBuild.build(project, buildAndRunConfigurations);
        }
        
        private static IllegalArgumentException c(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
