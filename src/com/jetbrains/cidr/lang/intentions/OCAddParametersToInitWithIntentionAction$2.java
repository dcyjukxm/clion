// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.types.OCObjectType;
import java.util.List;
import java.util.Collections;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateMethodActionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.generate.handlers.OCGenerateInitWithHandler;

class OCAddParametersToInitWithIntentionAction$2 extends OCGenerateInitWithHandler {
    final /* synthetic */ OCInstanceVariableSymbol val$ivar;
    
    @NotNull
    @Override
    protected OCGenerateMethodActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        final OCGenerateMethodActionContext evaluateActionContext = super.evaluateActionContext(ocClassSymbol, psiElement);
        OCGenerateMethodActionContext ocGenerateMethodActionContext;
        try {
            ocGenerateMethodActionContext = new OCGenerateMethodActionContext((OCClassSymbol)((OCActionContext<OCClassSymbol, M>)evaluateActionContext).getParent(), Collections.singletonList(evaluateActionContext.getBaseMethod()), evaluateActionContext.getType(), psiElement) {
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
            };
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction$2", "evaluateActionContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocGenerateMethodActionContext;
    }
    
    @Override
    protected boolean enableChooseDialog(final Collection<OCInstanceVariableSymbol> collection) {
        return false;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}