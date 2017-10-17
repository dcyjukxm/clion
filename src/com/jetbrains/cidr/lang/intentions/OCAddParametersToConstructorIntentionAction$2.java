// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateConstructorContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateConstructorHandler;

class OCAddParametersToConstructorIntentionAction$2 extends OCGenerateConstructorHandler {
    final /* synthetic */ OCDeclaratorSymbol val$field;
    final /* synthetic */ OCCppActionContext val$context;
    
    @NotNull
    @Override
    protected OCGenerateConstructorContext evaluateActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
        OCGenerateConstructorContext ocGenerateConstructorContext;
        try {
            ocGenerateConstructorContext = new OCGenerateConstructorContext(ocStructSymbol, psiElement) {
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
            };
            if (ocGenerateConstructorContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction$2", "evaluateActionContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return ocGenerateConstructorContext;
    }
    
    @NotNull
    @Override
    protected List<? extends OCStructSymbol> getParents(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "at", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction$2", "getParents"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        List<OCMembersContainer> singletonList;
        try {
            singletonList = Collections.singletonList(this.val$context.getParent());
            if (singletonList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction$2", "getParents"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return (List<? extends OCStructSymbol>)singletonList;
    }
    
    @Override
    protected boolean enableChooseDialog(final Collection<OCDeclaratorSymbol> collection) {
        return false;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}