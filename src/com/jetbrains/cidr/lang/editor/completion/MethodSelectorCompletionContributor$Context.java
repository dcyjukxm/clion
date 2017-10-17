// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import org.jetbrains.annotations.NotNull;

public static class Context
{
    @NotNull
    private final String myExactSelectorPrefix;
    @NotNull
    private final OCObjectTypeContext myReceiverContext;
    @NotNull
    private final Condition<OCMethodSymbol> myCondition;
    @Nullable
    private final PsiElement myReceiver;
    
    public Context(@NotNull final String myExactSelectorPrefix, @Nullable final PsiElement myReceiver, @NotNull final OCObjectTypeContext myReceiverContext, @NotNull final Condition<OCMethodSymbol> myCondition) {
        if (myExactSelectorPrefix == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "<init>"));
        }
        if (myReceiverContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "<init>"));
        }
        if (myCondition == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "<init>"));
        }
        this.myExactSelectorPrefix = myExactSelectorPrefix;
        this.myReceiver = myReceiver;
        this.myReceiverContext = myReceiverContext;
        this.myCondition = myCondition;
    }
    
    @NotNull
    public String getExactSelectorPrefix() {
        String myExactSelectorPrefix;
        try {
            myExactSelectorPrefix = this.myExactSelectorPrefix;
            if (myExactSelectorPrefix == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "getExactSelectorPrefix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myExactSelectorPrefix;
    }
    
    @NotNull
    public OCObjectTypeContext getReceiverContext() {
        OCObjectTypeContext myReceiverContext;
        try {
            myReceiverContext = this.myReceiverContext;
            if (myReceiverContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "getReceiverContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myReceiverContext;
    }
    
    @NotNull
    public Condition<OCMethodSymbol> getCondition() {
        Condition<OCMethodSymbol> myCondition;
        try {
            myCondition = this.myCondition;
            if (myCondition == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "getCondition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myCondition;
    }
    
    @Nullable
    public PsiElement getReceiver() {
        return this.myReceiver;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
