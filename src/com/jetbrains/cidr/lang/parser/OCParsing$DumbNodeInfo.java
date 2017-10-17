// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.lang.PsiBuilder;

private static class DumbNodeInfo
{
    final PsiBuilder.Marker marker;
    final int childDepth;
    int childCount;
    
    public DumbNodeInfo(final PsiBuilder.Marker marker, final int childDepth) {
        this.marker = marker;
        this.childDepth = childDepth;
    }
}
