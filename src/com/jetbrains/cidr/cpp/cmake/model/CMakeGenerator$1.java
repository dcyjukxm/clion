// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import java.util.List;
import com.intellij.openapi.util.Pair;
import java.util.Comparator;

class CMakeGenerator$1 implements Comparator<Pair<PerConfigurationTargetInfo, List<CMakeConfiguration>>> {
    final PerTargetInfoComparator myBaseComparator = new PerTargetInfoComparator();
    
    @Override
    public int compare(final Pair<PerConfigurationTargetInfo, List<CMakeConfiguration>> pair, final Pair<PerConfigurationTargetInfo, List<CMakeConfiguration>> pair2) {
        return this.myBaseComparator.compare((PerConfigurationTargetInfo)pair.first, (PerConfigurationTargetInfo)pair2.first);
    }
}