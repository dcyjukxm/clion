// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;

class CidrTestRunConfigurationEditor$1 implements DocumentListener {
    public void documentChanged(final DocumentEvent documentEvent) {
        CidrTestRunConfigurationEditor.this.scheduleSuiteAndTestsUpdate();
    }
}