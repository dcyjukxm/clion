// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.intellij.codeInsight.intention.HighPriorityAction;

public class OCSynthesizePropertyQuickFix extends OCSynthesizePropertyQuickFixBase implements HighPriorityAction
{
    public OCSynthesizePropertyQuickFix(final OCImplementationSymbol ocImplementationSymbol, final OCPropertySymbol ocPropertySymbol) {
        super(ocImplementationSymbol, ocPropertySymbol);
    }
    
    @Override
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCSynthesizePropertyQuickFix", "isAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (!super.isAvailable(project, editor, psiFile)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        final String categoryName = ((OCSymbolWithParent<T, OCClassSymbol>)this.myProperty).getParent().getCategoryName();
        Label_0099: {
            try {
                if (categoryName == null) {
                    break Label_0099;
                }
                final String s = categoryName;
                final boolean b = s.isEmpty();
                if (b) {
                    break Label_0099;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
            try {
                final String s = categoryName;
                final boolean b = s.isEmpty();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
