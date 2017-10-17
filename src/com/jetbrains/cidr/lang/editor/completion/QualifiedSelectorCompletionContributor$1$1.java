// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.util.containers.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class QualifiedSelectorCompletionContributor$1$1 extends CommonProcessors.CollectProcessor<OCSymbol> {
    private Set<String> names = new HashSet<String>();
    private Map<String, OCSymbol> members = new HashMap();
    final /* synthetic */ OCCompletionParameters val$parameters;
    
    public boolean process(final OCSymbol ocSymbol) {
        if (ocSymbol.getKind().isType() || ocSymbol.isUnnamed()) {
            return true;
        }
        PsiFile psiFile = this.val$parameters.getOriginalFile();
        if (psiFile instanceof OCCodeFragment) {
            final PsiElement context = psiFile.getContext();
            if (context != null) {
                psiFile = context.getContainingFile().getOriginalFile();
            }
        }
        if (!(psiFile instanceof OCFile) || !OCFileSymbols.isSymbolImported((OCFile)psiFile, ocSymbol)) {
            final OCFile containingOCFile = ocSymbol.getContainingOCFile();
            if (containingOCFile != null && !containingOCFile.isHeader()) {
                return true;
            }
        }
        if (ocSymbol.getKind().isConstructorOrDestructor()) {
            return true;
        }
        if (!(ocSymbol instanceof OCFunctionSymbol) && ocSymbol.getKind() != OCSymbolKind.STRUCT_FIELD) {
            return !this.names.add(ocSymbol.getSignature()) || super.process((Object)ocSymbol);
        }
        final String name = ocSymbol.getName();
        final OCSymbolWithQualifiedName parent = ((OCSymbolWithQualifiedName)ocSymbol).getParent();
        if (parent == null) {
            return super.process((Object)ocSymbol);
        }
        if (this.members.containsKey(name)) {
            return !this.members.get(name).equals(parent) || super.process((Object)ocSymbol);
        }
        this.members.put(name, parent);
        return super.process((Object)ocSymbol);
    }
}