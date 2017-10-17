// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.ide.NativeIconProvider;
import com.intellij.ui.SimpleColoredComponent;
import javax.swing.JList;
import com.intellij.openapi.options.ConfigurationException;
import com.jetbrains.cidr.lang.OCLog;
import javax.swing.JComboBox;
import org.jetbrains.annotations.Nullable;
import java.awt.Component;
import javax.swing.JLabel;
import com.intellij.util.ui.GridBag;
import javax.swing.JPanel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.ArrayList;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.vfs.LocalFileSystem;
import java.io.File;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListCellRenderer;
import com.jetbrains.cidr.CidrBundle;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.project.Project;

public class CidrRunConfigurationExecutableEditor<RC extends CidrRunConfiguration, BC extends CidrBuildConfiguration, T extends CidrBuildTarget<BC>, BCH extends CidrBuildConfigurationHelper<BC, T>>
{
    private final String SELECT_EXTERNAL_EXECUTABLE;
    private final Project myProject;
    private final BCH myConfigHelper;
    private final ComboBox myExecutableCombo;
    
    public CidrRunConfigurationExecutableEditor(final Project myProject, @NotNull final BCH myConfigHelper, final boolean b) {
        if (myConfigHelper == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configHelper", "com/jetbrains/cidr/execution/CidrRunConfigurationExecutableEditor", "<init>"));
        }
        this.SELECT_EXTERNAL_EXECUTABLE = CidrBundle.message("build.configuration.dialog.item.selectOther", new Object[0]);
        this.myProject = myProject;
        this.myConfigHelper = myConfigHelper;
        (this.myExecutableCombo = CidrRunConfigurationSettingsEditor.createCombo()).setRenderer((ListCellRenderer)new MyExecutablesRenderer(b));
        this.a(false, null, null);
        this.myExecutableCombo.addActionListener((ActionListener)new ActionListener() {
            Object lastSelection = CidrRunConfigurationExecutableEditor.this.myExecutableCombo.getSelectedItem();
            
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                final Object selectedItem = CidrRunConfigurationExecutableEditor.this.myExecutableCombo.getSelectedItem();
                try {
                    if (selectedItem != CidrRunConfigurationExecutableEditor.this.SELECT_EXTERNAL_EXECUTABLE) {
                        this.lastSelection = selectedItem;
                        return;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final Object lastSelection = this.lastSelection;
                CidrRunConfigurationExecutableEditor.this.myExecutableCombo.setSelectedItem(this.lastSelection);
                final FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(true, false, false, false, false, false) {
                    public boolean isFileSelectable(@NotNull final VirtualFile virtualFile) {
                        try {
                            if (virtualFile == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/CidrRunConfigurationExecutableEditor$1$1", "isFileSelectable"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        return ActionListener.this.a(virtualFile);
                    }
                    
                    private static IllegalArgumentException a(final IllegalArgumentException ex) {
                        return ex;
                    }
                };
                VirtualFile fileByIoFile = null;
                if (lastSelection instanceof File) {
                    fileByIoFile = LocalFileSystem.getInstance().findFileByIoFile((File)lastSelection);
                }
                FileChooser.chooseFiles((FileChooserDescriptor)fileChooserDescriptor, CidrRunConfigurationExecutableEditor.this.myProject, fileByIoFile, list -> {
                    final VirtualFile virtualFile = (VirtualFile)ContainerUtil.getFirstItem(list);
                    Object o = null;
                    Label_0046: {
                        Label_0027: {
                            try {
                                if (virtualFile == null) {
                                    break Label_0027;
                                }
                                final ActionListener actionListener = this;
                                final VirtualFile virtualFile2 = virtualFile;
                                final boolean b = actionListener.a(virtualFile2);
                                if (!b) {
                                    break Label_0027;
                                }
                                break Label_0027;
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            try {
                                final ActionListener actionListener = this;
                                final VirtualFile virtualFile2 = virtualFile;
                                final boolean b = actionListener.a(virtualFile2);
                                if (!b) {
                                    o = null;
                                    break Label_0046;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                        }
                        o = new File(virtualFile.getPath());
                    }
                    CidrRunConfigurationExecutableEditor.this.a(true, o, null);
                });
            }
            
            private boolean a(@NotNull final VirtualFile virtualFile) {
                try {
                    if (virtualFile == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/CidrRunConfigurationExecutableEditor$1", "isSelectable"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (virtualFile.isDirectory()) {
                        return "app".equals(virtualFile.getExtension());
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return new File(virtualFile.getPath()).canExecute();
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
    }
    
    private void a(final boolean b, final Object o, final ExecutableData executableData) {
        final Object comboBoxItem = CidrRunConfigurationSettingsEditor.createComboBoxItem(o, executableData);
        final ArrayList<Object> list = new ArrayList<Object>();
        Label_0040: {
            try {
                if (comboBoxItem == null) {
                    break Label_0040;
                }
                final ArrayList<Object> list2 = list;
                final Object o2 = comboBoxItem;
                final boolean b2 = list2.contains(o2);
                if (!b2) {
                    break Label_0040;
                }
                break Label_0040;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final ArrayList<Object> list2 = list;
                final Object o2 = comboBoxItem;
                final boolean b2 = list2.contains(o2);
                if (!b2) {
                    list.add(comboBoxItem);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        list.add(null);
        list.addAll(this.myConfigHelper.getRunTargets());
        list.add(this.SELECT_EXTERNAL_EXECUTABLE);
        if (b) {
            final ComboBoxModel model = this.myExecutableCombo.getModel();
            int i = 0;
            try {
                while (i < model.getSize()) {
                    list.add(model.getElementAt(i));
                    ++i;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        ContainerUtil.removeDuplicates((Collection)list);
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(final Object o, final Object o2) {
                return this.a(o) - this.a(o2);
            }
            
            private int a(final Object o) {
                if (o == null) {
                    return 0;
                }
                if (o == CidrRunConfigurationExecutableEditor.this.SELECT_EXTERNAL_EXECUTABLE) {
                    return 10;
                }
                final Class<?> class1 = o.getClass();
                if (CidrBuildTarget.class.isAssignableFrom(class1)) {
                    return 1;
                }
                if (CidrRunConfigurationSettingsEditor.InvalidItem.class.isAssignableFrom(class1)) {
                    return 2;
                }
                if (File.class.isAssignableFrom(class1)) {
                    return 3;
                }
                return 4;
            }
        });
        this.myExecutableCombo.setModel((ComboBoxModel)new DefaultComboBoxModel(list.toArray()));
        this.myExecutableCombo.setSelectedItem(comboBoxItem);
    }
    
    public void createAdditionalControls(final JPanel panel, final GridBag gridBag) {
        final JLabel label = new JLabel(CidrBundle.message("build.configuration.executable", new Object[0]) + ":");
        panel.add(label, gridBag.nextLine().next());
        panel.add((Component)this.myExecutableCombo, gridBag.next().coverLine());
        label.setLabelFor((Component)this.myExecutableCombo);
    }
    
    public void onTargetSelected(@Nullable final T selectedItem) {
        Label_0020: {
            try {
                if (selectedItem == null) {
                    return;
                }
                final CidrBuildTarget<BC> cidrBuildTarget = selectedItem;
                final boolean b = cidrBuildTarget.isExecutable();
                if (b) {
                    break Label_0020;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CidrBuildTarget<BC> cidrBuildTarget = selectedItem;
                final boolean b = cidrBuildTarget.isExecutable();
                if (b) {
                    this.myExecutableCombo.setSelectedItem((Object)null);
                    this.myExecutableCombo.setSelectedItem((Object)selectedItem);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    public void resetEditorFrom(@NotNull final RC rc) {
        try {
            if (rc == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runConfiguration", "com/jetbrains/cidr/execution/CidrRunConfigurationExecutableEditor", "resetEditorFrom"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ExecutableData executableData = ((CidrExecutableDataHolder)rc).getExecutableData();
        Object o = ((CidrExecutableDataHolder)rc).getExecutableData();
        Label_0116: {
            Label_0097: {
                try {
                    if (executableData == null) {
                        break Label_0116;
                    }
                    if (executableData.target == null) {
                        break Label_0097;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                o = ((CidrBuildConfigurationHelper<BC, Object>)this.myConfigHelper).findRunTarget(executableData.target);
                break Label_0116;
            }
            if (executableData.path != null) {
                o = new File(executableData.path);
            }
        }
        this.a(false, o, executableData);
    }
    
    public void applyEditorTo(final RC rc) throws ConfigurationException {
        final Object selectedItem = CidrRunConfigurationSettingsEditor.getSelectedItem((JComboBox)this.myExecutableCombo);
        ExecutableData executableData;
        if (selectedItem == null) {
            executableData = (ExecutableData)CidrRunConfigurationSettingsEditor.getSelectedInvalidItemValue((JComboBox)this.myExecutableCombo);
        }
        else if (selectedItem instanceof CidrBuildTarget) {
            executableData = new ExecutableData(new BuildTargetData((CidrBuildTarget)selectedItem));
        }
        else if (selectedItem instanceof File) {
            executableData = new ExecutableData(((File)selectedItem).getPath());
        }
        else {
            OCLog.LOG.assertTrue(this.SELECT_EXTERNAL_EXECUTABLE.equals(selectedItem), (Object)("Unexpected selection: " + selectedItem));
            executableData = ((CidrExecutableDataHolder)rc).getExecutableData();
        }
        ((CidrExecutableDataHolder)rc).setExecutableData(executableData);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private class MyExecutablesRenderer extends CidrRunConfigurationSettingsEditor.MyBaseRenderer
    {
        public MyExecutablesRenderer(final boolean b) {
            super(b);
        }
        
        @Override
        protected void appendNotFound(Object o, final boolean b) {
            if (o instanceof ExecutableData) {
                final String path = ((ExecutableData)o).path;
                o = ((path != null) ? new File(path).getName() : ((ExecutableData)o).target);
            }
            super.appendNotFound(o, b);
        }
        
        @Override
        protected String getSeparatorAbove(final JList list, final Object o, final int n) {
            if (n == 0) {
                return null;
            }
            if (o == CidrRunConfigurationExecutableEditor.this.SELECT_EXTERNAL_EXECUTABLE) {
                return "";
            }
            final Object element = list.getModel().getElementAt(n - 1);
            if (o instanceof File && !(element instanceof File)) {
                return "";
            }
            return super.getSeparatorAbove(list, o, n);
        }
        
        @Override
        protected void customizeCellRenderer(final SimpleColoredComponent simpleColoredComponent, final JList list, final Object o, final int n, final boolean b, final boolean b2) {
            if (o instanceof File) {
                final File file = (File)o;
                simpleColoredComponent.setIcon(NativeIconProvider.getNativeIcon(file));
                if (!file.exists()) {
                    this.appendNotFound(file.getName(), b);
                }
                else {
                    simpleColoredComponent.append(file.getName());
                }
                return;
            }
            super.customizeCellRenderer(simpleColoredComponent, list, o, n, b, b2);
        }
    }
}
