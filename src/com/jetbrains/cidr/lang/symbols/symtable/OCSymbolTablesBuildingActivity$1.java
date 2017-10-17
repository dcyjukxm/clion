// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.UserDataHolder;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.util.Processor;
import com.intellij.openapi.project.DumbModeTask;

class OCSymbolTablesBuildingActivity$1 extends DumbModeTask {
    final /* synthetic */ String val$activityName;
    final /* synthetic */ Processor val$activity;
    
    public void performInDumbMode(@NotNull final ProgressIndicator progressIndicator) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$1", "performInDumbMode"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        final MyProgressIndicator myProgressIndicator = new MyProgressIndicator(progressIndicator);
        final OCSymbolTablesBuildingActivity$1.1MyDisposable 1MyDisposable = new OCSymbolTablesBuildingActivity$1.1MyDisposable(myProgressIndicator);
        try {
            final List list = (List)OCSymbolTablesBuildingActivity.ACTIVITY_LOG.getValue((UserDataHolder)OCSymbolTablesBuildingActivity.access$1000(OCSymbolTablesBuildingActivity.this));
            final long currentTimeMillis = System.currentTimeMillis();
            synchronized (list) {
                list.add(this.val$activityName + " " + currentTimeMillis + ": START");
            }
            Disposer.register((Disposable)OCSymbolTablesBuildingActivity.access$1000(OCSymbolTablesBuildingActivity.this), (Disposable)1MyDisposable);
            this.val$activity.process((Object)myProgressIndicator);
            1MyDisposable.shouldCancel = false;
            synchronized (list) {
                list.add(this.val$activityName + " " + currentTimeMillis + ": END after " + (System.currentTimeMillis() - currentTimeMillis));
            }
        }
        catch (Throwable t3) {
            try {
                if (OCSymbolTablesBuildingActivity.access$1000(OCSymbolTablesBuildingActivity.this).isDisposed() || !OCSymbolTablesBuildingActivity.access$1000(OCSymbolTablesBuildingActivity.this).isOpen()) {
                    return;
                }
            }
            catch (Throwable t2) {
                throw a(t2);
            }
            final boolean b = t3 instanceof ProcessCanceledException;
            Logger log = null;
            String string = null;
            Throwable t4 = null;
            Label_0348: {
                try {
                    log = OCLog.LOG;
                    string = "Unexpected exception during symbol building (" + this.val$activityName + ")";
                    if (b) {
                        t4 = new RuntimeException(t3);
                        break Label_0348;
                    }
                }
                catch (Throwable t5) {
                    throw a(t5);
                }
                t4 = t3;
            }
            log.error(string, t4);
        }
        finally {
            Disposer.dispose((Disposable)1MyDisposable);
        }
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}