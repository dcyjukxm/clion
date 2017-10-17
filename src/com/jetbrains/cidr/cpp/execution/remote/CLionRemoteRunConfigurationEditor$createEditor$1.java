// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.jetbrains.cidr.execution.debugger.remote.CidrRemoteDebugParameters;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;
import java.awt.Dimension;
import kotlin.collections.CollectionsKt;
import com.intellij.ui.AddEditRemovePanel$TableModel;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.ui.components.JBLabel;
import com.jetbrains.cidr.cpp.CPPBundle;
import java.awt.Component;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.util.ui.JBUI;
import java.awt.Insets;
import com.intellij.util.ui.GridBag;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.debugger.remote.CidrRemotePathMapping;
import com.intellij.ui.AddEditRemovePanel;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.JBTextField;
import javax.swing.JComboBox;
import com.intellij.openapi.options.SettingsEditor;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import com.jetbrains.cidr.cpp.CPPToolchains;
import java.awt.event.ActionEvent;
import kotlin.jvm.functions.Function0;
import kotlin.Metadata;
import java.awt.event.ActionListener;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005" }, d2 = { "<anonymous>", "", "it", "Ljava/awt/event/ActionEvent;", "kotlin.jvm.PlatformType", "actionPerformed" })
static final class CLionRemoteRunConfigurationEditor$createEditor$1 implements ActionListener {
    @Override
    public final void actionPerformed(final ActionEvent actionEvent) {
        final CPPToolchains instance = CPPToolchains.getInstance();
        final EditDebuggersDialog editDebuggersDialog = new EditDebuggersDialog(CLionRemoteRunConfigurationEditor.access$getProject$p(this.this$0), new ArrayList<String>(instance.getDebuggers()), this.this$0.a());
        if (editDebuggersDialog.showAndGet()) {
            instance.setDebuggers(editDebuggersDialog.getList());
            this.$updateDebuggers.invoke();
            this.this$0.a(editDebuggersDialog.getSelected());
        }
    }
}