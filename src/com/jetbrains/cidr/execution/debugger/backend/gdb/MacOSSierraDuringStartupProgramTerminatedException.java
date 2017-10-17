// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.options.ShowSettingsUtil;
import javax.swing.event.HyperlinkEvent;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.PlatformUtils;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import javax.swing.event.HyperlinkListener;
import com.intellij.execution.ExecutionException;

class MacOSSierraDuringStartupProgramTerminatedException extends ExecutionException implements HyperlinkListener
{
    public MacOSSierraDuringStartupProgramTerminatedException(final Throwable t) {
        super(a(t.getMessage()), t);
    }
    
    @NotNull
    private static String a(final String s) {
        String s2 = CidrDebuggerBundle.message("debug.gdb.error.duringStartupProgramTerminatedOnSierra", s);
        if (PlatformUtils.isCLion()) {
            s2 = s2 + "\n" + CidrDebuggerBundle.message("debug.gdb.error.changeSettingsToLLDB", new Object[0]);
        }
        String s3;
        try {
            s3 = s2;
            if (s3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/MacOSSierraDuringStartupProgramTerminatedException", "getBalloonMessage"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s3;
    }
    
    public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
        try {
            if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                ShowSettingsUtil.getInstance().showSettingsDialog((Project)null, "Toolchains");
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
