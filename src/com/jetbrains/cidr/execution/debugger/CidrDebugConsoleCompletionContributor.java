// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.completion.CompletionResultSet;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionContributor;

public class CidrDebugConsoleCompletionContributor extends CompletionContributor
{
    public void fillCompletionVariants(@NotNull final CompletionParameters completionParameters, @NotNull final CompletionResultSet set) {
        try {
            if (completionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/execution/debugger/CidrDebugConsoleCompletionContributor", "fillCompletionVariants"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/debugger/CidrDebugConsoleCompletionContributor", "fillCompletionVariants"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (completionParameters.getInvocationCount() != 0) {
            final PsiFile originalFile = completionParameters.getOriginalFile();
            final Document document = PsiDocumentManager.getInstance(originalFile.getProject()).getDocument(originalFile);
            if (document != null) {
                final CidrDebugProcess cidrDebugProcess = (CidrDebugProcess)document.getUserData((Key)CidrDebugProcess.DEBUG_PROCESS_KEY);
                try {
                    if (cidrDebugProcess != null) {
                        cidrDebugProcess.handleConsoleCompletion(completionParameters, set);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        super.fillCompletionVariants(completionParameters, set);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
