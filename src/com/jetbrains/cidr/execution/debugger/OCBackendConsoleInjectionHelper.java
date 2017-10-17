// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.messages.Topic;
import com.jetbrains.cidr.execution.debugger.backend.lang.GDBExpressionPlaceholder;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.util.messages.MessageBusConnection;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.Pair;
import com.intellij.xdebugger.XDebugSessionListener;
import com.jetbrains.cidr.lang.editor.OCInjectionListener;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.XDebugSession;

public class OCBackendConsoleInjectionHelper implements BackendConsoleInjectionHelper
{
    @Override
    public void subscribeToInjection(@NotNull final XDebugSession xDebugSession) {
        try {
            if (xDebugSession == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/execution/debugger/OCBackendConsoleInjectionHelper", "subscribeToInjection"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final MessageBusConnection connect = xDebugSession.getProject().getMessageBus().connect();
        class 1Listener implements OCInjectionListener, XDebugSessionListener
        {
            private volatile Pair<Document, OCCodeFragment> myDocumentAndFragment;
            
            @Override
            public void didInject(@NotNull final OCFile ocFile, final PsiLanguageInjectionHost psiLanguageInjectionHost) {
                try {
                    if (ocFile == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "injected", "com/jetbrains/cidr/execution/debugger/OCBackendConsoleInjectionHelper$1Listener", "didInject"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                if (psiLanguageInjectionHost instanceof GDBExpressionPlaceholder) {
                    final Document document = psiLanguageInjectionHost.getContainingFile().getOriginalFile().getViewProvider().getDocument();
                    try {
                        if (document != null) {
                            a(this.myDocumentAndFragment = (Pair<Document, OCCodeFragment>)Pair.create((Object)document, (Object)ocFile));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
            
            public void stackFrameChanged() {
                a(this.myDocumentAndFragment);
            }
            
            public void sessionStopped() {
                connect.disconnect();
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }
        final 1Listener 1Listener = new 1Listener();
        connect.subscribe((Topic)OCInjectionListener.INJECTION_TOPIC, (Object)1Listener);
        xDebugSession.addSessionListener((XDebugSessionListener)1Listener);
    }
    
    private static void a(@Nullable final Pair<Document, OCCodeFragment> pair) {
        try {
            if (pair == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CidrDebugProcess cidrDebugProcess = (CidrDebugProcess)((Document)pair.first).getUserData((Key)CidrDebugProcess.DEBUG_PROCESS_KEY);
        try {
            if (cidrDebugProcess != null) {
                ((OCCodeFragment)pair.second).setContext(cidrDebugProcess.getDebuggerContext());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
