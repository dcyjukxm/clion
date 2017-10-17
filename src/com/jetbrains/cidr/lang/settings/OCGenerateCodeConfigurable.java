// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.util.ArrayList;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import java.util.Arrays;
import com.intellij.util.ArrayUtil;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import java.awt.Dimension;
import com.intellij.ui.ToolbarDecorator;
import javax.swing.JList;
import javax.swing.ListModel;
import com.intellij.ui.components.JBList;
import javax.swing.JScrollPane;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.util.PlatformUtils;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.util.Iterator;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.util.List;
import javax.swing.border.Border;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.openapi.ui.VerticalFlowLayout;
import java.awt.Component;
import com.intellij.util.ui.GridBag;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.openapi.application.ApplicationBundle;
import org.jetbrains.annotations.Nls;
import com.jetbrains.cidr.lang.util.OCDeclarationKind;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;

public class OCGenerateCodeConfigurable implements CodeStyleConfigurable
{
    @NotNull
    private final CodeStyleSettings mySettings;
    private JCheckBox myRetainParametersBox;
    private JCheckBox myUseSettersBox;
    private JCheckBox myPutIvarsToImplementationBox;
    private JCheckBox mySemicolonAfterMethodSignatureBox;
    private JCheckBox myUseNSTypesInIntroduce;
    private JCheckBox myLineCommentOnFirstColumnCheckBox;
    private JCheckBox myBlockCommentOnFirstColumnCheckBox;
    private JCheckBox myBlockCommentIndentCheckBox;
    private JCheckBox myBriefDocTagCheckBox;
    private EnumOption<OCCodeStyleSettings.DocTagPrefix> myLineTagPrefixEnumOption;
    private EnumOption<OCCodeStyleSettings.DocTagPrefix> myBlockTagPrefixEnumOption;
    private JPanel myIvarReleaseFormatComponent;
    private JPanel myGenerateIvarsForPropertiesComponent;
    private JPanel myTypeQualifiersPlacingComponent;
    private JTextField myIvarPrefixField;
    private JTextField myIvarSuffixField;
    private DefaultListModel<OCDeclarationKind> myFileDeclsOrder;
    private DefaultListModel<OCDeclarationKind> myClassDeclsOrder;
    private JTextField myFieldPrefixField;
    private JTextField myFieldSuffixField;
    private JTextField myGetterPrefixField;
    private JTextField mySetterPrefixField;
    private JCheckBox myGenerateAdditionalEqBox;
    private JCheckBox myGenerateAdditionalRelBox;
    private JCheckBox myInsertVirtualWithOverrideBox;
    private JCheckBox myUseModernCastsBox;
    private OCEnumComboOption<OCCodeStyleSettings.ReleaseStyle> myIvarReleaseFormatOption;
    private OCEnumComboOption<OCCodeStyleSettings.RememberedOption> myGenerateIvarsForPropertiesOption;
    private OCEnumGroupOption<OCCodeStyleSettings.Placement> myTypeQualifiersPlacingOption;
    private static final int VGAP_NEXT_SECTION = 13;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCGenerateCodeConfigurable(@NotNull final CodeStyleSettings mySettings) {
        if (mySettings == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "<init>"));
        }
        this.mySettings = mySettings;
    }
    
    @Nls
    public String getDisplayName() {
        return "Code Generation";
    }
    
    public String getHelpTopic() {
        return "Code_Style_Code_Gen";
    }
    
    private void b(final JPanel panel, final JPanel panel2) {
        this.myLineCommentOnFirstColumnCheckBox = new JCheckBox(ApplicationBundle.message("checkbox.line.comment.at.first.column", new Object[0]));
        this.myBlockCommentOnFirstColumnCheckBox = new JCheckBox(ApplicationBundle.message("checkbox.block.comment.at.first.column", new Object[0]));
        this.myBlockCommentIndentCheckBox = new JCheckBox(OCBundle.message("checkbox.block.comment.indent", new Object[0]));
        this.myFileDeclsOrder = new DefaultListModel<OCDeclarationKind>();
        this.myTypeQualifiersPlacingOption = new OCEnumGroupOption<OCCodeStyleSettings.Placement>("Const/volatile qualifier placement", OCEnumGroupOption.Presentation.Line, OCCodeStyleSettings.Placement.values(), new String[] { "const int x = 0", "int const x = 0" });
        this.myTypeQualifiersPlacingComponent = this.myTypeQualifiersPlacingOption.createComponent();
        final JPanel panel3 = new JPanel(new GridBagLayout());
        final GridBag setDefaultAnchor = new GridBag().setDefaultWeightX(1.0).setDefaultAnchor(17);
        panel3.add(this.myLineCommentOnFirstColumnCheckBox, setDefaultAnchor.nextLine());
        panel3.add(this.myBlockCommentOnFirstColumnCheckBox, setDefaultAnchor.nextLine());
        panel3.add(this.myBlockCommentIndentCheckBox, setDefaultAnchor.nextLine());
        final JPanel panel4 = new JPanel((LayoutManager)new VerticalFlowLayout());
        panel4.setBorder((Border)IdeBorderFactory.createTitledBorder("General", true));
        panel4.add(panel3);
        panel4.add(this.myTypeQualifiersPlacingComponent);
        panel.add(panel4);
        final List<OCDeclarationKind> ourFileDeclarationKinds = OCDeclarationKind.ourFileDeclarationKinds;
        final DefaultListModel<OCDeclarationKind> myFileDeclsOrder = new DefaultListModel<OCDeclarationKind>();
        this.myFileDeclsOrder = myFileDeclsOrder;
        panel2.add(a(ourFileDeclarationKinds, myFileDeclsOrder, "Top-level declarations order"));
    }
    
    private void a(final JPanel panel, final JPanel panel2) {
        this.myRetainParametersBox = new JCheckBox("Retain object parameters in initWith...");
        this.myUseSettersBox = new JCheckBox("Use property setters in initWith...");
        this.myPutIvarsToImplementationBox = new JCheckBox("Put ivars to implementation if possible");
        this.mySemicolonAfterMethodSignatureBox = new JCheckBox("Semicolon after method signature in implementation");
        this.myIvarReleaseFormatOption = new OCEnumComboOption<OCCodeStyleSettings.ReleaseStyle>("Ivars release style (for 'dealloc')", OCCodeStyleSettings.ReleaseStyle.values(), new String[] { "[ivar release]", "[ivar release], ivar = nil", "property = nil" });
        this.myGenerateIvarsForPropertiesOption = new OCEnumComboOption<OCCodeStyleSettings.RememberedOption>("Generate ivar declarations for properties", OCCodeStyleSettings.RememberedOption.values(), new String[] { "Always generate", "Never generate", "Ask" });
        this.myUseNSTypesInIntroduce = new JCheckBox("Use NSInteger and CGFloat in introduce variable");
        this.myIvarPrefixField = new JTextField(5);
        this.myIvarSuffixField = new JTextField(5);
        this.myIvarReleaseFormatComponent = this.myIvarReleaseFormatOption.createComponent();
        this.myGenerateIvarsForPropertiesComponent = this.myGenerateIvarsForPropertiesOption.createComponent();
        final JPanel panel3 = new JPanel((LayoutManager)new VerticalFlowLayout());
        panel3.setBorder((Border)IdeBorderFactory.createTitledBorder("Objective-C", true));
        final JPanel panel4 = new JPanel(new GridBagLayout());
        final GridBag setDefaultAnchor = new GridBag().setDefaultWeightX(1.0).setDefaultAnchor(17);
        panel4.add(new JLabel("Prefix for instance variables:"), setDefaultAnchor.nextLine().next().weightx(0.0).insets(0, 0, 0, 10));
        panel4.add(this.myIvarPrefixField, setDefaultAnchor.next().fillCellNone());
        panel4.add(new JLabel("Suffix for instance variables:"), setDefaultAnchor.nextLine().next().weightx(0.0).insets(0, 0, 0, 10));
        panel4.add(this.myIvarSuffixField, setDefaultAnchor.next().fillCellNone());
        panel4.add(this.myRetainParametersBox, setDefaultAnchor.nextLine().next().coverLine().insets(13, -1, -1, -1));
        panel4.add(this.myUseSettersBox, setDefaultAnchor.nextLine().next().coverLine());
        panel4.add(this.mySemicolonAfterMethodSignatureBox, setDefaultAnchor.nextLine().next().coverLine());
        panel4.add(this.myPutIvarsToImplementationBox, setDefaultAnchor.nextLine().next().coverLine());
        panel4.add(this.myUseNSTypesInIntroduce, setDefaultAnchor.nextLine().next().coverLine());
        panel3.add(panel4);
        panel3.add(this.myIvarReleaseFormatComponent);
        panel3.add(this.myGenerateIvarsForPropertiesComponent);
        panel.add(panel3);
        final List<OCDeclarationKind> ourClassDeclarationKinds = OCDeclarationKind.ourClassDeclarationKinds;
        final DefaultListModel<OCDeclarationKind> myClassDeclsOrder = new DefaultListModel<OCDeclarationKind>();
        this.myClassDeclsOrder = myClassDeclsOrder;
        panel2.add(a(ourClassDeclarationKinds, myClassDeclsOrder, "Objective-C class order"));
    }
    
    private void a(final JPanel panel) {
        this.myFieldPrefixField = new JTextField(5);
        this.myFieldSuffixField = new JTextField(5);
        this.myGetterPrefixField = new JTextField(5);
        this.mySetterPrefixField = new JTextField(5);
        this.myGenerateAdditionalEqBox = new JCheckBox(OCBundle.message("generate.comparison.operators.additional.eq.option.desc", new Object[0]));
        this.myGenerateAdditionalRelBox = new JCheckBox(OCBundle.message("generate.comparison.operators.additional.rel.option.desc", new Object[0]));
        this.myInsertVirtualWithOverrideBox = new JCheckBox(OCBundle.message("override.implement.cpp.action.insertVirtualWithOverride", new Object[0]));
        this.myUseModernCastsBox = new JCheckBox(OCBundle.message("intentions.insert.cast.use.modern", new Object[0]));
        final JPanel panel2 = new JPanel((LayoutManager)new VerticalFlowLayout());
        panel2.setBorder((Border)IdeBorderFactory.createTitledBorder("C++", true));
        final JPanel panel3 = new JPanel(new GridBagLayout());
        final GridBag setDefaultAnchor = new GridBag().setDefaultWeightX(1.0).setDefaultAnchor(17);
        panel3.add(new JLabel("Prefix for fields:"), setDefaultAnchor.nextLine().next().weightx(0.0).insets(0, 0, 0, 10));
        panel3.add(this.myFieldPrefixField, setDefaultAnchor.next().fillCellNone());
        panel3.add(new JLabel("Prefix for getters:"), setDefaultAnchor.next().weightx(0.0).insets(0, 0, 0, 10));
        panel3.add(this.myGetterPrefixField, setDefaultAnchor.next().fillCellNone());
        panel3.add(new JLabel("Suffix for fields:"), setDefaultAnchor.nextLine().next().weightx(0.0).insets(0, 0, 0, 10));
        panel3.add(this.myFieldSuffixField, setDefaultAnchor.next().fillCellNone());
        panel3.add(new JLabel("Prefix for setters:"), setDefaultAnchor.next().weightx(0.0).insets(0, 0, 0, 10));
        panel3.add(this.mySetterPrefixField, setDefaultAnchor.next().fillCellNone());
        panel3.add(this.myGenerateAdditionalEqBox, setDefaultAnchor.nextLine().next().coverLine().insets(13, -1, -1, -1));
        panel3.add(this.myGenerateAdditionalRelBox, setDefaultAnchor.nextLine().next().coverLine());
        panel3.add(this.myInsertVirtualWithOverrideBox, setDefaultAnchor.nextLine().next().coverLine().insets(13, -1, -1, -1));
        panel3.add(this.myUseModernCastsBox, setDefaultAnchor.nextLine().next().coverLine().insets(13, -1, -1, -1));
        panel2.add(panel3);
        panel.add(panel2);
    }
    
    private void b(final JPanel panel) {
        final JPanel panel2 = new JPanel((LayoutManager)new VerticalFlowLayout(false, false));
        panel2.setBorder((Border)IdeBorderFactory.createTitledBorder("Documentation comments", true));
        this.myBriefDocTagCheckBox = new JCheckBox("Add @brief tag");
        this.myLineTagPrefixEnumOption = new EnumOption<OCCodeStyleSettings.DocTagPrefix>("Tag prefix in line comments", OCCodeStyleSettings.DocTagPrefix.values(), new String[] { "@param", "\\param" });
        this.myBlockTagPrefixEnumOption = new EnumOption<OCCodeStyleSettings.DocTagPrefix>("Tag prefix in block comments", OCCodeStyleSettings.DocTagPrefix.values(), new String[] { "@param", "\\param" });
        final JPanel panel3 = new JPanel(new GridBagLayout());
        final GridBag setDefaultAnchor = new GridBag().setDefaultWeightX(0.0).setDefaultAnchor(17);
        panel3.add(this.myBriefDocTagCheckBox, setDefaultAnchor.nextLine().next().insets(0, 0, 4, 0));
        panel3.add(this.myLineTagPrefixEnumOption.getLabel(), setDefaultAnchor.nextLine().next().insets(4, 0, 0, 10));
        final Iterator<JRadioButton> iterator = this.myLineTagPrefixEnumOption.getButtons().iterator();
        while (iterator.hasNext()) {
            panel3.add(iterator.next(), setDefaultAnchor.next());
        }
        panel3.add(this.myBlockTagPrefixEnumOption.getLabel(), setDefaultAnchor.nextLine().next().insets(0, 0, 0, 10));
        final Iterator<JRadioButton> iterator2 = this.myBlockTagPrefixEnumOption.getButtons().iterator();
        while (iterator2.hasNext()) {
            panel3.add(iterator2.next(), setDefaultAnchor.next());
        }
        panel2.add(panel3);
        panel.add(panel2);
    }
    
    public JComponent createComponent() {
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel panel2 = new JPanel((LayoutManager)new VerticalFlowLayout());
        final JPanel panel3 = new JPanel((LayoutManager)new VerticalFlowLayout());
        try {
            this.b(panel2, panel3);
            if (PlatformUtils.isAppCode()) {
                this.a(panel2, panel3);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.a(panel2);
        this.b(panel2);
        panel.add(panel2, "Center");
        panel.add(panel3, "East");
        final JScrollPane scrollPane = ScrollPaneFactory.createScrollPane((Component)panel);
        scrollPane.setHorizontalScrollBarPolicy(30);
        scrollPane.setVerticalScrollBarPolicy(20);
        scrollPane.setBorder(null);
        return scrollPane;
    }
    
    private static JPanel a(final List<OCDeclarationKind> list, final DefaultListModel<OCDeclarationKind> defaultListModel, final String s) {
        final JBList list2 = new JBList((ListModel)defaultListModel);
        int max = 150;
        int n = 0;
        for (int i = 0; i < list.size(); ++i) {
            final Dimension preferredSize = list2.getCellRenderer().getListCellRendererComponent((JList<? extends OCDeclarationKind>)list2, list.get(i), i, true, true).getPreferredSize();
            max = Math.max(max, preferredSize.width);
            n += (int)preferredSize.getHeight();
        }
        final JPanel panel = ToolbarDecorator.createDecorator((JList)list2).disableAddAction().disableRemoveAction().setPreferredSize(new Dimension(max, n)).createPanel();
        panel.setBorder(IdeBorderFactory.createBorder());
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(panel);
        panel2.setBorder((Border)IdeBorderFactory.createTitledBorder(s, false));
        return panel2;
    }
    
    public boolean isModified() {
        boolean b = this.b() | this.c();
        if (PlatformUtils.isAppCode()) {
            b |= this.a();
        }
        return b | this.d();
    }
    
    private boolean c() {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)this.mySettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        return this.isModified(this.myFieldPrefixField, ocCodeStyleSettings.FIELDS_PREFIX) | this.isModified(this.myFieldSuffixField, ocCodeStyleSettings.FIELDS_SUFFIX) | this.isModified(this.myGetterPrefixField, ocCodeStyleSettings.GETTERS_PREFIX) | this.isModified(this.mySetterPrefixField, ocCodeStyleSettings.SETTERS_PREFIX) | a(this.myGenerateAdditionalEqBox, ocCodeStyleSettings.GENERATE_ADDITIONAL_EQ_OPERATORS) | a(this.myGenerateAdditionalRelBox, ocCodeStyleSettings.GENERATE_ADDITIONAL_REL_OPERATORS) | a(this.myInsertVirtualWithOverrideBox, ocCodeStyleSettings.INSERT_VIRTUAL_WITH_OVERRIDE) | a(this.myUseModernCastsBox, ocCodeStyleSettings.USE_MODERN_CASTS);
    }
    
    private boolean a() {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)this.mySettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        return a(this.myRetainParametersBox, ocCodeStyleSettings.RETAIN_OBJECT_PARAMETERS_IN_CONSTRUCTOR) | a(this.myUseSettersBox, ocCodeStyleSettings.USE_SETTERS_IN_CONSTRUCTOR) | a(this.myPutIvarsToImplementationBox, ocCodeStyleSettings.PUT_IVARS_TO_IMPLEMENTATION) | a(this.mySemicolonAfterMethodSignatureBox, ocCodeStyleSettings.SEMICOLON_AFTER_METHOD_SIGNATURE) | a(this.myUseNSTypesInIntroduce, ocCodeStyleSettings.INTRODUCE_USE_NS_TYPES) | a(this.myIvarReleaseFormatOption, this.myIvarReleaseFormatComponent, ocCodeStyleSettings.RELEASE_STYLE) | a(this.myGenerateIvarsForPropertiesOption, this.myGenerateIvarsForPropertiesComponent, ocCodeStyleSettings.GENERATE_INSTANCE_VARIABLES_FOR_PROPERTIES) | this.isModified(this.myIvarPrefixField, ocCodeStyleSettings.IVARS_PREFIX) | this.isModified(this.myIvarSuffixField, ocCodeStyleSettings.IVARS_SUFFIX) | a(this.myClassDeclsOrder, ocCodeStyleSettings.CLASS_DECLARATIONS_ORDER);
    }
    
    private boolean b() {
        final CommonCodeStyleSettings commonSettings = this.mySettings.getCommonSettings((Language)OCLanguage.getInstance());
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)this.mySettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        return a(this.myLineCommentOnFirstColumnCheckBox, commonSettings.LINE_COMMENT_AT_FIRST_COLUMN) | a(this.myBlockCommentOnFirstColumnCheckBox, commonSettings.BLOCK_COMMENT_AT_FIRST_COLUMN) | a(this.myBlockCommentIndentCheckBox, ocCodeStyleSettings.INDENT_BLOCK_COMMENT) | a(this.myFileDeclsOrder, ocCodeStyleSettings.FILE_DECLARATIONS_ORDER) | a(this.myTypeQualifiersPlacingOption, this.myTypeQualifiersPlacingComponent, ocCodeStyleSettings.TYPE_QUALIFIERS_PLACEMENT);
    }
    
    private boolean d() {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)this.mySettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        return a(this.myBriefDocTagCheckBox, ocCodeStyleSettings.ADD_BRIEF_TAG) | a(this.myLineTagPrefixEnumOption, ocCodeStyleSettings.TAG_PREFIX_OF_LINE_COMMENT) | a(this.myBlockTagPrefixEnumOption, ocCodeStyleSettings.TAG_PREFIX_OF_BLOCK_COMMENT);
    }
    
    public void apply() throws ConfigurationException {
        this.apply(this.mySettings);
    }
    
    public void apply(@NotNull final CodeStyleSettings codeStyleSettings) throws ConfigurationException {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "apply"));
            }
        }
        catch (ConfigurationException ex) {
            throw b((Exception)ex);
        }
        try {
            this.a(codeStyleSettings);
            this.d(codeStyleSettings);
            if (PlatformUtils.isAppCode()) {
                this.g(codeStyleSettings);
            }
        }
        catch (ConfigurationException ex2) {
            throw b((Exception)ex2);
        }
        this.b(codeStyleSettings);
    }
    
    private void d(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "applyCppOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        ocCodeStyleSettings.FIELDS_PREFIX = this.myFieldPrefixField.getText().trim();
        ocCodeStyleSettings.FIELDS_SUFFIX = this.myFieldSuffixField.getText().trim();
        ocCodeStyleSettings.GETTERS_PREFIX = this.myGetterPrefixField.getText().trim();
        ocCodeStyleSettings.SETTERS_PREFIX = this.mySetterPrefixField.getText().trim();
        ocCodeStyleSettings.GENERATE_ADDITIONAL_EQ_OPERATORS = this.myGenerateAdditionalEqBox.isSelected();
        ocCodeStyleSettings.GENERATE_ADDITIONAL_REL_OPERATORS = this.myGenerateAdditionalRelBox.isSelected();
        ocCodeStyleSettings.INSERT_VIRTUAL_WITH_OVERRIDE = this.myInsertVirtualWithOverrideBox.isSelected();
        ocCodeStyleSettings.USE_MODERN_CASTS = this.myUseModernCastsBox.isSelected();
    }
    
    private void g(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "applyObjCOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        ocCodeStyleSettings.RETAIN_OBJECT_PARAMETERS_IN_CONSTRUCTOR = this.myRetainParametersBox.isSelected();
        ocCodeStyleSettings.USE_SETTERS_IN_CONSTRUCTOR = this.myUseSettersBox.isSelected();
        ocCodeStyleSettings.PUT_IVARS_TO_IMPLEMENTATION = this.myPutIvarsToImplementationBox.isSelected();
        ocCodeStyleSettings.SEMICOLON_AFTER_METHOD_SIGNATURE = this.mySemicolonAfterMethodSignatureBox.isSelected();
        ocCodeStyleSettings.INTRODUCE_USE_NS_TYPES = this.myUseNSTypesInIntroduce.isSelected();
        ocCodeStyleSettings.RELEASE_STYLE = this.myIvarReleaseFormatOption.getSelectedValue(this.myIvarReleaseFormatComponent);
        ocCodeStyleSettings.GENERATE_INSTANCE_VARIABLES_FOR_PROPERTIES = this.myGenerateIvarsForPropertiesOption.getSelectedValue(this.myGenerateIvarsForPropertiesComponent);
        ocCodeStyleSettings.IVARS_PREFIX = this.myIvarPrefixField.getText().trim();
        ocCodeStyleSettings.IVARS_SUFFIX = this.myIvarSuffixField.getText().trim();
        ocCodeStyleSettings.CLASS_DECLARATIONS_ORDER = Arrays.asList((OCDeclarationKind[])ArrayUtil.toObjectArray((Class)OCDeclarationKind.class, this.myClassDeclsOrder.toArray()));
    }
    
    private void a(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "applyGeneralOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final CommonCodeStyleSettings commonSettings = codeStyleSettings.getCommonSettings((Language)OCLanguage.getInstance());
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        commonSettings.LINE_COMMENT_AT_FIRST_COLUMN = this.myLineCommentOnFirstColumnCheckBox.isSelected();
        commonSettings.BLOCK_COMMENT_AT_FIRST_COLUMN = this.myBlockCommentOnFirstColumnCheckBox.isSelected();
        ocCodeStyleSettings.INDENT_BLOCK_COMMENT = this.myBlockCommentIndentCheckBox.isSelected();
        ocCodeStyleSettings.FILE_DECLARATIONS_ORDER = Arrays.asList((OCDeclarationKind[])ArrayUtil.toObjectArray((Class)OCDeclarationKind.class, this.myFileDeclsOrder.toArray()));
        ocCodeStyleSettings.TYPE_QUALIFIERS_PLACEMENT = this.myTypeQualifiersPlacingOption.getSelectedValue(this.myTypeQualifiersPlacingComponent);
    }
    
    private void b(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "applyDocCommentOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        ocCodeStyleSettings.ADD_BRIEF_TAG = this.myBriefDocTagCheckBox.isSelected();
        ocCodeStyleSettings.TAG_PREFIX_OF_LINE_COMMENT = this.myLineTagPrefixEnumOption.getSelected();
        ocCodeStyleSettings.TAG_PREFIX_OF_BLOCK_COMMENT = this.myBlockTagPrefixEnumOption.getSelected();
    }
    
    public static void customizeSettings(@NotNull final CodeStyleSettingsCustomizable codeStyleSettingsCustomizable) {
        try {
            if (codeStyleSettingsCustomizable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "customizeSettings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        codeStyleSettingsCustomizable.showStandardOptions(new String[] { "LINE_COMMENT_AT_FIRST_COLUMN", "BLOCK_COMMENT_AT_FIRST_COLUMN" });
    }
    
    public void reset() {
        this.reset(this.mySettings);
    }
    
    public void reset(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "reset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            this.e(codeStyleSettings);
            this.c(codeStyleSettings);
            if (PlatformUtils.isAppCode()) {
                this.h(codeStyleSettings);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        this.f(codeStyleSettings);
    }
    
    private void c(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "resetCppOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        this.myFieldPrefixField.setText(ocCodeStyleSettings.FIELDS_PREFIX);
        this.myFieldSuffixField.setText(ocCodeStyleSettings.FIELDS_SUFFIX);
        this.myGetterPrefixField.setText(ocCodeStyleSettings.GETTERS_PREFIX);
        this.mySetterPrefixField.setText(ocCodeStyleSettings.SETTERS_PREFIX);
        this.myGenerateAdditionalEqBox.setSelected(ocCodeStyleSettings.GENERATE_ADDITIONAL_EQ_OPERATORS);
        this.myGenerateAdditionalRelBox.setSelected(ocCodeStyleSettings.GENERATE_ADDITIONAL_REL_OPERATORS);
        this.myInsertVirtualWithOverrideBox.setSelected(ocCodeStyleSettings.INSERT_VIRTUAL_WITH_OVERRIDE);
        this.myUseModernCastsBox.setSelected(ocCodeStyleSettings.USE_MODERN_CASTS);
    }
    
    private void h(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "resetObjCOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        this.myRetainParametersBox.setSelected(ocCodeStyleSettings.RETAIN_OBJECT_PARAMETERS_IN_CONSTRUCTOR);
        this.myUseSettersBox.setSelected(ocCodeStyleSettings.USE_SETTERS_IN_CONSTRUCTOR);
        this.myPutIvarsToImplementationBox.setSelected(ocCodeStyleSettings.PUT_IVARS_TO_IMPLEMENTATION);
        this.mySemicolonAfterMethodSignatureBox.setSelected(ocCodeStyleSettings.SEMICOLON_AFTER_METHOD_SIGNATURE);
        this.myUseNSTypesInIntroduce.setSelected(ocCodeStyleSettings.INTRODUCE_USE_NS_TYPES);
        this.myIvarReleaseFormatOption.selectValue(this.myIvarReleaseFormatComponent, ocCodeStyleSettings.RELEASE_STYLE);
        this.myGenerateIvarsForPropertiesOption.selectValue(this.myGenerateIvarsForPropertiesComponent, ocCodeStyleSettings.GENERATE_INSTANCE_VARIABLES_FOR_PROPERTIES);
        this.myIvarPrefixField.setText(ocCodeStyleSettings.IVARS_PREFIX);
        this.myIvarSuffixField.setText(ocCodeStyleSettings.IVARS_SUFFIX);
        this.myClassDeclsOrder.removeAllElements();
        final Iterator<OCDeclarationKind> iterator = ocCodeStyleSettings.CLASS_DECLARATIONS_ORDER.iterator();
        while (iterator.hasNext()) {
            this.myClassDeclsOrder.addElement(iterator.next());
        }
    }
    
    private void e(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "resetGeneralOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final CommonCodeStyleSettings commonSettings = codeStyleSettings.getCommonSettings((Language)OCLanguage.getInstance());
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        this.myLineCommentOnFirstColumnCheckBox.setSelected(commonSettings.LINE_COMMENT_AT_FIRST_COLUMN);
        this.myBlockCommentOnFirstColumnCheckBox.setSelected(commonSettings.BLOCK_COMMENT_AT_FIRST_COLUMN);
        this.myBlockCommentIndentCheckBox.setSelected(ocCodeStyleSettings.INDENT_BLOCK_COMMENT);
        this.myTypeQualifiersPlacingOption.selectValue(this.myTypeQualifiersPlacingComponent, ocCodeStyleSettings.TYPE_QUALIFIERS_PLACEMENT);
        this.myFileDeclsOrder.removeAllElements();
        final Iterator<OCDeclarationKind> iterator = ocCodeStyleSettings.FILE_DECLARATIONS_ORDER.iterator();
        while (iterator.hasNext()) {
            this.myFileDeclsOrder.addElement(iterator.next());
        }
    }
    
    private void f(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/OCGenerateCodeConfigurable", "resetDocCommentOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        this.myBriefDocTagCheckBox.setSelected(ocCodeStyleSettings.ADD_BRIEF_TAG);
        this.myLineTagPrefixEnumOption.selectedValue(ocCodeStyleSettings.TAG_PREFIX_OF_LINE_COMMENT);
        this.myBlockTagPrefixEnumOption.selectedValue(ocCodeStyleSettings.TAG_PREFIX_OF_BLOCK_COMMENT);
    }
    
    private static boolean a(final JCheckBox checkBox, final boolean b) {
        try {
            if (checkBox.isSelected() != b) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return false;
    }
    
    private static <T, Comp extends JComponent> boolean a(final OCOption<T, Comp> ocOption, final Comp comp, final T t) {
        try {
            if (!ocOption.getSelectedValue(comp).equals(t)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return false;
    }
    
    private static boolean a(final DefaultListModel defaultListModel, final List<OCDeclarationKind> list) {
        Label_0026: {
            try {
                if (OCGenerateCodeConfigurable.$assertionsDisabled) {
                    break Label_0026;
                }
                final DefaultListModel<OCDeclarationKind> defaultListModel2 = (DefaultListModel<OCDeclarationKind>)defaultListModel;
                final int n = defaultListModel2.size();
                final List<OCDeclarationKind> list2 = list;
                final int n2 = list2.size();
                if (n != n2) {
                    break Label_0026;
                }
                break Label_0026;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final DefaultListModel<OCDeclarationKind> defaultListModel2 = (DefaultListModel<OCDeclarationKind>)defaultListModel;
                final int n = defaultListModel2.size();
                final List<OCDeclarationKind> list2 = list;
                final int n2 = list2.size();
                if (n != n2) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        int n3 = 0;
        while (true) {
            Label_0076: {
                try {
                    if (n3 >= defaultListModel.size()) {
                        break;
                    }
                    final DefaultListModel<OCDeclarationKind> defaultListModel3 = (DefaultListModel<OCDeclarationKind>)defaultListModel;
                    final int n4 = n3;
                    final OCDeclarationKind ocDeclarationKind = defaultListModel3.elementAt(n4);
                    final List<OCDeclarationKind> list3 = list;
                    final int n5 = n3;
                    final OCDeclarationKind ocDeclarationKind2 = list3.get(n5);
                    if (ocDeclarationKind != ocDeclarationKind2) {
                        return true;
                    }
                    break Label_0076;
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                try {
                    final DefaultListModel<OCDeclarationKind> defaultListModel3 = (DefaultListModel<OCDeclarationKind>)defaultListModel;
                    final int n4 = n3;
                    final OCDeclarationKind ocDeclarationKind = defaultListModel3.elementAt(n4);
                    final List<OCDeclarationKind> list3 = list;
                    final int n5 = n3;
                    final OCDeclarationKind ocDeclarationKind2 = list3.get(n5);
                    if (ocDeclarationKind != ocDeclarationKind2) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            ++n3;
        }
        return false;
    }
    
    private static boolean a(final EnumOption<?> enumOption, final Object o) {
        try {
            if (enumOption.getSelected() != o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCGenerateCodeConfigurable.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    private static class EnumOption<T>
    {
        private final T[] myValues;
        private final JLabel myLabel;
        private final List<JRadioButton> myButtons;
        
        public EnumOption(final String s, final T[] myValues, final String... array) {
            this.myButtons = new ArrayList<JRadioButton>();
            assert myValues.length == array.length;
            this.myValues = myValues;
            this.myLabel = new JLabel(s + ":");
            final ButtonGroup buttonGroup = new ButtonGroup();
            for (int length = array.length, i = 0; i < length; ++i) {
                final JRadioButton radioButton = new JRadioButton(array[i]);
                buttonGroup.add(radioButton);
                this.myButtons.add(radioButton);
            }
        }
        
        public List<JRadioButton> getButtons() {
            return this.myButtons;
        }
        
        public JLabel getLabel() {
            return this.myLabel;
        }
        
        public T getSelected() {
            for (int i = 0; i < this.myButtons.size(); ++i) {
                if (this.myButtons.get(i).isSelected()) {
                    return (T)this.myValues[i];
                }
            }
            return null;
        }
        
        public void selectedValue(final T t) {
            for (int i = 0; i < this.myValues.length; ++i) {
                if (this.myValues[i] == t) {
                    this.myButtons.get(i).setSelected(true);
                    break;
                }
            }
        }
    }
}
