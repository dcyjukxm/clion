// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.vfs.VirtualFile;
import java.util.Iterator;
import com.intellij.openapi.vfs.newvfs.events.VFileCreateEvent;
import java.util.Set;
import com.intellij.openapi.vfs.newvfs.events.VFileDeleteEvent;
import com.intellij.util.containers.HashSet;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import java.util.List;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;

class FileSymbolTablesCache$3 implements BulkFileListener {
    public void after(@NotNull final List<? extends VFileEvent> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "events", "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache$3", "after"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final HashSet set = new HashSet();
        for (final VFileEvent vFileEvent : list) {
            try {
                if (vFileEvent instanceof VFileDeleteEvent) {
                    FileSymbolTablesCache.access$600(FileSymbolTablesCache.this, vFileEvent.getFile(), (Set)set);
                    continue;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            if (vFileEvent instanceof VFileCreateEvent) {
                final VirtualFile file = vFileEvent.getFile();
                Label_0142: {
                    try {
                        if (file == null) {
                            continue;
                        }
                        final VirtualFile virtualFile = file;
                        final boolean b = SymbolTableProvider.isSourceFile(virtualFile);
                        if (b) {
                            break Label_0142;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final VirtualFile virtualFile = file;
                        final boolean b = SymbolTableProvider.isSourceFile(virtualFile);
                        if (!b) {
                            continue;
                        }
                        set.add((Object)file.getName());
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
            }
        }
        try {
            if (!set.isEmpty()) {
                FileSymbolTablesCache.this.invalidateDirtyIncludes((Set<String>)set);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}