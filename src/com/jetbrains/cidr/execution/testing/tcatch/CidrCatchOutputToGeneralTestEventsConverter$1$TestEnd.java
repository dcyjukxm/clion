// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.intellij.execution.testframework.sm.runner.GeneralTestEventsProcessor;
import org.jetbrains.annotations.NotNull;

class TestEnd
{
    String testName;
    String nodeId;
    int durationInMs;
    boolean success;
    
    TestEnd(@NotNull final String testName, final String nodeId, final int durationInMs, final boolean success) {
        if (testName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1$TestEnd", "<init>"));
        }
        if (nodeId == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodeId", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchOutputToGeneralTestEventsConverter$1$TestEnd", "<init>"));
        }
        this.testName = testName;
        this.nodeId = nodeId;
        this.durationInMs = durationInMs;
        this.success = success;
    }
    
    void update(final int n, final boolean b) {
        this.durationInMs += n;
        this.success &= b;
    }
    
    void processEnd(final GeneralTestEventsProcessor generalTestEventsProcessor) {
        generalTestEventsProcessor.addToInvokeLater(() -> DefaultHandler.this.this$0.process(CidrCatchOutputToGeneralTestEventsConverter.access$200(DefaultHandler.this.this$0).testFinished(this.testName, this.nodeId, Integer.toString(this.durationInMs), this.success)));
    }
}
