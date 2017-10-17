// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.build;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.ui.content.MessageView;
import com.intellij.openapi.progress.EmptyProgressIndicator;
import java.io.OutputStream;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.ui.InputValidator;
import javax.swing.Icon;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.actionSystem.AnAction;

public abstract class ShowBuildConsoleInternalAction extends AnAction implements DumbAware
{
    public void actionPerformed(final AnActionEvent anActionEvent) {
        final Project project = (Project)anActionEvent.getData(CommonDataKeys.PROJECT);
        final String showMultilineInputDialog = Messages.showMultilineInputDialog(project, "", "Enter text", "", (Icon)null, (InputValidator)null);
        if (showMultilineInputDialog == null) {
            return;
        }
        final ProcessHandler processHandler = new ProcessHandler() {
            protected void destroyProcessImpl() {
            }
            
            protected void detachProcessImpl() {
            }
            
            public boolean detachIsDefault() {
                return false;
            }
            
            public OutputStream getProcessInput() {
                return null;
            }
        };
        final CidrBuild.BuildContext buildContext = new CidrBuild.BuildContext(project);
        buildContext.processHandler = processHandler;
        buildContext.indicator = (ProgressIndicator)new EmptyProgressIndicator();
        MessageView.SERVICE.getInstance(project);
        this.createConsole(project, buildContext);
        processHandler.notifyTextAvailable(showMultilineInputDialog, ProcessOutputTypes.STDOUT);
        ToolWindowManager.getInstance(project).getToolWindow(ToolWindowId.MESSAGES_WINDOW).show((Runnable)null);
    }
    
    protected abstract void createConsole(final Project p0, final CidrBuild.BuildContext p1);
}
