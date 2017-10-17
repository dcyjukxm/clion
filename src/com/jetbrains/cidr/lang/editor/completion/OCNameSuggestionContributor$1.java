// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import java.util.Collection;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class OCNameSuggestionContributor$1 extends OCCompletionProvider {
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCNameSuggestionContributor$1", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCDeclarator ocDeclarator = (OCDeclarator)ocCompletionParameters.getPosition().getParent();
        OCElement typeElement = null;
        Label_0118: {
            if (ocDeclarator.getParent() instanceof OCDeclaration) {
                typeElement = ((OCDeclaration)ocDeclarator.getParent()).getTypeElement();
                try {
                    if (typeElement == null) {
                        return;
                    }
                    final OCElement ocElement = typeElement;
                    final boolean b = ocElement.isEmpty();
                    if (b) {
                        return;
                    }
                    break Label_0118;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCElement ocElement = typeElement;
                    final boolean b = ocElement.isEmpty();
                    if (b) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        final OCType resolvedType = ocDeclarator.getResolvedType();
        Label_0302: {
            Label_0234: {
                if (resolvedType instanceof OCReferenceType) {
                    final List<OCSymbol> resolveToSymbols = ((OCReferenceType)resolvedType).getReference(ocDeclarator.getContainingFile()).resolveToSymbols(ocCompletionParameters.getOriginalFile());
                    final String name = resolvedType.getName();
                    Label_0193: {
                        try {
                            if (!resolveToSymbols.isEmpty()) {
                                break Label_0193;
                            }
                            final String s2 = "self";
                            final String s3 = name;
                            final boolean b2 = s2.equals(s3);
                            if (!b2) {
                                break Label_0193;
                            }
                            break Label_0193;
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                        try {
                            final String s2 = "self";
                            final String s3 = name;
                            final boolean b2 = s2.equals(s3);
                            if (!b2) {
                                if (!"super".equals(name)) {
                                    break Label_0234;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    MethodSelectorCompletionContributor.addCompletionsForReceiver(OCElementFactory.expressionFromText(name, (PsiElement)ocDeclarator, false), "", ocCompletionParameters, set, (Condition<OCMethodSymbol>)Conditions.alwaysTrue());
                    return;
                }
                try {
                    if (!(resolvedType instanceof OCObjectType)) {
                        break Label_0302;
                    }
                    if (typeElement == null) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            MethodSelectorCompletionContributor.addCompletionForReceiverContext(ocCompletionParameters, set, new MethodSelectorCompletionContributor.Context("", (PsiElement)typeElement, new OCObjectTypeContext(OCObjectTypeContext.StaticMode.STATIC, (OCObjectType)resolvedType, resolvedType), (Condition<OCMethodSymbol>)Conditions.alwaysTrue()));
            return;
        }
        final String name2 = ocDeclarator.getName();
        String s4 = null;
        Label_0327: {
            try {
                if (name2 == null) {
                    s4 = "";
                    break Label_0327;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            s4 = name2;
        }
        String substring = s4;
        final int lastIndex = substring.lastIndexOf("CIDR_RULE_ZZZ");
        if (lastIndex >= 0) {
            substring = substring.substring(0, lastIndex);
        }
        Object o = null;
        Label_0376: {
            try {
                if (substring.isEmpty()) {
                    o = Collections.emptyList();
                    break Label_0376;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            o = Collections.singletonList(substring);
        }
        final Iterator<String> iterator = OCNameSuggester.suggestForDeclaration(ocDeclarator, true, (Collection<String>)o).iterator();
        while (iterator.hasNext()) {
            set.addElement(OCCompletionPriority.elementWithPriority((LookupElement)LookupElementBuilder.create((String)iterator.next()), OCNameSuggestionContributor.PRIORITY));
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}