// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.command.WriteCommandAction;

class OCBaseInplaceIntroducer$1$1 extends WriteCommandAction {
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer$1$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        ActionListener.this.val$listener.process((Object)ActionListener.this.val$checkBox.isSelected());
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}