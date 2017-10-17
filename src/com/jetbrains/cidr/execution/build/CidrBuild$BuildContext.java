// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.build;

import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationTrackers;
import com.intellij.openapi.progress.ProcessCanceledException;
import java.util.concurrent.TimeUnit;
import com.jetbrains.cidr.CidrBundle;
import com.intellij.openapi.util.UserDataHolderEx;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import java.util.concurrent.Semaphore;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.ExecutionResult;

public static class BuildContext
{
    @NotNull
    protected final ExecutionResult<Boolean> myResult;
    private static final Key<Semaphore> BUILD_SEMAPHORE_KEY;
    private final Project myProject;
    private final Semaphore myBuildSemaphore;
    @NotNull
    public ProgressIndicator indicator;
    @NotNull
    public ProcessHandler processHandler;
    @Nullable
    public String finishMessage;
    @Nullable
    public String finishDetails;
    public final long started;
    public long duration;
    public int errors;
    public int warnings;
    
    public BuildContext(@NotNull final Project myProject) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/build/CidrBuild$BuildContext", "<init>"));
        }
        this.myResult = new ExecutionResult<Boolean>();
        this.started = System.currentTimeMillis();
        this.myProject = myProject;
        Semaphore myBuildSemaphore = (Semaphore)this.myProject.getUserData((Key)BuildContext.BUILD_SEMAPHORE_KEY);
        if (myBuildSemaphore == null) {
            myBuildSemaphore = (Semaphore)((UserDataHolderEx)this.myProject).putUserDataIfAbsent((Key)BuildContext.BUILD_SEMAPHORE_KEY, (Object)new Semaphore(1));
        }
        this.myBuildSemaphore = myBuildSemaphore;
    }
    
    public boolean waitAndStart() {
        this.indicator.pushState();
        try {
            this.indicator.setText(CidrBundle.message("build.waiting", new Object[0]));
            this.indicator.setText2("");
            while (true) {
                Label_0070: {
                    try {
                        this.indicator.checkCanceled();
                        final BuildContext buildContext = this;
                        final Semaphore semaphore = buildContext.myBuildSemaphore;
                        final long n = 100L;
                        final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                        final boolean b = semaphore.tryAcquire(n, timeUnit);
                        if (b) {
                            return true;
                        }
                        break Label_0070;
                    }
                    catch (ProcessCanceledException ex) {
                        throw a(ex);
                    }
                    try {
                        final BuildContext buildContext = this;
                        final Semaphore semaphore = buildContext.myBuildSemaphore;
                        final long n = 100L;
                        final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                        final boolean b = semaphore.tryAcquire(n, timeUnit);
                        if (!b) {
                            continue;
                        }
                    }
                    catch (InterruptedException ex2) {
                        throw new ProcessCanceledException();
                    }
                }
                break;
            }
        }
        catch (ProcessCanceledException ex3) {
            this.myResult.set(false);
            return false;
        }
        finally {
            this.indicator.popState();
        }
        return true;
    }
    
    private void a(final boolean b) {
        try {
            this.myBuildSemaphore.release();
            this.myResult.set(b);
            if (!this.myProject.isDisposed()) {
                OCWorkspaceModificationTrackers.getInstance(this.myProject).getBuildsTracker().incModificationCount();
            }
        }
        catch (ProcessCanceledException ex) {
            throw a(ex);
        }
    }
    
    public void error(final Throwable exception) {
        this.myBuildSemaphore.release();
        this.myResult.setException(exception);
    }
    
    static {
        BUILD_SEMAPHORE_KEY = Key.create("BUILD_SEMAPHORE_KEY");
    }
    
    private static ProcessCanceledException a(final ProcessCanceledException ex) {
        return ex;
    }
}
