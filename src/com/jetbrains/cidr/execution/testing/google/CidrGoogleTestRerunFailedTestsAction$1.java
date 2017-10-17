// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.intellij.execution.testframework.AbstractTestProxy;
import com.intellij.execution.testframework.Filter;

class CidrGoogleTestRerunFailedTestsAction$1 extends Filter {
    @Override
    public boolean shouldAccept(final AbstractTestProxy abstractTestProxy) {
        return abstractTestProxy.isLeaf();
    }
}