// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.event.FocusEvent;
import com.intellij.openapi.fileChooser.FileTextField;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import javax.accessibility.Accessible;
import javax.swing.JLabel;
import com.intellij.icons.AllIcons;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusListener;
import java.awt.Dimension;
import com.intellij.util.ui.FormBuilder;
import java.awt.Component;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.openapi.application.ApplicationManager;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.util.Iterator;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDirectory;
import com.jetbrains.cidr.lang.OCBundle;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import javax.swing.JTextField;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.ui.DialogWrapper;

public class CreateFileDialogBase extends DialogWrapper
{
    public static final String NAME_SELECTION_START_TAG = "<@!selection!@>";
    @NotNull
    private String myNameFieldTitle;
    private String myDefaultName;
    protected JTextField myNameField;
    protected TextFieldWithBrowseButton myLocationField;
    private boolean myLocationWasManuallyChanged;
    private DialogWrapperFacade myWrapper;
    @Nullable
    private final String myHelpId;
    static final /* synthetic */ boolean $assertionsDisabled;
    final /* synthetic */ OCNewFileActionBase this$0;
    
    public CreateFileDialogBase(final OCNewFileActionBase ocNewFileActionBase, final String s, @Nullable final String s2, final String s3) {
        this(ocNewFileActionBase, s, OCBundle.message("create.field.name", new Object[0]), s2, s3);
    }
    
