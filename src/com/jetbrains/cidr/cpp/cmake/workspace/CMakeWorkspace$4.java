// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.ui.content.ContentManager;
import com.intellij.openapi.Disposable;

class CMakeWorkspace$4 implements Disposable {
    final /* synthetic */ ContentManager val$cm;
    
    public void dispose() {
        this.val$cm.removeAllContents(true);
    }
}