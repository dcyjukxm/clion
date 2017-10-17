// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.openapi.fileTypes.FileType;
import java.util.Set;

public interface CidrLineBreakpointFileTypesProvider
{
    Set<FileType> getFileTypes();
}
