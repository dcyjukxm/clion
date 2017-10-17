// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import java.util.Collections;
import java.util.Arrays;
import javax.swing.JComponent;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateMethodActionContext;

class OCGenerateMethodHandler$1 extends OCGenerateMethodActionContext {
    @Override
    public List<OCClassSymbol> getSymbolsToModify() {
        if (((OCClassActionHandlerBase<P, M, C>)OCGenerateMethodHandler.this).getOption((C)this, (OCOption<Boolean, JComponent>)OCGenerateMethodHandler.access$000())) {
            return Arrays.asList(this.myInterfaceSymbol, this.myImplementationSymbol);
        }
        return (List<OCClassSymbol>)Collections.singletonList(this.myImplementationSymbol);
    }
    
    @Override
    protected boolean showSymbol(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
        return OCGenerateMethodHandler.this.showSymbolInChooser(ocInstanceVariableSymbol, this);
    }
}