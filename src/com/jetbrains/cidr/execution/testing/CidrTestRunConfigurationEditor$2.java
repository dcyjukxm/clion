// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.intellij.util.ui.update.Update;

class CidrTestRunConfigurationEditor$2 extends Update {
    public void run() {
        final boolean validSuiteName = CidrTestRunConfigurationEditor.this.isValidSuiteName();
        CidrTestRunConfigurationEditor.access$000(CidrTestRunConfigurationEditor.this).setEnabled(validSuiteName);
        CidrTestRunConfigurationEditor.this.myMethodCombo.setEnabled(validSuiteName);
        CidrTestRunConfigurationEditor.this.updateSuiteAndMethodControls();
    }
}