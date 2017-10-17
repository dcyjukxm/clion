// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.command.WriteCommandAction;

class OCCreateNewDefinitionIntentionAction$1 extends WriteCommandAction {
    final /* synthetic */ OCImportSymbolFix val$importFix;
    final /* synthetic */ Project val$project;
    final /* synthetic */ PsiFile val$file;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        this.val$importFix.fixFirstItem(this.val$project, this.val$file);
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}