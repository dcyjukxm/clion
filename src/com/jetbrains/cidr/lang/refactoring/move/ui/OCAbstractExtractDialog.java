// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import com.intellij.openapi.ui.Messages;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.ApplicationManager;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import com.intellij.ui.DocumentAdapter;
import com.intellij.openapi.util.text.StringUtil;
import javax.swing.Box;
import javax.swing.JComponent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import javax.swing.JTextField;
import javax.swing.JLabel;

public abstract class OCAbstractExtractDialog extends OCAbstractMoveDialog
{
    protected JLabel myClassNameLabel;
    protected JTextField myExtractedClassNameField;
    
    protected OCAbstractExtractDialog(@Nullable final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition, final Project project) {
        super(ocSymbolDeclarator, ocSymbol, condition, project);
    }
    
    protected JComponent createNorthPanel() {
        final Box verticalBox = Box.createVerticalBox();
        (this.myClassNameLabel = new JLabel()).setText(StringUtil.capitalize(this.getClassType()) + " name:");
        this.myExtractedClassNameField = new JTextField();
        this.myExtractedClassNameField.getDocument().addDocumentListener((DocumentListener)new DocumentAdapter() {
            protected void textChanged(final DocumentEvent documentEvent) {
                OCAbstractExtractDialog.this.validateButtons();
            }
        });
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(this.myClassNameLabel, "North");
        panel.add(this.myExtractedClassNameField, "Center");
        verticalBox.add(panel);
        verticalBox.add(Box.createVerticalStrut(7));
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(verticalBox, "Center");
        return panel2;
    }
    
    public JComponent getPreferredFocusedComponent() {
        return this.myExtractedClassNameField;
    }
    
    @NotNull
    public String getClassName() {
        String s = null;
        Label_0030: {
            try {
                if (ApplicationManager.getApplication().isUnitTestMode()) {
                    final String trim;
                    s = (trim = "zzz_source_new");
                    break Label_0030;
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            String trim;
            s = (trim = this.myExtractedClassNameField.getText().trim());
            try {
                if (trim == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/ui/OCAbstractExtractDialog", "getClassName"));
                }
            }
            catch (IllegalStateException ex2) {
                throw c(ex2);
            }
        }
        return s;
    }
    
    @Override
    public boolean allowsEmptySelection() {
        return true;
    }
    
    @Override
    protected void checkCanRun() throws ConfigurationException {
        super.checkCanRun();
        final String className = this.getClassName();
        try {
            if (!StringUtil.isJavaIdentifier(className)) {
                throw new ConfigurationException("Name of the " + StringUtil.decapitalize(this.getClassType()) + " is invalid");
            }
        }
        catch (ConfigurationException ex) {
            throw c((Exception)ex);
        }
    }
    
    @Override
    public boolean checkConflicts() {
        final OCSymbol resolveNameInScope = OCCodeInsightUtil.resolveNameInScope(this.getSymbolKind(), this.getClassName(), null, null, this.myProject);
        try {
            if (resolveNameInScope != null) {
                Messages.showErrorDialog(resolveNameInScope.getNameWithKindUppercase() + " already exists", "Unable to Create a File");
                return false;
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return super.checkConflicts();
    }
    
    @Override
    protected String getConflictMessage(final OCMemberInfo ocMemberInfo, final OCSymbol ocSymbol) {
        return ocSymbol.getNameWithKindUppercase() + " will be inaccessible in the extracted code";
    }
    
    protected abstract String getClassType();
    
    protected OCSymbolKind getSymbolKind() {
        return OCSymbolKind.INTERFACE;
    }
    
    @Override
    protected String getOKButtonText() {
        return "Ext&ract";
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
