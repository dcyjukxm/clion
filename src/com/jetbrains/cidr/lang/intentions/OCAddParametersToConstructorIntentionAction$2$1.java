// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateConstructorContext;

class OCAddParametersToConstructorIntentionAction$2$1 extends OCGenerateConstructorContext {
    @NotNull
    @Override
    public Collection<OCDeclaratorSymbol> getMemberCandidates() {
        List<OCDeclaratorSymbol> singletonList;
        try {
            singletonList = Collections.singletonList(OCGenerateConstructorHandler.this.val$field);
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction$2$1", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return singletonList;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}