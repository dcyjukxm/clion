// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import com.jetbrains.cidr.execution.debugger.CidrDebuggerSettings;
import javax.swing.event.HyperlinkEvent;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import javax.swing.event.HyperlinkListener;

public class DebuggerEvaluationTimedOutException extends DebuggerCommandTimedOutException implements HyperlinkListener
{
    public DebuggerEvaluationTimedOutException(final String s) {
        super(CidrDebuggerBundle.message("debug.command.error.evaluationTimedOut", s, CidrDebuggerBundle.message("debug.settings.enableValueRenderers.action", new Object[0])));
    }
    
    @Override
    public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
        if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            CidrDebuggerSettings.getInstance().RENDERERS_ENABLED = false;
        }
    }
}
