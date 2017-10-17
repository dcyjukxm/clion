// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertionContext;

private static class CppLookupElementInsertHandler extends LookupElementInsertHandler
{
    @Override
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        if (lookupElement.getObject() instanceof OCSymbolWithQualifiedName) {
            final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)lookupElement.getObject();
            final OCQualifiedName resolvedQualifiedName = ocSymbolWithQualifiedName.getResolvedQualifiedName(new OCResolveContext((PsiElement)ocSymbolWithQualifiedName.getContainingOCFile()));
            if (resolvedQualifiedName != null) {
                String s = resolvedQualifiedName.getCanonicalName(true);
                if (ocSymbolWithQualifiedName instanceof OCFunctionSymbol) {
                    s = s + "(" + StringUtil.join((Collection)ContainerUtil.map((Collection)((OCFunctionSymbol)ocSymbolWithQualifiedName).getParameterSymbols(), ocDeclaratorSymbol -> StringUtil.trimStart(ocDeclaratorSymbol.getResolvedType().getCanonicalName(), "::").replace(" *", "*")), ", ") + ")";
                    if (ocSymbolWithQualifiedName.isConst()) {
                        s += " const";
                    }
                }
                insertionContext.getDocument().replaceString(0, insertionContext.getTailOffset(), (CharSequence)s.substring(2));
            }
            if (!(lookupElement.getObject() instanceof OCFunctionSymbol)) {
                LookupElementInsertHandler.addCompletionString(insertionContext, insertionContext.getDocument().getTextLength(), "::");
                LookupElementInsertHandler.reInvokeCompletion(insertionContext);
            }
        }
    }
}
