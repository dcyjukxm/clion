// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import com.intellij.openapi.util.Computable;

class OCGlobalProjectSymbolsCache$1 implements Computable<Collection<VirtualFile>> {
    final /* synthetic */ FileSymbolTablesCache val$cache;
    
    public Collection<VirtualFile> compute() {
        return this.val$cache.getFilesToBuildTablesFor();
    }
}