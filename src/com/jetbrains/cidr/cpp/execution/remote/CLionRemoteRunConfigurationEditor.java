// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.jetbrains.cidr.execution.debugger.remote.CidrRemoteDebugParameters;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.openapi.util.io.FileUtil;
import java.util.List;
import com.jetbrains.cidr.cpp.CPPToolchains;
import org.jetbrains.annotations.NotNull;
import java.awt.Dimension;
import kotlin.collections.CollectionsKt;
import com.intellij.ui.AddEditRemovePanel$TableModel;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.ui.components.JBLabel;
import com.jetbrains.cidr.cpp.CPPBundle;
import java.awt.Component;
import kotlin.jvm.functions.Function0;
import java.awt.event.ActionListener;
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
import kotlin.Metadata;
import com.intellij.openapi.options.SettingsEditor;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u0014J\u0012\u0010\u0019\u001a\u00020\u00122\b\u0010\u001a\u001a\u0004\u0018\u00010\u0017H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b" }, d2 = { "Lcom/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfigurationEditor;", "Lcom/intellij/openapi/options/SettingsEditor;", "Lcom/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfiguration;", "project", "Lcom/intellij/openapi/project/Project;", "(Lcom/intellij/openapi/project/Project;)V", "myCommandField", "Lcom/intellij/ui/components/JBTextField;", "myDebuggersCombo", "Ljavax/swing/JComboBox;", "Lcom/jetbrains/cidr/cpp/execution/remote/DebuggerComboItem;", "myPathMappingsTable", "Lcom/intellij/ui/AddEditRemovePanel;", "Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemotePathMapping;", "mySymbolFileField", "Lcom/intellij/openapi/ui/TextFieldWithBrowseButton;", "mySysrootField", "applyEditorTo", "", "s", "createEditor", "Ljavax/swing/JComponent;", "getSelectedDebugger", "", "resetEditorFrom", "selectDebugger", "debugger", "clion" })
public final class CLionRemoteRunConfigurationEditor extends SettingsEditor<CLionRemoteRunConfiguration>
{
    private JComboBox<DebuggerComboItem> myDebuggersCombo;
    private JBTextField myCommandField;
    private TextFieldWithBrowseButton mySymbolFileField;
    private TextFieldWithBrowseButton mySysrootField;
    private AddEditRemovePanel<CidrRemotePathMapping> myPathMappingsTable;
    private final Project project;
    
