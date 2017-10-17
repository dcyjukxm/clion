// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.ui.Messages;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import javax.swing.JComponent;
import com.intellij.openapi.util.Condition;
import javax.swing.AbstractButton;
import com.intellij.util.ui.DialogUtil;
import com.intellij.util.ui.FormBuilder;
import com.jetbrains.cidr.lang.ui.OCTypeReferenceEditor;
import javax.swing.JCheckBox;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.project.Project;
import java.util.Properties;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import icons.CidrLangIcons;
import com.jetbrains.cidr.lang.OCBundle;

public class OCNewCategoryAction extends OCNewClassAction<CreateCategoryDialog>
{
    private static final String CATEGORY_TEMPLATE_NAME = "Objective-C Category File.m";
    
    public OCNewCategoryAction() {
        super(OCBundle.message("create.category.title", new Object[0]), OCBundle.message("create.category.description", new Object[0]), CidrLangIcons.CodeAssistantClassExtension);
    }
    
    @NotNull
    @Override
    protected String getDefaultName() {
        String defaultClassPrefix;
        try {
            defaultClassPrefix = this.getDefaultClassPrefix();
            if (defaultClassPrefix == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewCategoryAction", "getDefaultName"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return defaultClassPrefix;
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(String string) {
        string = ((CreateCategoryDialog)this.myState.dialogPeer).myClassField.getText() + "+" + string;
        String string2 = null;
        String string3 = null;
        Label_0119: {
            try {
                string2 = string + ((CreateCategoryDialog)this.myState.dialogPeer).getImplementationExtension();
                if (((CreateCategoryDialog)this.myState.dialogPeer).myCreateInterfaceCB.isSelected()) {
                    string3 = string + ".h";
                    break Label_0119;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            string3 = null;
        }
        final NewFileNames newFileNames = new NewFileNames(string2, string3);
        if (newFileNames == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewCategoryAction", "getNewFileNames"));
        }
        return newFileNames;
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s, final PsiFile psiFile) {
        OCFile associatedFile = null;
        Label_0032: {
            try {
                if (((OCFile)psiFile).isHeader()) {
                    associatedFile = ((OCFile)psiFile).getAssociatedFile();
                    break Label_0032;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            associatedFile = (OCFile)psiFile;
        }
        final OCFile ocFile = associatedFile;
        String s4 = null;
        Label_0069: {
            Label_0058: {
                try {
                    if (ocFile == null) {
                        break Label_0058;
                    }
                    final OCFile ocFile2 = ocFile;
                    final String s2 = ocFile2.getName();
                    final String s3 = ".mm";
                    final boolean b = s2.endsWith(s3);
                    if (b) {
                        break Label_0058;
                    }
                    break Label_0058;
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
                try {
                    final OCFile ocFile2 = ocFile;
                    final String s2 = ocFile2.getName();
                    final String s3 = ".mm";
                    final boolean b = s2.endsWith(s3);
                    if (b) {
                        s4 = ".mm";
                        break Label_0069;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw b(ex3);
                }
            }
            s4 = ".m";
        }
        final String s5 = s4;
        final OCClassSymbol baseClass = this.getBaseClass();
        Logger log = null;
        boolean b2 = false;
        Label_0094: {
            try {
                log = OCLog.LOG;
                if (baseClass != null) {
                    b2 = true;
                    break Label_0094;
                }
            }
            catch (IllegalStateException ex4) {
                throw b(ex4);
            }
            b2 = false;
        }
        log.assertTrue(b2);
        final String string = baseClass.getName() + "+" + s;
        NewFileNames newFileNames;
        try {
            newFileNames = new NewFileNames(string + s5, string + ".h");
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewCategoryAction", "getNewFileNames"));
            }
        }
        catch (IllegalStateException ex5) {
            throw b(ex5);
        }
        return newFileNames;
    }
    
    @Override
    protected void setAdditionalProperties(final Properties properties, final String s, final PsiFile psiFile, final Project project) {
        String text;
        OCClassSymbol baseClass;
        if (this.myState != null) {
            text = ((CreateCategoryDialog)this.myState.dialogPeer).myClassField.getText();
            baseClass = (OCClassSymbol)((CreateCategoryDialog)this.myState.dialogPeer).myClassField.getClassDeclaration(project);
        }
        else {
            baseClass = this.getBaseClass();
            String name = null;
            Label_0077: {
                try {
                    if (baseClass != null) {
                        name = baseClass.getName();
                        break Label_0077;
                    }
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                name = null;
            }
            text = name;
        }
        Label_0120: {
            Label_0100: {
                try {
                    if (baseClass == null) {
                        break Label_0120;
                    }
                    final String s2 = s;
                    final String s3 = ".h";
                    final boolean b = s2.endsWith(s3);
                    if (b) {
                        break Label_0100;
                    }
                    break Label_0120;
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
                try {
                    final String s2 = s;
                    final String s3 = ".h";
                    final boolean b = s2.endsWith(s3);
                    if (b) {
                        int length;
                        int i;
                        PsiFile psiFile2;
                        final Document document;
                        OCClassSymbol ocClassSymbol;
                        final PsiElement psiElement;
                        final OCInterfaceSymbol ocInterfaceSymbol;
                        this.addAuxAction(array -> {
                            baseClass.getMainInterface();
                            for (length = array.length; i < length; ++i) {
                                psiFile2 = array[i];
                                try {
                                    if (!(psiFile2 instanceof OCFile) || !((OCFile)psiFile2).isHeader()) {
                                        continue;
                                    }
                                }
                                catch (IllegalStateException ex3) {
                                    throw b(ex3);
                                }
                                PsiDocumentManager.getInstance(project).getDocument(psiFile2);
                                try {
                                    if (document != null) {
                                        PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(document);
                                    }
                                }
                                catch (IllegalStateException ex4) {
                                    throw b(ex4);
                                }
                                ocClassSymbol = (OCClassSymbol)((OCFile)psiFile2).getSameNamedClass();
                                Label_0127_1: {
                                    try {
                                        if (ocClassSymbol != null) {
                                            ((OCSymbol<PsiElement>)ocClassSymbol).locateDefinition();
                                            break Label_0127_1;
                                        }
                                    }
                                    catch (IllegalStateException ex5) {
                                        throw b(ex5);
                                    }
                                }
                                try {
                                    if (psiElement != null) {
                                        new OCImportSymbolFix(psiElement, ocInterfaceSymbol, true, true, true).fixFirstItem(project, psiFile2);
                                    }
                                }
                                catch (IllegalStateException ex6) {
                                    throw b(ex6);
                                }
                            }
                            return;
                        });
                    }
                }
                catch (IllegalStateException ex7) {
                    throw b(ex7);
                }
            }
            try {
                properties.setProperty("NAME", text + "(" + this.mySelectedName + ")");
                if (this.myState == null) {
                    properties.setProperty("HEADER_FILENAME", this.getNewFileNames(this.mySelectedName, psiFile).getHeaderName());
                    return;
                }
            }
            catch (IllegalStateException ex8) {
                throw b(ex8);
            }
        }
        try {
            if (((CreateCategoryDialog)this.myState.dialogPeer).myCreateInterfaceCB.isSelected()) {
                properties.setProperty("HEADER_FILENAME", this.getNewFileNames(this.mySelectedName).getHeaderName());
                return;
            }
        }
        catch (IllegalStateException ex9) {
            throw b(ex9);
        }
        properties.setProperty("HEADER_FILENAME", text + ".h");
    }
    
    @Override
    protected String getFileTemplate(final String s) {
        Label_0032: {
            try {
                if (s.endsWith(".m")) {
                    return "Objective-C Class.m";
                }
                final String s2 = s;
                final String s3 = ".mm";
                final boolean b = s2.endsWith(s3);
                if (b) {
                    return "Objective-C Class.m";
                }
                break Label_0032;
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            try {
                final String s2 = s;
                final String s3 = ".mm";
                final boolean b = s2.endsWith(s3);
                if (b) {
                    return "Objective-C Class.m";
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            try {
                if (s.endsWith(".h")) {
                    return "Objective-C Category File.m";
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        try {
            assert false;
        }
        catch (IllegalStateException ex4) {
            throw b(ex4);
        }
        return null;
    }
    
    @Nullable
    protected OCClassSymbol getBaseClass() {
        OCClassSymbol classInFile = null;
        Label_0034: {
            try {
                if (this.myState.selectedFile instanceof OCFile) {
                    classInFile = OCCodeInsightUtil.getClassInFile((OCFile)this.myState.selectedFile);
                    break Label_0034;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            classInFile = null;
        }
        final OCClassSymbol ocClassSymbol = classInFile;
        Label_0055: {
            try {
                if (ocClassSymbol == null) {
                    return ocClassSymbol;
                }
                final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                final String s = ocClassSymbol2.getCategoryName();
                if (s != null) {
                    break Label_0055;
                }
                return ocClassSymbol;
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            try {
                final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                final String s = ocClassSymbol2.getCategoryName();
                if (s != null) {
                    return ocClassSymbol.getMainInterface();
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        return ocClassSymbol;
    }
    
    @NotNull
    @Override
    protected CreateCategoryDialog createDialog(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultName", "com/jetbrains/cidr/lang/actions/newFile/OCNewCategoryAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        CreateCategoryDialog createCategoryDialog;
        try {
            createCategoryDialog = new CreateCategoryDialog(s);
            if (createCategoryDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewCategoryAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return createCategoryDialog;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCNewCategoryAction.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
    
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
}
