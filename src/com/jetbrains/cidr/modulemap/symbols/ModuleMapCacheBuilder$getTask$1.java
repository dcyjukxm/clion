// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.Consumer;
import com.intellij.util.Producer;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.jetbrains.cidr.lang.symbols.symtable.OCSymbolTablesBuildingActivity;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0005H\u0016J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0016¨\u0006\b" }, d2 = { "com/jetbrains/cidr/modulemap/symbols/ModuleMapCacheBuilder$getTask$1", "Lcom/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$TaskProvider;", "Lcom/jetbrains/cidr/lang/workspace/headerRoots/HeadersSearchRoot;", "(Lcom/intellij/util/Producer;Lcom/intellij/util/Consumer;)V", "getItemProvider", "Lcom/intellij/util/Producer;", "getWorker", "Lcom/intellij/util/Consumer;", "cidr-lang" })
public static final class ModuleMapCacheBuilder$getTask$1 implements TaskProvider<HeadersSearchRoot> {
    @NotNull
    @Override
    public Producer<HeadersSearchRoot> getItemProvider() {
        return (Producer<HeadersSearchRoot>)this.$producer;
    }
    
    @NotNull
    @Override
    public Consumer<HeadersSearchRoot> getWorker() {
        return (Consumer<HeadersSearchRoot>)this.$consumer;
    }
}