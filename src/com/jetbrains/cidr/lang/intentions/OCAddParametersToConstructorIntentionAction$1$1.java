// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.CommonProcessors;

class OCAddParametersToConstructorIntentionAction$1$1 extends CommonProcessors.CollectProcessor<OCFunctionSymbol> {
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
}