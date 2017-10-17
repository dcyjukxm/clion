// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import javax.swing.JList;
import com.intellij.ui.SimpleColoredComponent;
import com.intellij.ui.TextFieldWithAutoCompletion;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.text.StringUtil;
import javax.swing.ComboBoxModel;
import com.intellij.ui.CollectionComboBoxModel;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.ui.SimpleTextAttributes;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import com.intellij.execution.ExecutionBundle;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.ui.OCFieldAdapter;
import com.intellij.util.ui.update.Update;
import com.intellij.util.ui.JBInsets;
import com.jetbrains.cidr.CidrBundle;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import javax.swing.ListCellRenderer;
import java.awt.Insets;
import com.intellij.util.ui.JBUI;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.UIUtil;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.intellij.openapi.Disposable;
import javax.swing.JComponent;
import com.intellij.util.ui.GridBag;
import javax.swing.JPanel;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import java.util.Set;
import com.intellij.util.ui.update.MergingUpdateQueue;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import com.intellij.util.ui.AsyncProcessIcon;
import com.intellij.ui.EditorTextField;
import com.jetbrains.cidr.lang.ui.OCTextFieldWithSymbolAutoCompletion;
import com.jetbrains.cidr.execution.CidrRunConfigurationSettingsEditor;
import com.jetbrains.cidr.execution.CidrBuildConfigurationHelper;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import com.jetbrains.cidr.execution.CidrBuildConfiguration;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CidrTestRunConfigurationEditor$4 implements ActionListener {
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        CidrTestRunConfigurationEditor.this.d();
    }
}