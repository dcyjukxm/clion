// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.util.CommonProcessors;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;

class OCAddParametersToConstructorIntentionAction$1 extends OCCppActionContext<OCStructSymbol, OCFunctionSymbol> {
    final /* synthetic */ OCDeclaratorSymbol val$field;
    final /* synthetic */ OCStructSymbol val$parent;
    
    @NotNull
    @Override
    public Collection<OCFunctionSymbol> getMemberCandidates() {
        final CommonProcessors.CollectProcessor<OCFunctionSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCFunctionSymbol>() {
            protected boolean accept(OCFunctionSymbol ocFunctionSymbol) {
                ocFunctionSymbol = (OCFunctionSymbol)ocFunctionSymbol.getDefinitionSymbol();
                if (ocFunctionSymbol == null) {
                    return false;
                }
                final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)ocFunctionSymbol.locateFunctionDefinition();
                final OCConstructorInitializationList list = (ocFunctionDefinition != null) ? ocFunctionDefinition.getConstructorInitializationList() : null;
                if (list != null) {
                    final Iterator<OCConstructorFieldInitializer> iterator = list.getInitializers().iterator();
                    while (iterator.hasNext()) {
                        final OCReferenceElement referenceElement = iterator.next().getReferenceElement();
                        if (OCCppActionContext.this.val$field.equals((referenceElement != null) ? referenceElement.resolveToSymbol() : null)) {
                            return false;
                        }
                    }
                }
                return true;
            }
        };
        Collection results;
        try {
            this.val$parent.processConstructors((Processor<? super OCFunctionSymbol>)collectProcessor);
            results = collectProcessor.getResults();
            if (results == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCAddParametersToConstructorIntentionAction$1", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Collection<OCFunctionSymbol>)results;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}