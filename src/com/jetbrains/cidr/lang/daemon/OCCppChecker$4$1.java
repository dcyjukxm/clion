// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;

class OCCppChecker$4$1 extends OCCppActionContext<OCStructSymbol, OCFunctionSymbol> {
    @NotNull
    @Override
    public Collection<OCFunctionSymbol> getMemberCandidates() {
        List<OCFunctionSymbol> singletonList;
        try {
            singletonList = Collections.singletonList(OCAddParametersToConstructorIntentionAction.this.val$symbol);
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCCppChecker$4$1", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return singletonList;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}