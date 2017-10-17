// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.util.containers.hash.EqualityPolicy;
import com.intellij.util.containers.LinkedMultiMap;

class CMakeSettingsFileParser$1 extends LinkedMultiMap<String, T> {
    protected EqualityPolicy<String> getEqualityPolicy() {
        return (EqualityPolicy<String>)CMakeSettingsFileParser.access$000(CMakeSettingsFileParser.this);
    }
}