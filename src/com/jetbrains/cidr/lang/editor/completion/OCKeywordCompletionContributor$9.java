// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiComment;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import java.util.List;

static final class OCKeywordCompletionContributor$9 extends OCCompletionProvider {
    final /* synthetic */ List val$keywords;
    final /* synthetic */ Character val$keywordPrefix;
    final /* synthetic */ Character val$presentationPrefix;
    
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$9", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0073: {
            try {
                if (ocCompletionParameters.getPosition() instanceof PsiComment) {
                    return;
                }
                final OCCompletionParameters ocCompletionParameters2 = ocCompletionParameters;
                final OCExpression ocExpression = OCKeywordCompletionContributor.access$000(ocCompletionParameters2);
                if (ocExpression != null) {
                    return;
                }
                break Label_0073;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCCompletionParameters ocCompletionParameters2 = ocCompletionParameters;
                final OCExpression ocExpression = OCKeywordCompletionContributor.access$000(ocCompletionParameters2);
                if (ocExpression != null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final CompletionResultSet withOCPrefixMather = OCKeywordCompletionContributor.withOCPrefixMather(set, ocCompletionParameters.getRealPosition(), ocCompletionParameters.getOffset());
        final String string = "@" + s;
        final boolean startsWith = s.startsWith("_");
        for (final Keyword keyword : this.val$keywords) {
            String s2 = keyword.lookUpString;
            if (this.val$keywordPrefix != null) {
                s2 = this.val$keywordPrefix + s2;
            }
            Label_0243: {
                Label_0221: {
                    Label_0214: {
                        try {
                            if (StringUtil.startsWithIgnoreCase(s2, s)) {
                                break Label_0221;
                            }
                            final String s3 = s2;
                            final String s4 = string;
                            final boolean b = StringUtil.startsWithIgnoreCase(s3, s4);
                            if (!b) {
                                break Label_0214;
                            }
                            break Label_0221;
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                        try {
                            final String s3 = s2;
                            final String s4 = string;
                            final boolean b = StringUtil.startsWithIgnoreCase(s3, s4);
                            if (!b) {
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    try {
                        if (startsWith) {
                            break Label_0243;
                        }
                        final String s5 = s2;
                        final String s6 = "_";
                        final boolean b2 = s5.startsWith(s6);
                        if (b2) {
                            break Label_0243;
                        }
                        break Label_0243;
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                try {
                    final String s5 = s2;
                    final String s6 = "_";
                    final boolean b2 = s5.startsWith(s6);
                    if (b2) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            LookupElementBuilder lookupElementBuilder = TemplateInsertHandler.lookup(s2);
            if (this.val$presentationPrefix != null) {
                lookupElementBuilder = lookupElementBuilder.withPresentableText(this.val$presentationPrefix + lookupElementBuilder.getLookupString());
            }
            if (keyword.decorator != null) {
                lookupElementBuilder = keyword.decorator.decorate(ocCompletionParameters, lookupElementBuilder);
            }
            withOCPrefixMather.addElement(OCCompletionPriority.keywordWithPriority((LookupElement)lookupElementBuilder, keyword.priority));
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}