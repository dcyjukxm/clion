// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;

class OCAddParametersToInitWithIntentionAction$1 extends OCObjCActionContext<OCMethodSymbol> {
    @Override
    protected Class<OCMethodSymbol> getMemberSymbolClass() {
        return OCMethodSymbol.class;
    }
    
    @NotNull
    @Override
    public Collection<OCMethodSymbol> getMemberCandidates() {
        final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
        Collection results;
        try {
            this.getImplementationSymbol().processMembers(this.getMemberSymbolClass(), (com.intellij.util.Processor<? super OCMethodSymbol>)collectProcessor);
            results = collectProcessor.getResults();
            if (results == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToInitWithIntentionAction$1", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return (Collection<OCMethodSymbol>)results;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}