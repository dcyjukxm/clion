// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.application.ApplicationAdapter;

static final class OCSymbolTablesBuildingActivity$4 extends ApplicationAdapter {
    final /* synthetic */ ProgressIndicator val$localProgress;
    
    public void beforeWriteActionStart(@NotNull final Object o) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$4", "beforeWriteActionStart"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.val$localProgress.cancel();
    }
    
    public void writeActionFinished(@NotNull final Object o) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$4", "writeActionFinished"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.val$localProgress.isRunning()) {
                this.val$localProgress.stop();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.val$localProgress.start();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}