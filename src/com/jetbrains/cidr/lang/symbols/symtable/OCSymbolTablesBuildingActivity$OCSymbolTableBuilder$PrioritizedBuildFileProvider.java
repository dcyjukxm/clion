// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import java.util.Iterator;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.intellij.util.Producer;

private static class PrioritizedBuildFileProvider implements a
{
    private boolean myUseClusters;
    private Producer<OCBuildFileDescriptor> myCurrentProvider;
    @NotNull
    private final Collection<a> myProviders;
    @NotNull
    private final a myBottomProvider;
    
    private PrioritizedBuildFileProvider(@NotNull final Collection<a> myProviders, @NotNull final a myBottomProvider) {
        if (myProviders == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "providers", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder$PrioritizedBuildFileProvider", "<init>"));
        }
        if (myBottomProvider == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "bottomProvider", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTablesBuildingActivity$OCSymbolTableBuilder$PrioritizedBuildFileProvider", "<init>"));
        }
        this.myUseClusters = true;
        this.myProviders = myProviders;
        this.myBottomProvider = myBottomProvider;
    }
    
    @Nullable
    public OCBuildFileDescriptor produce() {
        final OCBuildFileDescriptor a = this.a();
        try {
            if (a != null) {
                return a;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (OCBuildFileDescriptor)this.myBottomProvider.produce();
    }
    
    private OCBuildFileDescriptor a() {
        try {
            if (!this.myUseClusters) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        while (true) {
            if (this.myCurrentProvider != null) {
                final Object myCurrentProvider = this.myCurrentProvider.produce();
                try {
                    if (myCurrentProvider != null) {
                        return (OCBuildFileDescriptor)myCurrentProvider;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            Object myCurrentProvider;
            synchronized (this.myProviders) {
                final Iterator<a> iterator = this.myProviders.iterator();
                if (!iterator.hasNext()) {
                    break;
                }
                myCurrentProvider = iterator.next();
                iterator.remove();
            }
            this.myCurrentProvider = (Producer<OCBuildFileDescriptor>)myCurrentProvider;
        }
        this.myUseClusters = false;
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
