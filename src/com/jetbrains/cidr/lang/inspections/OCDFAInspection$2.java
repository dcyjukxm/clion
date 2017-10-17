// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.dfa.contextSensitive.OCDFAUtils;
import com.jetbrains.sourceglider.ui.DummyThreadCallback;

static final class OCDFAInspection$2 extends DummyThreadCallback {
    final /* synthetic */ OCDFAUtils.WorkingTimeMeasurer val$measurer;
    final /* synthetic */ boolean val$contextSensitive;
    
    @Override
    public boolean checkCancelled() {
        OCDFAUtils.DFAException ex = null;
        StringBuilder sb2 = null;
        String s2 = null;
        Label_0054: {
            Label_0043: {
                try {
                    ProgressManager.checkCanceled();
                    if (!this.val$measurer.isTimeOver()) {
                        return false;
                    }
                    ex = new(com.jetbrains.cidr.lang.dfa.contextSensitive.OCDFAUtils.DFAException.class);
                    final StringBuilder sb = new StringBuilder();
                    final String s = "DFA time is over";
                    sb2 = sb.append(s);
                    final DummyThreadCallback dummyThreadCallback = this;
                    final boolean b = dummyThreadCallback.val$contextSensitive;
                    if (b) {
                        break Label_0043;
                    }
                    break Label_0043;
                }
                catch (OCDFAUtils.DFAException ex2) {
                    throw b(ex2);
                }
                try {
                    ex = new(com.jetbrains.cidr.lang.dfa.contextSensitive.OCDFAUtils.DFAException.class);
                    final StringBuilder sb = new StringBuilder();
                    final String s = "DFA time is over";
                    sb2 = sb.append(s);
                    final DummyThreadCallback dummyThreadCallback = this;
                    final boolean b = dummyThreadCallback.val$contextSensitive;
                    if (b) {
                        s2 = " (context-sensitive)";
                        break Label_0054;
                    }
                }
                catch (OCDFAUtils.DFAException ex3) {
                    throw b(ex3);
                }
            }
            s2 = "";
        }
        new OCDFAUtils.DFAException(sb2.append(s2).toString());
        throw ex;
    }
    
    private static OCDFAUtils.DFAException b(final OCDFAUtils.DFAException ex) {
        return ex;
    }
}