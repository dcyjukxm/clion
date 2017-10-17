// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.build;

import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.progress.ProcessCanceledException;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.ExecutionResult;
import com.intellij.openapi.progress.Task;

static final class CidrBuild$1 extends Task.Backgroundable {
    final /* synthetic */ ExecutionResult val$indicatorResult;
    final /* synthetic */ BuildContext val$context;
    final /* synthetic */ Object val$processCreationLock;
    
    public void run(@NotNull final ProgressIndicator progressIndicator) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/execution/build/CidrBuild$1", "run"));
            }
        }
        catch (InterruptedException ex) {
            throw b(ex);
        }
        this.val$indicatorResult.set(progressIndicator);
        int n = 0;
        while (true) {
            Label_0141: {
                Label_0078: {
                    try {
                        if (this.val$context.myResult.isDone()) {
                            break;
                        }
                        final int n2 = n;
                        if (n2 == 0) {
                            break Label_0078;
                        }
                        break Label_0141;
                    }
                    catch (InterruptedException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final int n2 = n;
                        if (n2 != 0) {
                            break Label_0141;
                        }
                        if (!progressIndicator.isCanceled()) {
                            break Label_0141;
                        }
                    }
                    catch (InterruptedException ex3) {
                        throw b(ex3);
                    }
                }
                n = 1;
                synchronized (this.val$processCreationLock) {
                    final ProcessHandler processHandler = this.val$context.processHandler;
                    try {
                        if (processHandler != null) {
                            processHandler.destroyProcess();
                        }
                    }
                    catch (InterruptedException ex4) {
                        throw b(ex4);
                    }
                }
                try {
                    Thread.sleep(100L);
                    continue;
                }
                catch (InterruptedException ex5) {
                    throw new ProcessCanceledException((Throwable)ex5);
                }
            }
            break;
        }
    }
    
    private static InterruptedException b(final InterruptedException ex) {
        return ex;
    }
}