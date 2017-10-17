// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ui.JBInsets;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.intellij.util.ArrayUtil;
import com.intellij.util.PlatformUtils;
import java.util.Collection;
import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import java.awt.Component;
import java.awt.Insets;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.GridBag;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import com.intellij.openapi.ui.ComboBox;
import javax.swing.tree.TreePath;
import javax.swing.JTree;
import com.intellij.util.ui.tree.TreeUtil;
import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultTreeModel;
import com.intellij.openapi.actionSystem.AnActionEvent;
import javax.swing.JComponent;
import com.intellij.openapi.actionSystem.CommonShortcuts;
import com.intellij.ide.IdeBundle;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.containers.ImmutableList;
import java.util.Enumeration;
import com.intellij.util.messages.Topic;
import com.intellij.openapi.application.ApplicationManager;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.options.ConfigurationException;
import java.util.Iterator;
import javax.swing.tree.MutableTreeNode;
import com.intellij.openapi.ui.NamedConfigurable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.IconUtil;
import com.intellij.execution.ExecutionBundle;
import com.intellij.openapi.actionSystem.AnAction;
import java.util.ArrayList;
import javax.swing.border.Border;
import com.intellij.util.ui.UIUtil;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;
import com.intellij.openapi.ui.MasterDetailsComponent;

private static class MyMasterDetailsComponent extends MasterDetailsComponent implements CodeStyleConfigurable
{
    private final CodeStyleSettings mySettings;
    
    public MyMasterDetailsComponent(final CodeStyleSettings mySettings) {
        this.mySettings = mySettings;
        this.initTree();
        this.myTree.getEmptyText().setText(OCBundle.message("fileExtensions.no.extensions", new Object[0]));
    }
    
