// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.FileModificationService;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public abstract class OCSymbolQuickFix<T extends OCSymbol<?>> extends OCQuickFix
{
    protected T mySymbol;
    
    protected OCSymbolQuickFix(@Nullable final T mySymbol) {
        this.mySymbol = mySymbol;
    }
    
    protected abstract boolean isAvailable(final T p0);
    
    protected abstract String getTextInternal(final T p0);
    
    protected abstract void invoke(final T p0);
    
    @Override
    public boolean isAvailable() {
        return this.isAvailable(this.mySymbol);
    }
    
    @Override
    protected String getTextInternal() {
        return this.getTextInternal(this.mySymbol);
    }
    
    public void invoke() {
        final OCFile containingOCFile = this.mySymbol.getContainingOCFile();
        Label_0031: {
            try {
                if (containingOCFile == null) {
                    return;
                }
                final FileModificationService fileModificationService = FileModificationService.getInstance();
                final OCFile ocFile = containingOCFile;
                final boolean b = fileModificationService.prepareFileForWrite((PsiFile)ocFile);
                if (b) {
                    break Label_0031;
                }
                return;
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            try {
                final FileModificationService fileModificationService = FileModificationService.getInstance();
                final OCFile ocFile = containingOCFile;
                final boolean b = fileModificationService.prepareFileForWrite((PsiFile)ocFile);
                if (b) {
                    PsiDocumentManager.getInstance(containingOCFile.getProject()).commitAllDocuments();
                    this.invoke(this.mySymbol);
                }
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
        }
    }
    
    @Override
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCSymbolQuickFix", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        this.invoke();
    }
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
