// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugProcessStarter;
import com.intellij.xdebugger.XDebuggerManager;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebugSessionListener;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessInfo;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import com.intellij.xdebugger.attach.XLocalAttachDebugger;

public class CidrLocalAttachDebugger implements XLocalAttachDebugger
{
    @NotNull
    private final DebuggerDriverConfiguration myConfiguration;
    
    public CidrLocalAttachDebugger(@NotNull final DebuggerDriverConfiguration myConfiguration) {
        if (myConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger", "<init>"));
        }
        this.myConfiguration = myConfiguration;
    }
    
    @NotNull
    @Override
    public String getDebuggerDisplayName() {
        String driverName;
        try {
            driverName = this.myConfiguration.getDriverName();
            if (driverName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger", "getDebuggerDisplayName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return driverName;
    }
    
    @NotNull
    public DebuggerDriverConfiguration getConfiguration() {
        DebuggerDriverConfiguration myConfiguration;
        try {
            myConfiguration = this.myConfiguration;
            if (myConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger", "getConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myConfiguration;
    }
    
    @Override
    public void attachDebugSession(@NotNull final Project project, @NotNull final ProcessInfo processInfo) throws ExecutionException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger", "attachDebugSession"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (processInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processInfo", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger", "attachDebugSession"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        this.doAttachDebugSession(project, processInfo, new XDebugSessionListener[0]);
    }
    
    @NotNull
    public XDebugSession doAttachDebugSession(@NotNull final Project project, @NotNull final ProcessInfo processInfo, @NotNull final XDebugSessionListener... array) throws ExecutionException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger", "doAttachDebugSession"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (processInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processInfo", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger", "doAttachDebugSession"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "listeners", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger", "doAttachDebugSession"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        XDebugSession startSessionAndShowTab;
        try {
            startSessionAndShowTab = XDebuggerManager.getInstance(project).startSessionAndShowTab(processInfo.getExecutableDisplayName(), (RunContentDescriptor)null, (XDebugProcessStarter)new XDebugProcessStarter() {
                @NotNull
                public XDebugProcess start(@NotNull final XDebugSession xDebugSession) throws ExecutionException {
                    try {
                        if (xDebugSession == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger$1", "start"));
                        }
                    }
                    catch (ExecutionException ex) {
                        throw b(ex);
                    }
                    final XDebugSessionListener[] val$listeners = array;
                    for (int length = val$listeners.length, i = 0; i < length; ++i) {
                        xDebugSession.addSessionListener(val$listeners[i]);
                    }
                    final CidrLocalAttachedDebugProcess cidrLocalAttachedDebugProcess = new CidrLocalAttachedDebugProcess(CidrLocalAttachDebugger.this.myConfiguration, processInfo, xDebugSession, TextConsoleBuilderFactory.getInstance().createBuilder(project));
                    CidrLocalAttachedDebugProcess cidrLocalAttachedDebugProcess2;
                    try {
                        cidrLocalAttachedDebugProcess.start();
                        cidrLocalAttachedDebugProcess2 = cidrLocalAttachedDebugProcess;
                        if (cidrLocalAttachedDebugProcess2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger$1", "start"));
                        }
                    }
                    catch (ExecutionException ex2) {
                        throw b(ex2);
                    }
                    return cidrLocalAttachedDebugProcess2;
                }
                
                private static ExecutionException b(final ExecutionException ex) {
                    return ex;
                }
            });
            if (startSessionAndShowTab == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachDebugger", "doAttachDebugSession"));
            }
        }
        catch (ExecutionException ex4) {
            throw b((Exception)ex4);
        }
        return startSessionAndShowTab;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
