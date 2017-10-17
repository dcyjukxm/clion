// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateMethodActionContext;

class OCAddParametersToInitWithIntentionAction$2$1 extends OCGenerateMethodActionContext {
    @NotNull
    @Override
    public Collection<OCInstanceVariableSymbol> getMemberCandidates() {
        List<OCInstanceVariableSymbol> singletonList;
        try {
            singletonList = Collections.singletonList(OCGenerateInitWithHandler.this.val$ivar);
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction$2$1", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return singletonList;
    }
    
    private static IllegalStateException c(final IllegalStateException ex) {
        return ex;
    }
}