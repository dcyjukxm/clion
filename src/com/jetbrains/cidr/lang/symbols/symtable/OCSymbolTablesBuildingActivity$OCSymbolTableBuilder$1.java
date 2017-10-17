// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.preprocessor.OCImportGraph;
import com.intellij.util.Consumer;
import java.util.Collection;
import com.intellij.util.Producer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

class OCSymbolTablesBuildingActivity$OCSymbolTableBuilder$1 implements TaskProvider<OCBuildFileDescriptor> {
    final /* synthetic */ ArrayList val$clusterProviders;
    final /* synthetic */ a val$remainingProvider;
    
    @Override
    public Producer<OCBuildFileDescriptor> getItemProvider() {
        return (Producer<OCBuildFileDescriptor>)new PrioritizedBuildFileProvider((Collection)this.val$clusterProviders, this.val$remainingProvider);
    }
    
    @Override
    public Consumer<OCBuildFileDescriptor> getWorker() {
        return (Consumer<OCBuildFileDescriptor>)(ocBuildFileDescriptor -> {
            OCImportGraph.buildSymbolAndRootHeaderCache(ocBuildFileDescriptor.myConfiguration, ocBuildFileDescriptor.myFile, ocBuildFileDescriptor.myLanguageKind, OCSymbolTableBuilder.access$500(OCSymbolTableBuilder.this));
            OCSymbolTableBuilder.access$500(OCSymbolTableBuilder.this).setFraction(atomicInteger.incrementAndGet() / n);
        });
    }
}