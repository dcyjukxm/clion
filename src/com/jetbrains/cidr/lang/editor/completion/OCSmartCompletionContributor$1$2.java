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
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.Processor;

class OCSmartCompletionContributor$1$2 implements Processor<OCMethodSymbol> {
    private final Set<String> processed = new HashSet<String>();
    final /* synthetic */ OCType val$expectedType;
    final /* synthetic */ OCObjectType val$obj;
    final /* synthetic */ OCFile val$file;
    final /* synthetic */ boolean val$onlySendMessage;
    final /* synthetic */ String val$className;
    final /* synthetic */ OCExpression val$contextExpression;
    final /* synthetic */ CompletionResultSet val$result;
    
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        if (ocMethodSymbol.isConstructorMethod() && this.val$expectedType.isCompatible(ocMethodSymbol.getReturnType(this.val$obj).resolve((PsiFile)this.val$file), (PsiElement)this.val$file) && this.processed.add(ocMethodSymbol.getName())) {
            if ("init".equals(ocMethodSymbol.getName()) && this.processed.add("new")) {
                final OCType terminalType = this.val$expectedType.getTerminalType();
                if (terminalType instanceof OCObjectType) {
                    final OCMethodSymbol ocMethodSymbol2 = ((OCObjectType)terminalType).findMember("new", OCMethodSymbol.class);
                    if (ocMethodSymbol2 != null) {
                        final LookupElementBuilder lookup = SymbolLookupBuilderUtil.lookup(ocMethodSymbol2, this.val$onlySendMessage, "[" + this.val$className, this.val$obj, (PsiElement)this.val$contextExpression, null);
                        lookup.putUserData((Key)OCCompletionStatistician.STATISTICIAN_TYPENAME_CONTEXT, (Object)this.val$className);
                        this.val$result.addElement((LookupElement)lookup);
                    }
                }
            }
            final LookupElementBuilder lookup2 = SymbolLookupBuilderUtil.lookup(ocMethodSymbol, this.val$onlySendMessage, "[[" + this.val$className + " alloc]", this.val$obj, (PsiElement)this.val$contextExpression, null);
            lookup2.putUserData((Key)OCCompletionStatistician.STATISTICIAN_TYPENAME_CONTEXT, (Object)this.val$className);
            this.val$result.addElement((LookupElement)lookup2);
        }
        return true;
    }
}