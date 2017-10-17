// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers.implement;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.util.Processor;
import java.util.Collections;
import java.util.Set;
import com.intellij.util.CommonProcessors;
import java.util.HashSet;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementActionContext;

class OCImplementOCMethodsHandler$1 extends OCOverrideImplementActionContext {
    final /* synthetic */ PsiElement val$element;
    
    @NotNull
    @Override
    public Collection<OCMethodSymbol> getMemberCandidates() {
        final CommonProcessors.CollectProcessor<OCMethodSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCMethodSymbol>() {
            final /* synthetic */ Set val$names = new HashSet();
            
            protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
                if (this.val$names.add(ocMethodSymbol.getName())) {
                    OCOverrideImplementActionContext.this.myAbstractMethods.add(ocMethodSymbol);
                    return true;
                }
                return false;
            }
        };
        Collection results = null;
        Label_0077: {
            List<OCMethodSymbol> list = null;
            Label_0042: {
                try {
                    if (this.getType().getClassSymbol() != null) {
                        break Label_0077;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0042;
                    }
                    return list;
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler$1", "getMemberCandidates"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw b(ex2);
                }
            }
            return list;
            try {
                this.getType().processInterfaceMethods(this.getInterfaceSymbol(), null, (Processor<OCMethodSymbol>)collectProcessor, this.val$element, true);
                results = collectProcessor.getResults();
                if (results == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCImplementOCMethodsHandler$1", "getMemberCandidates"));
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        return (Collection<OCMethodSymbol>)results;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}