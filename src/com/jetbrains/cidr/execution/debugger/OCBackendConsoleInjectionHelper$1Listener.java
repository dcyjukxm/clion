// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.jetbrains.cidr.execution.debugger.backend.lang.GDBExpressionPlaceholder;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.util.messages.MessageBusConnection;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.Pair;
import com.intellij.xdebugger.XDebugSessionListener;
import com.jetbrains.cidr.lang.editor.OCInjectionListener;

class 1Listener implements OCInjectionListener, XDebugSessionListener
{
    private volatile Pair<Document, OCCodeFragment> myDocumentAndFragment;
    final /* synthetic */ MessageBusConnection val$connection;
    
    1Listener(final MessageBusConnection val$connection) {
        this.val$connection = val$connection;
    }
    
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
                    OCBackendConsoleInjectionHelper.access$000(this.myDocumentAndFragment = (Pair<Document, OCCodeFragment>)Pair.create((Object)document, (Object)ocFile));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    public void stackFrameChanged() {
        OCBackendConsoleInjectionHelper.access$000(this.myDocumentAndFragment);
    }
    
    public void sessionStopped() {
        this.val$connection.disconnect();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
