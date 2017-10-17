// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.editor.completion.OCCodeContextType;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.psi.PsiElement;
import java.util.Arrays;
import com.intellij.util.containers.HashMap;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.Expression;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.List;
import java.util.Map;
import com.intellij.openapi.util.Key;
import com.intellij.codeInsight.template.Macro;

public class ObjectWithFunctionsMacro extends Macro
{
    public static final String MACRO_NAME = "objectWithFunctions";
    private static final Key<Map<String, List<OCDeclaratorSymbol>>> MACRO_LAST_RESULT_KEY;
    
    public String getName() {
        return "objectWithFunctions";
    }
    
    public String getPresentableName() {
        return "objectWithFunctions(...)";
    }
    
    @Nullable
    public Result calculateResult(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/ObjectWithFunctionsMacro", "calculateResult"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<OCDeclaratorSymbol> a = a(expressionContext, OCTemplatesUtil.getStringsList(array, expressionContext));
        try {
            if (a.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Iterator<OCDeclaratorSymbol> iterator = a.iterator();
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/ObjectWithFunctionsMacro", "calculateLookupItems"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<OCDeclaratorSymbol> a = a(expressionContext, OCTemplatesUtil.getStringsList(array, expressionContext));
        try {
            if (a.size() < 2) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final LookupElement[] array2 = new LookupElement[a.size()];
        int n = 0;
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : a) {
            array2[n++] = (LookupElement)LookupElementBuilder.create(ocDeclaratorSymbol.getName()).withIcon(ocDeclaratorSymbol.getIcon());
        }
        return array2;
    }
    
    @NotNull
    private static List<OCDeclaratorSymbol> a(@NotNull final ExpressionContext expressionContext, final List<String> list) {
        try {
            if (expressionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/liveTemplates/ObjectWithFunctionsMacro", "getVariablesWithMethods"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement insertionPlace = OCTemplatesUtil.getInsertionPlace(expressionContext);
        Object o = ObjectWithFunctionsMacro.MACRO_LAST_RESULT_KEY.get((UserDataHolder)insertionPlace);
        if (o == null) {
            o = new HashMap();
            ObjectWithFunctionsMacro.MACRO_LAST_RESULT_KEY.set((UserDataHolder)insertionPlace, o);
        }
        final String string = Arrays.toString(list.toArray());
        final List<OCDeclaratorSymbol> list2 = ((Map<String, List<OCDeclaratorSymbol>>)o).get(string);
        Label_0157: {
            List<OCDeclaratorSymbol> list3 = null;
            Label_0122: {
                try {
                    if (list2 == null) {
                        break Label_0157;
                    }
                    list3 = list2;
                    if (list3 == null) {
                        break Label_0122;
                    }
                    return list3;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    list3 = list2;
                    if (list3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/ObjectWithFunctionsMacro", "getVariablesWithMethods"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return list3;
        }
        final List<OCDeclaratorSymbol> variablesWithMethods = OCTemplatesUtil.getVariablesWithMethods(expressionContext, list);
        List<OCDeclaratorSymbol> list4;
        try {
            ((Map<String, List<OCDeclaratorSymbol>>)o).put(string, variablesWithMethods);
            list4 = variablesWithMethods;
            if (list4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/ObjectWithFunctionsMacro", "getVariablesWithMethods"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return list4;
    }
    
    public boolean isAcceptableInContext(final TemplateContextType templateContextType) {
        return templateContextType instanceof OCCodeContextType;
    }
    
    static {
        MACRO_LAST_RESULT_KEY = Key.create("objectWithFunctions");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
