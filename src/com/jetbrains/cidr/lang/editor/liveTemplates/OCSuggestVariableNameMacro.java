// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.jetbrains.cidr.lang.editor.completion.OCCodeContextType;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import java.util.LinkedHashSet;
import java.util.Collections;
import java.util.Iterator;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
import java.util.Collection;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Expression;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.Macro;

public class OCSuggestVariableNameMacro extends Macro
{
    public String getName() {
        return "suggestVariableName";
    }
    
    public String getPresentableName() {
        return "suggestVariableName()";
    }
    
    @NotNull
    public String getDefaultValue() {
        String s;
        try {
            s = "a";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/OCSuggestVariableNameMacro", "getDefaultValue"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public Result calculateResult(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/OCSuggestVariableNameMacro", "calculateResult"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final Collection<String> a = a(expressionContext);
        try {
            if (a.isEmpty()) {
                final TextResult textResult = null;
                return (Result)textResult;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final TextResult textResult = new TextResult((String)a.iterator().next());
        return (Result)textResult;
    }
    
    public LookupElement[] calculateLookupItems(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/OCSuggestVariableNameMacro", "calculateLookupItems"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final Collection<String> a = a(expressionContext);
        try {
            if (a.size() < 2) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final LookupElement[] array2 = new LookupElement[a.size()];
        int n = 0;
        for (final String s : a) {
            array2[n++] = (LookupElement)LookupElementBuilder.create((Object)s, s);
        }
        return array2;
    }
    
    private static Collection<String> a(final ExpressionContext expressionContext) {
        final Collection<String> names = OCTemplatesUtil.getNames(expressionContext);
        Label_0025: {
            try {
                if (names == null) {
                    break Label_0025;
                }
                final Collection<String> collection = names;
                final boolean b = collection.isEmpty();
                if (b) {
                    break Label_0025;
                }
                break Label_0025;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final Collection<String> collection = names;
                final boolean b = collection.isEmpty();
                if (b) {
                    return (Collection<String>)Collections.emptyList();
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final LinkedHashSet set = new LinkedHashSet<Object>(names);
        final PsiElement insertionPlace = OCTemplatesUtil.getInsertionPlace(expressionContext);
        final String text = insertionPlace.getText();
        final Iterator<String> iterator = set.iterator();
        String s = null;
        while (iterator.hasNext()) {
            final String s2 = iterator.next();
            Label_0119: {
                try {
                    if (s2.equals(text)) {
                        continue;
                    }
                    final OCSymbolKind ocSymbolKind = null;
                    final String s3 = s2;
                    final PsiElement psiElement = insertionPlace;
                    final ExpressionContext expressionContext2 = expressionContext;
                    final Project project = expressionContext2.getProject();
                    final boolean b2 = OCCodeInsightUtil.isUniqueInScope(ocSymbolKind, s3, psiElement, project);
                    if (!b2) {
                        break Label_0119;
                    }
                    continue;
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCSymbolKind ocSymbolKind = null;
                    final String s3 = s2;
                    final PsiElement psiElement = insertionPlace;
                    final ExpressionContext expressionContext2 = expressionContext;
                    final Project project = expressionContext2.getProject();
                    final boolean b2 = OCCodeInsightUtil.isUniqueInScope(ocSymbolKind, s3, psiElement, project);
                    if (b2) {
                        continue;
                    }
                    iterator.remove();
                    if (s != null) {
                        continue;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            s = s2;
        }
        try {
            if (set.isEmpty()) {
                return Collections.singleton(OCNameSuggester.suggestUniqueName(null, s, insertionPlace));
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        return (Collection<String>)set;
    }
    
    public boolean isAcceptableInContext(final TemplateContextType templateContextType) {
        return templateContextType instanceof OCCodeContextType;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
