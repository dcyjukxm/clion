// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;

class OCGenerateDestructorHandler$1 extends OCCppActionContext<OCStructSymbol, OCDeclaratorSymbol> {
    @NotNull
    @Override
    public Collection<OCDeclaratorSymbol> getMemberCandidates() {
        List<OCDeclaratorSymbol> emptyList;
        try {
            emptyList = Collections.emptyList();
            if (emptyList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDestructorHandler$1", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return emptyList;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}