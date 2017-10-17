// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.statistics;

import com.intellij.execution.RunManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.project.Project;
import com.intellij.execution.configurations.RunConfiguration;
import java.util.List;
import com.intellij.openapi.application.ReadAction;

class CidrTestingFrameworkApplicationUsagesCollector$1 extends ReadAction<List<RunConfiguration>> {
    final /* synthetic */ Project val$project;
    
    protected void run(@NotNull final Result<List<RunConfiguration>> result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/statistics/CidrTestingFrameworkApplicationUsagesCollector$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        result.setResult((Object)RunManager.getInstance(this.val$project).getAllConfigurationsList());
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}