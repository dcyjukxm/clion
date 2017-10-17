// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.asm.AsmFileType;
import com.jetbrains.cidr.lang.OCFileType;
import com.intellij.openapi.fileTypes.FileType;
import java.util.Set;

public class OCLineBreakpointFileTypesProvider implements CidrLineBreakpointFileTypesProvider
{
    private static final Set<FileType> TYPES;
    
    @Override
    public Set<FileType> getFileTypes() {
        return OCLineBreakpointFileTypesProvider.TYPES;
    }
    
    static {
        TYPES = ContainerUtil.immutableSet((Object[])new FileType[] { OCFileType.INSTANCE, AsmFileType.INSTANCE });
    }
}
