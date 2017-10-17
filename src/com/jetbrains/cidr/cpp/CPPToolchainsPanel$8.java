// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.ui.UIUtil;
import java.io.File;
import javax.swing.event.DocumentEvent;
import com.intellij.util.NullableConsumer;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.NullableFunction;
import com.intellij.util.Alarm;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.DocumentAdapter;

class CPPToolchainsPanel$8 extends DocumentAdapter {
    final /* synthetic */ CPPToolchainsCheckPanel.UpdateType val$checkUpdateType;
    final /* synthetic */ TextFieldWithBrowseButton val$pathField;
    final /* synthetic */ Alarm val$alarm;
    final /* synthetic */ NullableFunction val$readVersion;
    final /* synthetic */ String val$tool;
    final /* synthetic */ JBLabel val$versionField;
    final /* synthetic */ NullableConsumer val$onTextChange;
    
    protected void textChanged(final DocumentEvent documentEvent) {
        CPPToolchainsPanel.access$700(CPPToolchainsPanel.this, this.val$checkUpdateType);
        final String preparePath = CPPToolchains.preparePath(this.val$pathField.getText());
        try {
            this.val$alarm.cancelAllRequests();
            final NullableFunction val$readVersion;
            final String val$tool;
            final JBLabel val$versionField;
            final IllegalArgumentException ex;
            final StringBuilder sb;
            final String s;
            this.val$alarm.addRequest(() -> {
                val$readVersion = this.val$readVersion;
                val$tool = this.val$tool;
                val$versionField = this.val$versionField;
                try {
                    if (val$readVersion == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "readVersion", "com/jetbrains/cidr/cpp/CPPToolchainsPanel$8", "lambda$textChanged$1"));
                        throw ex;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                sb = new StringBuilder();
                Label_0111_1: {
                    try {
                        if (s == null) {
                            sb.append(' ');
                            break Label_0111_1;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    sb.append(val$tool).append(" ").append(CPPToolchainsPanel.access$1300((String)val$readVersion.fun((Object)new File(s))));
                }
                UIUtil.invokeLaterIfNeeded(() -> val$versionField.setText(sb.toString()));
                return;
            }, 300);
            if (this.val$onTextChange != null) {
                this.val$onTextChange.consume((Object)preparePath);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}