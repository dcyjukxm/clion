// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCThisSelfSuperSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.codeInsight.completion.PrioritizedLookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;

public enum OCCompletionPriority
{
    HIGHEST_PRIORITY(1000.0f), 
    HIGH_KEYWORDS_PRIORITY(150.0f), 
    HIGH_PRIORITY4(140.0f), 
    HIGH_PRIORITY3(130.0f), 
    HIGH_PRIORITY2(120.0f), 
    HIGH_PRIORITY1(110.0f), 
    HIGH_SMART_COMPLETION_PRIORITY(107.0f), 
    SMART_COMPLETION_PRIORITY(105.0f), 
    HIGH_PRIORITY(100.0f), 
    NORMAL_PRIORITY(50.0f), 
    LOW_PRIORITY2(20.0f), 
    LOW_PRIORITY1(10.0f), 
    LOW_PRIORITY0(5.0f), 
    NORMAL_KEYWORDS_PRIORITY(-10.0f), 
    LOW_KEYWORDS_PRIORITY(-15.0f);
    
    private final float myValue;
    
    private OCCompletionPriority(final float myValue) {
        this.myValue = myValue;
    }
    
    public static LookupElement elementWithPriority(final LookupElement lookupElement, final OCCompletionPriority ocCompletionPriority) {
        return a(lookupElement, ocCompletionPriority, false);
    }
    
    private static LookupElement a(LookupElement bold, final OCCompletionPriority ocCompletionPriority, final boolean b) {
        if (b && bold instanceof LookupElementBuilder) {
            bold = (LookupElement)((LookupElementBuilder)bold).bold();
        }
        return PrioritizedLookupElement.withPriority(bold, (double)ocCompletionPriority.myValue);
    }
    
    public static LookupElement keywordWithPriority(final String s, final OCCompletionPriority ocCompletionPriority) {
        return a((LookupElement)LookupElementBuilder.create(s), ocCompletionPriority, true);
    }
    
    public static LookupElement keywordWithPriority(final LookupElement lookupElement, final OCCompletionPriority ocCompletionPriority) {
        return a(lookupElement, ocCompletionPriority, true);
    }
    
    public float getValue() {
        return this.myValue;
    }
    
    public static OCCompletionPriority getPriority(final OCSymbol ocSymbol) {
        final OCSymbolKind kind = ocSymbol.getKind();
        if (kind.isLocal() || kind == OCSymbolKind.MACRO_PARAMETER) {
            return (ocSymbol instanceof OCThisSelfSuperSymbol) ? OCCompletionPriority.NORMAL_PRIORITY : OCCompletionPriority.HIGH_PRIORITY4;
        }
        if (kind == OCSymbolKind.MACRO) {
            final String name = ocSymbol.getName();
            if ("nil".equals(name) || "null".equals(name) || "YES".equals(name) || "NO".equals(name)) {
                return OCCompletionPriority.HIGH_PRIORITY3;
            }
        }
        if (kind.isTemplateParameter()) {
            return OCCompletionPriority.HIGH_PRIORITY3;
        }
        if (kind == OCSymbolKind.INSTANCE_VARIABLE || kind == OCSymbolKind.PROPERTY || kind == OCSymbolKind.STRUCT_FIELD) {
            return OCCompletionPriority.HIGH_PRIORITY1;
        }
        if (kind.isConstructorOrDestructor()) {
            return OCCompletionPriority.LOW_PRIORITY1;
        }
        if (ocSymbol instanceof OCSymbolWithQualifiedName && ((OCSymbolWithQualifiedName)ocSymbol).getResolvedOwner() instanceof OCStructSymbol) {
            return OCCompletionPriority.HIGH_PRIORITY1;
        }
        if (kind.isStructLike() && ocSymbol.isPredeclaration()) {
            return OCCompletionPriority.LOW_PRIORITY0;
        }
        return OCCompletionPriority.NORMAL_PRIORITY;
    }
}
