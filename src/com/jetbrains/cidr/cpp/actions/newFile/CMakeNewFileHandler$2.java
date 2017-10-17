// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import com.intellij.openapi.util.Pair;
import java.util.ArrayList;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import gnu.trove.TObjectIntProcedure;

class CMakeNewFileHandler$2 implements TObjectIntProcedure<CMakeTarget> {
    final /* synthetic */ ArrayList val$cts;
    
    public boolean execute(final CMakeTarget cMakeTarget, final int n) {
        this.val$cts.add(Pair.create((Object)cMakeTarget, (Object)n));
        return true;
    }
}