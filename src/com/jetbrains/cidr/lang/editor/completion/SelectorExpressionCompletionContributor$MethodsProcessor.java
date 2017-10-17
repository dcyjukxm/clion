// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.HashSet;
import com.intellij.codeInsight.completion.CompletionResultSet;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

private static class MethodsProcessor implements Processor<OCSymbol>
{
    private final Set<String> myNames;
    private boolean myCheckInterfaces;
    private CompletionResultSet myResult;
    
    private MethodsProcessor(final CompletionResultSet myResult) {
        this.myNames = new HashSet<String>();
        this.myResult = myResult;
    }
    
    public boolean process(final OCSymbol ocSymbol) {
        if (!(ocSymbol instanceof OCMethodSymbol) || this.myCheckInterfaces != ((OCMethodSymbol)ocSymbol).getParent() instanceof OCInterfaceSymbol) {
            return true;
        }
        if (!this.myNames.contains(ocSymbol.getName())) {
            this.myResult.addElement((LookupElement)SymbolLookupBuilderUtil.lookup(ocSymbol.getName(), ocSymbol, false, null, null, null, null, ocSymbol.getBaseIcon()).withInsertHandler((InsertHandler)null));
            this.myNames.add(ocSymbol.getName());
        }
        return true;
    }
}
