// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.codeInsight.FileModificationService;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateIvarsActionContext;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.intellij.codeInsight.intention.LowPriorityAction;

public class OCMakePropertyDynamicQuickFix extends OCSynthesizePropertyQuickFixBase implements LowPriorityAction
{
    public OCMakePropertyDynamicQuickFix(final OCImplementationSymbol ocImplementationSymbol, final OCPropertySymbol ocPropertySymbol) {
        super(ocImplementationSymbol, ocPropertySymbol);
    }
    
    @NotNull
    @Override
    public String getText() {
        String string = null;
        Label_0055: {
            String s = null;
            Label_0020: {
                try {
                    if (this.myProperty != null) {
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
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCMakePropertyDynamicQuickFix", "getText"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return s;
            try {
                string = "Make " + this.myProperty.getNameWithKindLowercase() + " @dynamic";
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCMakePropertyDynamicQuickFix", "getText"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return string;
    }
    
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile, @NotNull final OCGenerateIvarsActionContext ocGenerateIvarsActionContext) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCMakePropertyDynamicQuickFix", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCMakePropertyDynamicQuickFix", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (ocGenerateIvarsActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/quickfixes/OCMakePropertyDynamicQuickFix", "invoke"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        final OCClassDeclarationBase ocClassDeclarationBase = this.myImplementationSymbol.locateDefinition();
        Label_0188: {
            try {
                if (!(ocClassDeclarationBase instanceof OCImplementation)) {
                    return;
                }
                final FileModificationService fileModificationService = FileModificationService.getInstance();
                final OCClassDeclarationBase ocClassDeclarationBase2 = ocClassDeclarationBase;
                final PsiFile psiFile2 = ocClassDeclarationBase2.getContainingFile();
                final boolean b = fileModificationService.prepareFileForWrite(psiFile2);
                if (!b) {
                    return;
                }
                break Label_0188;
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            try {
                final FileModificationService fileModificationService = FileModificationService.getInstance();
                final OCClassDeclarationBase ocClassDeclarationBase2 = ocClassDeclarationBase;
                final PsiFile psiFile2 = ocClassDeclarationBase2.getContainingFile();
                final boolean b = fileModificationService.prepareFileForWrite(psiFile2);
                if (!b) {
                    return;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
        }
        final OCImplementation ocImplementation = (OCImplementation)ocClassDeclarationBase;
        ocImplementation.addBefore((PsiElement)OCElementFactory.synthesizeList("@dynamic", this.myProperty.getName(), null, (PsiElement)psiFile), ocImplementation.getInstanceVariablesList().getNextSibling());
        final VirtualFile virtualFile = psiFile.getVirtualFile();
        Label_0270: {
            try {
                if (virtualFile == null) {
                    return;
                }
                final Application application = ApplicationManager.getApplication();
                final boolean b2 = application.isUnitTestMode();
                if (!b2) {
                    break Label_0270;
                }
                return;
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
            try {
                final Application application = ApplicationManager.getApplication();
                final boolean b2 = application.isUnitTestMode();
                if (!b2) {
                    CommandProcessor.getInstance().addAffectedFiles(project, new VirtualFile[] { virtualFile });
                }
            }
            catch (IncorrectOperationException ex7) {
                throw a(ex7);
            }
        }
    }
    
    @Nullable
    public PsiElement getElementToMakeWritable(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentFile", "com/jetbrains/cidr/lang/quickfixes/OCMakePropertyDynamicQuickFix", "getElementToMakeWritable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public boolean startInWriteAction() {
        return true;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
