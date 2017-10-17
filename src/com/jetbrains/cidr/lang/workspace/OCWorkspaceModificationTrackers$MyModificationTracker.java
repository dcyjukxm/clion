// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import com.intellij.psi.PsiManager;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.DefaultModificationTracker;

private class MyModificationTracker extends DefaultModificationTracker
{
    @Nullable
    private final Runnable myPublisher;
    
    private MyModificationTracker(final Runnable myPublisher) {
        this.myPublisher = myPublisher;
    }
    
    public void incModificationCount() {
        super.incModificationCount();
        if (this.myPublisher != null) {
            this.myPublisher.run();
        }
        ApplicationManager.getApplication().invokeLater(() -> PsiManager.getInstance(OCWorkspaceModificationTrackers.access$100(OCWorkspaceModificationTrackers.this)).dropPsiCaches(), OCWorkspaceModificationTrackers.access$100(OCWorkspaceModificationTrackers.this).getDisposed());
    }
}
