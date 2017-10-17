// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.util.containers.ContainerUtil;
import java.util.Arrays;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.codeInspection.ExternalAnnotatorInspectionVisitor;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.InspectionManager;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.settings.CPPLanguageConfigurable;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import com.intellij.ui.DocumentAdapter;
import com.intellij.util.PlatformIcons;
import javax.swing.JButton;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import javax.swing.JTextField;
import com.intellij.openapi.ui.Messages;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.FieldPanel;
import java.net.URI;
import java.awt.Desktop;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import com.intellij.ui.HyperlinkLabel;
import org.jetbrains.annotations.Nullable;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import com.intellij.util.ui.JBUI;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nls;
import com.jetbrains.cidr.lang.daemon.clang.tidy.ClangTidyAnnotator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class ClangTidyInspection extends OCInspections.GeneralCpp
{
    public static final String INSPECTION_SHORT_NAME = "ClangTidyInspection";
    @NotNull
    private String myClangTidyChecks;
    @NotNull
    private static final List<String> DEFAULT_CHECKS_CONFIGURATION;
    
    public ClangTidyInspection() {
        this.myClangTidyChecks = getDefaultChecks();
    }
    
    @Override
    public boolean isEnabledByDefault() {
        return ClangTidyAnnotator.isAvailable();
    }
    
    @NotNull
    @Override
    public String getShortName() {
        String s;
        try {
            s = "ClangTidyInspection";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "getShortName"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return s;
    }
    
    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        String s;
        try {
            s = "Clang-Tidy";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "getDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return s;
    }
    
    @Nullable
    public JComponent createOptionsPanel() {
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.add((Component)d(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 18, 2, (Insets)JBUI.emptyInsets(), 0, 0));
        panel.add((Component)this.c(), new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, 18, 2, (Insets)JBUI.emptyInsets(), 0, 0));
        panel.add((Component)b(), new GridBagConstraints(0, 2, 1, 1, 0.0, 1.0, 18, 2, (Insets)JBUI.insetsTop(20), 0, 0));
        return panel;
    }
    
    @NotNull
    private static HyperlinkLabel d() {
        final HyperlinkLabel hyperlinkLabel = new HyperlinkLabel("List of enabled/disabled clang-tidy checks:");
        HyperlinkLabel hyperlinkLabel2;
        try {
            hyperlinkLabel.addHyperlinkListener((HyperlinkListener)new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(new URI("http://clang.llvm.org/extra/clang-tidy/#using-clang-tidy"));
                        }
                        catch (Exception ex) {}
                    }
                }
            });
            hyperlinkLabel2 = hyperlinkLabel;
            if (hyperlinkLabel2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "createHint"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return hyperlinkLabel2;
    }
    
    @NotNull
    private FieldPanel c() {
        final JBTextField jbTextField = new JBTextField(30);
        final FieldPanel fieldPanel = new FieldPanel((JTextField)jbTextField, (String)null, (String)null, (ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                Messages.showTextAreaDialog((JTextField)jbTextField, "Edit Clang-Tidy Checks", "ClangTidyInspection", s -> StringUtil.split(s, ","), list -> StringUtil.join((Collection)list, ","));
            }
        }, (Runnable)null);
        FieldPanel fieldPanel2;
        try {
            ((JButton)fieldPanel.getComponent(1)).setIcon(PlatformIcons.OPEN_EDIT_DIALOG_ICON);
            fieldPanel.getTextField().getDocument().addDocumentListener((DocumentListener)new DocumentAdapter() {
                protected void textChanged(final DocumentEvent documentEvent) {
                    ClangTidyInspection.this.myClangTidyChecks = fieldPanel.getTextField().getText();
                }
            });
            fieldPanel.setText(this.myClangTidyChecks);
            jbTextField.getEmptyText().setText(getAllDisabledChecks());
            fieldPanel2 = fieldPanel;
            if (fieldPanel2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "createChecksField"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return fieldPanel2;
    }
    
    @NotNull
    private static HyperlinkLabel b() {
        final HyperlinkLabel hyperlinkLabel = new HyperlinkLabel("Check this settings if you want to use external clang-tidy instead of the built-in one");
        HyperlinkLabel hyperlinkLabel2;
        try {
            hyperlinkLabel.addHyperlinkListener((HyperlinkListener)new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
                    final Project project = (Project)CommonDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext((Component)hyperlinkLabel));
                    if (project != null) {
                        ShowSettingsUtil.getInstance().showSettingsDialog(project, (Class)CPPLanguageConfigurable.class);
                    }
                }
            });
            hyperlinkLabel2 = hyperlinkLabel;
            if (hyperlinkLabel2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "createLinkToClangTidySettings"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return hyperlinkLabel2;
    }
    
    @Nullable
    public ProblemDescriptor[] checkFile(@NotNull final PsiFile psiFile, @NotNull final InspectionManager inspectionManager, final boolean b) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "checkFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        try {
            if (inspectionManager == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "manager", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "checkFile"));
            }
        }
        catch (IllegalStateException ex2) {
            throw d(ex2);
        }
        return ExternalAnnotatorInspectionVisitor.checkFileWithExternalAnnotator(psiFile, inspectionManager, b, (com.intellij.lang.annotation.ExternalAnnotator<Object, Object>)new ClangTidyAnnotator());
    }
    
    @NotNull
    public String getClangTidyChecks() {
        String s = null;
        Label_0024: {
            try {
                if (!this.myClangTidyChecks.isEmpty()) {
                    final String s2;
                    s = (s2 = this.myClangTidyChecks);
                    break Label_0024;
                }
            }
            catch (IllegalStateException ex) {
                throw d(ex);
            }
            String s2;
            s = (s2 = getAllDisabledChecks());
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "getClangTidyChecks"));
                }
            }
            catch (IllegalStateException ex2) {
                throw d(ex2);
            }
        }
        return s;
    }
    
    public void setClangTidyChecks(@NotNull final String myClangTidyChecks) {
        try {
            if (myClangTidyChecks == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clangTidyChecks", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "setClangTidyChecks"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        this.myClangTidyChecks = myClangTidyChecks;
    }
    
    @NotNull
    public static String getDefaultChecks() {
        String join;
        try {
            join = StringUtil.join((Collection)ClangTidyInspection.DEFAULT_CHECKS_CONFIGURATION, ",");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "getDefaultChecks"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return join;
    }
    
    @NotNull
    public static String getAllEnabledChecks() {
        String s;
        try {
            s = "*";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "getAllEnabledChecks"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return s;
    }
    
    @NotNull
    public static String getAllEnabledChecksExceptClangAnalyzer() {
        String s;
        try {
            s = "*,-clang-analyzer-*";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "getAllEnabledChecksExceptClangAnalyzer"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return s;
    }
    
    @NotNull
    public static String getAllDisabledChecks() {
        String s;
        try {
            s = "-*";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/ClangTidyInspection", "getAllDisabledChecks"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return s;
    }
    
    private static List<String> a() {
        return (List<String>)ContainerUtil.newUnmodifiableList((List)Arrays.asList("*", "-cert-env33-c", "-cert-dcl50-cpp", "-cert-dcl59-cpp", "-cppcoreguidelines-no-malloc", "-cppcoreguidelines-pro-bounds-array-to-pointer-decay", "-cppcoreguidelines-pro-bounds-constant-array-index", "-cppcoreguidelines-pro-bounds-pointer-arithmetic", "-cppcoreguidelines-pro-type-const-cast", "-cppcoreguidelines-pro-type-cstyle-cast", "-cppcoreguidelines-pro-type-reinterpret-cast", "-cppcoreguidelines-pro-type-union-access", "-cppcoreguidelines-pro-type-vararg", "-cppcoreguidelines-special-member-functions", "-google-*", "google-default-arguments", "google-explicit-constructor", "google-runtime-member-string-references", "google-runtime-memset", "google-runtime-operator", "-hicpp-named-parameter", "-hicpp-no-assembler", "-hicpp-function-size", "-hicpp-special-member-functions", "-llvm-*", "-readability-else-after-return", "-readability-implicit-bool-cast", "-readability-named-parameter", "-readability-simplify-boolean-expr", "-readability-braces-around-statements", "-readability-identifier-naming", "-readability-function-size", "-readability-redundant-member-init", "-misc-bool-pointer-implicit-conversion", "-misc-definitions-in-headers", "-misc-unused-alias-decls", "-misc-unused-parameters", "-misc-unused-using-decls", "-modernize-use-using", "-modernize-use-default-member-init", "-clang-diagnostic-*", "-clang-analyzer-*"));
    }
    
    static {
        DEFAULT_CHECKS_CONFIGURATION = a();
    }
    
    private static IllegalStateException d(final IllegalStateException ex) {
        return ex;
    }
}
