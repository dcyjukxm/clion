// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import com.intellij.codeInsight.completion.CodeCompletionHandlerBase;
import com.intellij.codeInsight.completion.CompletionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.openapi.util.Condition;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

private static class LookupElementInsertHandler implements InsertHandler<LookupElement>
{
    private final char myChar;
    private final Condition<InsertionContext> myCondition;
    
    private LookupElementInsertHandler() {
        this(' ');
    }
    
    private LookupElementInsertHandler(final char c) {
        this(c, (Condition<InsertionContext>)null);
    }
    
    private LookupElementInsertHandler(final char myChar, @Nullable final Condition<InsertionContext> myCondition) {
        this.myChar = myChar;
        this.myCondition = myCondition;
    }
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        Label_0025: {
            try {
                if (insertionContext.getCompletionChar() == this.myChar) {
                    return;
                }
                final LookupElementInsertHandler lookupElementInsertHandler = this;
                final Condition<InsertionContext> condition = lookupElementInsertHandler.myCondition;
                if (condition != null) {
                    break Label_0025;
                }
                break Label_0025;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final LookupElementInsertHandler lookupElementInsertHandler = this;
                final Condition<InsertionContext> condition = lookupElementInsertHandler.myCondition;
                if (condition != null) {
                    if (!this.myCondition.value((Object)insertionContext)) {
                        return;
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final int tailOffset = insertionContext.getTailOffset();
        final CharSequence charsSequence = insertionContext.getDocument().getCharsSequence();
        try {
            if (tailOffset == charsSequence.length()) {
                addCompletionString(insertionContext, tailOffset, String.valueOf(this.myChar));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    protected static void addCompletionString(@NotNull final InsertionContext insertionContext, final int n, @NotNull final String s) {
        try {
            if (insertionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$LookupElementInsertHandler", "addCompletionString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "string", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$LookupElementInsertHandler", "addCompletionString"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        insertionContext.getDocument().insertString(n, (CharSequence)s);
        insertionContext.getEditor().getCaretModel().moveCaretRelatively(s.length(), 0, false, false, true);
    }
    
    protected static void reInvokeCompletion(@NotNull final InsertionContext insertionContext) {
        try {
            if (insertionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$LookupElementInsertHandler", "reInvokeCompletion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final IllegalArgumentException ex2;
        insertionContext.setLaterRunnable(() -> {
            try {
                if (insertionContext == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$LookupElementInsertHandler", "lambda$reInvokeCompletion$0"));
                    throw ex2;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            new CodeCompletionHandlerBase(CompletionType.BASIC).invokeCompletion(insertionContext.getProject(), insertionContext.getEditor());
        });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
