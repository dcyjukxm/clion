// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.lookup.LookupElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.types.OCObjectType;
import org.jetbrains.annotations.NotNull;
import java.util.HashSet;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

private static class CompletionBuilder implements Processor<OCSymbol>
{
    private PsiElement myContextExpression;
    private CompletionResultSet myResult;
    private HashSet<String> myNames;
    
    public CompletionBuilder(final CompletionResultSet myResult, final PsiElement myContextExpression) {
        this.myContextExpression = myContextExpression;
        this.myResult = myResult;
        (this.myNames = new HashSet<String>()).add("_dispatch_data_destructor_free");
    }
    
    public boolean process(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$CompletionBuilder", "process"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.tryAddLookup(ocSymbol, (LookupElement)SymbolLookupBuilderUtil.lookup(ocSymbol, false, null, null, this.myContextExpression, null));
        return true;
    }
    
    public void tryAddLookup(@NotNull final OCSymbol ocSymbol, @NotNull final LookupElement lookupElement) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$CompletionBuilder", "tryAddLookup"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (lookupElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lookup", "com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$CompletionBuilder", "tryAddLookup"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (this.myNames.add(ocSymbol.getSignature())) {
                this.addLookup(lookupElement);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    protected void addLookup(@NotNull final LookupElement lookupElement) {
        try {
            if (lookupElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lookup", "com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$CompletionBuilder", "addLookup"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myResult.addElement(lookupElement);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
