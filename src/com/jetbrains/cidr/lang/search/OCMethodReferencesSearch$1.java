// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.util.Processor;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.openapi.application.ReadAction;

class OCMethodReferencesSearch$1 extends ReadAction<Boolean> {
    final /* synthetic */ ReferencesSearch.SearchParameters val$queryParameters;
    final /* synthetic */ Processor val$consumer;
    
    protected void run(@NotNull final Result<Boolean> result) {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$1", "run"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        result.setResult((Object)OCMethodReferencesSearch.access$000(OCMethodReferencesSearch.this, this.val$queryParameters, this.val$consumer));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}