// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.jetbrains.cidr.lang.ui.OCTextFieldWithSymbolAutoCompletion;

class CidrTestRunConfigurationEditor$3 implements OCTextFieldWithSymbolAutoCompletion.ProcessListener {
    @Override
    public void onStarted() {
        CidrTestRunConfigurationEditor.access$100(CidrTestRunConfigurationEditor.this).setVisible(true);
    }
    
    @Override
    public void onFinished() {
        CidrTestRunConfigurationEditor.access$100(CidrTestRunConfigurationEditor.this).setVisible(false);
    }
}