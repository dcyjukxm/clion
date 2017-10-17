// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import gnu.trove.TObjectHashingStrategy;
import com.intellij.util.containers.hash.EqualityPolicy;

class CMakeSettingsFileParser$2 implements EqualityPolicy<String> {
    final /* synthetic */ TObjectHashingStrategy val$strategy;
    
    public int getHashCode(final String s) {
        return this.val$strategy.computeHashCode((Object)s);
    }
    
    public boolean isEqual(final String s, final String s2) {
        return this.val$strategy.equals((Object)s, (Object)s2);
    }
}