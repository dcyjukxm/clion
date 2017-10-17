// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import java.util.regex.Matcher;
import java.util.Arrays;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.testframework.sm.ServiceMessageBuilder;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class CidrTestEventProcessor
{
    @NotNull
    private String myLocationProtocol;
    
    public CidrTestEventProcessor(@NotNull final String myLocationProtocol) {
        if (myLocationProtocol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "locationProtocol", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "<init>"));
        }
        this.myLocationProtocol = myLocationProtocol;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> suiteStarted(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteStarted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<ServiceMessageBuilder> suiteStarted;
        try {
            suiteStarted = this.suiteStarted(s, s);
            if (suiteStarted == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteStarted"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return suiteStarted;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> suiteStarted(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteStarted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteStarted"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> suiteStarted;
        try {
            suiteStarted = this.suiteStarted(s, null, null, s2);
            if (suiteStarted == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteStarted"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return suiteStarted;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> suiteStarted(@NotNull final String s, @Nullable final String s2, @Nullable final String s3, @NotNull final String s4) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteStarted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s4 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteStarted"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> doSuiteStarted;
        try {
            doSuiteStarted = this.doSuiteStarted(s, s2, s3, s4);
            if (doSuiteStarted == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteStarted"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return doSuiteStarted;
    }
    
    @NotNull
    protected List<ServiceMessageBuilder> doSuiteStarted(@NotNull final String s, @Nullable final String s2, @Nullable final String s3, @NotNull final String s4) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "doSuiteStarted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s4 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "doSuiteStarted"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> singletonList;
        try {
            singletonList = Collections.singletonList(c(s2, d(s3, this.a(s4, ServiceMessageBuilder.testSuiteStarted(s)))));
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "doSuiteStarted"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return singletonList;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> suiteFinished(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteFinished"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<ServiceMessageBuilder> suiteFinished;
        try {
            suiteFinished = this.suiteFinished(s, null);
            if (suiteFinished == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteFinished"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return suiteFinished;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> suiteFinished(@NotNull final String s, @Nullable final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteFinished"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<ServiceMessageBuilder> singletonList;
        try {
            singletonList = Collections.singletonList(d(s2, ServiceMessageBuilder.testSuiteFinished(s)));
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "suiteFinished"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return singletonList;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> testStarted(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStarted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStarted"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> testStarted;
        try {
            testStarted = this.testStarted(s, null, null, s2);
            if (testStarted == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStarted"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return testStarted;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> testStarted(@NotNull final String s, @Nullable final String s2, @Nullable final String s3, @NotNull final String s4) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStarted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s4 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStarted"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> doTestStarted;
        try {
            doTestStarted = this.doTestStarted(s, s2, s3, s4);
            if (doTestStarted == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStarted"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return doTestStarted;
    }
    
    @NotNull
    protected List<ServiceMessageBuilder> doTestStarted(@NotNull final String s, @Nullable final String s2, @Nullable final String s3, @NotNull final String s4) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "doTestStarted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s4 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "doTestStarted"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> singletonList;
        try {
            singletonList = Collections.singletonList(c(s2, d(s3, this.a(s4, ServiceMessageBuilder.testStarted(s)))));
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "doTestStarted"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return singletonList;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> testFinished(@NotNull final String s, @NotNull final String s2, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testFinished"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "duration", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testFinished"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> testFinished;
        try {
            testFinished = this.testFinished(s, null, s2, b);
            if (testFinished == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testFinished"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return testFinished;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> testFinished(@NotNull final String s, @Nullable final String s2, @NotNull final String s3, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testFinished"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "duration", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testFinished"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> doTestFinished;
        try {
            doTestFinished = this.doTestFinished(s, s2, s3, b, false);
            if (doTestFinished == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testFinished"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return doTestFinished;
    }
    
    @NotNull
    protected List<ServiceMessageBuilder> doTestFinished(@NotNull final String s, @Nullable final String s2, @NotNull final String s3, final boolean b, final boolean b2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "doTestFinished"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "duration", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "doTestFinished"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ServiceMessageBuilder serviceMessageBuilder = null;
        if (!b) {
            serviceMessageBuilder = ServiceMessageBuilder.testFailed(s);
        }
        else if (b2) {
            serviceMessageBuilder = ServiceMessageBuilder.testIgnored(s);
        }
        final ServiceMessageBuilder d = d(s2, e(s3, ServiceMessageBuilder.testFinished(s)));
        List<ServiceMessageBuilder> list2 = null;
        Label_0186: {
            List<ServiceMessageBuilder> list = null;
            Label_0151: {
                try {
                    if (serviceMessageBuilder != null) {
                        break Label_0186;
                    }
                    final ServiceMessageBuilder serviceMessageBuilder2 = d;
                    list = Collections.singletonList(serviceMessageBuilder2);
                    if (list == null) {
                        break Label_0151;
                    }
                    return list;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final ServiceMessageBuilder serviceMessageBuilder2 = d;
                    list = Collections.singletonList(serviceMessageBuilder2);
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "doTestFinished"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return list;
            try {
                d(s2, b("", serviceMessageBuilder));
                list2 = Arrays.asList(serviceMessageBuilder, d);
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "doTestFinished"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return list2;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> testErrOut(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testErrOut"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "output", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testErrOut"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> testErrOut;
        try {
            testErrOut = this.testErrOut(s, null, s2);
            if (testErrOut == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testErrOut"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return testErrOut;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> testErrOut(@NotNull final String s, @Nullable final String s2, @NotNull final String s3) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testErrOut"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "output", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testErrOut"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> singletonList;
        try {
            singletonList = Collections.singletonList(d(s2, ServiceMessageBuilder.testStdErr(s).addAttribute("out", s3)));
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testErrOut"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return singletonList;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> testStdOut(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStdOut"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "output", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStdOut"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> testStdOut;
        try {
            testStdOut = this.testStdOut(s, null, s2);
            if (testStdOut == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStdOut"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return testStdOut;
    }
    
    @NotNull
    public List<ServiceMessageBuilder> testStdOut(@NotNull final String s, @Nullable final String s2, @NotNull final String s3) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStdOut"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "output", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStdOut"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        List<ServiceMessageBuilder> singletonList;
        try {
            singletonList = Collections.singletonList(d(s2, ServiceMessageBuilder.testStdOut(s).addAttribute("out", s3)));
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "testStdOut"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return singletonList;
    }
    
    @NotNull
    private ServiceMessageBuilder a(@Nullable final String s, @NotNull final ServiceMessageBuilder serviceMessageBuilder) {
        try {
            if (serviceMessageBuilder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "setLocation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ServiceMessageBuilder addAttribute = null;
        Label_0095: {
            ServiceMessageBuilder serviceMessageBuilder2 = null;
            Label_0060: {
                try {
                    if (s != null) {
                        break Label_0095;
                    }
                    serviceMessageBuilder2 = serviceMessageBuilder;
                    if (serviceMessageBuilder2 == null) {
                        break Label_0060;
                    }
                    return serviceMessageBuilder2;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    serviceMessageBuilder2 = serviceMessageBuilder;
                    if (serviceMessageBuilder2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "setLocation"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return serviceMessageBuilder2;
            try {
                addAttribute = serviceMessageBuilder.addAttribute("locationHint", this.myLocationProtocol + "://" + s);
                if (addAttribute == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "setLocation"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return addAttribute;
    }
    
    @NotNull
    private static ServiceMessageBuilder d(@Nullable final String s, @NotNull final ServiceMessageBuilder serviceMessageBuilder) {
        try {
            if (serviceMessageBuilder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "setNodeId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ServiceMessageBuilder a;
        try {
            a = a("nodeId", s, serviceMessageBuilder);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "setNodeId"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a;
    }
    
    @NotNull
    private static ServiceMessageBuilder c(@Nullable final String s, @NotNull final ServiceMessageBuilder serviceMessageBuilder) {
        try {
            if (serviceMessageBuilder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "setParentNodeId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ServiceMessageBuilder a;
        try {
            a = a("parentNodeId", s, serviceMessageBuilder);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "setParentNodeId"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a;
    }
    
    @NotNull
    private static ServiceMessageBuilder b(@Nullable final String s, @NotNull final ServiceMessageBuilder serviceMessageBuilder) {
        try {
            if (serviceMessageBuilder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "setMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ServiceMessageBuilder a;
        try {
            a = a("message", s, serviceMessageBuilder);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "setMessage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a;
    }
    
    @NotNull
    private static ServiceMessageBuilder e(@Nullable final String s, @NotNull final ServiceMessageBuilder serviceMessageBuilder) {
        try {
            if (serviceMessageBuilder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "setDuration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ServiceMessageBuilder a;
        try {
            a = a("duration", s, serviceMessageBuilder);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "setDuration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a;
    }
    
    private static ServiceMessageBuilder a(@NotNull final String s, @Nullable final String s2, @NotNull final ServiceMessageBuilder serviceMessageBuilder) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attrName", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "addParamIfNeed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (serviceMessageBuilder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "addParamIfNeed"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s2 == null) {
                return serviceMessageBuilder;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return serviceMessageBuilder.addAttribute(s, s2);
    }
    
    @Nullable
    public Matcher hideOutputMatcher(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/testing/CidrTestEventProcessor", "hideOutputMatcher"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
