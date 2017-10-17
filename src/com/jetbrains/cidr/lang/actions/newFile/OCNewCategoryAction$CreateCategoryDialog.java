// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.ui.Messages;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import javax.swing.JComponent;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import javax.swing.AbstractButton;
import com.intellij.util.ui.DialogUtil;
import com.intellij.util.ui.FormBuilder;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.ui.OCTypeReferenceEditor;
import javax.swing.JCheckBox;

protected class CreateCategoryDialog extends CreateClassDialog
{
    private JCheckBox myCreateInterfaceCB;
    private OCTypeReferenceEditor myClassField;
    
    public CreateCategoryDialog(final String s) {
        super(OCBundle.message("create.category.dialog.title", new Object[0]), OCBundle.message("create.field.name.category", new Object[0]), s);
    }
    
    @Override
    public void fillGenericControls(final FormBuilder formBuilder) {
        DialogUtil.registerMnemonic((AbstractButton)(this.myCreateInterfaceCB = new JCheckBox(OCBundle.message("create.checkbox.category.interface", new Object[0]))), '&');
        this.myClassField = OCTypeReferenceEditor.create(OCNewCategoryAction.this.getBaseClass(), (Condition<OCSymbol>)(ocSymbol -> ocSymbol instanceof OCInterfaceSymbol && ((OCInterfaceSymbol)ocSymbol).getCategoryName() == null), (PsiElement)OCNewCategoryAction.this.myState.selectedFile, false, OCNewCategoryAction.this.myState.project);
        formBuilder.addLabeledComponent(OCBundle.message("create.field.class", new Object[0]), (JComponent)this.myClassField);
        super.fillGenericControls(formBuilder);
        formBuilder.addLabeledComponent("", (JComponent)this.myCreateInterfaceCB, 0);
        this.myClassField.addDocumentListener((DocumentListener)new DocumentListener() {
            public void documentChanged(final DocumentEvent documentEvent) {
                CreateCategoryDialog.this.validateOkAction();
            }
        });
        this.myCreateInterfaceCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                CreateCategoryDialog.this.validateOkAction();
            }
        });
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(OCNewCategoryAction.this.myState.project).getCustomSettings((Class)OCCodeStyleSettings.class);
        this.myCreateInterfaceCB.setSelected(ocCodeStyleSettings == null || ocCodeStyleSettings.CREATE_INTERFACE_FOR_CATEGORIES);
        final OCCodeStyleSettings ocCodeStyleSettings2;
        OCNewCategoryAction.this.addAuxAction(p1 -> {
            if (ocCodeStyleSettings2 != null) {
                ocCodeStyleSettings2.CREATE_INTERFACE_FOR_CATEGORIES = this.myCreateInterfaceCB.isSelected();
            }
        });
    }
    
    @Override
    protected boolean areControlsConsistent() {
        final OCSymbol classDeclaration = this.myClassField.getClassDeclaration(OCNewCategoryAction.this.myState.project);
        return (classDeclaration instanceof OCInterfaceSymbol || classDeclaration instanceof OCImplementationSymbol || Messages.showYesNoDialog(OCBundle.message("create.category.no.class.dialog", this.myClassField.getText()), OCBundle.message("create.category.no.class.dialog.title", new Object[0]), Messages.getWarningIcon()) == 0) && super.areControlsConsistent();
    }
    
    @Nullable
    @Override
    protected String collectOkActionErrors() {
        if (StringUtil.isEmpty(this.myClassField.getText()) || StringUtil.isEmpty(this.myLocationField.getText())) {
            return "";
        }
        return super.collectOkActionErrors();
    }
}
