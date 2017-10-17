// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.command.WriteCommandAction;

class OCImportSymbolFix$AutoImportItem$1 extends WriteCommandAction {
    final /* synthetic */ PsiElement val$element;
    final /* synthetic */ PsiFile val$file;
    final /* synthetic */ ImportStyle val$importStyle;
    final /* synthetic */ OCFile val$associatedFile;
    
    public void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        int n = this.val$element.getTextOffset();
        if (AutoImportItem.access$500(AutoImportItem.this) != null) {
            n = Math.min(n, AutoImportItem.access$500(AutoImportItem.this).getTextOffset());
        }
        try {
            OCImportSymbolFix.addImportToFile((OCFile)this.val$file, OCImportSymbolFix.access$600(AutoImportItem.this, this.val$importStyle, this.val$element), this.val$importStyle, n);
            if (this.val$associatedFile != null) {
                OCImportSymbolFix.addImportToFile(this.val$associatedFile, OCImportSymbolFix.access$600(AutoImportItem.this, ImportStyle.INCLUDE, this.val$element), ImportStyle.INCLUDE, n);
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}