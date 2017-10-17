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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public abstract class CidrTestRunConfigurationEditor<SUITE, CONFIGURATION extends CidrRunConfiguration, BC extends CidrBuildConfiguration, TARGET extends CidrBuildTarget<BC>, BCH extends CidrBuildConfigurationHelper<BC, TARGET>> extends CidrRunConfigurationSettingsEditor<CONFIGURATION, BC, TARGET, BCH>
{
    protected OCTextFieldWithSymbolAutoCompletion<SUITE> mySuiteField;
    protected EditorTextField myPatternField;
    private AsyncProcessIcon myProcessIcon;
    private JLabel myMethodComboLabel;
    private JLabel mySuiteFieldLabel;
    private JLabel myPatternFieldLabel;
    private JLabel mySuitesTipLabel;
    private JRadioButton myRbPattern;
    protected MyComboBox myMethodCombo;
    private MergingUpdateQueue myQueue;
    private Set<String> mySuiteMethods;
    private volatile boolean myIsDisposed;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public boolean isDisposed() {
        return this.myIsDisposed;
    }
    
    public CidrTestRunConfigurationEditor(@NotNull final Project project, @NotNull final BCH bch) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationEditor", "<init>"));
        }
        if (bch == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configHelper", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationEditor", "<init>"));
        }
        super(project, bch);
        this.mySuiteMethods = Collections.emptySet();
        this.myIsDisposed = false;
    }
    
    @Override
    protected void createEditorInner(final JPanel panel, final GridBag gridBag) {
        this.myQueue = new MergingUpdateQueue("UpdateMethods", 500, false, (JComponent)panel, (Disposable)this, (JComponent)panel, true);
        this.a(panel, gridBag);
        this.mySuiteFieldLabel = new JLabel(this.getSuiteFieldLabelName() + ":");
        final JPanel panel2 = new JPanel(new GridBagLayout());
        panel.add(panel2, gridBag.nextLine().next().insets(0, 0, 0, 0));
        final GridBag setDefaultFill = new GridBag().setDefaultFill(1);
        panel2.add(this.mySuiteFieldLabel, setDefaultFill.nextLine().next().weightx(0.9));
        (this.myProcessIcon = new AsyncProcessIcon("TestCompletion")).setVisible(false);
        panel2.add((Component)this.myProcessIcon, setDefaultFill.next().anchor(22).weightx(0.1));
        panel.add((Component)(this.mySuiteField = this.createSuiteField()), gridBag.next().coverLine().insets(0, 0, 0, 0));
        this.mySuiteFieldLabel.setLabelFor((Component)this.mySuiteField);
        panel.add(this.mySuitesTipLabel = (JLabel)new JBLabel(this.getSuitesTipMessage(), UIUtil.ComponentStyle.SMALL, UIUtil.FontColor.BRIGHTER), gridBag.nextLine().next().next().coverLine().insets((Insets)JBUI.insets(-1, 10, 4, 0)));
        final JBInsets insets = JBUI.insets(-1, -1, 12, -1);
        panel.add(this.myMethodComboLabel = new JLabel(this.getTestFieldLabelName() + ":"), gridBag.nextLine().next().insets((Insets)insets));
        panel.add((Component)(this.myMethodCombo = CidrRunConfigurationSettingsEditor.createCombo()), gridBag.next().coverLine().insets((Insets)insets));
        this.myMethodComboLabel.setLabelFor((Component)this.myMethodCombo);
        this.mySuiteField.setPlaceholder(this.getSuitePlaceholder());
        this.myMethodCombo.setRenderer((ListCellRenderer)new MyTestsRenderer());
        this.mySuiteField.getDocument().addDocumentListener((DocumentListener)new DocumentListener() {
            public void documentChanged(final DocumentEvent documentEvent) {
                CidrTestRunConfigurationEditor.this.scheduleSuiteAndTestsUpdate();
            }
        });
        panel.add(this.myPatternFieldLabel = new JLabel(CidrBundle.message("test.configuration.pattern", new Object[0]) + ":"), gridBag.nextLine().next());
        panel.add((Component)(this.myPatternField = new EditorTextField()), gridBag.next().coverLine());
        this.myPatternField.setVisible(false);
        this.myPatternField.setPlaceholder(this.getPatternPlaceholder());
        this.myPatternField.setPreferredWidth(150);
        this.myPatternFieldLabel.setLabelFor((Component)this.myPatternField);
        this.myPatternFieldLabel.setVisible(false);
        super.createEditorInner(panel, gridBag);
    }
    
    public void setPassThroughUpdateMode(final boolean passThrough) {
        this.myQueue.setPassThrough(passThrough);
    }
    
    protected void scheduleSuiteAndTestsUpdate() {
        this.myQueue.cancelAllUpdates();
        this.myQueue.queue((Update)new Update("go!") {
            public void run() {
                final boolean validSuiteName = CidrTestRunConfigurationEditor.this.isValidSuiteName();
                CidrTestRunConfigurationEditor.this.myMethodComboLabel.setEnabled(validSuiteName);
                CidrTestRunConfigurationEditor.this.myMethodCombo.setEnabled(validSuiteName);
                CidrTestRunConfigurationEditor.this.updateSuiteAndMethodControls();
            }
        });
    }
    
    protected OCTextFieldWithSymbolAutoCompletion<SUITE> createSuiteField() {
        return OCTextFieldWithSymbolAutoCompletion.create(this.myProject, this.createSuiteAdapter(), this.createSuiteCompletionCondition(), new OCTextFieldWithSymbolAutoCompletion.ProcessListener() {
            @Override
            public void onStarted() {
                CidrTestRunConfigurationEditor.this.myProcessIcon.setVisible(true);
            }
            
            @Override
            public void onFinished() {
                CidrTestRunConfigurationEditor.this.myProcessIcon.setVisible(false);
            }
        });
    }
    
    protected void disposeEditor() {
        this.myIsDisposed = true;
        this.mySuiteField.cancelRunningTasks();
        super.disposeEditor();
    }
    
    @NotNull
    protected abstract OCFieldAdapter<SUITE> createSuiteAdapter();
    
    @Nullable
    protected abstract Condition<SUITE> createSuiteCompletionCondition();
    
    private void a(final JPanel panel, final GridBag gridBag) {
        panel.add((Component)new JBLabel(ExecutionBundle.message("junit.configuration.configure.junit.test.kind.label", new Object[0])), gridBag.nextLine().next());
        final ButtonGroup buttonGroup = new ButtonGroup();
        final JRadioButton radioButton = new JRadioButton();
        radioButton.setText(this.getSuiteFieldLabelName() + " / " + this.getTestFieldLabelName());
        radioButton.setSelected(true);
        (this.myRbPattern = new JRadioButton()).setText(CidrBundle.message("test.configuration.pattern", new Object[0]));
        buttonGroup.add(radioButton);
        buttonGroup.add(this.myRbPattern);
        panel.add(radioButton, gridBag.next().weightx(0.0));
        panel.add(this.myRbPattern, gridBag.next().weightx(0.0).insets((Insets)JBUI.insetsBottom(4)));
        final ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                CidrTestRunConfigurationEditor.this.d();
            }
        };
        radioButton.addActionListener(actionListener);
        this.myRbPattern.addActionListener(actionListener);
    }
    
    private void d() {
        final boolean selected = this.myRbPattern.isSelected();
        JLabel myMethodComboLabel = null;
        boolean visible5 = false;
        Label_0105: {
            Label_0085: {
                MyComboBox myMethodCombo = null;
                Label_0065: {
                    JLabel mySuitesTipLabel = null;
                    Label_0045: {
                        JLabel mySuiteFieldLabel = null;
                        Label_0025: {
                            OCTextFieldWithSymbolAutoCompletion<SUITE> mySuiteField;
                            try {
                                mySuiteField = this.mySuiteField;
                                if (!selected) {
                                    final boolean visible = true;
                                    break Label_0025;
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw c(ex);
                            }
                            final boolean visible = false;
                            try {
                                mySuiteField.setVisible(visible);
                                mySuiteFieldLabel = this.mySuiteFieldLabel;
                                if (!selected) {
                                    final boolean visible2 = true;
                                    break Label_0045;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw c(ex2);
                            }
                        }
                        final boolean visible2 = false;
                        try {
                            mySuiteFieldLabel.setVisible(visible2);
                            mySuitesTipLabel = this.mySuitesTipLabel;
                            if (!selected) {
                                final boolean visible3 = true;
                                break Label_0065;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw c(ex3);
                        }
                    }
                    final boolean visible3 = false;
                    try {
                        mySuitesTipLabel.setVisible(visible3);
                        myMethodCombo = this.myMethodCombo;
                        if (!selected) {
                            final boolean visible4 = true;
                            break Label_0085;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw c(ex4);
                    }
                }
                final boolean visible4 = false;
                try {
                    myMethodCombo.setVisible(visible4);
                    myMethodComboLabel = this.myMethodComboLabel;
                    if (!selected) {
                        visible5 = true;
                        break Label_0105;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw c(ex5);
                }
            }
            visible5 = false;
        }
        myMethodComboLabel.setVisible(visible5);
        this.myPatternField.setVisible(selected);
        this.myPatternFieldLabel.setVisible(selected);
    }
    
    private void a() {
        final Editor editor = this.mySuiteField.getEditor();
        try {
            if (editor == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final MarkupModel markupModel = editor.getMarkupModel();
        markupModel.removeAllHighlighters();
        final String testSuiteText = this.getTestSuiteText();
        try {
            if (testSuiteText == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (!this.isValidSuiteName()) {
                markupModel.addRangeHighlighter(0, this.mySuiteField.getDocument().getText().length(), 5000, SimpleTextAttributes.ERROR_ATTRIBUTES.toTextAttributes(), HighlighterTargetArea.EXACT_RANGE);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
    }
    
    public abstract boolean isValidSuiteName();
    
    private void c() {
        this.a((String)this.myMethodCombo.getSelectedItem());
    }
    
    private void a(@Nullable String selectedItem) {
        if (this.getTestSuiteText() == null) {
            selectedItem = null;
        }
        this.mySuiteMethods = this.collectSuiteTests();
        final ArrayList<Comparable> list = new ArrayList<Comparable>(this.mySuiteMethods);
        Label_0099: {
            Label_0068: {
                Label_0055: {
                    try {
                        list.add("");
                        if (selectedItem == null) {
                            break Label_0068;
                        }
                        final ArrayList<Comparable> list2 = list;
                        final String s = selectedItem;
                        final boolean b = list2.contains(s);
                        if (!b) {
                            break Label_0055;
                        }
                        break Label_0068;
                    }
                    catch (IllegalArgumentException ex) {
                        throw c(ex);
                    }
                    try {
                        final ArrayList<Comparable> list2 = list;
                        final String s = selectedItem;
                        final boolean b = list2.contains(s);
                        if (!b) {
                            list.add(selectedItem);
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw c(ex2);
                    }
                }
                try {
                    Collections.sort(list);
                    if (selectedItem != null || list.isEmpty()) {
                        break Label_0099;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
            }
            selectedItem = (String)list.get(0);
        }
        this.myMethodCombo.setModel((ComboBoxModel)new CollectionComboBoxModel((List)list, (Object)""));
        this.myMethodCombo.setSelectedItem((Object)selectedItem);
    }
    
    @NotNull
    protected abstract Set<String> collectSuiteTests();
    
    @Override
    protected void resetEditorFrom(@NotNull final CONFIGURATION configuration) {
        try {
            if (configuration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "selected", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationEditor", "resetEditorFrom"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        Label_0069: {
            try {
                super.resetEditorFrom(configuration);
                if (CidrTestRunConfigurationEditor.$assertionsDisabled) {
                    break Label_0069;
                }
                final CidrRunConfiguration cidrRunConfiguration = configuration;
                final boolean b = cidrRunConfiguration instanceof CidrTestRunConfiguration;
                if (!b) {
                    break Label_0069;
                }
                break Label_0069;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final CidrRunConfiguration cidrRunConfiguration = configuration;
                final boolean b = cidrRunConfiguration instanceof CidrTestRunConfiguration;
                if (!b) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        final CidrTestRunConfigurationData testData = ((CidrTestRunConfiguration)configuration).getTestData();
        JRadioButton myRbPattern = null;
        boolean selected = false;
        Label_0153: {
            try {
                this.mySuiteField.setText(StringUtil.notNullize(testData.getTestSuite()));
                this.a(StringUtil.notNullize(testData.getTestName()));
                this.myPatternField.setText(StringUtil.notNullize(testData.getTestPattern()));
                myRbPattern = this.myRbPattern;
                if (testData.getTestMode() == CidrScopeInfo.Mode.PATTERN) {
                    selected = true;
                    break Label_0153;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
            selected = false;
        }
        myRbPattern.setSelected(selected);
        this.d();
    }
    
    @Override
    protected void applyEditorTo(@NotNull final CONFIGURATION configuration) throws ConfigurationException {
        try {
            if (configuration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "selected", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationEditor", "applyEditorTo"));
            }
        }
        catch (ConfigurationException ex) {
            throw c((Exception)ex);
        }
        Label_0069: {
            try {
                super.applyEditorTo(configuration);
                if (CidrTestRunConfigurationEditor.$assertionsDisabled) {
                    break Label_0069;
                }
                final CidrRunConfiguration cidrRunConfiguration = configuration;
                final boolean b = cidrRunConfiguration instanceof CidrTestRunConfiguration;
                if (!b) {
                    break Label_0069;
                }
                break Label_0069;
            }
            catch (ConfigurationException ex2) {
                throw c((Exception)ex2);
            }
            try {
                final CidrRunConfiguration cidrRunConfiguration = configuration;
                final boolean b = cidrRunConfiguration instanceof CidrTestRunConfiguration;
                if (!b) {
                    throw new AssertionError();
                }
            }
            catch (ConfigurationException ex3) {
                throw c((Exception)ex3);
            }
        }
        final CidrTestRunConfigurationData testData = ((CidrTestRunConfiguration)configuration).getTestData();
        CidrScopeInfo.Mode mode = null;
        Label_0114: {
            try {
                if (this.myRbPattern.isSelected()) {
                    mode = CidrScopeInfo.Mode.PATTERN;
                    break Label_0114;
                }
            }
            catch (ConfigurationException ex4) {
                throw c((Exception)ex4);
            }
            mode = CidrScopeInfo.Mode.SUITE_TEST;
        }
        final CidrScopeInfo.Mode testMode = mode;
        CidrTestRunConfigurationData cidrTestRunConfigurationData3 = null;
        String nullize = null;
        Label_0190: {
            Label_0158: {
                CidrTestRunConfigurationData cidrTestRunConfigurationData2 = null;
                Label_0135: {
                    CidrTestRunConfigurationData cidrTestRunConfigurationData;
                    try {
                        cidrTestRunConfigurationData = testData;
                        if (testMode == CidrScopeInfo.Mode.SUITE_TEST) {
                            final String testSuiteText = this.getTestSuiteText();
                            break Label_0135;
                        }
                    }
                    catch (ConfigurationException ex5) {
                        throw c((Exception)ex5);
                    }
                    final String testSuiteText = null;
                    try {
                        cidrTestRunConfigurationData.setTestSuite(testSuiteText);
                        cidrTestRunConfigurationData2 = testData;
                        if (testMode == CidrScopeInfo.Mode.SUITE_TEST) {
                            final String b2 = this.b();
                            break Label_0158;
                        }
                    }
                    catch (ConfigurationException ex6) {
                        throw c((Exception)ex6);
                    }
                }
                final String b2 = null;
                try {
                    cidrTestRunConfigurationData2.setTestName(b2);
                    cidrTestRunConfigurationData3 = testData;
                    if (testMode == CidrScopeInfo.Mode.PATTERN) {
                        nullize = StringUtil.nullize(this.myPatternField.getText().trim());
                        break Label_0190;
                    }
                }
                catch (ConfigurationException ex7) {
                    throw c((Exception)ex7);
                }
            }
            nullize = null;
        }
        cidrTestRunConfigurationData3.setTestPattern(nullize);
        testData.setTestMode(testMode);
    }
    
    @Nullable
    protected String getTestSuiteText() {
        return StringUtil.nullize(this.mySuiteField.getText().trim());
    }
    
    @Nullable
    private String b() {
        return StringUtil.nullize((String)this.myMethodCombo.getSelectedItem());
    }
    
    protected void updateSuiteAndMethodControls() {
        try {
            if (this.myIsDisposed) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        this.a();
        this.c();
    }
    
    public TextFieldWithAutoCompletion<SUITE> getSuiteField() {
        return this.mySuiteField;
    }
    
    protected boolean isChecking() {
        return false;
    }
    
    protected String getPatternPlaceholder() {
        return "";
    }
    
    protected abstract String getSuitePlaceholder();
    
    protected abstract String getSuitesTipMessage();
    
    protected abstract String getTestFieldLabelName();
    
    protected abstract String getSuiteFieldLabelName();
    
    protected abstract String getAllTestsMessage();
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!CidrTestRunConfigurationEditor.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
    
    private class MyTestsRenderer extends MyBaseRenderer
    {
        public MyTestsRenderer() {
            super(CidrTestRunConfigurationEditor.this.hasTargetsInSeveralProjects());
        }
        
        @Override
        protected boolean isChecking() {
            return CidrTestRunConfigurationEditor.this.isChecking();
        }
        
        @Override
        protected void customizeCellRenderer(final SimpleColoredComponent simpleColoredComponent, final JList list, final Object o, final int n, final boolean b, final boolean b2) {
            if (o instanceof String) {
                final String s = (String)o;
                if (s.isEmpty()) {
                    simpleColoredComponent.append(CidrTestRunConfigurationEditor.this.getAllTestsMessage(), this.grayed(b));
                }
                else if (!CidrTestRunConfigurationEditor.this.mySuiteMethods.contains(s)) {
                    this.appendNotFound(s, b);
                }
                else {
                    simpleColoredComponent.append(s);
                }
            }
        }
    }
}
