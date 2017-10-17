// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.util.Processor;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.openapi.application.ReadAction;

class OCElementInMacroSubstitutionReferenceSearch$1 extends ReadAction<Boolean> {
    final /* synthetic */ ReferencesSearch.SearchParameters val$queryParameters;
    final /* synthetic */ Processor val$consumer;
    
    protected void run(@NotNull final Result<Boolean> result) {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1", "run"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        result.setResult((Object)OCElementInMacroSubstitutionReferenceSearch.this.doExecute(this.val$queryParameters, (Processor<PsiReference>)this.val$consumer));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}