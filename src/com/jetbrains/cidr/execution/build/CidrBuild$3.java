// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.build;

import com.jetbrains.cidr.lang.OCLog;
import java.io.IOException;
import com.intellij.openapi.actionSystem.AnActionEvent;
import javax.swing.Icon;
import java.io.File;
import com.intellij.openapi.actionSystem.AnAction;

static final class CidrBuild$3 extends AnAction {
    final /* synthetic */ File val$finalFile;
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        try {
            if (this.val$finalFile == null) {
                return;
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            Runtime.getRuntime().exec("open " + this.val$finalFile.getPath());
        }
        catch (IOException ex2) {
            OCLog.LOG.error("Cannot open build log file: " + this.val$finalFile, (Throwable)ex2);
        }
    }
    
    public boolean isDumbAware() {
        return true;
    }
    
    public void update(final AnActionEvent anActionEvent) {
        super.update(anActionEvent);
        anActionEvent.getPresentation().setEnabled(this.val$finalFile != null);
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
}