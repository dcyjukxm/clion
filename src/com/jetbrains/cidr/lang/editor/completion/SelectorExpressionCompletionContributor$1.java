// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import java.util.HashSet;
import java.util.Set;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.resolve.OCSelectorAdHocResolver;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class SelectorExpressionCompletionContributor$1 extends OCCompletionProvider {
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/SelectorExpressionCompletionContributor$1", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final MethodsProcessor methodsProcessor = new MethodsProcessor(set);
        final OCSelectorExpression ocSelectorExpression = (OCSelectorExpression)ocCompletionParameters.getPosition().getParent();
        final OCObjectTypeContext actionTargetContext = OCSelectorAdHocResolver.getActionTargetContext(ocSelectorExpression);
        Label_0097: {
            try {
                if (actionTargetContext == null) {
                    break Label_0097;
                }
                final OCCompletionParameters ocCompletionParameters2 = ocCompletionParameters;
                final int n = ocCompletionParameters2.getInvocationCount();
                final int n2 = 2;
                if (n < n2) {
                    break Label_0097;
                }
                break Label_0097;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCCompletionParameters ocCompletionParameters2 = ocCompletionParameters;
                final int n = ocCompletionParameters2.getInvocationCount();
                final int n2 = 2;
                if (n < n2) {
                    actionTargetContext.getType().getClassSymbol().processMembersInAllCategories((String)null, (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)methodsProcessor, false);
                    set.addLookupAdvertisement("Press " + SelectorExpressionCompletionContributor.access$100("CodeCompletion") + " again for all method selectors");
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        methodsProcessor.myCheckInterfaces = true;
        OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(ocSelectorExpression.getProject(), (Processor<OCSymbol>)methodsProcessor, null);
        methodsProcessor.myCheckInterfaces = false;
        OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(ocSelectorExpression.getProject(), (Processor<OCSymbol>)methodsProcessor, null);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}