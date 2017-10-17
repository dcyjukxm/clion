// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

class OCSymbolWithQualifiedName$1 implements Processor<OCSymbol> {
    private OCQualifiedName myResolvedName;
    private OCType myResolvedType;
    final /* synthetic */ Class val$myClass;
    final /* synthetic */ Processor val$processor;
    
    public boolean process(final OCSymbol ocSymbol) {
        if (ocSymbol.equals(OCSymbolWithQualifiedName.this)) {
            return true;
        }
        if (!((OCSymbolWithQualifiedName)ocSymbol).getClass().equals(this.val$myClass)) {
            return true;
        }
        if (!(ocSymbol instanceof OCSymbolWithQualifiedName)) {
            return true;
        }
        if (this.myResolvedName == null) {
            this.myResolvedName = OCSymbolWithQualifiedName.this.getResolvedQualifiedName(false);
            if (this.myResolvedName == null) {
                return false;
            }
        }
        if (this.myResolvedType == null) {
            this.myResolvedType = OCSymbolWithQualifiedName.this.getType().resolve((PsiFile)OCSymbolWithQualifiedName.this.getContainingOCFile());
        }
        return !OCSymbolWithQualifiedName.access$000(OCSymbolWithQualifiedName.this, (OCSymbolWithQualifiedName)ocSymbol, this.myResolvedName, this.myResolvedType, new OCResolveContext((PsiElement)OCSymbolWithQualifiedName.this.getContainingOCFile())) || this.val$processor.process((Object)ocSymbol);
    }
}