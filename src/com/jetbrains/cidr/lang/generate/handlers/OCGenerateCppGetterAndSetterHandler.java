// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateGetterSetterContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;

public class OCGenerateCppGetterAndSetterHandler extends OCGenerateCppGetterSetterHandlerBase
{
    @Override
    protected String getActionTitle() {
        return "Generate Getters and Setters";
    }
    
    @Nullable
    @Override
    protected OCGenerateGetterSetterContext evaluateActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
        return new OCGenerateGetterSetterContext(ocStructSymbol, psiElement, OCGenerateGetterSetterContext.Mode.GETTER_SETTER);
    }
}
