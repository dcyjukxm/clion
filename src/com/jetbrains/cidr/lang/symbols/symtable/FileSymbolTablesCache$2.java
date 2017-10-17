// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceModificationListener;

class FileSymbolTablesCache$2 implements OCWorkspaceModificationListener {
    @Override
    public void buildSettingsChanged() {
        OCSymbolTablesBuildingActivity.getInstance(FileSymbolTablesCache.access$100(FileSymbolTablesCache.this)).rebuildSymbols();
    }
    
    @Override
    public void projectFilesChanged() {
        OCInclusionContextUtil.invalidateHeaderRootAndActiveConfigurationForAllExcept(null, FileSymbolTablesCache.access$200(FileSymbolTablesCache.this));
    }
    
    @Override
    public void sourceFilesChanged() {
        OCInclusionContextUtil.invalidateHeaderRootAndActiveConfigurationForAllExcept(null, FileSymbolTablesCache.access$300(FileSymbolTablesCache.this));
    }
    
    @Override
    public void selectedResolveConfigurationChanged() {
        OCInclusionContextUtil.invalidateHeaderRootAndActiveConfigurationForAllExcept(null, FileSymbolTablesCache.access$400(FileSymbolTablesCache.this));
        FileSymbolTablesCache.access$500(FileSymbolTablesCache.this);
    }
}