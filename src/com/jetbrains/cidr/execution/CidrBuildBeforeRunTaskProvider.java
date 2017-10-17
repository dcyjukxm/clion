// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.BeforeRunTask;
import com.jetbrains.cidr.CidrBundle;
import com.intellij.openapi.util.Key;
import com.intellij.execution.BeforeRunTaskProvider;

public abstract class CidrBuildBeforeRunTaskProvider extends BeforeRunTaskProvider
{
    public static final Key<BuildBeforeRunTask> ID;
    
    public Key<BuildBeforeRunTask> getId() {
        return CidrBuildBeforeRunTaskProvider.ID;
    }
    
    public String getName() {
        return CidrBundle.message("build", new Object[0]);
    }
    
    public String getDescription(final BeforeRunTask beforeRunTask) {
        return CidrBundle.message("build", new Object[0]);
    }
    
    public boolean isConfigurable() {
        return false;
    }
    
    public boolean isSingleton() {
        return true;
    }
    
    public BeforeRunTask createTask(@NotNull final RunConfiguration runConfiguration) {
        try {
            if (runConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runConfiguration", "com/jetbrains/cidr/execution/CidrBuildBeforeRunTaskProvider", "createTask"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(runConfiguration instanceof CidrRunConfiguration)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return new BuildBeforeRunTask();
    }
    
    public boolean configureTask(final RunConfiguration runConfiguration, final BeforeRunTask beforeRunTask) {
        return false;
    }
    
    public boolean canExecuteTask(final RunConfiguration runConfiguration, final BeforeRunTask beforeRunTask) {
        return runConfiguration instanceof CidrRunConfiguration;
    }
    
    static {
        ID = Key.create(BuildBeforeRunTask.class.getName());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class BuildBeforeRunTask extends BeforeRunTask<BuildBeforeRunTask>
    {
        private BuildBeforeRunTask() {
            super((Key)CidrBuildBeforeRunTaskProvider.ID);
            this.setEnabled(true);
        }
    }
}
