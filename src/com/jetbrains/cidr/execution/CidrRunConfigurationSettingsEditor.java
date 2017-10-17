// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.ui.SimpleTextAttributes;
import javax.swing.Icon;
import com.intellij.openapi.util.text.StringUtil;
import javax.swing.JList;
import com.intellij.ui.ErrorLabel;
import com.intellij.ui.SimpleColoredComponent;
import com.intellij.ui.GroupedElementsRenderer;
import com.intellij.openapi.util.Comparing;
import javax.swing.JComboBox;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.execution.CommonProgramRunConfigurationParameters;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import javax.swing.ListCellRenderer;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JLabel;
import java.awt.Component;
import com.jetbrains.cidr.CidrBundle;
import java.awt.Insets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.GridBag;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.ui.CommonProgramParametersPanel;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBLabel;
import javax.swing.JComponent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.openapi.options.SettingsEditor;

public abstract class CidrRunConfigurationSettingsEditor<CONFIGURATION extends CidrRunConfiguration, BC extends CidrBuildConfiguration, TARGET extends CidrBuildTarget<BC>, BCH extends CidrBuildConfigurationHelper<BC, TARGET>> extends SettingsEditor<CONFIGURATION> implements PanelWithAnchor
{
    protected final Project myProject;
    protected final BCH myConfigHelper;
    private JComponent myAnchor;
    private JBLabel myTargetLabel;
    protected ComboBox myTargetCombo;
    protected ComboBox myConfigurationCombo;
    private CommonProgramParametersPanel myCommonProgramParameters;
    
