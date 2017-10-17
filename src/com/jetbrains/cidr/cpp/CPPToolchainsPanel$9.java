// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.application.ApplicationActivationListener;

class CPPToolchainsPanel$9 implements ApplicationActivationListener {
    public void applicationActivated(final IdeFrame ideFrame) {
        CPPToolchainsPanel.access$1400(CPPToolchainsPanel.this);
    }
    
    public void applicationDeactivated(final IdeFrame ideFrame) {
        CPPToolchainsPanel.access$1500(CPPToolchainsPanel.this);
    }
}