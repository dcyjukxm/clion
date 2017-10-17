// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.pointers.VirtualFilePointer;
import gnu.trove.THashMap;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/project/CidrRootConfiguration$State;", "", "()V", "roots", "Lgnu/trove/THashMap;", "Lcom/intellij/openapi/vfs/pointers/VirtualFilePointer;", "Lcom/jetbrains/cidr/project/CidrRootConfiguration$RootType;", "getRoots", "()Lgnu/trove/THashMap;", "cidr-common" })
private static final class State
{
    @NotNull
    private final THashMap<VirtualFilePointer, RootType> roots;
    
    @NotNull
    public final THashMap<VirtualFilePointer, RootType> getRoots() {
        return this.roots;
    }
    
    public State() {
        this.roots = (THashMap<VirtualFilePointer, RootType>)new THashMap();
    }
}
