// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.openapi.util.Comparing;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCTollFreeBridges;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

private class BoxingProcessor extends CommonProcessors.FindFirstProcessor<OCMethodSymbol>
{
    private final OCType myExprType;
    private final String myMethodPrefix;
    
    public BoxingProcessor(final OCType myExprType, final String myMethodPrefix) {
        this.myExprType = myExprType;
        this.myMethodPrefix = myMethodPrefix;
    }
    
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        if ((ocMethodSymbol.getName().equals("valueWithPointer:") && this.myExprType instanceof OCPointerType && OCTollFreeBridges.getNSCounterpart(this.myExprType.getName()) == null) || (ocMethodSymbol.getName().equals("value:withObjCType:") && this.myExprType instanceof OCStructType && !OCTollFreeBridges.hasCFCounterpart(this.myExprType.getName()))) {
            return super.process((Object)ocMethodSymbol);
        }
        if (ocMethodSymbol.getName().startsWith(this.myMethodPrefix) && ocMethodSymbol.getSelectors().size() == 1 && !ocMethodSymbol.isDeprecated()) {
            final OCDeclaratorSymbol parameter = ocMethodSymbol.getSelectors().get(0).getParameter();
            if (parameter != null) {
                final OCType resolvedType = parameter.getResolvedType();
                if (resolvedType.equalsWithAliasName(this.myExprType, (PsiElement)OCConvertTypeIntentionAction.this.myElementPtr.getContainingFile()) || (resolvedType instanceof OCPointerType && Comparing.equal(resolvedType.getAliasName(), this.myExprType.getAliasName()) && ((OCPointerType)resolvedType).validateConstPointers(this.myExprType, OCConvertTypeIntentionAction.this.myElementPtr.getElement()).getState() == OCType.TypeCheckState.OK)) {
                    return super.process((Object)ocMethodSymbol);
                }
            }
        }
        return true;
    }
}
