// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.util.CommonProcessors;

class OCGenerateIvarsHandler$1 extends CommonProcessors.FindFirstProcessor<OCInstanceVariableSymbol> {
    protected boolean accept(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
        return ocInstanceVariableSymbol.getGeneratedFromProperty() == null;
    }
}