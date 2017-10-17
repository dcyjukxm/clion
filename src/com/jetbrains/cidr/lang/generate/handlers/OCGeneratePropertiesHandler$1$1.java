// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.intentions.OCMoveToPrivateCategoryIntentionAction;

class OCGeneratePropertiesHandler$1$1 extends OCMoveToPrivateCategoryIntentionAction {
    final /* synthetic */ PsiElement val$newDeclaration;
    
    @Override
    protected OCMemberSymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCGeneratePropertiesHandler$1$1", "locateCandidate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return ((OCProperty)this.val$newDeclaration).getDeclaration().getDeclarators().get(0).getSymbol();
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}