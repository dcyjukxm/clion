// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.intellij.openapi.diagnostic.Logger;
import java.util.Iterator;
import java.util.regex.Pattern;
import com.jetbrains.cidr.execution.testing.CidrTestLog;
import com.jetbrains.cidr.lang.ui.OCFieldAdapter;
import com.jetbrains.cidr.CidrBundle;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import java.util.Map;
import com.intellij.openapi.util.Couple;
import com.jetbrains.cidr.lang.ui.OCFieldAdapterForSymbolName;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Condition;
import com.google.common.collect.Maps;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.NavigableMap;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfigurationEditorWithLWValidation;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.openapi.progress.util.ProgressIndicatorUtils;
import com.jetbrains.cidr.execution.CidrBuildConfigurationHelper;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import com.jetbrains.cidr.execution.CidrBuildConfiguration;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.progress.ProcessCanceledException;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.progress.util.ReadTask;

class CidrGoogleTestRunConfigurationEditor$1 extends ReadTask {
    final /* synthetic */ Computable val$action;
    final /* synthetic */ Project val$project;
    final /* synthetic */ String val$suiteName;
    final /* synthetic */ CidrGoogleTestRunConfigurationEditor this$0;
    
    @Override
    public Continuation performInReadAction(@NotNull final ProgressIndicator progressIndicator) throws ProcessCanceledException {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor$1", "performInReadAction"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw a(ex);
        }
        return new Continuation((Runnable)this.val$action.compute(), ModalityState.any());
    }
    
    @Override
    public void onCanceled(@NotNull final ProgressIndicator progressIndicator) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestRunConfigurationEditor$1", "onCanceled"));
            }
        }
        catch (ProcessCanceledException ex) {
            throw a(ex);
        }
        Label_0077: {
            try {
                if (this.val$project.isDisposed()) {
                    return;
                }
                final ReadTask readTask = this;
                final CidrGoogleTestRunConfigurationEditor cidrGoogleTestRunConfigurationEditor = readTask.this$0;
                final ReadTask readTask2 = this;
                final String s = readTask2.val$suiteName;
                final OCStructSymbol ocStructSymbol = cidrGoogleTestRunConfigurationEditor.c(s);
                if (ocStructSymbol == null) {
                    break Label_0077;
                }
                return;
            }
            catch (ProcessCanceledException ex2) {
                throw a(ex2);
            }
            try {
                final ReadTask readTask = this;
                final CidrGoogleTestRunConfigurationEditor cidrGoogleTestRunConfigurationEditor = readTask.this$0;
                final ReadTask readTask2 = this;
                final String s = readTask2.val$suiteName;
                final OCStructSymbol ocStructSymbol = cidrGoogleTestRunConfigurationEditor.c(s);
                if (ocStructSymbol == null) {
                    ProgressIndicatorUtils.scheduleWithWriteActionPriority(this);
                }
            }
            catch (ProcessCanceledException ex3) {
                throw a(ex3);
            }
        }
    }
    
    private static ProcessCanceledException a(final ProcessCanceledException ex) {
        return ex;
    }
}