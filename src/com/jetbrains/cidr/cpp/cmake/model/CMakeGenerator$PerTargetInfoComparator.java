// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.io.FileUtil;
import java.io.File;
import java.util.Comparator;

private class PerTargetInfoComparator implements Comparator<PerConfigurationTargetInfo>
{
    final File mainCMakeLists;
    
    private PerTargetInfoComparator() {
        this.mainCMakeLists = CMakeGenerator.access$300(CMakeGenerator.access$200(CMakeGenerator.this));
    }
    
    @Override
    public int compare(final PerConfigurationTargetInfo perConfigurationTargetInfo, final PerConfigurationTargetInfo perConfigurationTargetInfo2) {
        final int compare = Comparing.compare(FileUtil.filesEqual(this.mainCMakeLists, perConfigurationTargetInfo.cmakeListsFile), FileUtil.filesEqual(this.mainCMakeLists, perConfigurationTargetInfo2.cmakeListsFile));
        if (compare != 0) {
            return -compare;
        }
        if (FileUtil.filesEqual(perConfigurationTargetInfo.cmakeListsFile, perConfigurationTargetInfo2.cmakeListsFile)) {
            return StringUtil.compare(perConfigurationTargetInfo.targetName, perConfigurationTargetInfo2.targetName, true);
        }
        final int compare2 = Comparing.compare(FileUtil.isAncestor(this.mainCMakeLists.getParentFile(), perConfigurationTargetInfo.cmakeListsFile.getParentFile(), true), FileUtil.isAncestor(this.mainCMakeLists.getParentFile(), perConfigurationTargetInfo2.cmakeListsFile.getParentFile(), true));
        if (compare2 != 0) {
            return -compare2;
        }
        if (FileUtil.isAncestor(perConfigurationTargetInfo.cmakeListsFile.getParentFile(), perConfigurationTargetInfo2.cmakeListsFile.getParentFile(), true)) {
            return -1;
        }
        if (FileUtil.isAncestor(perConfigurationTargetInfo2.cmakeListsFile.getParentFile(), perConfigurationTargetInfo.cmakeListsFile.getParentFile(), true)) {
            return 1;
        }
        return StringUtil.compare(perConfigurationTargetInfo.cmakeListsFile.getPath(), perConfigurationTargetInfo2.cmakeListsFile.getPath(), true);
    }
}
