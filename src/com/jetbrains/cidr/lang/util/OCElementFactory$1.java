// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.openapi.command.WriteCommandAction;

static final class OCElementFactory$1 extends WriteCommandAction<OCCodeFragment> {
    final /* synthetic */ String val$text;
    final /* synthetic */ Project val$project;
    final /* synthetic */ PsiElement val$context;
    
    protected void run(@NotNull final Result<OCCodeFragment> result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/util/OCElementFactory$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        result.setResult((Object)OCElementFactory.typeCodeFragment(this.val$text, this.val$project, this.val$context, true, true));
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}