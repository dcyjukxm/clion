// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.Set;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCType;

private static class ResultInfo
{
    final OCType resolvedType;
    final Collection<VirtualFile> usedFiles;
    final Set<OCTypeParameterSymbol> typeDependencies;
    
    private ResultInfo(final OCType resolvedType, final Collection<VirtualFile> usedFiles, final Set<OCTypeParameterSymbol> typeDependencies) {
        this.resolvedType = resolvedType;
        this.usedFiles = usedFiles;
        this.typeDependencies = typeDependencies;
    }
}
