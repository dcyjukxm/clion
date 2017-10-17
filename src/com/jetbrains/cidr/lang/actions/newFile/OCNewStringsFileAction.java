// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import java.util.Properties;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileType;
import icons.CidrLangIcons;
import com.jetbrains.cidr.lang.OCBundle;

public class OCNewStringsFileAction extends OCNewFileActionBase
{
    private static final String STRINGS_EXTENSION = ".strings";
    private static final String STRINGS_TEMPLATE_NAME = "Objective-C Strings File.strings";
    private static final String CONTENTS_PROPERTY = "CONTENTS";
    private String myTableName;
    private String myContent;
    
    public OCNewStringsFileAction(final String myTableName, final String myContent) {
        super(null, OCBundle.message("create.strings.title", new Object[0]), OCBundle.message("create.strings.description", new Object[0]), CidrLangIcons.CodeAssistantGlobal);
        this.myTableName = myTableName;
        this.myContent = myContent;
    }
    
    @NotNull
    @Override
    protected String getDefaultName() {
        String myTableName;
        try {
            myTableName = this.myTableName;
            if (myTableName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewStringsFileAction", "getDefaultName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myTableName;
    }
    
    @Override
    protected String getFileTemplate(final String s) {
        return "Objective-C Strings File.strings";
    }
    
    @Override
    protected void setAdditionalProperties(final Properties properties, final String s, final PsiFile psiFile, final Project project) {
        properties.setProperty("CONTENTS", this.myContent);
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s) {
        NewFileNames newFileNames;
        try {
            newFileNames = new NewFileNames(s + ".strings", null);
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewStringsFileAction", "getNewFileNames"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return newFileNames;
    }
    
    @NotNull
    @Override
    protected CreateFileDialogBase createDialog(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultName", "com/jetbrains/cidr/lang/actions/newFile/OCNewStringsFileAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        CreateStringsFileDialog createStringsFileDialog;
        try {
            createStringsFileDialog = new CreateStringsFileDialog(s);
            if (createStringsFileDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewStringsFileAction", "createDialog"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return createStringsFileDialog;
    }
    
    protected boolean isNameFieldEnabled() {
        return true;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    protected class CreateStringsFileDialog extends CreateFileDialogBase
    {
        public CreateStringsFileDialog(final String s) {
            super(OCBundle.message("create.strings.dialog.title", new Object[0]), s, null);
        }
        
        @Override
        protected boolean isNameFieldEnabled() {
            return OCNewStringsFileAction.this.isNameFieldEnabled();
        }
    }
}
