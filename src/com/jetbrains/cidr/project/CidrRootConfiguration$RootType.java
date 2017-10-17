// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n" }, d2 = { "Lcom/jetbrains/cidr/project/CidrRootConfiguration$RootType;", "", "elementName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getElementName", "()Ljava/lang/String;", "SOURCE", "LIBRARY", "EXCLUDED", "cidr-common" })
public enum RootType
{
    SOURCE("sourceRoots"), 
    LIBRARY("libraryRoots"), 
    EXCLUDED("excludeRoots");
    
    @NotNull
    private final String elementName;
    
    @NotNull
    public final String getElementName() {
        return this.elementName;
    }
    
    protected RootType(final String elementName) {
        Intrinsics.checkParameterIsNotNull((Object)elementName, "elementName");
        super(s, n);
        this.elementName = elementName;
    }
}
