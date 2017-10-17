// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.util.Processor;

class OCSymbolReferenceResolver$2 implements Processor<OCSymbol> {
    private OCQualifiedName myResolvedNameWithArguments;
    private OCQualifiedName myResolvedNameWithoutArguments;
    final /* synthetic */ Class val$myClass;
    final /* synthetic */ OCFile val$file;
    final /* synthetic */ OCSymbolWithQualifiedName val$symbol;
    final /* synthetic */ CommonProcessors.FindFirstProcessor val$processor;
    
    public boolean process(final OCSymbol ocSymbol) {
        if (ocSymbol.isPredeclaration()) {
            return true;
        }
        if (!((OCSymbolWithQualifiedName)ocSymbol).getClass().equals(this.val$myClass)) {
            return true;
        }
        if (!(ocSymbol instanceof OCSymbolWithQualifiedName)) {
            return true;
        }
        final OCNamespaceLikeSymbol membersContainer = this.val$file.getMembersContainer(OCSymbolReferenceResolver.access$000(OCSymbolReferenceResolver.this));
        boolean b = false;
        for (OCSymbolWithQualifiedName parent = (OCSymbolWithQualifiedName)ocSymbol; parent != null; parent = parent.getParent()) {
            if (!membersContainer.processMembers(parent.getName(), (com.intellij.util.Processor<OCSymbol>)(ocSymbol2 -> !ocSymbol2.equals(parent)))) {
                b = true;
                break;
            }
        }
        if (!b) {
            return true;
        }
        if (this.myResolvedNameWithoutArguments == null) {
            this.myResolvedNameWithoutArguments = OCSymbolReferenceResolver.this.getResolvedQualifiedName(this.val$symbol, false);
            if (this.myResolvedNameWithoutArguments == null) {
                return false;
            }
        }
        if (!this.myResolvedNameWithoutArguments.equals(OCSymbolReferenceResolver.this.getResolvedQualifiedName((OCSymbolWithQualifiedName)ocSymbol, false))) {
            return true;
        }
        if (this.myResolvedNameWithArguments == null) {
            this.myResolvedNameWithArguments = OCSymbolReferenceResolver.this.getResolvedQualifiedName(this.val$symbol, true);
            if (this.myResolvedNameWithArguments == null) {
                return false;
            }
        }
        return !this.myResolvedNameWithArguments.equals(OCSymbolReferenceResolver.this.getResolvedQualifiedName((OCSymbolWithQualifiedName)ocSymbol, true)) || this.val$processor.process((Object)ocSymbol);
    }
}