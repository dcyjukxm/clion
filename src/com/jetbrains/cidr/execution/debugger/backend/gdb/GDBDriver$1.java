// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.pty4j.unix.PTYOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class GDBDriver$1 extends OutputStream {
    @Override
    public void write(final int n) throws IOException {
        final PTYOutputStream access$000 = GDBDriver.access$000(GDBDriver.this);
        try {
            if (access$000 != null) {
                access$000.write(n);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        final PTYOutputStream access$000 = GDBDriver.access$000(GDBDriver.this);
        try {
            if (access$000 != null) {
                access$000.write(array, n, n2);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public void close() throws IOException {
        final PTYOutputStream access$000 = GDBDriver.access$000(GDBDriver.this);
        try {
            if (access$000 != null) {
                access$000.close();
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
}