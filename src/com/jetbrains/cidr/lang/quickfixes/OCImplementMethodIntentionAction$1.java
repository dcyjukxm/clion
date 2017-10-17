// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementActionContext;

class OCImplementMethodIntentionAction$1 extends OCOverrideImplementActionContext {
    @NotNull
    @Override
    public Collection<OCMethodSymbol> getMemberCandidates() {
        List<OCMethodSymbol> singletonList;
        try {
            singletonList = Collections.singletonList(OCImplementMethodIntentionAction.access$000(OCImplementMethodIntentionAction.this));
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImplementMethodIntentionAction$1", "getMemberCandidates"));
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