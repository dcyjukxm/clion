// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.statistics;

import com.jetbrains.cidr.execution.testing.CidrTestRunConfiguration;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.application.ReadAction;

class CidrTestingFrameworkApplicationUsagesCollector$2 extends ReadAction<String> {
    final /* synthetic */ RunConfiguration val$runConfiguration;
    
    protected void run(@NotNull final Result<String> result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/statistics/CidrTestingFrameworkApplicationUsagesCollector$2", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        result.setResult((Object)((CidrTestRunConfiguration)this.val$runConfiguration).getTestingFrameworkName());
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}