// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.application.ApplicationListener;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.ex.ApplicationEx;
import java.util.concurrent.atomic.AtomicBoolean;
import com.intellij.openapi.application.ApplicationAdapter;

class OCSymbolTablesBuildingActivity$2 extends ApplicationAdapter {
    AtomicBoolean isHandled = new AtomicBoolean();
    final /* synthetic */ ApplicationEx val$app;
    final /* synthetic */ Runnable val$r;
    
    public void afterWriteActionFinished(@NotNull final Object o) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$2", "afterWriteActionFinished"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!this.isHandled.getAndSet(true)) {
                this.val$app.removeApplicationListener((ApplicationListener)this);
                this.val$r.run();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}