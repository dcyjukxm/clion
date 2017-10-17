// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.patterns.ElementPattern;
import com.intellij.codeInsight.completion.CompletionType;
import java.util.Iterator;
import com.intellij.psi.PsiReference;
import com.intellij.codeInsight.lookup.LookupElement;
import com.jetbrains.cidr.lang.resolve.references.OCResourceReference;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.resolve.references.OCResourceCompletionProvider;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.resolve.references.OCResourceReferenceContributor;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class OCResourceCompletionContributor extends OCCompletionContributorBase
{
    public OCResourceCompletionContributor() {
        final OCCompletionProvider ocCompletionProvider = new OCCompletionProvider() {
            @Override
            protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
                try {
                    if (ocCompletionParameters == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCResourceCompletionContributor$1", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final PsiElement parent = ocCompletionParameters.getRealPosition().getParent();
                Label_0174: {
                    PsiElement parent2;
                    Object o;
                    if (parent instanceof OCReferenceElement) {
                        parent2 = parent.getParent();
                        o = null;
                    }
                    else {
                        Label_0168: {
                            try {
                                if (!(parent instanceof OCLiteralExpression) || !((OCLiteralExpression)parent).getTextWithMacros().startsWith("@\"")) {
                                    break Label_0168;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                            parent2 = parent;
                            String substring = null;
                            Label_0163: {
                                try {
                                    if (ocCompletionParameters.getOffset() > parent.getTextOffset()) {
                                        substring = ((OCLiteralExpression)parent).getTextWithMacros().substring(2, ocCompletionParameters.getOffset() - parent.getTextOffset());
                                        break Label_0163;
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                                substring = null;
                            }
                            o = substring;
                            break Label_0174;
                        }
                        parent2 = null;
                        o = null;
                    }
                    try {
                        if (parent2 != null) {
                            OCResourceReferenceContributor.processReferenceProviders((Processor<OCResourceCompletionProvider>)(ocResourceCompletionProvider -> {
                                for (final PsiReference psiReference : ocResourceCompletionProvider.getReferencesByElement(parent2, new ProcessingContext())) {
                                    OCResourceReference ocResourceReference = null;
                                    boolean b = false;
                                    Label_0054: {
                                        try {
                                            ocResourceReference = (OCResourceReference)psiReference;
                                            if (o == null) {
                                                b = true;
                                                break Label_0054;
                                            }
                                        }
                                        catch (IllegalArgumentException ex) {
                                            throw a(ex);
                                        }
                                        b = false;
                                    }
                                    for (final LookupElement lookupElement : ocResourceReference.getLookupElements(b)) {
                                        CompletionResultSet withPrefixMatcher = null;
                                        Label_0105: {
                                            try {
                                                if (o != null) {
                                                    withPrefixMatcher = set.withPrefixMatcher(o);
                                                    break Label_0105;
                                                }
                                            }
                                            catch (IllegalArgumentException ex2) {
                                                throw a(ex2);
                                            }
                                            withPrefixMatcher = set;
                                        }
                                        withPrefixMatcher.addElement(lookupElement);
                                    }
                                }
                                return true;
                            }));
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)OCCompletionPatterns.REFERENCE_ELEMENT, ocCompletionProvider);
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)OCCompletionPatterns.LITERAL_EXPRESSION, ocCompletionProvider);
        this.register(CompletionType.SMART, (ElementPattern<? extends PsiElement>)OCCompletionPatterns.REFERENCE_ELEMENT, ocCompletionProvider);
        this.register(CompletionType.SMART, (ElementPattern<? extends PsiElement>)OCCompletionPatterns.LITERAL_EXPRESSION, ocCompletionProvider);
    }
}
