// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

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
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;

public class OCCreateInterfaceIntentionAction extends OCNewFileActionBase implements IntentionAction
{
    private OCImplementationSymbol myImplementation;
    private PsiElement myImplNameIdentifier;
    
    public OCCreateInterfaceIntentionAction(final OCImplementationSymbol myImplementation, final PsiElement myImplNameIdentifier) {
        super((FileType)OCFileType.INSTANCE, OCBundle.message("create.interface.title", new Object[0]), OCBundle.message("create.interface.description", new Object[0]), CidrLangIcons.CodeAssistantClass);
        this.myImplementation = myImplementation;
        this.myImplNameIdentifier = myImplNameIdentifier;
    }
    
    @NotNull
    public String getText() {
        String string = null;
        Label_0055: {
            String s = null;
            Label_0020: {
                try {
                    if (this.myImplementation != null) {
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
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCCreateInterfaceIntentionAction", "getText"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return s;
            try {
                string = "Create interface for " + this.myImplementation.getNameWithKindLowercase();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCCreateInterfaceIntentionAction", "getText"));
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCCreateInterfaceIntentionAction", "getFamilyName"));
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
            s = "Interface";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCCreateInterfaceIntentionAction", "getDefaultName"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCCreateInterfaceIntentionAction", "isAvailable"));
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
                final OCCreateInterfaceIntentionAction ocCreateInterfaceIntentionAction = this;
                final OCImplementationSymbol ocImplementationSymbol = ocCreateInterfaceIntentionAction.myImplementation;
                final boolean b = OCSearchScope.isInProjectSources(ocImplementationSymbol);
                if (b) {
                    break Label_0068;
                }
                return false;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final OCCreateInterfaceIntentionAction ocCreateInterfaceIntentionAction = this;
                final OCImplementationSymbol ocImplementationSymbol = ocCreateInterfaceIntentionAction.myImplementation;
                final boolean b = OCSearchScope.isInProjectSources(ocImplementationSymbol);
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCCreateInterfaceIntentionAction", "invoke"));
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
            final IllegalArgumentException ex3;
            this.addAuxAction(p2 -> {
                try {
                    if (project == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCCreateInterfaceIntentionAction", "lambda$invoke$0"));
                        throw ex3;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
                new OCImportSymbolFix(this.myImplNameIdentifier, this.myImplementation.getInterface(true, this.myImplementation.getCategoryName())).fixFirstItem(project, psiFile);
                OCImportSymbolFix.fixAllSymbolsRecursively(this.myImplNameIdentifier);
                return;
            });
            if (!ApplicationManager.getApplication().isUnitTestMode()) {
                this.performAction(project, psiFile.getParent(), psiFile, this.myImplementation.getName());
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
    }
    
    public boolean startInWriteAction() {
        return false;
    }
    
    @Override
    protected String getFileTemplate(final String s) {
        return "Objective-C Header File.h";
    }
    
    @NotNull
    @Override
    protected NewFileNames getNewFileNames(final String s) {
        NewFileNames newFileNames;
        try {
            newFileNames = new NewFileNames(null, s + ".h");
            if (newFileNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCCreateInterfaceIntentionAction", "getNewFileNames"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return newFileNames;
    }
    
    @NotNull
    @Override
    protected CreateInterfaceDialog createDialog(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defaultName", "com/jetbrains/cidr/lang/quickfixes/OCCreateInterfaceIntentionAction", "createDialog"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        CreateInterfaceDialog createInterfaceDialog;
        try {
            createInterfaceDialog = new CreateInterfaceDialog(s);
            if (createInterfaceDialog == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCCreateInterfaceIntentionAction", "createDialog"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return createInterfaceDialog;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
    
    protected class CreateInterfaceDialog extends CreateFileDialogBase
    {
        public CreateInterfaceDialog(final String s) {
            super(OCCreateInterfaceIntentionAction.this.getText(), s, null);
        }
    }
}
