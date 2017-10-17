// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.TaskInfo;
import com.intellij.util.ui.UIUtil;
import com.intellij.openapi.wm.ex.ProgressIndicatorEx;
import com.intellij.openapi.progress.util.AbstractProgressIndicatorExBase;
import com.intellij.openapi.progress.util.ProgressIndicatorBase;

private class MyProgressIndicator extends ProgressIndicatorBase
{
    public MyProgressIndicator() {
        this.addStateDelegate(new AbstractProgressIndicatorExBase() {
            @Override
            public void cancel() {
                super.cancel();
                MyProgressIndicator.this.c();
            }
        });
    }
    
    @Override
    public void start() {
        super.start();
        UIUtil.invokeAndWaitIfNeeded(() -> {
            ImportCMakeProjectStepAdapter.access$500(ImportCMakeProjectStepAdapter.this).setIndeterminate(true);
            ImportCMakeProjectStepAdapter.access$600(ImportCMakeProjectStepAdapter.this).setVisible(true);
            ImportCMakeProjectStepAdapter.access$700(ImportCMakeProjectStepAdapter.this).setVisible(false);
        });
    }
    
    private void c() {
        UIUtil.invokeAndWaitIfNeeded(() -> {
            ImportCMakeProjectStepAdapter.access$500(ImportCMakeProjectStepAdapter.this).setIndeterminate(false);
            ImportCMakeProjectStepAdapter.access$600(ImportCMakeProjectStepAdapter.this).setVisible(false);
            ImportCMakeProjectStepAdapter.access$700(ImportCMakeProjectStepAdapter.this).setVisible(true);
        });
    }
    
    @Override
    public void stop() {
        super.stop();
        this.c();
    }
    
    @Override
    public void finish(@NotNull final TaskInfo taskInfo) {
        try {
            if (taskInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "task", "com/jetbrains/cidr/cpp/cmake/projectWizard/ImportCMakeProjectStepAdapter$MyProgressIndicator", "finish"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        super.finish(taskInfo);
        this.c();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
