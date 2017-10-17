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
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.OCLanguageKindCalculator;
import java.util.List;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.util.containers.ImmutableList;
import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import com.intellij.openapi.project.Project;
import java.util.Properties;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.Couple;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.fileTypes.FileType;
import icons.CidrLangIcons;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.OCFileType;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;

public class OCNewCppClassAction<T extends MyCreateClassDialog> extends OCNewFileWithSelectedExtensionAction<T>
{
    protected static final String NAMESPACES_OPEN_PROPERTY = "NAMESPACES_OPEN";
    protected static final String NAMESPACES_CLOSE_PROPERTY = "NAMESPACES_CLOSE";
    private OCQualifiedName myNamespace;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCNewCppClassAction() {
        super((FileType)OCFileType.INSTANCE, OCBundle.message("create.cpp.class.title", new Object[0]), OCBundle.message("create.cpp.class.description", new Object[0]), CidrLangIcons.CodeAssistantStruct);
    }
    
    public OCNewCppClassAction(@Nullable final OCQualifiedName myNamespace) {
        this();
        this.myNamespace = myNamespace;
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s) {
        String string = null;
        Label_0066: {
            try {
                if (this.myState.dialogPeer.myHeaderOnly) {
                    string = null;
                    break Label_0066;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            string = s + "." + this.myState.dialogPeer.myFileExtensionPair.mySourceExt;
        }
        final NewFileNames newFileNames = new NewFileNames(string, s + "." + this.myState.dialogPeer.myFileExtensionPair.myHeaderExt);
        if (newFileNames == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewCppClassAction", "getNewFileNames"));
        }
        return newFileNames;
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s, final PsiFile psiFile) {
        final Couple<String> determineExtensions = this.determineExtensions((OCFile)psiFile);
        NewFileNames newFileNames;
        try {
            newFileNames = new NewFileNames(s + "." + (String)determineExtensions.first, s + "." + (String)determineExtensions.second);
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewCppClassAction", "getNewFileNames"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return newFileNames;
    }
    
    @Override
    protected boolean isValidName(final String s) {
        return StringUtil.isJavaIdentifier(s);
    }
    
    @Override
    protected void setAdditionalProperties(final Properties properties, final String s, final PsiFile psiFile, final Project project) {
        String string = "";
        String string2 = "";
        for (OCQualifiedName ocQualifiedName = this.myNamespace; ocQualifiedName != null; ocQualifiedName = ocQualifiedName.getQualifier()) {
            final String name = ocQualifiedName.getName();
            Label_0046: {
                try {
                    if (name == null) {
                        break;
                    }
                    final String s2 = name;
                    final boolean b = s2.isEmpty();
                    if (b) {
                        break Label_0046;
                    }
                    break Label_0046;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final String s2 = name;
                    final boolean b = s2.isEmpty();
                    if (b) {
                        break;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            string = "namespace " + name + " {" + string + "\n";
            string2 += "}\n";
        }
        String s3;
        if (this.myState != null) {
            s3 = this.getNewFileNames(this.mySelectedName).getHeaderName();
        }
        else {
            Label_0162: {
                try {
                    if (OCNewCppClassAction.$assertionsDisabled) {
                        break Label_0162;
                    }
                    final PsiFile psiFile2 = psiFile;
                    if (psiFile2 == null) {
                        break Label_0162;
                    }
                    break Label_0162;
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                try {
                    final PsiFile psiFile2 = psiFile;
                    if (psiFile2 == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            s3 = this.getNewFileNames(this.mySelectedName, psiFile).getHeaderName();
        }
        properties.setProperty("HEADER_FILENAME", s3);
        properties.setProperty("NAMESPACES_OPEN", string);
        properties.setProperty("NAMESPACES_CLOSE", string2);
    }
    
    @Override
    protected String getFileTemplate(final String s) {
        try {
            if (OCFileTypeHelpers.isSourceFile(s)) {
                return "C++ Class.cc";
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (OCFileTypeHelpers.isHeaderFile(s)) {
                return "C++ Class Header.h";
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            assert false;
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @NotNull
    @Override
    protected T createDialog(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultName", "com/jetbrains/cidr/lang/actions/newFile/OCNewCppClassAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        MyCreateClassDialog myCreateClassDialog;
        try {
            myCreateClassDialog = new MyCreateClassDialog(s);
            if (myCreateClassDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewCppClassAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (T)myCreateClassDialog;
    }
    
    @NotNull
    @Override
    protected ImmutableList<OCCodeStyleSettings.FileExtensionPair> getExtensions(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/actions/newFile/OCNewCppClassAction", "getExtensions"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final List filter = ContainerUtil.filter((Collection)super.getExtensions(project), fileExtensionPair -> {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/actions/newFile/OCNewCppClassAction", "lambda$getExtensions$0"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final OCLanguageKind tryFileExtension = OCLanguageKindCalculator.tryFileExtension(project, "." + fileExtensionPair.mySourceExt);
            Label_0091: {
                try {
                    if (tryFileExtension == null) {
                        return false;
                    }
                    final OCLanguageKind ocLanguageKind = tryFileExtension;
                    final boolean b = ocLanguageKind.isCpp();
                    if (b) {
                        break Label_0091;
                    }
                    return false;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCLanguageKind ocLanguageKind = tryFileExtension;
                    final boolean b = ocLanguageKind.isCpp();
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            return false;
        });
        ImmutableList immutableList;
        try {
            immutableList = ContainerUtil.immutableList(filter);
            if (immutableList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewCppClassAction", "getExtensions"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (ImmutableList<OCCodeStyleSettings.FileExtensionPair>)immutableList;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCNewCppClassAction.class.desiredAssertionStatus()) {
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
    
    public class MyCreateClassDialog extends CreateFileDialog
    {
        boolean myHeaderOnly;
        
        public MyCreateClassDialog(final String s) {
            super(OCBundle.message("create.cpp.class.dialog.title", new Object[0]), s);
        }
        
        @Override
        public void fillGenericControls(final FormBuilder formBuilder) {
            super.fillGenericControls(formBuilder);
            final NonFocusableCheckBox nonFocusableCheckBox = new NonFocusableCheckBox(OCBundle.message("create.checkbox.cpp.class.header", new Object[0]));
            DialogUtil.registerMnemonic((AbstractButton)nonFocusableCheckBox, '&');
            ((AbstractButton)nonFocusableCheckBox).addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent itemEvent) {
                    MyCreateClassDialog.this.myHeaderOnly = ((AbstractButton)nonFocusableCheckBox).isSelected();
                    if (MyCreateClassDialog.this.myKindCombo != null) {
                        MyCreateClassDialog.this.reloadExtensions();
                    }
                    MyCreateClassDialog.this.validateOkAction();
                }
            });
            OCNewCppClassAction.this.addAuxAction(p0 -> {
                try {
                    UsageTrigger.trigger("cidr.newFile.cppClass");
                    if (this.myHeaderOnly) {
                        UsageTrigger.trigger("cidr.newFile.cppClass.headerOnly");
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
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pair", "com/jetbrains/cidr/lang/actions/newFile/OCNewCppClassAction$MyCreateClassDialog", "getPresentableName"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            String s = null;
            Label_0085: {
                try {
                    if (this.myHeaderOnly) {
                        final String s2;
                        s = (s2 = "." + fileExtensionPair.myHeaderExt);
                        break Label_0085;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                String s2;
                s = (s2 = super.getPresentableName(fileExtensionPair));
                try {
                    if (s2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewCppClassAction$MyCreateClassDialog", "getPresentableName"));
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
