// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.util.UserDataHolder;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import java.util.HashSet;
import javax.swing.Icon;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.Processor;

static final class MethodSelectorCompletionContributor$4 implements Processor<OCMethodSymbol> {
    private Set<String> names = new HashSet<String>();
    final /* synthetic */ OCObjectTypeContext val$receiverContext;
    final /* synthetic */ OCCompletionParameters val$parameters;
    final /* synthetic */ boolean val$completeFullSelector;
    final /* synthetic */ Context val$context;
    final /* synthetic */ CompletionResultSet val$result;
    final /* synthetic */ int[] val$addedCnt;
    final /* synthetic */ String val$exactSelectorPrefix;
    final /* synthetic */ Icon val$icon;
    
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        final String name = ocMethodSymbol.getName();
        if (!this.val$receiverContext.fitsStaticness(ocMethodSymbol)) {
            return true;
        }
        if (ocMethodSymbol.isUnavailable() || ocMethodSymbol.isForbiddenByARC((PsiElement)this.val$parameters.getOriginalFile())) {
            return true;
        }
        final PsiFile originalFile = this.val$parameters.getOriginalFile();
        if (!(originalFile instanceof OCFile) || !OCFileSymbols.isSymbolImported((OCFile)originalFile, ocMethodSymbol)) {
            final OCFile containingOCFile = ocMethodSymbol.getContainingOCFile();
            if (containingOCFile != null && !containingOCFile.isHeader()) {
                return true;
            }
        }
        if (!this.names.add(name)) {
            return true;
        }
        if (this.val$completeFullSelector) {
            LookupElement lookupElement2;
            final LookupElement lookupElement = lookupElement2 = SymbolLookupBuilderUtil.lookup(ocMethodSymbol, null, this.val$receiverContext.getType(), this.val$parameters.getPosition(), Comparing.equal(((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent().getName(), this.val$receiverContext.getType().getClassName()), null);
            MethodSelectorCompletionContributor.CONTEXT.set((UserDataHolder)lookupElement2, (Object)this.val$context);
            while (lookupElement2 instanceof LookupElementDecorator) {
                lookupElement2 = ((LookupElementDecorator)lookupElement2).getDelegate();
                MethodSelectorCompletionContributor.CONTEXT.set((UserDataHolder)lookupElement2, (Object)this.val$context);
            }
            if (this.val$result.getPrefixMatcher().prefixMatches(lookupElement)) {
                this.val$result.addElement(lookupElement);
                final int[] val$addedCnt = this.val$addedCnt;
                final int n = 0;
                ++val$addedCnt[n];
            }
        }
        else if (name.startsWith(this.val$exactSelectorPrefix)) {
            final String substring = name.substring(this.val$exactSelectorPrefix.length());
            if (!substring.isEmpty()) {
                final int index = substring.indexOf(58);
                this.val$result.addElement((LookupElement)LookupElementBuilder.create((index >= 0) ? substring.substring(0, index + 1) : substring).withIcon(this.val$icon).withInsertHandler(MethodSelectorCompletionContributor.access$300()));
                final int[] val$addedCnt2 = this.val$addedCnt;
                final int n2 = 0;
                ++val$addedCnt2[n2];
            }
        }
        return true;
    }
}