// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.ui.content.Content;
import com.intellij.util.ui.UIUtil;
import com.intellij.xdebugger.XDebugSessionListener;
import com.intellij.execution.console.LanguageConsoleView;
import com.intellij.openapi.util.Condition;
import com.intellij.util.Consumer;
import com.intellij.execution.console.LanguageConsoleBuilder;
import com.intellij.execution.ui.layout.PlaceInGrid;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import javax.swing.JComponent;
import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.ui.RunnerLayoutUi;
import com.intellij.xdebugger.ui.XDebugTabLayouter;

class CidrDebugProcess$3 extends XDebugTabLayouter {
    public void registerAdditionalContent(@NotNull final RunnerLayoutUi runnerLayoutUi) {
        try {
            if (runnerLayoutUi == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ui", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$3", "registerAdditionalContent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Content content = runnerLayoutUi.createContent("DEBUGGER_BACKEND_CONSOLE", CidrDebugProcess.access$300(CidrDebugProcess.this).getComponent(), CidrDebugProcess.access$400(CidrDebugProcess.this), AllIcons.Debugger.ToolConsole, (JComponent)null);
        Disposer.register((Disposable)runnerLayoutUi.getContentManager(), (Disposable)CidrDebugProcess.access$300(CidrDebugProcess.this));
        content.setCloseable(false);
        runnerLayoutUi.addContent(content, 0, PlaceInGrid.center, false);
        final BackendConsoleInjectionHelper[] array = (BackendConsoleInjectionHelper[])BackendConsoleInjectionHelper.EP_NAME.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].subscribeToInjection(CidrDebugProcess.this.getSession());
        }
        LanguageConsoleBuilder.registerExecuteAction(CidrDebugProcess.access$300(CidrDebugProcess.this), (Consumer<String>)(s -> CidrDebugProcess.this.executeConsoleCommand(s)), "AppCode.Debug.Console", null, null);
        CidrDebugProcess.this.getSession().addSessionListener((XDebugSessionListener)new XDebugSessionListener() {
            public void sessionPaused() {
                this.b(true);
            }
            
            public void sessionResumed() {
                this.b(false);
            }
            
            public void sessionStopped() {
                this.b(false);
            }
            
            private void b(final boolean editable) {
                UIUtil.invokeAndWaitIfNeeded(() -> {
                    if (!CidrDebugProcess.this.getProject().isDisposed() && !Disposer.isDisposed((Disposable)CidrDebugProcess.access$300(CidrDebugProcess.this))) {
                        CidrDebugProcess.access$300(CidrDebugProcess.this).setEditable(editable);
                    }
                });
            }
        });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}