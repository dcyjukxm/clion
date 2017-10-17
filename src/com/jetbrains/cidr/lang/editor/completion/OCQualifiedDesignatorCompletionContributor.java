// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCQualifiedDesignator;
import com.intellij.psi.tree.IElementType;
import com.intellij.patterns.PlatformPatterns;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.patterns.ElementPattern;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.PsiElementPattern;

public class OCQualifiedDesignatorCompletionContributor extends OCCompletionContributorBase
{
    public static final PsiElementPattern.Capture<PsiElement> IDENTIFIER_IN_QUALIFIED_DESIGNATOR;
    
    public OCQualifiedDesignatorCompletionContributor() {
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)OCQualifiedDesignatorCompletionContributor.IDENTIFIER_IN_QUALIFIED_DESIGNATOR, new InnerCompletionProvider());
        this.register(CompletionType.SMART, (ElementPattern<? extends PsiElement>)OCQualifiedDesignatorCompletionContributor.IDENTIFIER_IN_QUALIFIED_DESIGNATOR, new InnerCompletionProvider());
    }
    
    static {
        IDENTIFIER_IN_QUALIFIED_DESIGNATOR = (PsiElementPattern.Capture)PlatformPatterns.psiElement((IElementType)OCTokenTypes.IDENTIFIER).withParent((Class)OCQualifiedDesignator.class);
    }
    
    private static class InnerCompletionProvider extends OCCompletionProvider
    {
        @Override
        protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
            try {
                if (ocCompletionParameters == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ps", "com/jetbrains/cidr/lang/editor/completion/OCQualifiedDesignatorCompletionContributor$InnerCompletionProvider", "addCompletions"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final PsiElement parent = ocCompletionParameters.getPosition().getParent();
            final OCFile ocFile = (OCFile)parent.getContainingFile().getOriginalFile();
            if (parent instanceof OCQualifiedDesignator) {
                final OCType parentType = ((OCQualifiedDesignator)parent).getParentType();
                if (parentType instanceof OCStructType) {
                    final OCSymbolsToLookupConverter ocSymbolsToLookupConverter = new OCSymbolsToLookupConverter(OCSymbolGroupContext.STRUCT_FIELD_CONTEXT, new OCSymbolsToLookupConverter.ConverterState(), set, false, parent, null);
                    ((OCStructType)parentType).processFields(null, (Processor<? super OCDeclaratorSymbol>)ocSymbolsToLookupConverter, new OCResolveContext((PsiElement)ocFile));
                    ocSymbolsToLookupConverter.finish();
                }
            }
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
