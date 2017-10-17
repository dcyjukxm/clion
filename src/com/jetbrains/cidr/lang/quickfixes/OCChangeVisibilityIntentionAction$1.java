// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.intentions.OCDeclareMethodInInterfaceIntentionAction;

static final class OCChangeVisibilityIntentionAction$1 extends OCDeclareMethodInInterfaceIntentionAction {
    final /* synthetic */ OCMethodSymbol val$implSymbol;
    
    @Nullable
    @Override
    protected OCMethodSymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction$1", "locateCandidate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return this.val$implSymbol;
    }
    
    @Nullable
    protected OCClassSymbol getParent(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction$1", "getParent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCChangeVisibilityIntentionAction$1", "getParent"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (this.val$implSymbol != null) {
                return ((OCSymbolWithParent<T, OCClassSymbol>)this.val$implSymbol).getParent();
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return null;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}