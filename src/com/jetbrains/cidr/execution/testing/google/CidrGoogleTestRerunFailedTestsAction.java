// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.jetbrains.cidr.execution.testing.CidrTestRunConfiguration;
import com.jetbrains.cidr.execution.CidrCommandLineState;
import com.jetbrains.cidr.execution.testing.CidrTestScope;
import com.intellij.execution.Executor;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.intellij.execution.Location;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Couple;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.execution.testing.CidrTestScopeElement;
import com.intellij.execution.testframework.AbstractTestProxy;
import com.intellij.execution.testframework.Filter;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.ui.ComponentContainer;
import com.jetbrains.cidr.execution.testing.CidrRerunFailedTestsAction;

public class CidrGoogleTestRerunFailedTestsAction extends CidrRerunFailedTestsAction
{
    public CidrGoogleTestRerunFailedTestsAction(@NotNull final ComponentContainer componentContainer) {
        if (componentContainer == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "componentContainer", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRerunFailedTestsAction", "<init>"));
        }
        super(componentContainer);
    }
    
    @NotNull
    @Override
    protected Filter getFilter(@NotNull final Project project, @NotNull final GlobalSearchScope globalSearchScope) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRerunFailedTestsAction", "getFilter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (globalSearchScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "searchScope", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRerunFailedTestsAction", "getFilter"));
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRerunFailedTestsAction", "getFilter"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return and;
    }
    
    @Nullable
    @Override
    protected CidrTestScopeElement getElement(@NotNull final AbstractTestProxy abstractTestProxy, final Project project) {
        try {
            if (abstractTestProxy == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "test", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRerunFailedTestsAction", "getElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final CidrGoogleTestLocation a = a(abstractTestProxy, project);
        String param = null;
        Label_0066: {
            try {
                if (a != null) {
                    param = a.getParam();
                    break Label_0066;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            param = null;
        }
        final String s = param;
        String s2 = null;
        switch (a(abstractTestProxy)) {
            case 2: {
                s2 = String.format("%s.%s", abstractTestProxy.getParent().getName(), abstractTestProxy.getName());
                break;
            }
            case 3: {
                s2 = String.format("%s/%s.%s", abstractTestProxy.getParent().getParent().getName(), s, abstractTestProxy.getName());
                break;
            }
            case 4: {
                try {
                    if (a == null) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                final PsiElement psiElement = a.getPsiElement();
                try {
                    if (!(psiElement instanceof OCStruct)) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                final Couple<String> googleTestName = CidrGoogleTestUtil.extractGoogleTestName(((OCStruct)psiElement).getSymbol());
                try {
                    if (googleTestName == null) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                if (a.isTyped()) {
                    s2 = String.format("%s/%s/%s.%s", a.getInstantiation(), googleTestName.first, s, googleTestName.second);
                    break;
                }
                s2 = String.format("%s/%s.%s/%s", a.getInstantiation(), googleTestName.first, googleTestName.second, s);
                break;
            }
            default: {
                s2 = null;
                break;
            }
        }
        final CidrTestScopeElement cidrTestScopeElement = new CidrTestScopeElement() {
            @Contract(pure = true)
            @Override
            public String toString() {
                return s2;
            }
        };
        try {
            if (s2 == null) {
                final Object o = null;
                return (CidrTestScopeElement)o;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        final Object o = cidrTestScopeElement;
        return (CidrTestScopeElement)o;
    }
    
    @Nullable
    private static CidrGoogleTestLocation a(final AbstractTestProxy abstractTestProxy, final Project project) {
        final Location location = abstractTestProxy.getLocation(project, OCSearchScope.getProjectSourcesScope(project));
        try {
            if (location instanceof CidrGoogleTestLocation) {
                return (CidrGoogleTestLocation)location;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    private static int a(AbstractTestProxy parent) {
        int n;
        for (n = 0; parent.getParent() != null; parent = parent.getParent(), ++n) {}
        return n;
    }
    
    @Nullable
    @Override
    protected CidrCommandLineState createState(@NotNull final CidrRunConfiguration cidrRunConfiguration, @NotNull final ExecutionEnvironment executionEnvironment, @NotNull final Executor executor, @NotNull final CidrTestScope cidrTestScope) {
        try {
            if (cidrRunConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRerunFailedTestsAction", "createState"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (executionEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRerunFailedTestsAction", "createState"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (executor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRerunFailedTestsAction", "createState"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (cidrTestScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "failedTests", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRerunFailedTestsAction", "createState"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return ((CidrTestRunConfiguration)cidrRunConfiguration).createState(executionEnvironment, executor, cidrTestScope);
    }
    
    @Override
    protected CidrTestScope createTestScope() {
        return new CidrTestScope(':');
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
