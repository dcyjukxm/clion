// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCTollFreeBridges;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

private class UnboxingProcessor extends CommonProcessors.FindFirstProcessor<OCMethodSymbol>
{
    private final String myMethodSuffix;
    
    public UnboxingProcessor(final String myMethodSuffix) {
        this.myMethodSuffix = myMethodSuffix;
    }
    
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        return ((!ocMethodSymbol.getName().equals("pointerValue") || !(OCConvertTypeIntentionAction.access$000(OCConvertTypeIntentionAction.this) instanceof OCPointerType) || OCConvertTypeIntentionAction.access$000(OCConvertTypeIntentionAction.this).isPointerToObject() || OCTollFreeBridges.getNSCounterpart(OCConvertTypeIntentionAction.access$000(OCConvertTypeIntentionAction.this).getName()) != null) && (!ocMethodSymbol.getName().equals("getValue:") || !(OCConvertTypeIntentionAction.access$000(OCConvertTypeIntentionAction.this) instanceof OCStructType) || OCTollFreeBridges.hasCFCounterpart(OCConvertTypeIntentionAction.access$000(OCConvertTypeIntentionAction.this).getName())) && (!ocMethodSymbol.getName().endsWith(this.myMethodSuffix) || !ocMethodSymbol.getReturnType().equalsWithAliasName(OCConvertTypeIntentionAction.access$000(OCConvertTypeIntentionAction.this), (PsiElement)OCConvertTypeIntentionAction.this.myElementPtr.getContainingFile()) || ocMethodSymbol.isStatic())) || super.process((Object)ocMethodSymbol);
    }
}
