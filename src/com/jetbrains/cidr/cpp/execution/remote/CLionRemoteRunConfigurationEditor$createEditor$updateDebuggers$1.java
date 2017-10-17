// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.remote.CidrRemoteDebugParameters;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;
import java.awt.Dimension;
import com.intellij.ui.AddEditRemovePanel$TableModel;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.ui.components.JBLabel;
import com.jetbrains.cidr.cpp.CPPBundle;
import java.awt.Component;
import java.awt.event.ActionListener;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import kotlin.TypeCastException;
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
import java.util.Iterator;
import com.jetbrains.cidr.cpp.toolchains.GDB;
import kotlin.text.StringsKt;
import com.intellij.ui.CollectionComboBoxModel;
import javax.swing.ComboBoxModel;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.collections.CollectionsKt;
import java.util.List;
import java.util.ArrayList;
import com.jetbrains.cidr.cpp.CPPToolchains;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002" }, d2 = { "<anonymous>", "", "invoke" })
static final class CLionRemoteRunConfigurationEditor$createEditor$updateDebuggers$1 extends Lambda implements Function0<Unit> {
    public final void invoke() {
        final CPPToolchains instance = CPPToolchains.getInstance();
        final ArrayList<Object> list = new ArrayList<Object>();
        final String s = "Default";
        String s2;
        if (instance.isUseGDB()) {
            final StringBuilder append = new StringBuilder().append(s);
            String string;
            if (instance.isUseBundleGDB()) {
                string = " (Bundled)";
            }
            else {
                final StringBuilder append2 = new StringBuilder().append(" (");
                final GDB gdb = instance.getGDB();
                string = append2.append((gdb != null) ? gdb.getExecutablePath() : null).append(')').toString();
            }
            s2 = append.append(string).toString();
        }
        else {
            s2 = s + " (Not selected)";
        }
        list.add(new DefaultDebuggerComboItem(s2));
        final ArrayList<Object> list2 = list;
        final List<String> list3 = instance.getDebuggers();
        final ArrayList<Object> list4 = list2;
        final List<String> list5 = list3;
        final ArrayList<Object> list6 = new ArrayList<Object>(CollectionsKt.collectionSizeOrDefault((Iterable)list3, 10));
        for (final String next : list5) {
            final ArrayList<Object> list7 = list6;
            final String s3 = next;
            final ArrayList<Object> list8 = list7;
            final String s4 = s3;
            Intrinsics.checkExpressionValueIsNotNull((Object)s4, "it");
            list8.add(new CustomDebuggerComboItem(s4));
        }
        list4.addAll(list6);
        CLionRemoteRunConfigurationEditor.access$getMyDebuggersCombo$p(this.this$0).setModel((ComboBoxModel)new CollectionComboBoxModel((List)list));
        CLionRemoteRunConfigurationEditor.access$getMyDebuggersCombo$p(this.this$0).setPrototypeDisplayValue(new CustomDebuggerComboItem(StringsKt.repeat((CharSequence)" ", 10)));
    }
}