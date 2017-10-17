// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class OCSmartCompletionContributor$2 extends OCCompletionProvider {
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$2", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCQualifiedExpression ocQualifiedExpression = (OCQualifiedExpression)ocCompletionParameters.getPosition().getParent();
        final OCDeclarator ocDeclarator = (OCDeclarator)PsiTreeUtil.getParentOfType((PsiElement)ocQualifiedExpression, (Class)OCDeclarator.class);
        OCSymbol symbol = null;
        Label_0090: {
            try {
                if (ocDeclarator != null) {
                    symbol = ocDeclarator.getSymbol();
                    break Label_0090;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            symbol = null;
        }
        final OCSymbol ocSymbol = symbol;
        final OCType resolve = OCExpectedTypeUtil.getExpectedType(ocQualifiedExpression).resolve((PsiFile)ocQualifiedExpression.getContainingOCFile());
        if (resolve != OCUnknownType.INSTANCE) {
            final Iterator<OCPunctuatorElementType> iterator = ocQualifiedExpression.qualifyingTokensForCompletion().iterator();
            while (iterator.hasNext()) {
                OCSmartCompletionContributor.access$100(set, ocQualifiedExpression, resolve, ocSymbol, iterator.next());
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}