    protected void reInitWholePanelIfNeeded() {
        final boolean myToReInitWholePanel = this.myToReInitWholePanel;
        try {
            super.reInitWholePanelIfNeeded();
            if (myToReInitWholePanel) {
                this.getMaster().putClientProperty(UIUtil.KEEP_BORDER_SIDES, 6);
                this.myWholePanel.setBorder(null);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @Nullable
    protected ArrayList<AnAction> createActions(final boolean b) {
        final ArrayList<MyAddAction> list = (ArrayList<MyAddAction>)new ArrayList<MyMoveAction>();
        list.add((MyMoveAction)new MyAddAction());
        list.add((MyMoveAction)new MasterDetailsComponent.MyDeleteAction((MasterDetailsComponent)this));
        list.add(new MyMoveAction(ExecutionBundle.message("move.up.action.name", new Object[0]), IconUtil.getMoveUpIcon(), -1));
        list.add(new MyMoveAction(ExecutionBundle.message("move.down.action.name", new Object[0]), IconUtil.getMoveDownIcon(), 1));
        return (ArrayList<AnAction>)list;
    }
    
    protected void processRemovedItems() {
    }
    
    protected boolean wasObjectStored(final Object o) {
        return false;
    }
    
    @Nls
    public String getDisplayName() {
        return OCBundle.message("fileExtensions.tabName", new Object[0]);
    }
    
    public void reset() {
        this.reset(this.mySettings);
    }
    
    public void reset(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCFileExtensionsSettingsProvider$MyMasterDetailsComponent", "reset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myRoot.removeAllChildren();
        final Iterator iterator = ((OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class)).FILE_EXTENSION_PAIRS_ORDERED.iterator();
        while (iterator.hasNext()) {
            this.myRoot.add((MutableTreeNode)new MasterDetailsComponent.MyNode((NamedConfigurable)new MyFileExtensionPairConfigurable(iterator.next())));
        }
        super.reset();
    }
    
    public void apply() throws ConfigurationException {
        this.apply(this.mySettings);
    }
    
    public void apply(@NotNull final CodeStyleSettings codeStyleSettings) throws ConfigurationException {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCFileExtensionsSettingsProvider$MyMasterDetailsComponent", "apply"));
            }
        }
        catch (ConfigurationException ex) {
            throw b((Exception)ex);
        }
        final String message = OCBundle.message("fileExtensions.error", new Object[0]);
        try {
            if (this.myRoot.getChildCount() == 0) {
                throw new ConfigurationException(OCBundle.message("fileExtensions.noExtensionsError", new Object[0]), message);
            }
        }
        catch (ConfigurationException ex2) {
            throw b((Exception)ex2);
        }
        this.checkForEmptyAndDuplicatedNames(OCBundle.message("fileExtensions.extensionPair", new Object[0]), message, (Class)MyFileExtensionPairConfigurable.class);
        super.apply();
        final Enumeration children = this.myRoot.children();
        final ArrayList arrayList = ContainerUtil.newArrayList();
        try {
            while (children.hasMoreElements()) {
                arrayList.add(children.nextElement().getConfigurable().getEditableObject());
            }
        }
        catch (ConfigurationException ex3) {
            throw b((Exception)ex3);
        }
        ((OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class)).FILE_EXTENSION_PAIRS_ORDERED = (ImmutableList<OCCodeStyleSettings.FileExtensionPair>)ContainerUtil.immutableList((List)arrayList);
        ((OCFileExtensionsSettingsListener)ApplicationManager.getApplication().getMessageBus().syncPublisher((Topic)OCFileExtensionsSettingsListener.TOPIC)).settingsUpdated();
    }
    
    public boolean isModified() {
        final ImmutableList<OCCodeStyleSettings.FileExtensionPair> file_EXTENSION_PAIRS_ORDERED = ((OCCodeStyleSettings)this.mySettings.getCustomSettings((Class)OCCodeStyleSettings.class)).FILE_EXTENSION_PAIRS_ORDERED;
        try {
            if (this.myRoot.getChildCount() != file_EXTENSION_PAIRS_ORDERED.size()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        for (int i = 0; i < this.myRoot.getChildCount(); ++i) {
            final OCCodeStyleSettings.FileExtensionPair fileExtensionPair = (OCCodeStyleSettings.FileExtensionPair)file_EXTENSION_PAIRS_ORDERED.get(i);
            final MyFileExtensionPairConfigurable myFileExtensionPairConfigurable = (MyFileExtensionPairConfigurable)((MasterDetailsComponent.MyNode)this.myRoot.getChildAt(i)).getConfigurable();
            final OCCodeStyleSettings.FileExtensionPair editableObject = myFileExtensionPairConfigurable.getEditableObject();
            try {
                if (!fileExtensionPair.equals(editableObject)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                if (!this.isInitialized((NamedConfigurable)myFileExtensionPairConfigurable)) {
                    continue;
                }
                final MyFileExtensionPairConfigurable myFileExtensionPairConfigurable2 = myFileExtensionPairConfigurable;
                final boolean b = myFileExtensionPairConfigurable2.isModified();
                if (b) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final MyFileExtensionPairConfigurable myFileExtensionPairConfigurable2 = myFileExtensionPairConfigurable;
                final boolean b = myFileExtensionPairConfigurable2.isModified();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    private class MyAddAction extends AnAction
    {
        public MyAddAction() {
            super(IdeBundle.message("add.scope.popup.title", new Object[0]), (String)null, IconUtil.getAddIcon());
            this.registerCustomShortcutSet(CommonShortcuts.INSERT, (JComponent)MyMasterDetailsComponent.this.myTree);
        }
        
        public void actionPerformed(final AnActionEvent anActionEvent) {
            final MasterDetailsComponent.MyNode myNode = new MasterDetailsComponent.MyNode((NamedConfigurable)new MyFileExtensionPairConfigurable());
            MyMasterDetailsComponent.this.myRoot.add((MutableTreeNode)myNode);
            ((DefaultTreeModel)MyMasterDetailsComponent.this.myTree.getModel()).reload((TreeNode)MyMasterDetailsComponent.this.myRoot);
            MyMasterDetailsComponent.this.selectNodeInTree((DefaultMutableTreeNode)myNode);
        }
    }
    
    private class MyMoveAction extends AnAction
    {
        private final int myDirection;
        
        protected MyMoveAction(final String s, final Icon icon, final int myDirection) {
            super(s, s, icon);
            this.myDirection = myDirection;
        }
        
        public void actionPerformed(final AnActionEvent anActionEvent) {
            TreeUtil.moveSelectedRow((JTree)MyMasterDetailsComponent.this.myTree, this.myDirection);
        }
        
        public void update(final AnActionEvent anActionEvent) {
            boolean enabled = false;
            final TreePath selectionPath = MyMasterDetailsComponent.this.myTree.getSelectionPath();
            if (selectionPath != null) {
                final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)selectionPath.getLastPathComponent();
                enabled = (((this.myDirection < 0) ? defaultMutableTreeNode.getPreviousSibling() : defaultMutableTreeNode.getNextSibling()) != null);
            }
            anActionEvent.getPresentation().setEnabled(enabled);
        }
    }
    
    private class MyFileExtensionPairConfigurable extends NamedConfigurable
    {
        @NotNull
        private OCCodeStyleSettings.FileExtensionPair myFileExtensionPair;
        private ComboBox<String> mySourceExtField;
        private ComboBox<String> myHeaderExtField;
        
        public MyFileExtensionPairConfigurable() {
            this.myFileExtensionPair = new OCCodeStyleSettings.FileExtensionPair("cpp", "h");
        }
        
        public MyFileExtensionPairConfigurable(final OCCodeStyleSettings.FileExtensionPair myFileExtensionPair) {
            if (myFileExtensionPair == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileExtensionPair", "com/jetbrains/cidr/lang/settings/OCFileExtensionsSettingsProvider$MyMasterDetailsComponent$MyFileExtensionPairConfigurable", "<init>"));
            }
            this.myFileExtensionPair = myFileExtensionPair;
        }
        
        public void setDisplayName(final String s) {
        }
        
        public OCCodeStyleSettings.FileExtensionPair getEditableObject() {
            return this.myFileExtensionPair;
        }
        
        public String getBannerSlogan() {
            return null;
        }
        
        public JComponent createOptionsPanel() {
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBag setDefaultInsets = new GridBag().setDefaultWeightX(1, 1.0).setDefaultAnchor(1, 23).setDefaultInsets(0, 0, 4, 10);
            final JBInsets insets = JBUI.insets(10, 0, 4, 10);
            panel.add((Component)new JBLabel(OCBundle.message("fileExtensions.sourceExtension", new Object[0])), setDefaultInsets.nextLine().next().insets((Insets)insets));
            List list = ContainerUtil.sorted((Collection)OCFileTypeHelpers.SOURCE_FILE_EXTENSIONS);
            if (!PlatformUtils.isAppCode()) {
                list = ContainerUtil.filter((Collection)list, s -> {
                    Label_0025: {
                        try {
                            if ("m".equals(s)) {
                                return false;
                            }
                            final String s2 = "mm";
                            final String s3 = s;
                            final boolean b = s2.equals(s3);
                            if (!b) {
                                break Label_0025;
                            }
                            return false;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            final String s2 = "mm";
                            final String s3 = s;
                            final boolean b = s2.equals(s3);
                            if (!b) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    return false;
                });
            }
            panel.add((Component)(this.mySourceExtField = (ComboBox<String>)new ComboBox((Object[])ArrayUtil.toStringArray((Collection)list))), setDefaultInsets.next().insets((Insets)insets));
            this.mySourceExtField.addItemListener((ItemListener)new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent itemEvent) {
                    MyMasterDetailsComponent.this.TREE_UPDATER.run();
                }
            });
            panel.add((Component)new JBLabel(OCBundle.message("fileExtensions.headerExtension", new Object[0])), setDefaultInsets.nextLine().next());
            panel.add((Component)(this.myHeaderExtField = (ComboBox<String>)new ComboBox((Object[])ArrayUtil.toStringArray((Collection)ContainerUtil.sorted((Collection)ContainerUtil.filter((Collection)OCFileTypeHelpers.HEADER_FILE_EXTENSIONS, s -> {
                Label_0023: {
                    try {
                        if (s.isEmpty()) {
                            return false;
                        }
                        final String s2 = "pch";
                        final String s3 = s;
                        final boolean b = s2.equals(s3);
                        if (!b) {
                            break Label_0023;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final String s2 = "pch";
                        final String s3 = s;
                        final boolean b = s2.equals(s3);
                        if (!b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return false;
            }))))), setDefaultInsets.next());
            this.myHeaderExtField.addItemListener((ItemListener)new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent itemEvent) {
                    MyMasterDetailsComponent.this.TREE_UPDATER.run();
                }
            });
            final JPanel panel2 = new JPanel(new BorderLayout());
            panel2.add(panel, "North");
            return panel2;
        }
        
        public String getDisplayName() {
            String mySourceExt = null;
            Label_0031: {
                try {
                    if (this.mySourceExtField == null) {
                        mySourceExt = this.myFileExtensionPair.mySourceExt;
                        break Label_0031;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                mySourceExt = (String)this.mySourceExtField.getSelectedItem();
            }
            final String s = mySourceExt;
            try {
                if (this.myHeaderExtField == null) {
                    final String myHeaderExt = this.myFileExtensionPair.myHeaderExt;
                    return new OCCodeStyleSettings.FileExtensionPair(s, myHeaderExt).toString();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final String myHeaderExt = (String)this.myHeaderExtField.getSelectedItem();
            return new OCCodeStyleSettings.FileExtensionPair(s, myHeaderExt).toString();
        }
        
        @Nullable
        public String getHelpTopic() {
            return null;
        }
        
        public boolean isModified() {
            Label_0053: {
                try {
                    if (!StringUtil.equals((CharSequence)this.mySourceExtField.getSelectedItem(), (CharSequence)this.myFileExtensionPair.mySourceExt)) {
                        break Label_0053;
                    }
                    final MyFileExtensionPairConfigurable myFileExtensionPairConfigurable = this;
                    final ComboBox<String> comboBox = myFileExtensionPairConfigurable.myHeaderExtField;
                    final Object o = comboBox.getSelectedItem();
                    final CharSequence charSequence = (CharSequence)o;
                    final MyFileExtensionPairConfigurable myFileExtensionPairConfigurable2 = this;
                    final OCCodeStyleSettings.FileExtensionPair fileExtensionPair = myFileExtensionPairConfigurable2.myFileExtensionPair;
                    final String s = fileExtensionPair.myHeaderExt;
                    final boolean b = StringUtil.equals(charSequence, (CharSequence)s);
                    if (!b) {
                        break Label_0053;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final MyFileExtensionPairConfigurable myFileExtensionPairConfigurable = this;
                    final ComboBox<String> comboBox = myFileExtensionPairConfigurable.myHeaderExtField;
                    final Object o = comboBox.getSelectedItem();
                    final CharSequence charSequence = (CharSequence)o;
                    final MyFileExtensionPairConfigurable myFileExtensionPairConfigurable2 = this;
                    final OCCodeStyleSettings.FileExtensionPair fileExtensionPair = myFileExtensionPairConfigurable2.myFileExtensionPair;
                    final String s = fileExtensionPair.myHeaderExt;
                    final boolean b = StringUtil.equals(charSequence, (CharSequence)s);
                    if (!b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        }
        
        public void apply() throws ConfigurationException {
            this.myFileExtensionPair = new OCCodeStyleSettings.FileExtensionPair((String)this.mySourceExtField.getSelectedItem(), (String)this.myHeaderExtField.getSelectedItem());
        }
        
        public void reset() {
            this.mySourceExtField.setSelectedItem((Object)this.myFileExtensionPair.mySourceExt);
            this.myHeaderExtField.setSelectedItem((Object)this.myFileExtensionPair.myHeaderExt);
        }
        
        public void disposeUIResources() {
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
