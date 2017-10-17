// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import javax.swing.JComponent;
import com.intellij.internal.statistic.UsageTrigger;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import com.intellij.util.ui.DialogUtil;
import com.intellij.ui.NonFocusableCheckBox;
import com.intellij.util.ui.FormBuilder;
import com.jetbrains.cidr.lang.psi.impl.OCFileImpl;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import java.util.Properties;
import com.intellij.openapi.util.Couple;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.openapi.fileTypes.FileType;
import icons.CidrLangIcons;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.OCFileType;

public class OCNewSourceFileAction extends OCNewFileWithSelectedExtensionAction<CreateFileDialog>
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCNewSourceFileAction() {
        super((FileType)OCFileType.INSTANCE, OCBundle.message("create.cpp.source.title", new Object[0]), OCBundle.message("create.cpp.source.title", new Object[0]), CidrLangIcons.FileType_cpp);
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s) {
        final OCCodeStyleSettings.FileExtensionPair myFileExtensionPair = ((CreateFileDialog)this.myState.dialogPeer).myFileExtensionPair;
        final String string = s + "." + myFileExtensionPair.mySourceExt;
        String s2 = null;
        String string2 = null;
        Label_0087: {
            try {
                s2 = string;
                if (this.a()) {
                    string2 = s + '.' + myFileExtensionPair.myHeaderExt;
                    break Label_0087;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            string2 = null;
        }
        final NewFileNames newFileNames = new NewFileNames(s2, string2);
        if (newFileNames == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewSourceFileAction", "getNewFileNames"));
        }
        return newFileNames;
    }
    
    private boolean a() {
        return ((MyCreateFileDialog)this.myState.dialogPeer).myShouldAddHeader;
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s, final PsiFile psiFile) {
        String s2 = null;
        String s3 = null;
        final String extension = FileUtilRt.getExtension(s);
        Label_0050: {
            Label_0039: {
                try {
                    if (extension.length() <= 0) {
                        break Label_0050;
                    }
                    if (!OCFileTypeHelpers.isHeaderFile(s)) {
                        break Label_0039;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                s2 = extension;
                break Label_0050;
            }
            if (OCFileTypeHelpers.isSourceFile(s)) {
                s3 = extension;
            }
        }
        final String nameWithoutExtension = FileUtilRt.getNameWithoutExtension(s);
        final Couple<String> determineExtensions = this.determineExtensions((OCFile)psiFile, s3, s2);
        NewFileNames newFileNames;
        try {
            newFileNames = new NewFileNames(nameWithoutExtension + '.' + (String)determineExtensions.first, nameWithoutExtension + '.' + (String)determineExtensions.second);
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewSourceFileAction", "getNewFileNames"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return newFileNames;
    }
    
    @Override
    protected void setAdditionalProperties(final Properties properties, final String s, @Nullable final PsiFile psiFile, final Project project) {
        String s2;
        if (this.myState != null) {
            s2 = this.getNewFileNames(this.mySelectedName).getHeaderName();
        }
        else {
            Label_0040: {
                try {
                    if (OCNewSourceFileAction.$assertionsDisabled) {
                        break Label_0040;
                    }
                    final PsiFile psiFile2 = psiFile;
                    if (psiFile2 == null) {
                        break Label_0040;
                    }
                    break Label_0040;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final PsiFile psiFile2 = psiFile;
                    if (psiFile2 == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            s2 = this.getNewFileNames(this.mySelectedName, psiFile).getHeaderName();
        }
        try {
            if (s2 != null) {
                properties.setProperty("HEADER_FILENAME", s2);
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
    }
    
    @Override
    protected boolean isValidName(final String s) {
        final String string = s + "." + ((CreateFileDialog)this.myState.dialogPeer).myFileExtensionPair.mySourceExt;
        Label_0061: {
            try {
                if (!super.isValidName(string)) {
                    return false;
                }
                final String s2 = string;
                final boolean b = OCFileImpl.isSourceCodeFile(s2);
                if (b) {
                    break Label_0061;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final String s2 = string;
                final boolean b = OCFileImpl.isSourceCodeFile(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    protected String getFileTemplate(final String s) {
        try {
            if (OCFileImpl.isHeaderFile(s)) {
                return OCNewHeaderFileAction.headerTemplate();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return "C Source File.c";
    }
    
    @NotNull
    @Override
    protected CreateFileDialog createDialog(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultName", "com/jetbrains/cidr/lang/actions/newFile/OCNewSourceFileAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        MyCreateFileDialog myCreateFileDialog;
        try {
            myCreateFileDialog = new MyCreateFileDialog(s);
            if (myCreateFileDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewSourceFileAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return myCreateFileDialog;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCNewSourceFileAction.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    private class MyCreateFileDialog extends CreateFileDialog
    {
        private boolean myShouldAddHeader;
        
        public MyCreateFileDialog(final String s) {
            super(s);
        }
        
        @Override
        public void fillGenericControls(final FormBuilder formBuilder) {
            super.fillGenericControls(formBuilder);
            final NonFocusableCheckBox nonFocusableCheckBox = new NonFocusableCheckBox(OCBundle.message("create.checkbox.source.associated.header", new Object[0]));
            DialogUtil.registerMnemonic((AbstractButton)nonFocusableCheckBox, '&');
            ((AbstractButton)nonFocusableCheckBox).addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent itemEvent) {
                    MyCreateFileDialog.this.myShouldAddHeader = ((AbstractButton)nonFocusableCheckBox).isSelected();
                    if (MyCreateFileDialog.this.myKindCombo != null) {
                        MyCreateFileDialog.this.reloadExtensions();
                    }
                    MyCreateFileDialog.this.validateOkAction();
                }
            });
            OCNewSourceFileAction.this.addAuxAction(p0 -> {
                try {
                    UsageTrigger.trigger("cidr.newFile.source");
                    if (this.myShouldAddHeader) {
                        UsageTrigger.trigger("cidr.newFile.source.withHeader");
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                return;
            });
            formBuilder.addComponent((JComponent)nonFocusableCheckBox);
        }
        
        @NotNull
        @Override
        protected String getPresentableName(@NotNull final OCCodeStyleSettings.FileExtensionPair fileExtensionPair) {
            try {
                if (fileExtensionPair == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pair", "com/jetbrains/cidr/lang/actions/newFile/OCNewSourceFileAction$MyCreateFileDialog", "getPresentableName"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            String s = null;
            Label_0085: {
                try {
                    if (this.myShouldAddHeader) {
                        final String s2;
                        s = (s2 = super.getPresentableName(fileExtensionPair));
                        break Label_0085;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                String s2;
                s = (s2 = "." + fileExtensionPair.mySourceExt);
                try {
                    if (s2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewSourceFileAction$MyCreateFileDialog", "getPresentableName"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
            }
            return s;
        }
        
        private static IllegalArgumentException c(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
