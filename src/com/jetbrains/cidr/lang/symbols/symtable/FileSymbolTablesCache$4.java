// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import java.util.Set;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.progress.ProgressIndicator;
import java.io.IOException;
import com.intellij.openapi.util.ThrowableComputable;

class FileSymbolTablesCache$4 implements ThrowableComputable<FileSymbolTablesPack, IOException> {
    final /* synthetic */ ProgressIndicator val$indicator;
    final /* synthetic */ VirtualFile val$file;
    final /* synthetic */ FileSymbolTableSerializer val$serializer;
    final /* synthetic */ String val$projectLocationHash;
    final /* synthetic */ Set val$dirtySet;
    
    public FileSymbolTablesPack compute() throws IOException {
        try {
            this.val$indicator.checkCanceled();
            if (!FileSymbolTablesCache.access$1300(this.val$file)) {
                return null;
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        final FileSymbolTablesPack access$1400 = FileSymbolTablesCache.access$1400(this.val$serializer, this.val$projectLocationHash, this.val$file);
        try {
            if (access$1400 == null) {
                this.val$dirtySet.add(this.val$file);
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        return access$1400;
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
}