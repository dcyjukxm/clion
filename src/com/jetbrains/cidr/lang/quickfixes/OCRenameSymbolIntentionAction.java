// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.openapi.application.Application;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.refactoring.rename.OCInplaceRenameHandler;
import com.jetbrains.cidr.lang.refactoring.rename.OCInplaceRenamer;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.codeInsight.intention.HighPriorityAction;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCRenameSymbolIntentionAction extends OCSymbolQuickFix<OCSymbol<?>> implements HighPriorityAction
{
    public OCRenameSymbolIntentionAction(final OCSymbol ocSymbol) {
        super(ocSymbol);
    }
    
    @Override
    protected boolean isAvailable(final OCSymbol ocSymbol) {
        return OCSearchScope.isInProjectSources(ocSymbol);
    }
    
    @Override
    protected String getTextInternal(final OCSymbol ocSymbol) {
        return "Rename " + ocSymbol.getNameWithKindLowercase();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Rename symbol";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRenameSymbolIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw d(ex);
        }
        return s;
    }
    
    @Override
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCRenameSymbolIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw d(ex);
        }
        if (psiFile != null) {
            PsiDocumentManager.getInstance(project).commitAllDocuments();
            final PsiElement locateDefinition = ((OCSymbol<PsiElement>)this.mySymbol).locateDefinition();
            try {
                if (locateDefinition == null || editor == null) {
                    return;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw d(ex2);
            }
            final OCInplaceRenamer ocInplaceRenamer = (OCInplaceRenamer)new OCInplaceRenameHandler().createRenamer(locateDefinition, editor);
            Label_0123: {
                try {
                    if (ocInplaceRenamer == null) {
                        return;
                    }
                    final Application application = ApplicationManager.getApplication();
                    final boolean b = application.isUnitTestMode();
                    if (b) {
                        break Label_0123;
                    }
                    break Label_0123;
                }
                catch (IncorrectOperationException ex3) {
                    throw d(ex3);
                }
                try {
                    final Application application = ApplicationManager.getApplication();
                    final boolean b = application.isUnitTestMode();
                    if (b) {
                        ocInplaceRenamer.performRenameInner(locateDefinition, "renamed");
                        return;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw d(ex4);
                }
            }
            ocInplaceRenamer.performInplaceRename();
        }
    }
    
    @Override
    protected void invoke(final OCSymbol ocSymbol) {
    }
    
    private static IncorrectOperationException d(final IncorrectOperationException ex) {
        return ex;
    }
}
