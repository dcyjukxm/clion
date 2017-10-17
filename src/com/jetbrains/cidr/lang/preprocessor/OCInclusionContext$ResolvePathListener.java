// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;

public interface ResolvePathListener
{
    void resolve(@NotNull final OCIncludeSymbol.IncludePath p0, final boolean p1, @Nullable final VirtualFile p2);
}
