// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import com.intellij.internal.statistic.UsageTrigger;
import com.intellij.openapi.util.Disposer;
import java.util.Iterator;
import com.intellij.util.containers.ContainerUtil;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.jetbrains.cidr.lang.settings.OCCppNewDialogSettings;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import com.intellij.util.ui.DialogUtil;
import com.intellij.ui.NonFocusableCheckBox;
import java.awt.Component;
import com.intellij.ui.components.JBScrollPane;
import java.util.List;
import com.intellij.util.FunctionUtil;
import javax.swing.border.Border;
import com.jetbrains.cidr.lang.OCBundle;
import javax.swing.DefaultListModel;
import com.intellij.util.ui.FormBuilder;
import javax.swing.JComponent;
import com.intellij.ui.CheckBoxList;
import javax.swing.JCheckBox;
import com.jetbrains.cidr.lang.actions.newFile.DialogWrapperFacade;
import com.intellij.util.Function;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.ArrayList;
import com.jetbrains.cidr.cpp.cmake.model.CMakeModel;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileHelperUtil;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;
import java.util.Properties;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiDirectory;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileHelper;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileHelperProvider;

public class CPPNewFileHelperProvider implements OCNewFileHelperProvider
{
    @NotNull
    @Override
    public OCNewFileHelper createHelper() {
        Helper helper;
        try {
            helper = new Helper();
            if (helper == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider", "createHelper"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return helper;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    private static class Helper implements OCNewFileHelper
    {
        private Project myProject;
        private PsiFile mySampleFile;
        
        @Override
        public boolean initFromDataContext(@NotNull final DataContext dataContext) {
            try {
                if (dataContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dataContext", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper", "initFromDataContext"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                this.myProject = (Project)CommonDataKeys.PROJECT.getData(dataContext);
                if (this.myProject != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return false;
        }
        
        @Override
        public boolean initFromFile(@NotNull final PsiFile mySampleFile) {
            try {
                if (mySampleFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper", "initFromFile"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myProject = mySampleFile.getProject();
            this.mySampleFile = mySampleFile;
            return true;
        }
        
        @NotNull
        @Override
        public String getDefaultClassPrefix() {
            String s;
            try {
                s = "";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper", "getDefaultClassPrefix"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return s;
        }
        
        @Override
        public boolean canChangeDir() {
            return false;
        }
        
        @NotNull
        @Override
        public DialogWrapper createDialog(@NotNull final OCNewFileActionBase.CreateFileDialogBase createFileDialogBase, @Nullable final PsiDirectory psiDirectory, @Nullable final DataContext dataContext) {
            try {
                if (createFileDialogBase == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "peer", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper", "createDialog"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final CMakeNewFileHandler cMakeNewFileHandler = new CMakeNewFileHandler(this.myProject);
            Dialog dialog;
            try {
                dialog = new Dialog(createFileDialogBase, cMakeNewFileHandler, this.mySampleFile) {
                    @Nullable
                    protected String getHelpId() {
                        return createFileDialogBase.getHelpId();
                    }
                };
                if (dialog == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper", "createDialog"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return dialog;
        }
        
        @Override
        public void setProperties(@Nullable final DialogWrapper dialogWrapper, final Properties properties, @Nullable final PsiFile psiFile, final Project project) {
            final CMakeModel model = CMakeWorkspace.getInstance(project).getModel();
            String s = null;
            Label_0034: {
                try {
                    if (model == null) {
                        s = project.getName();
                        break Label_0034;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                s = model.getProjectName();
            }
            OCNewFileHelperUtil.fillCommonTemplateProperties(properties, s);
        }
        
        @Override
        public void doCreateFiles(@NotNull final Project project, @NotNull final PsiDirectory psiDirectory, @NotNull final String[] array, @NotNull final PsiFile[] array2, @Nullable final DialogWrapper dialogWrapper, @Nullable final PsiFile psiFile) {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper", "doCreateFiles"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (psiDirectory == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "directory", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper", "doCreateFiles"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (array == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileNames", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper", "doCreateFiles"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (array2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resultElements", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper", "doCreateFiles"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            final ArrayList<VirtualFile> list = new ArrayList<VirtualFile>();
            final Function function = virtualFile -> {
                list.add(virtualFile);
                return null;
            };
            try {
                OCNewFileHelperUtil.addCreatedFiles(psiDirectory, array, array2, project, (Function<VirtualFile, Void>)function);
                if (dialogWrapper instanceof Dialog) {
                    ((Dialog)dialogWrapper).handleNewFiles(list);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
        
        private static class Dialog extends DialogWrapper implements DialogWrapperFacade
        {
            @NotNull
            private final OCNewFileActionBase.CreateFileDialogBase myPeer;
            @NotNull
            private final NewFileHandler<NewFileTarget> myHandler;
            @Nullable
            private final PsiFile mySampleFile;
            private JCheckBox myAddToProjectCheckBox;
            private CheckBoxList<NewFileTarget> myTargetCheckBoxList;
            private JComponent myPreviewComponent;
            
            public Dialog(@NotNull final OCNewFileActionBase.CreateFileDialogBase myPeer, @NotNull final NewFileHandler myHandler, @Nullable final PsiFile mySampleFile) {
                if (myPeer == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "peer", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper$Dialog", "<init>"));
                }
                if (myHandler == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper$Dialog", "<init>"));
                }
                super(false);
                this.myPeer = myPeer;
                this.myHandler = (NewFileHandler<NewFileTarget>)myHandler;
                this.mySampleFile = mySampleFile;
                myPeer.setWrapper(this);
                this.setTitle(myPeer.getTitle());
                this.init();
            }
            
            @Nullable
            protected JComponent createCenterPanel() {
                final FormBuilder setVertical = FormBuilder.createFormBuilder().setVertical(false);
                this.myPeer.fillGenericControls(setVertical);
                this.myTargetCheckBoxList = new CheckBoxList<NewFileTarget>(new DefaultListModel()) {
                    @Nullable
                    protected String getSecondaryText(final int n) {
                        final NewFileTarget newFileTarget = (NewFileTarget)this.getItemAt(n);
                        return (newFileTarget == null) ? "" : newFileTarget.getAdditionalInfo();
                    }
                };
                this.myTargetCheckBoxList.getEmptyText().setText(OCBundle.message("create.tooltip.no.targets", new Object[0]));
                this.myTargetCheckBoxList.setBorder((Border)null);
                final List<NewFileTarget> targets = this.myHandler.getTargets();
                boolean b = false;
                Label_0091: {
                    try {
                        if (!targets.isEmpty()) {
                            b = true;
                            break Label_0091;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    b = false;
                }
                final boolean enabled = b;
                this.myTargetCheckBoxList.setItems((List)targets, FunctionUtil.string());
                final JBScrollPane jbScrollPane = new JBScrollPane((Component)this.myTargetCheckBoxList, 20, 31);
                DialogUtil.registerMnemonic((AbstractButton)(this.myAddToProjectCheckBox = (JCheckBox)new NonFocusableCheckBox(OCBundle.message("create.checkbox.targets", new Object[0]))), '&');
                this.myAddToProjectCheckBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(final ItemEvent itemEvent) {
                        Dialog.this.updateTargetControlsState();
                    }
                });
                final boolean shouldAddNewFilesToTheProject = OCCppNewDialogSettings.getInstance().shouldAddNewFilesToTheProject();
                Label_0266: {
                    Label_0201: {
                        JCheckBox myAddToProjectCheckBox = null;
                        Label_0192: {
                            try {
                                myAddToProjectCheckBox = this.myAddToProjectCheckBox;
                                if (!enabled) {
                                    break Label_0192;
                                }
                                final boolean b2 = shouldAddNewFilesToTheProject;
                                if (b2) {
                                    break Label_0192;
                                }
                                break Label_0192;
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                            try {
                                final boolean b2 = shouldAddNewFilesToTheProject;
                                if (b2) {
                                    final boolean selected = true;
                                    break Label_0201;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                        }
                        final boolean selected = false;
                        try {
                            myAddToProjectCheckBox.setSelected(selected);
                            this.myAddToProjectCheckBox.setEnabled(enabled);
                            setVertical.addComponent((JComponent)this.myAddToProjectCheckBox);
                            setVertical.setFormLeftIndent(20);
                            setVertical.addComponent((JComponent)jbScrollPane);
                            if (!enabled) {
                                break Label_0266;
                            }
                            final Dialog dialog = this;
                            final Dialog dialog2 = this;
                            final NewFileHandler<NewFileTarget> newFileHandler = dialog2.myHandler;
                            final JComponent component = newFileHandler.createPreviewComponent();
                            dialog.myPreviewComponent = component;
                            final Dialog dialog3 = this;
                            final JComponent component2 = dialog3.myPreviewComponent;
                            if (component2 != null) {
                                break Label_0266;
                            }
                            break Label_0266;
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    try {
                        final Dialog dialog = this;
                        final Dialog dialog2 = this;
                        final NewFileHandler<NewFileTarget> newFileHandler = dialog2.myHandler;
                        final JComponent component = newFileHandler.createPreviewComponent();
                        dialog.myPreviewComponent = component;
                        final Dialog dialog3 = this;
                        final JComponent component2 = dialog3.myPreviewComponent;
                        if (component2 != null) {
                            setVertical.addComponent(this.myPreviewComponent);
                            this.myTargetCheckBoxList.addListSelectionListener((ListSelectionListener)new ListSelectionListener() {
                                @Override
                                public void valueChanged(final ListSelectionEvent listSelectionEvent) {
                                    final int selectedIndex = Dialog.this.myTargetCheckBoxList.getSelectedIndex();
                                    Dialog.this.myHandler.updatePreviewComponent(Dialog.this.myPreviewComponent, (selectedIndex < 0) ? null : ((NewFileTarget)Dialog.this.myTargetCheckBoxList.getItemAt(selectedIndex)));
                                }
                            });
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                this.myPeer.validateOkAction();
                final JPanel panel = setVertical.getPanel();
                final JPanel panel2 = new JPanel(new BorderLayout());
                try {
                    panel2.add(panel, "Center");
                    if (enabled) {
                        this.a(this.myHandler, this.myPeer.getSelectedPath());
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                this.updateTargetControlsState();
                return panel2;
            }
            
            public void updateTargetControlsState() {
                final boolean selected = this.myAddToProjectCheckBox.isSelected();
                try {
                    this.myTargetCheckBoxList.setEnabled(selected);
                    if (this.myPreviewComponent != null) {
                        this.myPreviewComponent.setVisible(selected);
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            
            private void a(@NotNull final NewFileHandler<NewFileTarget> newFileHandler, @Nullable final String s) {
                try {
                    if (newFileHandler == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper$Dialog", "selectBestTarget"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final List<NewFileTarget> bestTargets = newFileHandler.bestTargets(this.mySampleFile, s);
                int intValue = 0;
                if (!bestTargets.isEmpty()) {
                    final Iterator<NewFileTarget> iterator = bestTargets.iterator();
                    while (iterator.hasNext()) {
                        this.myTargetCheckBoxList.setItemSelected((Object)iterator.next(), true);
                    }
                    final Integer n = (Integer)ContainerUtil.getFirstItem((List)getSelectedIndices(this.myTargetCheckBoxList));
                    if (n != null) {
                        intValue = n;
                        this.myTargetCheckBoxList.ensureIndexIsVisible((int)n);
                    }
                }
                Label_0174: {
                    try {
                        if (this.myTargetCheckBoxList.isEmpty()) {
                            return;
                        }
                        final Dialog dialog = this;
                        final JComponent component = dialog.myPreviewComponent;
                        if (component != null) {
                            break Label_0174;
                        }
                        return;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final Dialog dialog = this;
                        final JComponent component = dialog.myPreviewComponent;
                        if (component != null) {
                            this.myHandler.updatePreviewComponent(this.myPreviewComponent, (NewFileTarget)this.myTargetCheckBoxList.getItemAt(intValue));
                            this.myTargetCheckBoxList.setSelectedIndex(intValue);
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
            }
            
            @Nullable
            public JComponent getPreferredFocusedComponent() {
                return this.myPeer.getPreferredFocusedComponent();
            }
            
            protected void doOKAction() {
                try {
                    if (this.myPeer.checkCanDoOKAction()) {
                        super.doOKAction();
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            
            public void setOKEnabled(final boolean okActionEnabled) {
                this.setOKActionEnabled(okActionEnabled);
            }
            
            public void setErrorMessage(@Nullable final String errorText) {
                this.setErrorText(errorText);
            }
            
            protected void dispose() {
                super.dispose();
                Disposer.dispose(this.myPeer.getDisposable());
            }
            
            public void handleNewFiles(final ArrayList<VirtualFile> list) {
                final boolean selected = this.myAddToProjectCheckBox.isSelected();
                OCCppNewDialogSettings.getInstance().setAddNewFilesToTheProject(selected);
                UsageTrigger.trigger("cidr.newFile");
                if (selected) {
                    UsageTrigger.trigger("cidr.newFile.addedToProject");
                    this.myHandler.handleNewFiles(getSelectedIndices(this.myTargetCheckBoxList), list);
                }
            }
            
            @NotNull
            public static List<Integer> getSelectedIndices(final CheckBoxList<NewFileTarget> list) {
                final ArrayList<Integer> list2 = new ArrayList<Integer>();
                int n = 0;
                while (true) {
                    Label_0033: {
                        try {
                            if (n >= list.getItemsCount()) {
                                break;
                            }
                            final CheckBoxList<NewFileTarget> list3 = list;
                            final int n2 = n;
                            final boolean b = list3.isItemSelected(n2);
                            if (b) {
                                break Label_0033;
                            }
                            break Label_0033;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            final CheckBoxList<NewFileTarget> list3 = list;
                            final int n2 = n;
                            final boolean b = list3.isItemSelected(n2);
                            if (b) {
                                list2.add(n);
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    ++n;
                }
                ArrayList<Integer> list4;
                try {
                    list4 = list2;
                    if (list4 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CPPNewFileHelperProvider$Helper$Dialog", "getSelectedIndices"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                return list4;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }
    }
}
