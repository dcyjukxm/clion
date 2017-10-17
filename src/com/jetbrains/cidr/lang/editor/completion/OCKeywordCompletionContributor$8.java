// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.TailType;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.TailTypeDecorator;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.psi.PsiComment;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

static final class OCKeywordCompletionContributor$8 extends OCCompletionProvider {
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$8", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocCompletionParameters.getPosition() instanceof PsiComment) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCExpression access$000 = OCKeywordCompletionContributor.access$000(ocCompletionParameters);
        try {
            if (access$000 != null) {
                MethodSelectorCompletionContributor.addCompletionsForReceiver(access$000, "", ocCompletionParameters, set, (Condition<OCMethodSymbol>)Conditions.alwaysTrue());
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final CompletionResultSet withOCPrefixMather = OCKeywordCompletionContributor.withOCPrefixMather(set, ocCompletionParameters.getRealPosition(), ocCompletionParameters.getOffset());
        final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getContextOfType(ocCompletionParameters.getPosition(), new Class[] { OCCallable.class });
        if (ocCallable != null) {
            withOCPrefixMather.addElement(OCCompletionPriority.keywordWithPriority((LookupElement)new TailTypeDecorator<LookupElement>(TemplateInsertHandler.lookup("return").bold()) {
                final /* synthetic */ boolean val$isVoid = ocCallable.getReturnType().equalsAfterResolving(OCVoidType.instance(), (PsiElement)ocCallable.getContainingOCFile());
                
                @Nullable
                @Override
                protected TailType computeTailType(final InsertionContext insertionContext) {
                    final char completionChar = insertionContext.getCompletionChar();
                    if (this.val$isVoid) {
                        return (completionChar == ' ') ? TailType.HUMBLE_SPACE_BEFORE_WORD : TailType.SEMICOLON;
                    }
                    return (completionChar == ';') ? TailType.SEMICOLON : TailType.HUMBLE_SPACE_BEFORE_WORD;
                }
            }, OCCompletionPriority.HIGH_KEYWORDS_PRIORITY));
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}