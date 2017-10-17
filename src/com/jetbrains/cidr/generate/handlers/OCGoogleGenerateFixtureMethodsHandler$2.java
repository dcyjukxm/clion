// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.handlers;

import com.jetbrains.cidr.execution.testing.google.CidrGoogleTestUtil;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;

class OCGoogleGenerateFixtureMethodsHandler$2 extends OCCppActionContext<OCStructSymbol, OCFunctionSymbol> {
    final /* synthetic */ OCStructSymbol val$parent;
    
    @NotNull
    @Override
    public Collection<OCFunctionSymbol> getMemberCandidates() {
        List<OCFunctionSymbol> emptyList;
        try {
            emptyList = Collections.emptyList();
            if (emptyList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateFixtureMethodsHandler$2", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return emptyList;
    }
    
    @Override
    public boolean isValid() {
        return CidrGoogleTestUtil.isGoogleTestFixture(this.val$parent, true);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}