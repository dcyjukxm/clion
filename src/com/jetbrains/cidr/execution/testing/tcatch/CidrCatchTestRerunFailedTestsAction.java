// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.jetbrains.cidr.execution.testing.CidrTestRunConfiguration;
import com.jetbrains.cidr.execution.CidrCommandLineState;
import com.jetbrains.cidr.execution.testing.CidrTestScope;
import com.intellij.execution.Executor;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.intellij.execution.Location;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.execution.testing.CidrTestScopeElement;
import com.intellij.execution.testframework.AbstractTestProxy;
import com.intellij.execution.testframework.Filter;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.ui.ComponentContainer;
import com.jetbrains.cidr.execution.testing.CidrRerunFailedTestsAction;

public class CidrCatchTestRerunFailedTestsAction extends CidrRerunFailedTestsAction
{
    private static final String PROTOCOL_PREFIX = "catch://";
    
    public CidrCatchTestRerunFailedTestsAction(@NotNull final ComponentContainer componentContainer) {
        if (componentContainer == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "componentContainer", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRerunFailedTestsAction", "<init>"));
        }
        super(componentContainer);
    }
    
    @NotNull
    @Override
    protected Filter getFilter(@NotNull final Project project, @NotNull final GlobalSearchScope globalSearchScope) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRerunFailedTestsAction", "getFilter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (globalSearchScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "searchScope", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRerunFailedTestsAction", "getFilter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        Filter and;
        try {
            and = super.getFilter(project, globalSearchScope).and(new Filter() {
                @Override
                public boolean shouldAccept(final AbstractTestProxy abstractTestProxy) {
                    return abstractTestProxy.isLeaf();
                }
            });
            if (and == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRerunFailedTestsAction", "getFilter"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return and;
    }
    
    @Nullable
    @Override
    protected CidrTestScopeElement getElement(@NotNull final AbstractTestProxy abstractTestProxy, @NotNull final Project project) {
        try {
            if (abstractTestProxy == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "test", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRerunFailedTestsAction", "getElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRerunFailedTestsAction", "getElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final String a = a(abstractTestProxy);
        try {
            if (a == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return new CidrTestScopeElement() {
            @Contract(pure = true)
            @Override
            public String toString() {
                return a;
            }
        };
    }
    
    @Nullable
    private static String a(@NotNull final AbstractTestProxy abstractTestProxy) {
        try {
            if (abstractTestProxy == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "test", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRerunFailedTestsAction", "getTestName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final String locationUrl = abstractTestProxy.getLocationUrl();
        try {
            if (locationUrl == null || !locationUrl.startsWith("catch://")) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return (String)CidrCatchTestUtil.splitPath(locationUrl.substring("catch://".length())).first;
    }
    
    @Nullable
    private static CidrCatchTestLocation a(final AbstractTestProxy abstractTestProxy, final Project project) {
        final Location location = abstractTestProxy.getLocation(project, OCSearchScope.getProjectSourcesScope(project));
        try {
            if (location instanceof CidrCatchTestLocation) {
                return (CidrCatchTestLocation)location;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    protected CidrCommandLineState createState(@NotNull final CidrRunConfiguration cidrRunConfiguration, @NotNull final ExecutionEnvironment executionEnvironment, @NotNull final Executor executor, @NotNull final CidrTestScope cidrTestScope) {
        try {
            if (cidrRunConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRerunFailedTestsAction", "createState"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (executionEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRerunFailedTestsAction", "createState"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (executor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRerunFailedTestsAction", "createState"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (cidrTestScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "failedTests", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestRerunFailedTestsAction", "createState"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return ((CidrTestRunConfiguration)cidrRunConfiguration).createState(executionEnvironment, executor, cidrTestScope);
    }
    
    @Override
    protected CidrTestScope createTestScope() {
        return new CidrTestScope();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
