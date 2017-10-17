// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.symtable.SymbolTableProvider;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationTrackers;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.psi.util.CachedValueProvider;

static final class OCInclusionContextUtil$1 implements CachedValueProvider<OCResolveConfiguration> {
    final /* synthetic */ Project val$project;
    final /* synthetic */ PsiFile val$file;
    
    public CachedValueProvider.Result<OCResolveConfiguration> compute() {
        if (this.val$project.isDefault()) {
            return (CachedValueProvider.Result<OCResolveConfiguration>)new CachedValueProvider.Result((Object)null, new Object[] { ModificationTracker.NEVER_CHANGED });
        }
        final OCWorkspaceModificationTrackers instance = OCWorkspaceModificationTrackers.getInstance(this.val$project);
        return (CachedValueProvider.Result<OCResolveConfiguration>)new CachedValueProvider.Result((Object)this.a(), new Object[] { FileSymbolTablesCache.getInstance(this.val$project).getOutOfBlockModificationTracker(), instance.getProjectsListTracker(), instance.getProjectFilesListTracker(), instance.getSourceFilesListTracker(), instance.getSelectedResolveConfigurationTracker() });
    }
    
    private OCResolveConfiguration a() {
        if (SymbolTableProvider.isSourceFile(this.val$file)) {
            final VirtualFile virtualFile = OCInclusionContextUtil.getVirtualFile(this.val$file.getOriginalFile());
            if (virtualFile != null) {
                return OCInclusionContextUtil.getResolveRootAndActiveConfiguration(virtualFile, this.val$file.getProject()).getConfiguration();
            }
        }
        return null;
    }
}