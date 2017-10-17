// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiFile;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.openapi.util.Pass;

class OCIfRespondsToSurrounder$1 extends Pass<OCSendMessageExpression> {
    final /* synthetic */ Project val$project;
    final /* synthetic */ PsiElement[] val$elements;
    final /* synthetic */ List val$sendMessageExprs;
    
    public void pass(final OCSendMessageExpression ocSendMessageExpression) {
        new WriteCommandAction(this.val$project, new PsiFile[0]) {
            protected void run(@NotNull final Result result) throws Exception {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/surround/OCIfRespondsToSurrounder$1$1", "run"));
                    }
                }
                catch (Exception ex) {
                    throw b(ex);
                }
                OCIfRespondsToSurrounder.this.doSurroundElements(Pass.this.val$project, Pass.this.val$elements, Pass.this.val$sendMessageExprs.indexOf(ocSendMessageExpression));
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }.execute();
    }
}