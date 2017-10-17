// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.util.HashSet;
import com.intellij.util.containers.hash.HashMap;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.Map;
import java.util.Set;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.AutoCompletionPolicy;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import java.util.ArrayList;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

public class OCSymbolsToLookupConverter implements Processor<OCSymbol>
{
    private final OCSymbolGroupContext myContext;
    private final ConverterState myState;
    private final CompletionResultSet myResult;
    private final boolean myAllowAutocomplete;
    private final PsiElement myContextExpression;
    private final Condition<OCSymbol> myCondition;
    private final ArrayList<OCStructSymbol> myAccumulatedStructSymbols;
    private final VirtualFile myContextFile;
    private final boolean myInCpp;
    private boolean myEmpty;
    
    public static OCSymbolsToLookupConverter createLookupConverter(final OCSymbolGroupContext ocSymbolGroupContext, final ConverterState converterState, final CompletionResultSet set, final boolean b, final PsiElement psiElement, final Condition<OCSymbol> condition) {
        return new OCSymbolsToLookupConverter(ocSymbolGroupContext, converterState, set, b, psiElement, condition);
    }
    
    public OCSymbolsToLookupConverter(final OCSymbolGroupContext myContext, final ConverterState myState, final CompletionResultSet myResult, final boolean myAllowAutocomplete, final PsiElement myContextExpression, final Condition<OCSymbol> myCondition) {
        this.myEmpty = true;
        this.myContext = myContext;
        this.myState = myState;
        this.myResult = myResult;
        this.myAllowAutocomplete = myAllowAutocomplete;
        this.myContextExpression = myContextExpression;
        this.myCondition = myCondition;
        this.myAccumulatedStructSymbols = new ArrayList<OCStructSymbol>();
        this.myInCpp = !OCCodeInsightUtil.isInPlainOldC(myContextExpression);
        if (this.myContextExpression instanceof OCElement) {
            final OCFile containingOCFile = ((OCElement)this.myContextExpression).getContainingOCFile();
            this.myContextFile = ((containingOCFile != null) ? containingOCFile.getOriginalFile().getVirtualFile() : null);
        }
        else {
            this.myContextFile = null;
        }
    }
    
    public boolean process(final OCSymbol ocSymbol) {
        if (ocSymbol instanceof OCStructSymbol) {
            this.myAccumulatedStructSymbols.add((OCStructSymbol)ocSymbol);
            return true;
        }
        return this.doProcess(ocSymbol);
    }
    
    public boolean finish() {
        final Iterator<OCStructSymbol> iterator = this.myAccumulatedStructSymbols.iterator();
        while (iterator.hasNext()) {
            if (!this.doProcess(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    public boolean doProcess(final OCSymbol ocSymbol) {
        if (ocSymbol instanceof OCMethodSymbol) {
            return true;
        }
        if (ocSymbol instanceof OCClassSymbol && ((OCClassSymbol)ocSymbol).getCategoryName() != null) {
            return true;
        }
        if (ocSymbol instanceof OCUsingSymbol) {
            return true;
        }
        if (!OCSymbol.NON_FANTOM_SYMBOL_CONDITION.value((Object)ocSymbol)) {
            return true;
        }
        String s = ocSymbol.getName();
        final int lastIndex = s.lastIndexOf(32);
        if (lastIndex >= 0) {
            s = s.substring(lastIndex + 1);
        }
        if (!this.myResult.getPrefixMatcher().prefixMatches(s)) {
            return true;
        }
        if (this.myContext != null && !this.myContext.isSuitableSymbol(ocSymbol)) {
            if (!this.myInCpp || (!this.myContext.isSuitableSymbolKind(OCSymbolKind.GLOBAL_VARIABLE) && !this.myContext.isSuitableSymbolKind(OCSymbolKind.FUNCTION_DECLARATION))) {
                return true;
            }
            if (!(ocSymbol instanceof OCStructSymbol) && ocSymbol.getKind() != OCSymbolKind.TYPEDEF) {
                if (ocSymbol.getKind() != OCSymbolKind.TEMPLATE_TYPE_PARAMETER) {
                    return true;
                }
            }
        }
        if (this.myCondition != null && !this.myCondition.value((Object)ocSymbol)) {
            return true;
        }
        if (ocSymbol.isUnavailable() || ocSymbol.isForbiddenByARC(this.myContextExpression)) {
            return true;
        }
        if (Comparing.equal((Object)ocSymbol.getContainingFile(), (Object)this.myContextFile) && this.myContextExpression.getParent().getTextOffset() == ocSymbol.getOffset()) {
            return true;
        }
        if (this.myState.registerSymbol(ocSymbol)) {
            Object o;
            if (this.myAllowAutocomplete) {
                o = this.createLookup(ocSymbol);
            }
            else {
                o = this.createLookup(ocSymbol).withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE);
            }
            this.myResult.addElement((LookupElement)o);
            this.myEmpty = false;
        }
        return true;
    }
    
    public boolean isEmpty() {
        return this.myEmpty;
    }
    
    public LookupElementBuilder createLookup(final OCSymbol ocSymbol) {
        return SymbolLookupBuilderUtil.lookup(ocSymbol, this.myContextExpression, this.myContext);
    }
    
    public static class ConverterState
    {
        private final Set<String> myNames;
        private final Map<String, OCFunctionSymbol> myFunctionNamesToSymbol;
        private final MultiMap<String, String> myFunctionNamesToType;
        
        public ConverterState() {
            this.myFunctionNamesToType = (MultiMap<String, String>)MultiMap.createSet();
            this.myFunctionNamesToSymbol = (Map<String, OCFunctionSymbol>)new HashMap();
            this.myNames = new HashSet<String>();
        }
        
        boolean registerSymbol(final OCSymbol ocSymbol) {
            final String name = ocSymbol.getName();
            if (!(ocSymbol instanceof OCFunctionSymbol)) {
                return this.myNames.add(ocSymbol.getSignature());
            }
            if (this.myFunctionNamesToSymbol.containsKey(name)) {
                if (!this.myFunctionNamesToType.containsKey((Object)name)) {
                    this.myFunctionNamesToType.putValue((Object)name, (Object)a(this.myFunctionNamesToSymbol.get(name)));
                }
                return this.myFunctionNamesToType.get((Object)name).add(a(ocSymbol));
            }
            this.myFunctionNamesToSymbol.put(name, (OCFunctionSymbol)ocSymbol);
            return true;
        }
        
        private static String a(final OCSymbol ocSymbol) {
            return ocSymbol.getResolvedType().getCanonicalName();
        }
    }
}
