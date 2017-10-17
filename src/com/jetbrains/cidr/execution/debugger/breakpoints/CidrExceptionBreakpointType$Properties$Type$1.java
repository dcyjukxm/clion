// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.util.PlatformUtils;

enum CidrExceptionBreakpointType$Properties$Type$1
{
    @Override
    public boolean isAvailable() {
        return PlatformUtils.isAppCode() || PlatformUtils.isRubyMine();
    }
}