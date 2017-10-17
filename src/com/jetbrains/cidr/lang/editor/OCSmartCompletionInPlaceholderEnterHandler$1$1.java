// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.lang.editor.completion.SymbolLookupBuilderUtil;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.CompletionProgressIndicator;
import com.intellij.openapi.editor.Editor;
import com.intellij.codeInsight.completion.NextPrevParameterAction;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.CodeCompletionHandlerBase;

class OCSmartCompletionInPlaceholderEnterHandler$1$1 extends CodeCompletionHandlerBase {
    final /* synthetic */ OCSmartCompletionInPlaceholderEnterHandler$1 this$1;
    
    private void b() {
        Label_0033: {
            try {
                if (OCSmartCompletionInPlaceholderEnterHandler.access$000(Runnable.this.val$editor)) {
                    return;
                }
                final CodeCompletionHandlerBase codeCompletionHandlerBase = this;
                final Runnable runnable = codeCompletionHandlerBase.this$1;
                final Editor editor = runnable.val$editor;
                final boolean b = OCSmartCompletionInPlaceholderEnterHandler.access$100(editor);
                if (b) {
                    break Label_0033;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CodeCompletionHandlerBase codeCompletionHandlerBase = this;
                final Runnable runnable = codeCompletionHandlerBase.this$1;
                final Editor editor = runnable.val$editor;
                final boolean b = OCSmartCompletionInPlaceholderEnterHandler.access$100(editor);
                if (b) {
                    new NextPrevParameterAction.Next().getHandler().invoke(Runnable.this.val$project, Runnable.this.val$editor, Runnable.this.val$contextPsiFile);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Override
    protected void lookupItemSelected(final CompletionProgressIndicator completionProgressIndicator, @NotNull final LookupElement lookupElement, final char c, final List<LookupElement> list) {
        try {
            if (lookupElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/editor/OCSmartCompletionInPlaceholderEnterHandler$1$1", "lookupItemSelected"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            super.lookupItemSelected(completionProgressIndicator, lookupElement, c, list);
            if (lookupElement.getUserData((Key)SymbolLookupBuilderUtil.DONT_GO_NEXT_TEMPLATE) != Boolean.TRUE) {
                this.b();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @Override
    protected void completionFinished(final CompletionProgressIndicator completionProgressIndicator, final boolean b) {
        super.completionFinished(completionProgressIndicator, b);
        this.b();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}