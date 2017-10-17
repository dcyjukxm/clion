// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.editor.completion.OCCodeContextType;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.Macro;

public class CPPObjectWithBeginEndMacro extends Macro
{
    public static final String MACRO_NAME = "variableForRangeBasedIteration";
    
    public String getName() {
        return "variableForRangeBasedIteration";
    }
    
    public String getPresentableName() {
        return "variableForRangeBasedIteration()";
    }
    
    @Nullable
    public Result calculateResult(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/CPPObjectWithBeginEndMacro", "calculateResult"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<OCDeclaratorSymbol> variablesWithBeginEnd = OCTemplatesUtil.getVariablesWithBeginEnd(expressionContext);
        try {
            if (variablesWithBeginEnd.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Iterator<OCDeclaratorSymbol> iterator = variablesWithBeginEnd.iterator();
        while (iterator.hasNext()) {
            final OCElement ocElement = ((OCSymbolBase<OCElement>)iterator.next()).locateDefinition();
            try {
                if (ocElement != null) {
                    return (Result)new OCElementResult(ocElement);
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @Nullable
    public LookupElement[] calculateLookupItems(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/CPPObjectWithBeginEndMacro", "calculateLookupItems"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<OCDeclaratorSymbol> variablesWithBeginEnd = OCTemplatesUtil.getVariablesWithBeginEnd(expressionContext);
        try {
            if (variablesWithBeginEnd.size() < 2) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final LookupElement[] array2 = new LookupElement[variablesWithBeginEnd.size()];
        int n = 0;
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : variablesWithBeginEnd) {
            array2[n++] = (LookupElement)LookupElementBuilder.create(ocDeclaratorSymbol.getName()).withIcon(ocDeclaratorSymbol.getIcon());
        }
        return array2;
    }
    
    public boolean isAcceptableInContext(final TemplateContextType templateContextType) {
        return templateContextType instanceof OCCodeContextType;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