    public CreateFileDialogBase(@NotNull final String title, final String myNameFieldTitle, @Nullable final String myDefaultName, final String myHelpId) {
        if (myNameFieldTitle == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nameFieldTitle", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase", "<init>"));
        }
        super(false);
        this.myDefaultName = myDefaultName;
        this.myNameFieldTitle = myNameFieldTitle;
        this.myHelpId = myHelpId;
        this.setTitle(title);
        this.init();
    }
    
    @Nullable
    public String getHelpId() {
        return this.myHelpId;
    }
    
    @Nullable
    public String getSelectedPath() {
        return this.myLocationField.getText();
    }
    
    @NotNull
    public String getSelectedName() {
        String text;
        try {
            text = this.myNameField.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase", "getSelectedName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public PsiDirectory getSelectedDir() {
        return OCNewFileActionBase.this.myState.selectedDir;
    }
    
    public void setSelectedDir(final PsiDirectory psiDirectory) {
        Label_0023: {
            try {
                if (CreateFileDialogBase.$assertionsDisabled) {
                    break Label_0023;
                }
                final CreateFileDialogBase createFileDialogBase = this;
                final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                final DisposableState<T> disposableState = ocNewFileActionBase.myState;
                if (disposableState == null) {
                    break Label_0023;
                }
                break Label_0023;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CreateFileDialogBase createFileDialogBase = this;
                final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                final DisposableState<T> disposableState = ocNewFileActionBase.myState;
                if (disposableState == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        OCNewFileActionBase.this.myState = new DisposableState<T>(OCNewFileActionBase.this.myState.selectedFile, OCNewFileActionBase.this.myState.project, psiDirectory, OCNewFileActionBase.this.myState.dialogPeer);
    }
    
    public TextFieldWithBrowseButton getLocationField() {
        return this.myLocationField;
    }
    
    public boolean isLocationWasManuallyChanged() {
        return this.myLocationWasManuallyChanged;
    }
    
    public void setLocationWasManuallyChanged(final boolean myLocationWasManuallyChanged) {
        this.myLocationWasManuallyChanged = myLocationWasManuallyChanged;
    }
    
    public final void validateOkAction() {
        final String collectOkActionErrors = this.collectOkActionErrors();
        Label_0024: {
            try {
                this.setErrorText((String)null);
                if (collectOkActionErrors == null) {
                    final boolean okActionEnabled = true;
                    break Label_0024;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final boolean okActionEnabled = false;
            try {
                this.setOKActionEnabled(okActionEnabled);
                if (!StringUtil.isEmptyOrSpaces(collectOkActionErrors)) {
                    this.setErrorText(collectOkActionErrors);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Nullable
    protected String collectOkActionErrors() {
        final String selectedName = this.getSelectedName();
        try {
            if (StringUtil.isEmptyOrSpaces(selectedName)) {
                return "";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!this.a(selectedName)) {
                return OCBundle.message("create.error.invalid.name", selectedName);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (StringUtil.isEmpty(this.myLocationField.getText())) {
                return "";
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final String selectedPath = this.getSelectedPath();
        VirtualFile fileByPath = null;
        Label_0089: {
            try {
                if (selectedPath == null) {
                    fileByPath = null;
                    break Label_0089;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            fileByPath = LocalFileSystem.getInstance().findFileByPath(selectedPath);
        }
        final VirtualFile virtualFile = fileByPath;
        Label_0108: {
            try {
                if (virtualFile == null) {
                    break Label_0108;
                }
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = virtualFile2.isDirectory();
                if (!b) {
                    break Label_0108;
                }
                break Label_0108;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = virtualFile2.isDirectory();
                if (!b) {
                    return OCBundle.message("create.error.missing.target.dir", new Object[0]);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (final String s : OCNewFileActionBase.this.getNewFileNames(selectedName).getNames()) {
            final VirtualFile child = virtualFile.findChild(s);
            Label_0199: {
                try {
                    if (child == null) {
                        continue;
                    }
                    final VirtualFile virtualFile3 = child;
                    final boolean b2 = virtualFile3.isDirectory();
                    if (b2) {
                        break Label_0199;
                    }
                    break Label_0199;
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                try {
                    final VirtualFile virtualFile3 = child;
                    final boolean b2 = virtualFile3.isDirectory();
                    if (b2) {
                        sb.append(OCBundle.message("create.error.dir.exists", s)).append("<br>");
                        continue;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
            sb.append(OCBundle.message("create.error.file.exists", s)).append("<br>");
        }
        try {
            if (sb.length() > 0) {
                return sb.toString();
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
        return null;
    }
    
    public void setWrapper(final DialogWrapperFacade myWrapper) {
        this.myWrapper = myWrapper;
    }
    
    public void setOKActionEnabled(final boolean b) {
        try {
            if (this.myWrapper == null) {
                super.setOKActionEnabled(b);
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myWrapper.setOKEnabled(b);
    }
    
    protected void setErrorText(@Nullable final String errorMessage, @Nullable final JComponent component) {
        try {
            if (this.myWrapper == null) {
                super.setErrorText(errorMessage, component);
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myWrapper.setErrorMessage(errorMessage);
    }
    
    protected boolean areControlsConsistent() {
        return true;
    }
    
    @Nullable
    protected JComponent createCenterPanel() {
        return new JPanel();
    }
    
    public JComponent getPreferredFocusedComponent() {
        return this.myNameField;
    }
    
    public boolean checkCanDoOKAction() {
        try {
            if (!this.areControlsConsistent()) {
                ApplicationManager.getApplication().invokeLater(() -> IdeFocusManager.getGlobalInstance().doWhenFocusSettlesDown(() -> IdeFocusManager.getGlobalInstance().requestFocus((Component)this.myNameField, true)));
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return true;
    }
    
    protected void doOKAction() {
        try {
            if (this.checkCanDoOKAction()) {
                super.doOKAction();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    public void fillGenericControls(final FormBuilder formBuilder) {
        boolean b2 = false;
        Label_0048: {
            Label_0039: {
                try {
                    this.addNameField(formBuilder);
                    if (OCNewFileActionBase.this.myHelper == null) {
                        break Label_0039;
                    }
                    final CreateFileDialogBase createFileDialogBase = this;
                    final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                    final OCNewFileHelper ocNewFileHelper = ocNewFileActionBase.myHelper;
                    final boolean b = ocNewFileHelper.canChangeDir();
                    if (b) {
                        break Label_0039;
                    }
                    break Label_0039;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final CreateFileDialogBase createFileDialogBase = this;
                    final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                    final OCNewFileHelper ocNewFileHelper = ocNewFileActionBase.myHelper;
                    final boolean b = ocNewFileHelper.canChangeDir();
                    if (b) {
                        b2 = true;
                        break Label_0048;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            b2 = false;
        }
        this.addLocationField(formBuilder, b2);
    }
    
    protected void addNameField(final FormBuilder formBuilder) {
        this.addNameField(formBuilder, false);
    }
    
    protected final void addNameField(final FormBuilder formBuilder, final boolean b) {
        int index = this.myDefaultName.indexOf("<@!selection!@>");
        String text;
        if (index < 0) {
            index = 0;
            text = this.myDefaultName;
        }
        else {
            text = this.myDefaultName.substring(0, index) + this.myDefaultName.substring(index + "<@!selection!@>".length());
        }
        (this.myNameField = new JTextField()).setText(text);
        this.myNameField.setMinimumSize(new Dimension(250, this.myNameField.getPreferredSize().height));
        final NameFocusListener nameFocusListener = new NameFocusListener(index);
        this.myNameField.addFocusListener(nameFocusListener);
        this.myNameField.getDocument().addDocumentListener(new DocumentListener() {
            private void a() {
                CreateFileDialogBase.this.validateOkAction();
                nameFocusListener.resetSelectionStart();
            }
            
            @Override
            public void insertUpdate(final DocumentEvent documentEvent) {
                this.a();
            }
            
            @Override
            public void removeUpdate(final DocumentEvent documentEvent) {
                this.a();
            }
            
            @Override
            public void changedUpdate(final DocumentEvent documentEvent) {
                this.a();
            }
        });
        Accessible myNameField = this.myNameField;
        if (b) {
            final JPanel panel = new JPanel(new BorderLayout(5, 0));
            panel.add(this.myNameField, "Center");
            final JLabel label = new JLabel(AllIcons.Ide.UpDown);
            label.setToolTipText(OCBundle.message("create.tooltip.arrows.type", new Object[0]));
            panel.add(label, "East");
            myNameField = panel;
        }
        formBuilder.addLabeledComponent(this.myNameFieldTitle, (JComponent)myNameField);
        this.myNameField.setEnabled(this.isNameFieldEnabled());
    }
    
    protected final void addLocationField(final FormBuilder formBuilder, final boolean b) {
        final FileChooserDescriptor singleFolderDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        final FileTextField fileTextField = FileChooserFactory.getInstance().createFileTextField(singleFolderDescriptor, this.myDisposable);
        Label_0145: {
            Label_0113: {
                try {
                    fileTextField.getField().setColumns(25);
                    (this.myLocationField = new TextFieldWithBrowseButton(fileTextField.getField())).addBrowseFolderListener(OCBundle.message("create.folder.dialog.title", new Object[0]), OCBundle.message("create.folder.dialog.description", new Object[0]), OCNewFileActionBase.this.myState.project, singleFolderDescriptor);
                    if (OCNewFileActionBase.this.myState == null) {
                        break Label_0145;
                    }
                    final CreateFileDialogBase createFileDialogBase = this;
                    final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                    final DisposableState<T> disposableState = ocNewFileActionBase.myState;
                    final PsiDirectory psiDirectory = disposableState.selectedDir;
                    if (psiDirectory != null) {
                        break Label_0113;
                    }
                    break Label_0145;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final CreateFileDialogBase createFileDialogBase = this;
                    final OCNewFileActionBase ocNewFileActionBase = createFileDialogBase.this$0;
                    final DisposableState<T> disposableState = ocNewFileActionBase.myState;
                    final PsiDirectory psiDirectory = disposableState.selectedDir;
                    if (psiDirectory != null) {
                        this.myLocationField.setText(OCNewFileActionBase.this.myState.selectedDir.getVirtualFile().getPath());
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            try {
                fileTextField.getField().getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(final DocumentEvent documentEvent) {
                        CreateFileDialogBase.this.validateOkAction();
                        CreateFileDialogBase.this.myLocationWasManuallyChanged = true;
                    }
                    
                    @Override
                    public void removeUpdate(final DocumentEvent documentEvent) {
                        CreateFileDialogBase.this.validateOkAction();
                        CreateFileDialogBase.this.myLocationWasManuallyChanged = (documentEvent.getDocument().getLength() != 0);
                    }
                    
                    @Override
                    public void changedUpdate(final DocumentEvent documentEvent) {
                    }
                });
                if (b) {
                    formBuilder.addLabeledComponent(OCBundle.message("create.field.location", new Object[0]), (JComponent)this.myLocationField);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    private boolean a(final String s) {
        return OCNewFileActionBase.this.isValidName(s);
    }
    
    protected boolean isNameFieldEnabled() {
        return true;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCNewFileActionBase.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private class NameFocusListener implements FocusListener
    {
        private int mySelectionStart;
        
        public NameFocusListener(final int mySelectionStart) {
            this.mySelectionStart = mySelectionStart;
        }
        
        @Override
        public void focusGained(@NotNull final FocusEvent focusEvent) {
            try {
                if (focusEvent == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase$NameFocusListener", "focusGained"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            CreateFileDialogBase.this.myNameField.setCaretPosition(this.mySelectionStart);
            CreateFileDialogBase.this.myNameField.moveCaretPosition(CreateFileDialogBase.this.myNameField.getText().length());
        }
        
        @Override
        public void focusLost(@NotNull final FocusEvent focusEvent) {
            try {
                if (focusEvent == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$CreateFileDialogBase$NameFocusListener", "focusLost"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        
        public void resetSelectionStart() {
            this.mySelectionStart = 0;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
