// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.OCLog;
import java.util.Properties;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.FileModificationService;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.fileTypes.FileType;
import icons.CidrLangIcons;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.OCFileType;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;

public class OCImplementInterfaceIntentionAction extends OCNewFileActionBase implements IntentionAction
{
    private OCInterfaceSymbol myInterface;
    
    public OCImplementInterfaceIntentionAction(final OCInterfaceSymbol myInterface) {
        super((FileType)OCFileType.INSTANCE, OCBundle.message("create.implementation.title", new Object[0]), OCBundle.message("create.implementation.description", new Object[0]), CidrLangIcons.CodeAssistantClass);
        this.myInterface = myInterface;
    }
    
    @NotNull
    public String getText() {
        String string = null;
        Label_0055: {
            String s = null;
            Label_0020: {
                try {
                    if (this.myInterface != null) {
                        break Label_0055;
                    }
                    s = "Invalid";
                    if (s == null) {
                        break Label_0020;
                    }
                    return s;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    s = "Invalid";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementInterfaceIntentionAction", "getText"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return s;
            try {
                string = "Create implementation of " + this.myInterface.getNameWithKindLowercase();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementInterfaceIntentionAction", "getText"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return string;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementInterfaceIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    @NotNull
    @Override
    protected String getDefaultName() {
        String s;
        try {
            s = "Implementation";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementInterfaceIntentionAction", "getDefaultName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImplementInterfaceIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0068: {
            try {
                if (!super.isAvailable()) {
                    return false;
                }
                final OCImplementInterfaceIntentionAction ocImplementInterfaceIntentionAction = this;
                final OCInterfaceSymbol ocInterfaceSymbol = ocImplementInterfaceIntentionAction.myInterface;
                final boolean b = OCSearchScope.isInProjectSources(ocInterfaceSymbol);
                if (b) {
                    break Label_0068;
                }
                return false;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final OCImplementInterfaceIntentionAction ocImplementInterfaceIntentionAction = this;
                final OCInterfaceSymbol ocInterfaceSymbol = ocImplementInterfaceIntentionAction.myInterface;
                final boolean b = OCSearchScope.isInProjectSources(ocInterfaceSymbol);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImplementInterfaceIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (!FileModificationService.getInstance().prepareFileForWrite(psiFile)) {
                return;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            PsiDocumentManager.getInstance(project).commitAllDocuments();
            if (!ApplicationManager.getApplication().isUnitTestMode()) {
                this.performAction(project, psiFile.getParent(), psiFile, this.myInterface.getName());
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
    }
    
    public boolean startInWriteAction() {
        return false;
    }
    
    @Override
    protected String getFileTemplate(final String s) {
        return "Objective-C Class.m";
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s) {
        NewFileNames newFileNames;
        try {
            newFileNames = new NewFileNames(s + ".m", null);
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementInterfaceIntentionAction", "getNewFileNames"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return newFileNames;
    }
    
    @Override
    protected void setAdditionalProperties(final Properties properties, final String s, final PsiFile psiFile, final Project project) {
        final VirtualFile containingFile = this.myInterface.getContainingFile();
        Logger log = null;
        boolean b = false;
        Label_0028: {
            try {
                log = OCLog.LOG;
                if (containingFile != null) {
                    b = true;
                    break Label_0028;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            b = false;
        }
        log.assertTrue(b);
        properties.setProperty("HEADER_FILENAME", containingFile.getName());
    }
    
    @NotNull
    @Override
    protected CreateImplementationDialog createDialog(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultName", "com/jetbrains/cidr/lang/quickfixes/OCImplementInterfaceIntentionAction", "createDialog"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        CreateImplementationDialog createImplementationDialog;
        try {
            createImplementationDialog = new CreateImplementationDialog(s);
            if (createImplementationDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementInterfaceIntentionAction", "createDialog"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return createImplementationDialog;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
    
    protected class CreateImplementationDialog extends CreateFileDialogBase
    {
        public CreateImplementationDialog(final String s) {
            super(OCImplementInterfaceIntentionAction.this.getText(), s, null);
        }
    }
}