    public CidrRunConfigurationSettingsEditor(final Project myProject, @NotNull final BCH myConfigHelper) {
        if (myConfigHelper == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configHelper", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor", "<init>"));
        }
        this.myProject = myProject;
        this.myConfigHelper = myConfigHelper;
    }
    
    @NotNull
    protected JComponent createEditor() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBag setDefaultInsets = new GridBag().setDefaultFill(1).setDefaultAnchor(10).setDefaultWeightX(1, 1.0).setDefaultInsets(0, (Insets)JBUI.insets(0, 0, 4, 10)).setDefaultInsets(1, (Insets)JBUI.insetsBottom(4));
        JPanel panel2;
        try {
            this.createEditorInner(panel, setDefaultInsets);
            this.setAnchor(this.myCommonProgramParameters.getAnchor());
            panel2 = panel;
            if (panel2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor", "createEditor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return panel2;
    }
    
    protected void createEditorInner(final JPanel panel, final GridBag gridBag) {
        panel.add((Component)(this.myTargetLabel = new JBLabel(CidrBundle.message("build.configuration.target", new Object[0]) + ":")), gridBag.nextLine().next());
        panel.add((Component)(this.myTargetCombo = createCombo()), gridBag.next().coverLine());
        this.myTargetLabel.setLabelFor((Component)this.myTargetCombo);
        final JLabel label = new JLabel(CidrBundle.message("build.configuration.configuration", new Object[0]) + ":");
        panel.add(label, gridBag.nextLine().next());
        panel.add((Component)(this.myConfigurationCombo = createCombo()), gridBag.next().coverLine());
        label.setLabelFor((Component)this.myConfigurationCombo);
        this.createAdditionalControls(panel, gridBag);
        panel.add(this.myCommonProgramParameters = new CommonProgramParametersPanel(), gridBag.nextLine().weighty(1.0).insets(8, -1, 8, -1).coverLine());
        this.myTargetCombo.addItemListener((ItemListener)new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == 2 && CidrRunConfigurationSettingsEditor.this.myTargetCombo.getItemCount() > 0) {
                    return;
                }
                final CidrBuildTarget cidrBuildTarget = (itemEvent.getStateChange() == 1) ? ((CidrBuildTarget)CidrRunConfigurationSettingsEditor.getItem(itemEvent)) : null;
                new ReadAction() {
                    protected void run(@NotNull final Result result) throws Throwable {
                        try {
                            if (result == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor$1$1", "run"));
                            }
                        }
                        catch (Throwable t) {
                            throw a(t);
                        }
                        CidrRunConfigurationSettingsEditor.this.myConfigurationCombo.setModel((ComboBoxModel)new DefaultComboBoxModel(((CidrBuildConfigurationHelper<CidrBuildConfiguration, TARGET>)CidrRunConfigurationSettingsEditor.this.myConfigHelper).getConfigurations((TARGET)cidrBuildTarget).toArray()));
                        CidrRunConfigurationSettingsEditor.this.myConfigurationCombo.setSelectedItem((Object)null);
                        CidrRunConfigurationSettingsEditor.this.myConfigurationCombo.setSelectedItem((Object)((CidrBuildConfigurationHelper<BC, TARGET>)CidrRunConfigurationSettingsEditor.this.myConfigHelper).getDefaultConfiguration((TARGET)cidrBuildTarget));
                    }
                    
                    private static Throwable a(final Throwable t) {
                        return t;
                    }
                }.execute();
                CidrRunConfigurationSettingsEditor.this.onTargetSelected(cidrBuildTarget);
            }
        });
        this.myTargetCombo.setModel((ComboBoxModel)new DefaultComboBoxModel(this.getTargets().toArray()));
        this.myTargetCombo.setSelectedItem((Object)null);
        this.myTargetCombo.setRenderer((ListCellRenderer)this.getTargetsRenderer());
        this.myConfigurationCombo.setRenderer((ListCellRenderer)this.getTargetsRenderer());
    }
    
    @NotNull
    protected List<TARGET> getTargets() {
        List<TARGET> targets;
        try {
            targets = ((CidrBuildConfigurationHelper<BC, TARGET>)this.myConfigHelper).getTargets();
            if (targets == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor", "getTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return targets;
    }
    
    protected boolean hasTargetsInSeveralProjects() {
        return false;
    }
    
    @NotNull
    protected MyBaseRenderer getTargetsRenderer() {
        MyBaseRenderer myBaseRenderer;
        try {
            myBaseRenderer = new MyBaseRenderer(this.hasTargetsInSeveralProjects());
            if (myBaseRenderer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor", "getTargetsRenderer"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myBaseRenderer;
    }
    
    protected void createAdditionalControls(final JPanel panel, final GridBag gridBag) {
    }
    
    @NotNull
    protected static MyComboBox createCombo() {
        MyComboBox myComboBox;
        try {
            myComboBox = new MyComboBox(10);
            if (myComboBox == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor", "createCombo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myComboBox;
    }
    
    protected void onTargetSelected(@Nullable final TARGET target) {
    }
    
    protected void resetEditorFrom(@NotNull final CONFIGURATION configuration) {
        try {
            if (configuration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runConfiguration", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor", "resetEditorFrom"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.resetBuildAndConfigurationFrom(configuration);
        this.myCommonProgramParameters.reset(configuration);
    }
    
    protected void resetBuildAndConfigurationFrom(@NotNull final CONFIGURATION configuration) {
        try {
            if (configuration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runConfiguration", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor", "resetBuildAndConfigurationFrom"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final BuildTargetAndConfigurationData targetAndConfigurationData = configuration.getTargetAndConfigurationData();
        BuildTargetData target = null;
        Label_0065: {
            try {
                if (targetAndConfigurationData == null) {
                    target = null;
                    break Label_0065;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            target = targetAndConfigurationData.target;
        }
        new ReadAction() {
            protected void run(@NotNull final Result result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor$2", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                final CidrBuildTarget<BC> target = ((CidrBuildConfigurationHelper<BC, CidrBuildTarget<BC>>)CidrRunConfigurationSettingsEditor.this.myConfigHelper).findTarget(target);
                CidrBuildConfigurationHelper<BC, TARGET> myConfigHelper = null;
                CidrBuildTarget<BC> cidrBuildTarget = null;
                String configurationName = null;
                Label_0089: {
                    try {
                        myConfigHelper = CidrRunConfigurationSettingsEditor.this.myConfigHelper;
                        cidrBuildTarget = target;
                        if (targetAndConfigurationData == null) {
                            configurationName = null;
                            break Label_0089;
                        }
                    }
                    catch (Throwable t2) {
                        throw a(t2);
                    }
                    configurationName = targetAndConfigurationData.configurationName;
                }
                final CidrBuildConfiguration configuration = myConfigHelper.findConfiguration((TARGET)cidrBuildTarget, configurationName);
                ComboBox myConfigurationCombo = null;
                CidrBuildConfiguration cidrBuildConfiguration = null;
                Object configurationName2 = null;
                Label_0138: {
                    try {
                        CidrRunConfigurationSettingsEditor.setSelectedItem(CidrRunConfigurationSettingsEditor.this.myTargetCombo, target, target);
                        myConfigurationCombo = CidrRunConfigurationSettingsEditor.this.myConfigurationCombo;
                        cidrBuildConfiguration = configuration;
                        if (targetAndConfigurationData == null) {
                            configurationName2 = null;
                            break Label_0138;
                        }
                    }
                    catch (Throwable t3) {
                        throw a(t3);
                    }
                    configurationName2 = targetAndConfigurationData.configurationName;
                }
                CidrRunConfigurationSettingsEditor.setSelectedItem(myConfigurationCombo, cidrBuildConfiguration, configurationName2);
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
    }
    
    protected void applyEditorTo(@NotNull final CONFIGURATION configuration) throws ConfigurationException {
        try {
            if (configuration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runConfiguration", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor", "applyEditorTo"));
            }
        }
        catch (ConfigurationException ex) {
            throw b((Exception)ex);
        }
        this.applyBuildAndConfigurationTo(configuration);
        this.myCommonProgramParameters.applyTo(configuration);
    }
    
    protected void applyBuildAndConfigurationTo(@NotNull final CONFIGURATION configuration) {
        try {
            if (configuration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runConfiguration", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor", "applyBuildAndConfigurationTo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final CidrBuildTarget cidrBuildTarget = (CidrBuildTarget)getSelectedItem((JComboBox)this.myTargetCombo);
        final CidrBuildConfiguration cidrBuildConfiguration = (CidrBuildConfiguration)getSelectedItem((JComboBox)this.myConfigurationCombo);
        BuildTargetData buildTargetData = null;
        Label_0095: {
            try {
                if (cidrBuildTarget != null) {
                    buildTargetData = new BuildTargetData(cidrBuildTarget);
                    break Label_0095;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            buildTargetData = (BuildTargetData)getSelectedInvalidItemValue((JComboBox)this.myTargetCombo);
        }
        final BuildTargetData buildTargetData2 = buildTargetData;
        String name = null;
        Label_0124: {
            try {
                if (cidrBuildConfiguration != null) {
                    name = cidrBuildConfiguration.getName();
                    break Label_0124;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            name = (String)getSelectedInvalidItemValue((JComboBox)this.myConfigurationCombo);
        }
        configuration.setTargetAndConfigurationData(new BuildTargetAndConfigurationData(buildTargetData2, name));
        this.syncBuildAndExecute(configuration, buildTargetData2);
    }
    
    protected void syncBuildAndExecute(@NotNull final CONFIGURATION configuration, @Nullable final BuildTargetData buildTargetData) {
        try {
            if (configuration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor", "syncBuildAndExecute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    protected static void setSelectedItem(@NotNull final ComboBox comboBox, @Nullable Object comboBoxItem, @Nullable final Object o) {
        try {
            if (comboBox == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "combo", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor", "setSelectedItem"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        comboBoxItem = createComboBoxItem(comboBoxItem, o);
        boolean b = false;
        int n = 0;
        while (true) {
            Label_0084: {
                Label_0089: {
                    try {
                        if (n >= comboBox.getItemCount()) {
                            break;
                        }
                        if (!Comparing.equal(comboBoxItem, comboBox.getItemAt(n))) {
                            break Label_0089;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    break Label_0084;
                }
                ++n;
                continue;
            }
            b = true;
            break;
        }
        try {
            if (!b) {
                comboBox.addItem(comboBoxItem);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        comboBox.setSelectedItem(comboBoxItem);
    }
    
    public static Object createComboBoxItem(Object o, final Object o2) {
        try {
            if (o != null || o2 == null) {
                return o;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        o = new InvalidItem(o2);
        return o;
    }
    
    @Nullable
    protected static Object getItem(final ItemEvent itemEvent) {
        final Object item = itemEvent.getItem();
        try {
            if (item instanceof InvalidItem) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return item;
    }
    
    @Nullable
    protected static Object getSelectedItem(final JComboBox comboBox) {
        final Object selectedItem = comboBox.getSelectedItem();
        try {
            if (selectedItem instanceof InvalidItem) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return selectedItem;
    }
    
    @Nullable
    protected static Object getSelectedInvalidItemValue(final JComboBox comboBox) {
        final Object selectedItem = comboBox.getSelectedItem();
        try {
            if (selectedItem instanceof InvalidItem) {
                return ((InvalidItem)selectedItem).myValue;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    public JComponent getAnchor() {
        return this.myAnchor;
    }
    
    public void setAnchor(@Nullable final JComponent anchor) {
        this.myAnchor = anchor;
        this.myCommonProgramParameters.setAnchor(anchor);
        this.myTargetLabel.setAnchor(anchor);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    protected static class InvalidItem
    {
        @NotNull
        Object myValue;
        
        private InvalidItem(@NotNull final Object myValue) {
            if (myValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor$InvalidItem", "<init>"));
            }
            this.myValue = myValue;
        }
        
        @Override
        public String toString() {
            return this.myValue.toString();
        }
        
        @Override
        public boolean equals(final Object o) {
            try {
                if (this == o) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0039: {
                try {
                    if (o == null) {
                        return false;
                    }
                    final InvalidItem invalidItem = this;
                    final Class<? extends InvalidItem> clazz = invalidItem.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                    break Label_0039;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final InvalidItem invalidItem = this;
                    final Class<? extends InvalidItem> clazz = invalidItem.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final InvalidItem invalidItem2 = (InvalidItem)o;
            try {
                if (!this.myValue.equals(invalidItem2.myValue)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return this.myValue.hashCode();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    protected static class MyComboBox extends ComboBox<String>
    {
        public MyComboBox(final int n) {
            super(n);
        }
        
        public void fireSelectedItemChanged() {
            this.selectedItemChanged();
        }
    }
    
    protected static class MyBaseRenderer extends GroupedElementsRenderer.List implements ListCellRenderer
    {
        private final boolean hasTargetsInSeveralProjects;
        private SimpleColoredComponent myComponent;
        
        public MyBaseRenderer(final boolean hasTargetsInSeveralProjects) {
            this.hasTargetsInSeveralProjects = hasTargetsInSeveralProjects;
        }
        
        protected JComponent createItemComponent() {
            this.myTextLabel = new ErrorLabel();
            return (JComponent)(this.myComponent = new SimpleColoredComponent() {
                {
                    this.setFocusBorderAroundIcon(true);
                }
            });
        }
        
        public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean opaque, final boolean b) {
            this.myComponent.clear();
            this.customizeCellRenderer(this.myComponent, list, o, n, opaque, b);
            final String s = (n == -1) ? null : this.getSeparatorAbove(list, o, n);
            this.configureComponent((String)null, (String)null, (Icon)null, (Icon)null, opaque, s != null, StringUtil.isEmptyOrSpaces(s) ? null : s, -1);
            this.myComponent.setOpaque(opaque);
            this.myRendererComponent.setBackground(this.myComponent.getBackground());
            return (Component)this.myRendererComponent;
        }
        
        protected String getSeparatorAbove(final JList list, final Object o, final int n) {
            return (n > 0 && list.getModel().getElementAt(n - 1) == null) ? "" : null;
        }
        
        protected void customizeCellRenderer(final SimpleColoredComponent simpleColoredComponent, final JList list, Object name, final int n, final boolean b, final boolean b2) {
            if (name instanceof InvalidItem) {
                this.appendNotFound(((InvalidItem)name).myValue, b);
            }
            else {
                if (name instanceof CidrBuildTarget) {
                    final CidrBuildTarget cidrBuildTarget = (CidrBuildTarget)name;
                    simpleColoredComponent.setIcon(cidrBuildTarget.getIcon());
                    simpleColoredComponent.append(cidrBuildTarget.getName());
                    if (this.hasTargetsInSeveralProjects) {
                        simpleColoredComponent.append(" (" + cidrBuildTarget.getProjectName() + ")", this.grayed(b));
                    }
                    return;
                }
                if (name instanceof CidrBuildConfiguration) {
                    name = ((CidrBuildConfiguration)name).getName();
                }
                this.addValue(simpleColoredComponent, list, name, b);
            }
        }
        
        protected void appendNotFound(final Object o, final boolean b) {
            this.myComponent.append(String.valueOf(o), b ? SimpleTextAttributes.REGULAR_ATTRIBUTES : SimpleTextAttributes.ERROR_ATTRIBUTES);
            this.myComponent.append(" (" + (this.isChecking() ? CidrBundle.message("test.configuration.checking", new Object[0]) : CidrBundle.message("build.configuration.dialog.item.parameterNotFound", new Object[0])) + ")", this.grayed(b));
        }
        
        protected boolean isChecking() {
            return false;
        }
        
        protected SimpleTextAttributes grayed(final boolean b) {
            return b ? SimpleTextAttributes.REGULAR_ATTRIBUTES : SimpleTextAttributes.GRAYED_ATTRIBUTES;
        }
        
        protected void addValue(final SimpleColoredComponent simpleColoredComponent, final JList list, Object o, final boolean b) {
            SimpleTextAttributes simpleTextAttributes = SimpleTextAttributes.REGULAR_ATTRIBUTES;
            if (o == null) {
                o = ((list.getModel().getSize() == 0) ? CidrBundle.message("build.configuration.dialog.item.parameterNoVariants", new Object[0]) : CidrBundle.message("build.configuration.dialog.item.parameterNotSelected", new Object[0]));
                simpleTextAttributes = this.grayed(b);
            }
            simpleColoredComponent.append(String.valueOf(o), simpleTextAttributes);
        }
    }
}
