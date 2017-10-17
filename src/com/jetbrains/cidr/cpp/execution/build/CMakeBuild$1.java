// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.build;

import com.intellij.openapi.ui.Messages;
import com.jetbrains.cidr.CidrBundle;
import com.intellij.ui.content.ContentManagerListener;
import com.intellij.ui.content.ContentManagerEvent;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.build.CidrBuild;
import com.intellij.ui.content.ContentManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManagerAdapter;

static final class CMakeBuild$1 extends ContentManagerAdapter {
    final /* synthetic */ Content val$content;
    final /* synthetic */ ContentManager val$contentManager;
    final /* synthetic */ CidrBuild.BuildContext val$context;
    final /* synthetic */ Project val$project;
    
    public void contentRemoved(final ContentManagerEvent contentManagerEvent) {
        if (contentManagerEvent.getContent() != this.val$content) {
            return;
        }
        this.val$contentManager.removeContentManagerListener((ContentManagerListener)this);
    }
    
    public void contentRemoveQuery(final ContentManagerEvent contentManagerEvent) {
        if (contentManagerEvent.getContent() != this.val$content) {
            return;
        }
        if (!this.val$context.indicator.isCanceled() && !this.val$context.processHandler.isProcessTerminated() && !this.val$context.processHandler.isProcessTerminating()) {
            if (Messages.showYesNoDialog(this.val$project, CidrBundle.message("build.inProgress.cancelQuestion", new Object[0]), CidrBundle.message("build.inProgress", new Object[0]), Messages.getQuestionIcon()) == 0) {
                this.val$context.indicator.cancel();
            }
            else {
                contentManagerEvent.consume();
            }
        }
    }
}