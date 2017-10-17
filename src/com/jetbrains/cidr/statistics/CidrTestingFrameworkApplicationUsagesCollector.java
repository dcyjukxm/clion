// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.statistics;

import com.intellij.internal.statistic.beans.GroupDescriptor;
import java.util.Iterator;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfiguration;
import com.intellij.execution.RunManager;
import com.intellij.openapi.application.Result;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.application.ReadAction;
import java.util.List;
import gnu.trove.TObjectIntHashMap;
import com.intellij.internal.statistic.CollectUsagesException;
import com.intellij.internal.statistic.beans.UsageDescriptor;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.internal.statistic.AbstractApplicationUsagesCollector;

public class CidrTestingFrameworkApplicationUsagesCollector extends AbstractApplicationUsagesCollector
{
    @NotNull
    @Override
    public Set<UsageDescriptor> getProjectUsages(@NotNull final Project project) throws CollectUsagesException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/statistics/CidrTestingFrameworkApplicationUsagesCollector", "getProjectUsages"));
            }
        }
        catch (CollectUsagesException ex) {
            throw d(ex);
        }
        final TObjectIntHashMap tObjectIntHashMap = new TObjectIntHashMap();
        for (final RunConfiguration runConfiguration : (List)new ReadAction<List<RunConfiguration>>() {
            protected void run(@NotNull final Result<List<RunConfiguration>> result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/statistics/CidrTestingFrameworkApplicationUsagesCollector$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                result.setResult((Object)RunManager.getInstance(project).getAllConfigurationsList());
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute().getResultObject()) {
            if (runConfiguration instanceof CidrTestRunConfiguration) {
                final String s = (String)new ReadAction<String>() {
                    protected void run(@NotNull final Result<String> result) throws Throwable {
                        try {
                            if (result == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/statistics/CidrTestingFrameworkApplicationUsagesCollector$2", "run"));
                            }
                        }
                        catch (Throwable t) {
                            throw a(t);
                        }
                        result.setResult((Object)((CidrTestRunConfiguration)runConfiguration).getTestingFrameworkName());
                    }
                    
                    private static Throwable a(final Throwable t) {
                        return t;
                    }
                }.execute().getResultObject();
                try {
                    if (s == null) {
                        continue;
                    }
                }
                catch (CollectUsagesException ex2) {
                    throw d(ex2);
                }
                try {
                    if (!tObjectIntHashMap.containsKey((Object)s)) {
                        tObjectIntHashMap.put((Object)s, 0);
                    }
                }
                catch (CollectUsagesException ex3) {
                    throw d(ex3);
                }
                tObjectIntHashMap.increment((Object)s);
            }
        }
        Set map2Set;
        try {
            map2Set = ContainerUtil.map2Set(tObjectIntHashMap.keys(), o -> {
                final String s = (String)o;
                return new UsageDescriptor(s, tObjectIntHashMap.get((Object)s));
            });
            if (map2Set == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/statistics/CidrTestingFrameworkApplicationUsagesCollector", "getProjectUsages"));
            }
        }
        catch (CollectUsagesException ex4) {
            throw d(ex4);
        }
        return (Set<UsageDescriptor>)map2Set;
    }
    
    @NotNull
    @Override
    public GroupDescriptor getGroupId() {
        GroupDescriptor create;
        try {
            create = GroupDescriptor.create("Testing Frameworks");
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/statistics/CidrTestingFrameworkApplicationUsagesCollector", "getGroupId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return create;
    }
    
    private static Exception d(final Exception ex) {
        return ex;
    }
}
