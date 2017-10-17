// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.evaluation.EvaluationMode;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.application.WriteAction;

class OCDebuggerEditorsProvider$1 extends WriteAction<Document> {
    final /* synthetic */ EvaluationMode val$mode;
    final /* synthetic */ String val$text;
    final /* synthetic */ Project val$project;
    final /* synthetic */ PsiElement val$context;
    
    protected void run(@NotNull final Result<Document> result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/debugger/OCDebuggerEditorsProvider$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        OCCodeFragment ocCodeFragment = null;
        Label_0095: {
            try {
                if (this.val$mode == EvaluationMode.EXPRESSION) {
                    ocCodeFragment = OCElementFactory.expressionCodeFragment(this.val$text, this.val$project, this.val$context, true, false);
                    break Label_0095;
                }
            }
            catch (Throwable t2) {
                throw a(t2);
            }
            ocCodeFragment = OCElementFactory.expressionOrStatementsCodeFragment(this.val$text, this.val$project, this.val$context, true, false);
        }
        result.setResult((Object)PsiDocumentManager.getInstance(this.val$project).getDocument((PsiFile)ocCodeFragment));
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}