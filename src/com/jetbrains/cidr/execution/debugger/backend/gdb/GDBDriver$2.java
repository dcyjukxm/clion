// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.intellij.execution.ExecutionException;
import java.util.Iterator;

class GDBDriver$2 implements VoidCommand {
    @Override
    public void run() throws ExecutionException {
        final Iterator<GDBResponse.Record> iterator = GDBDriver.access$100(GDBDriver.this).iterator();
        while (iterator.hasNext()) {
            GDBDriver.access$200(GDBDriver.this, iterator.next());
        }
        GDBDriver.access$100(GDBDriver.this).clear();
    }
}