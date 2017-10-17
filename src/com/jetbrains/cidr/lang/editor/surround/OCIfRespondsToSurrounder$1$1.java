// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.openapi.command.WriteCommandAction;

class OCIfRespondsToSurrounder$1$1 extends WriteCommandAction {
    final /* synthetic */ OCSendMessageExpression val$expr;
    
    protected void run(@NotNull final Result result) throws Exception {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder$1$1", "run"));
            }
        }
        catch (Exception ex) {
            throw b(ex);
        }
        Pass.this.this$0.doSurroundElements(Pass.this.val$project, Pass.this.val$elements, Pass.this.val$sendMessageExprs.indexOf(this.val$expr));
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}