// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.xdebugger.breakpoints.XBreakpoint;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import com.jetbrains.cidr.execution.debugger.backend.LLCodepoint;
import java.util.List;
import com.intellij.util.containers.FactoryMap;

class CidrCodePointHandlerBase$1 extends FactoryMap<T, List<LLCodepoint>> {
    @Nullable
    protected List<LLCodepoint> create(final T t) {
        return new ArrayList<LLCodepoint>();
    }
}