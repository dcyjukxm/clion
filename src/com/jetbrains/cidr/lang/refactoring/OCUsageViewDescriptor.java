// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.intellij.refactoring.RefactoringBundle;
import com.intellij.usageView.UsageViewBundle;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.usageView.UsageViewDescriptor;

public class OCUsageViewDescriptor implements UsageViewDescriptor
{
    private PsiElement myElement;
    private String myElementsHeader;
    
    public OCUsageViewDescriptor(final PsiElement myElement, final String myElementsHeader) {
        this.myElement = myElement;
        this.myElementsHeader = myElementsHeader;
    }
    
    @NotNull
    public PsiElement[] getElements() {
        PsiElement[] array;
        try {
            array = new PsiElement[] { this.myElement };
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/OCUsageViewDescriptor", "getElements"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return array;
    }
    
    public String getProcessedElementsHeader() {
        return this.myElementsHeader;
    }
    
    public String getCodeReferencesText(final int n, final int n2) {
        return RefactoringBundle.message("references.to.be.changed", new Object[] { UsageViewBundle.getReferencesString(n, n2) });
    }
    
    public String getCommentReferencesText(final int n, final int n2) {
        return RefactoringBundle.message("comments.elements.header", new Object[] { UsageViewBundle.getOccurencesString(n, n2) });
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
