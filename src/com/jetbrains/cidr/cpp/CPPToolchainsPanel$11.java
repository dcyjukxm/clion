// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import java.awt.event.WindowListener;
import java.awt.Window;
import com.intellij.openapi.Disposable;

class CPPToolchainsPanel$11 implements Disposable {
    final /* synthetic */ Window val$welcomeWizard;
    final /* synthetic */ WindowListener val$listener;
    
    public void dispose() {
        this.val$welcomeWizard.removeWindowListener(this.val$listener);
    }
}