// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.command.WriteCommandAction;

class OCTypedHandlerDelegate$2 extends WriteCommandAction {
    final /* synthetic */ Document val$document;
    final /* synthetic */ OCLiteralExpression val$literal;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/OCTypedHandlerDelegate$2", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        this.val$document.insertString(this.val$literal.getTextRange().getStartOffset(), (CharSequence)"@");
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}