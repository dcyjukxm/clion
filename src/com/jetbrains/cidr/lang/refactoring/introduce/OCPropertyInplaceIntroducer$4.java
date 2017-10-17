// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.intentions.OCMoveToPrivateCategoryIntentionAction;

class OCPropertyInplaceIntroducer$4 extends OCMoveToPrivateCategoryIntentionAction {
    final /* synthetic */ OCSymbol val$propertySymbol;
    
    @Override
    protected OCMemberSymbol locateCandidate(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/introduce/OCPropertyInplaceIntroducer$4", "locateCandidate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return (OCMemberSymbol)this.val$propertySymbol;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}