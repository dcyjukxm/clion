// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import java.util.HashSet;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.OCObjectType;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.Processor;

class OCSmartCompletionContributor$1$3 implements Processor<OCMethodSymbol> {
    private final Set<String> processed = new HashSet<String>();
    final /* synthetic */ OCObjectType val$obj;
    final /* synthetic */ OCFile val$file;
    final /* synthetic */ OCType val$expectedType;
    final /* synthetic */ boolean val$onlySendMessage;
    final /* synthetic */ String val$className;
    final /* synthetic */ OCExpression val$contextExpression;
    final /* synthetic */ CompletionResultSet val$result;
    
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        if (ocMethodSymbol.isFactoryMethod()) {
            this.a(ocMethodSymbol, false);
        }
        else if (ocMethodSymbol.isStatic()) {
            this.a(ocMethodSymbol, true);
        }
        return true;
    }
    
    private void a(final OCMethodSymbol ocMethodSymbol, final boolean b) {
        final OCType resolve = ocMethodSymbol.getReturnType(this.val$obj).resolve((PsiFile)this.val$file);
        if (b && resolve.isPointerToID()) {
            return;
        }
        if (this.val$expectedType.isCompatible(resolve, (PsiElement)this.val$file) && this.processed.add(ocMethodSymbol.getName())) {
            final LookupElementBuilder lookup = SymbolLookupBuilderUtil.lookup(ocMethodSymbol, this.val$onlySendMessage, "[" + this.val$className, this.val$obj, (PsiElement)this.val$contextExpression, null);
            lookup.putUserData((Key)OCCompletionStatistician.STATISTICIAN_TYPENAME_CONTEXT, (Object)this.val$className);
            this.val$result.addElement((LookupElement)lookup);
        }
    }
}