    @NotNull
    protected JComponent createEditor() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBag setDefaultInsets = new GridBag().setDefaultFill(1).setDefaultAnchor(10).setDefaultWeightX(1, 1.0).setDefaultInsets(0, (Insets)JBUI.insets(0, 0, 4, 10)).setDefaultInsets(1, (Insets)JBUI.insetsBottom(4));
        final JComboBox comboBox = new(javax.swing.JComboBox.class);
        final JComboBox comboBox2 = comboBox;
        final JComboBox comboBox3 = comboBox;
        final DebuggerComboItem[] array = new DebuggerComboItem[0];
        final JComboBox myDebuggersCombo = comboBox3;
        new JComboBox(array);
        this.myDebuggersCombo = (JComboBox<DebuggerComboItem>)myDebuggersCombo;
        final JComboBox<DebuggerComboItem> myDebuggersCombo2 = this.myDebuggersCombo;
        if (myDebuggersCombo2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("myDebuggersCombo");
        }
        JComboBox<DebuggerComboItem> myDebuggersCombo3;
        try {
            myDebuggersCombo2.setEditable(false);
            myDebuggersCombo3 = this.myDebuggersCombo;
            if (myDebuggersCombo3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myDebuggersCombo");
            }
        }
        catch (TypeCastException ex) {
            throw b(ex);
        }
        final ComponentWithBrowseButton componentWithBrowseButton = new ComponentWithBrowseButton((JComponent)myDebuggersCombo3, (ActionListener)null);
        final Function0 function0 = (Function0)new CLionRemoteRunConfigurationEditor$createEditor$updateDebuggers.CLionRemoteRunConfigurationEditor$createEditor$updateDebuggers$1(this);
        JBTextField myCommandField;
        try {
            componentWithBrowseButton.addActionListener((ActionListener)new CLionRemoteRunConfigurationEditor$createEditor.CLionRemoteRunConfigurationEditor$createEditor$1(this, function0));
            function0.invoke();
            panel.add((Component)new JBLabel(CPPBundle.message("gdb", new Object[0]) + ":"), setDefaultInsets.nextLine().next().insetBottom(12));
            panel.add((Component)componentWithBrowseButton, setDefaultInsets.next().insetBottom(12));
            panel.add((Component)new JBLabel(CidrDebuggerBundle.message("run.configuration.gdb.command", new Object[0])), setDefaultInsets.nextLine().next().insetBottom(12));
            this.myCommandField = new JBTextField();
            myCommandField = this.myCommandField;
            if (myCommandField == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myCommandField");
            }
        }
        catch (TypeCastException ex2) {
            throw b(ex2);
        }
        JPanel panel2;
        JBTextField myCommandField2;
        try {
            myCommandField.getEmptyText().setText(CidrDebuggerBundle.message("run.configuration.gdb.command.hint", new Object[0]));
            panel2 = panel;
            myCommandField2 = this.myCommandField;
            if (myCommandField2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myCommandField");
            }
        }
        catch (TypeCastException ex3) {
            throw b(ex3);
        }
        TextFieldWithBrowseButton mySymbolFileField;
        try {
            panel2.add((Component)myCommandField2, setDefaultInsets.next().coverLine().insetBottom(12));
            panel.add((Component)new JBLabel(CidrDebuggerBundle.message("run.configuration.gdb.symbolFile", new Object[0])), setDefaultInsets.nextLine().next());
            this.mySymbolFileField = new TextFieldWithBrowseButton();
            mySymbolFileField = this.mySymbolFileField;
            if (mySymbolFileField == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mySymbolFileField");
            }
        }
        catch (TypeCastException ex4) {
            throw b(ex4);
        }
        JPanel panel3;
        TextFieldWithBrowseButton mySymbolFileField2;
        try {
            mySymbolFileField.addBrowseFolderListener(CidrDebuggerBundle.message("run.configuration.gdb.symbolFile.select", new Object[0]), (String)null, (Project)null, new FileChooserDescriptor(true, false, false, false, false, false));
            panel3 = panel;
            mySymbolFileField2 = this.mySymbolFileField;
            if (mySymbolFileField2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mySymbolFileField");
            }
        }
        catch (TypeCastException ex5) {
            throw b(ex5);
        }
        TextFieldWithBrowseButton mySysrootField;
        try {
            panel3.add((Component)mySymbolFileField2, setDefaultInsets.next().coverLine());
            panel.add((Component)new JBLabel(CidrDebuggerBundle.message("run.configuration.gdb.sysroot", new Object[0])), setDefaultInsets.nextLine().next());
            this.mySysrootField = new TextFieldWithBrowseButton();
            mySysrootField = this.mySysrootField;
            if (mySysrootField == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mySysrootField");
            }
        }
        catch (TypeCastException ex6) {
            throw b(ex6);
        }
        JPanel panel4;
        TextFieldWithBrowseButton mySysrootField2;
        try {
            mySysrootField.addBrowseFolderListener(CidrDebuggerBundle.message("run.configuration.gdb.sysroot.select", new Object[0]), (String)null, (Project)null, new FileChooserDescriptor(false, true, false, false, false, false));
            panel4 = panel;
            mySysrootField2 = this.mySysrootField;
            if (mySysrootField2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mySysrootField");
            }
        }
        catch (TypeCastException ex7) {
            throw b(ex7);
        }
        panel4.add((Component)mySysrootField2, setDefaultInsets.next().coverLine());
        panel.add((Component)new JBLabel(CidrDebuggerBundle.message("run.configuration.gdb.pathMappings", new Object[0])), setDefaultInsets.nextLine().next().coverLine().insetTop(4));
        final CLionRemoteRunConfigurationEditor$createEditor$model.CLionRemoteRunConfigurationEditor$createEditor$model$1 cLionRemoteRunConfigurationEditor$createEditor$model$1 = new CLionRemoteRunConfigurationEditor$createEditor$model.CLionRemoteRunConfigurationEditor$createEditor$model$1();
        AddEditRemovePanel<CidrRemotePathMapping> myPathMappingsTable;
        try {
            this.myPathMappingsTable = (AddEditRemovePanel<CidrRemotePathMapping>)new CLionRemoteRunConfigurationEditor$createEditor.CLionRemoteRunConfigurationEditor$createEditor$2(cLionRemoteRunConfigurationEditor$createEditor$model$1, (AddEditRemovePanel$TableModel)cLionRemoteRunConfigurationEditor$createEditor$model$1, CollectionsKt.emptyList());
            myPathMappingsTable = this.myPathMappingsTable;
            if (myPathMappingsTable == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myPathMappingsTable");
            }
        }
        catch (TypeCastException ex8) {
            throw b(ex8);
        }
        AddEditRemovePanel<CidrRemotePathMapping> myPathMappingsTable2;
        try {
            myPathMappingsTable.getTable().getEmptyText().setText(CidrDebuggerBundle.message("run.configuration.gdb.pathMappings.empty", new Object[0]));
            myPathMappingsTable2 = this.myPathMappingsTable;
            if (myPathMappingsTable2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myPathMappingsTable");
            }
        }
        catch (TypeCastException ex9) {
            throw b(ex9);
        }
        AddEditRemovePanel<CidrRemotePathMapping> myPathMappingsTable3;
        try {
            myPathMappingsTable2.getTable().setShowColumns(true);
            myPathMappingsTable3 = this.myPathMappingsTable;
            if (myPathMappingsTable3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myPathMappingsTable");
            }
        }
        catch (TypeCastException ex10) {
            throw b(ex10);
        }
        AddEditRemovePanel<CidrRemotePathMapping> myPathMappingsTable4;
        try {
            myPathMappingsTable3.setMinimumSize(new Dimension(100, 150));
            myPathMappingsTable4 = this.myPathMappingsTable;
            if (myPathMappingsTable4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myPathMappingsTable");
            }
        }
        catch (TypeCastException ex11) {
            throw b(ex11);
        }
        JPanel panel5;
        AddEditRemovePanel<CidrRemotePathMapping> myPathMappingsTable5;
        try {
            myPathMappingsTable4.setPreferredSize(new Dimension(100, 150));
            panel5 = panel;
            myPathMappingsTable5 = this.myPathMappingsTable;
            if (myPathMappingsTable5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myPathMappingsTable");
            }
        }
        catch (TypeCastException ex12) {
            throw b(ex12);
        }
        panel5.add((Component)myPathMappingsTable5, setDefaultInsets.nextLine().coverLine().insetBottom(12));
        return panel;
    }
    
    protected void applyEditorTo(@NotNull final CLionRemoteRunConfiguration cLionRemoteRunConfiguration) {
        Intrinsics.checkParameterIsNotNull((Object)cLionRemoteRunConfiguration, "s");
        final CPPToolchains instance = CPPToolchains.getInstance();
        final String a = this.a();
        Block_5: {
            if (a != null) {
                while (true) {
                    for (final String next : instance.getDebuggers()) {
                        final String s = next;
                        Label_0089: {
                            try {
                                if (FileUtil.pathsEqual(s, a)) {
                                    final String s2 = next;
                                    break Label_0089;
                                }
                                continue;
                            }
                            catch (TypeCastException ex) {
                                throw b(ex);
                            }
                            continue;
                            try {
                                final String s2;
                                if (s2 == null) {
                                    instance.setDebuggers(CollectionsKt.plus((Collection)instance.getDebuggers(), (Object)a));
                                }
                            }
                            catch (TypeCastException ex2) {
                                throw b(ex2);
                            }
                        }
                        break Block_5;
                    }
                    final String s2 = null;
                    continue;
                }
            }
        }
        JBTextField myCommandField;
        try {
            cLionRemoteRunConfiguration.setDebugger(a);
            myCommandField = this.myCommandField;
            if (myCommandField == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myCommandField");
            }
        }
        catch (TypeCastException ex3) {
            throw b(ex3);
        }
        final String text = myCommandField.getText();
        Intrinsics.checkExpressionValueIsNotNull((Object)text, "myCommandField.text");
        final TextFieldWithBrowseButton mySymbolFileField = this.mySymbolFileField;
        if (mySymbolFileField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mySymbolFileField");
        }
        final String text2 = mySymbolFileField.getText();
        Intrinsics.checkExpressionValueIsNotNull((Object)text2, "mySymbolFileField.text");
        final TextFieldWithBrowseButton mySysrootField = this.mySysrootField;
        if (mySysrootField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mySysrootField");
        }
        final String text3 = mySysrootField.getText();
        Intrinsics.checkExpressionValueIsNotNull((Object)text3, "mySysrootField.text");
        final AddEditRemovePanel<CidrRemotePathMapping> myPathMappingsTable = this.myPathMappingsTable;
        if (myPathMappingsTable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("myPathMappingsTable");
        }
        cLionRemoteRunConfiguration.setParams(new CidrRemoteDebugParameters(text, text2, text3, new ArrayList<CidrRemotePathMapping>(myPathMappingsTable.getData())));
    }
    
    protected void resetEditorFrom(@NotNull final CLionRemoteRunConfiguration cLionRemoteRunConfiguration) {
        JBTextField myCommandField;
        try {
            Intrinsics.checkParameterIsNotNull((Object)cLionRemoteRunConfiguration, "s");
            this.a(cLionRemoteRunConfiguration.getDebugger());
            myCommandField = this.myCommandField;
            if (myCommandField == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myCommandField");
            }
        }
        catch (TypeCastException ex) {
            throw b(ex);
        }
        TextFieldWithBrowseButton mySymbolFileField;
        try {
            myCommandField.setText(cLionRemoteRunConfiguration.getParams().getRemoteCommand());
            mySymbolFileField = this.mySymbolFileField;
            if (mySymbolFileField == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mySymbolFileField");
            }
        }
        catch (TypeCastException ex2) {
            throw b(ex2);
        }
        TextFieldWithBrowseButton mySysrootField;
        try {
            mySymbolFileField.setText(cLionRemoteRunConfiguration.getParams().getSymbolFile());
            mySysrootField = this.mySysrootField;
            if (mySysrootField == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mySysrootField");
            }
        }
        catch (TypeCastException ex3) {
            throw b(ex3);
        }
        AddEditRemovePanel<CidrRemotePathMapping> myPathMappingsTable;
        try {
            mySysrootField.setText(cLionRemoteRunConfiguration.getParams().getSysroot());
            myPathMappingsTable = this.myPathMappingsTable;
            if (myPathMappingsTable == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myPathMappingsTable");
            }
        }
        catch (TypeCastException ex4) {
            throw b(ex4);
        }
        myPathMappingsTable.setData((List)new ArrayList(cLionRemoteRunConfiguration.getParams().getPathMappings()));
    }
    
    private final void a(final String selectedItem) {
        if (selectedItem != null) {
            Integer value = null;
            int n = 1;
            JComboBox<DebuggerComboItem> myDebuggersCombo;
            try {
                myDebuggersCombo = this.myDebuggersCombo;
                if (myDebuggersCombo == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("myDebuggersCombo");
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            final int itemCount = myDebuggersCombo.getItemCount();
            while (true) {
                DebuggerComboItem item = null;
                Label_0069: {
                    JComboBox<DebuggerComboItem> comboBox = null;
                    Label_0057: {
                        try {
                            if (n >= itemCount) {
                                break;
                            }
                            final CLionRemoteRunConfigurationEditor cLionRemoteRunConfigurationEditor = this;
                            comboBox = cLionRemoteRunConfigurationEditor.myDebuggersCombo;
                            if (comboBox == null) {
                                break Label_0057;
                            }
                            break Label_0069;
                        }
                        catch (TypeCastException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final CLionRemoteRunConfigurationEditor cLionRemoteRunConfigurationEditor = this;
                            comboBox = cLionRemoteRunConfigurationEditor.myDebuggersCombo;
                            if (comboBox == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("myDebuggersCombo");
                            }
                        }
                        catch (TypeCastException ex3) {
                            throw b(ex3);
                        }
                    }
                    try {
                        item = comboBox.getItemAt(n);
                        if (item == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.jetbrains.cidr.cpp.execution.remote.CustomDebuggerComboItem");
                        }
                    }
                    catch (TypeCastException ex4) {
                        throw b(ex4);
                    }
                }
                if (FileUtil.pathsEqual(((CustomDebuggerComboItem)item).getPath(), selectedItem)) {
                    value = n;
                    break;
                }
                ++n;
            }
            JComboBox<DebuggerComboItem> myDebuggersCombo2 = null;
            Label_0159: {
                JComboBox<DebuggerComboItem> comboBox2 = null;
                Label_0137: {
                    try {
                        if (value == null) {
                            break Label_0159;
                        }
                        final CLionRemoteRunConfigurationEditor cLionRemoteRunConfigurationEditor2 = this;
                        comboBox2 = cLionRemoteRunConfigurationEditor2.myDebuggersCombo;
                        if (comboBox2 == null) {
                            break Label_0137;
                        }
                        break Label_0137;
                    }
                    catch (TypeCastException ex5) {
                        throw b(ex5);
                    }
                    try {
                        final CLionRemoteRunConfigurationEditor cLionRemoteRunConfigurationEditor2 = this;
                        comboBox2 = cLionRemoteRunConfigurationEditor2.myDebuggersCombo;
                        if (comboBox2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("myDebuggersCombo");
                        }
                    }
                    catch (TypeCastException ex6) {
                        throw b(ex6);
                    }
                }
                comboBox2.setSelectedIndex(value);
                return;
                try {
                    myDebuggersCombo2 = this.myDebuggersCombo;
                    if (myDebuggersCombo2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("myDebuggersCombo");
                    }
                }
                catch (TypeCastException ex7) {
                    throw b(ex7);
                }
            }
            myDebuggersCombo2.setSelectedItem(selectedItem);
        }
        else {
            JComboBox<DebuggerComboItem> myDebuggersCombo3;
            try {
                myDebuggersCombo3 = this.myDebuggersCombo;
                if (myDebuggersCombo3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("myDebuggersCombo");
                }
            }
            catch (TypeCastException ex8) {
                throw b(ex8);
            }
            myDebuggersCombo3.setSelectedIndex(0);
        }
    }
    
    private final String a() {
        JComboBox<DebuggerComboItem> myDebuggersCombo;
        try {
            myDebuggersCombo = this.myDebuggersCombo;
            if (myDebuggersCombo == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myDebuggersCombo");
            }
        }
        catch (TypeCastException ex) {
            throw b(ex);
        }
        final Object selectedItem = myDebuggersCombo.getSelectedItem();
        String path;
        if (selectedItem instanceof DefaultDebuggerComboItem) {
            path = null;
        }
        else if (selectedItem instanceof CustomDebuggerComboItem) {
            path = ((CustomDebuggerComboItem)selectedItem).getPath();
        }
        else {
            path = (String)selectedItem;
        }
        return path;
    }
    
    public CLionRemoteRunConfigurationEditor(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        this.project = project;
    }
    
    private static TypeCastException b(final TypeCastException ex) {
        return ex;
    }
}
