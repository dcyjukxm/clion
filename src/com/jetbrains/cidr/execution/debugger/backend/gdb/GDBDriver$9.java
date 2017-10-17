// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;

class GDBDriver$9 extends EvaluationCommand<LLValue> {
    final /* synthetic */ long val$threadId;
    final /* synthetic */ int val$frameIndex;
    final /* synthetic */ String val$expression;
    final /* synthetic */ String val$debuggerLanguage;
    
    @Override
    public LLValue call() throws ExecutionException, DebuggerCommandException {
        return GDBDriver.access$1100(GDBDriver.this, this.val$threadId, this.val$frameIndex, this.val$expression, this.val$debuggerLanguage);
    }
}