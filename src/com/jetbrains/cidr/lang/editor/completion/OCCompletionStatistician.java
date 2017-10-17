// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.objc.OCLocalizedStringSymbol;
import com.intellij.psi.PsiCodeFragment;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.statistics.StatisticsInfo;
import com.intellij.codeInsight.completion.CompletionLocation;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.util.Key;
import com.intellij.codeInsight.completion.CompletionStatistician;

public class OCCompletionStatistician extends CompletionStatistician
{
    public static Key<String> STATISTICIAN_TYPENAME_CONTEXT;
    public static Key<String> STATISTICIAN_VALUE;
    
    public StatisticsInfo serialize(final LookupElement lookupElement, final CompletionLocation completionLocation) {
        final Object object = lookupElement.getObject();
        if (!(object instanceof OCSymbol)) {
            return null;
        }
        final OCSymbol ocSymbol = (OCSymbol)object;
        if (ocSymbol.getKind().isLocal()) {
            return StatisticsInfo.EMPTY;
        }
        if (completionLocation.getCompletionParameters().getOriginalFile() instanceof PsiCodeFragment) {
            return StatisticsInfo.EMPTY;
        }
        String lookupString = (String)lookupElement.getUserData((Key)OCCompletionStatistician.STATISTICIAN_VALUE);
        if (lookupString == null) {
            lookupString = lookupElement.getLookupString();
        }
        if (ocSymbol instanceof OCLocalizedStringSymbol) {
            return new StatisticsInfo("completion#OC_LOCALIZED_STRING", lookupString);
        }
        if (ocSymbol instanceof OCSymbolWithQualifiedName) {
            final OCQualifiedName qualifier = ((OCSymbolWithQualifiedName)ocSymbol).getQualifier();
            if (qualifier != null) {
                return new StatisticsInfo("completion#OC_CPP#" + qualifier.getNameWithParent(), lookupString);
            }
        }
        if (ocSymbol instanceof OCMemberSymbol) {
            String name = (String)lookupElement.getUserData((Key)OCCompletionStatistician.STATISTICIAN_TYPENAME_CONTEXT);
            if (name == null) {
                name = ((OCSymbolWithParent<T, OCClassSymbol>)ocSymbol).getParent().getName();
            }
            return new StatisticsInfo("completion#OC#" + name, lookupString);
        }
        return null;
    }
    
    static {
        OCCompletionStatistician.STATISTICIAN_TYPENAME_CONTEXT = (Key<String>)Key.create("STATISTICIAN_TYPENAME_CONTEXT");
        OCCompletionStatistician.STATISTICIAN_VALUE = (Key<String>)Key.create("STATISTICIAN_VALUE");
    }
}
