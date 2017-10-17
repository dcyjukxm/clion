// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.intellij.codeInsight.completion.CompletionType;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.symbols.OCSymbolReferenceResolver;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

private static class InnerCompletionProvider extends OCCompletionProvider
{
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ps", "com/jetbrains/cidr/lang/editor/completion/OCNewExpressionCompletionContributor$InnerCompletionProvider", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement parent = ocCompletionParameters.getPosition().getParent();
        final int invocationCount = ocCompletionParameters.getInvocationCount();
        final OCFile ocFile = (OCFile)parent.getContainingFile().getOriginalFile();
        final OCSymbolsToLookupConverter.ConverterState converterState = new OCSymbolsToLookupConverter.ConverterState();
        final Condition<OCSymbol> a = a(ocCompletionParameters, ocFile);
        final OCSymbolGroupContext a2 = a();
        final OCSymbolReference.LocalReference localReference = OCSymbolReference.getLocalReference(OCSymbolReferenceResolver.getQualifiedName((OCNamespaceQualifierOwner)parent).changeName(null), parent);
        final OCSymbolsToLookupConverter ocSymbolsToLookupConverter = new OCSymbolsToLookupConverter(a2, converterState, set, true, parent, a);
        localReference.processPossibleSymbols((Processor<OCSymbol>)ocSymbolsToLookupConverter, (PsiFile)ocFile);
        ocSymbolsToLookupConverter.finish();
        if (invocationCount == 1) {
            set.addLookupAdvertisement("Press " + OCNewExpressionCompletionContributor.access$100("CodeCompletion") + " again for not-imported symbols");
        }
        else {
            try {
                if (!ocSymbolsToLookupConverter.isEmpty() || invocationCount < 2) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)ocFile);
            ocResolveContext.setProcessNonImported(true);
            final OCSymbolsToLookupConverter ocSymbolsToLookupConverter2 = new OCSymbolsToLookupConverter(a2, converterState, set, false, parent, (Condition<OCSymbol>)Conditions.and((Condition)a, (Condition)OCSymbol.HEADER_DECLARATION_CONDITION));
            ContainerUtil.process((List)ocResolveContext.resolveToSymbols(localReference), (Processor)ocSymbolsToLookupConverter2);
            ocSymbolsToLookupConverter2.finish();
        }
    }
    
    private static Condition<OCSymbol> a(@NotNull final OCCompletionParameters ocCompletionParameters, @Nullable final OCFile ocFile) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ps", "com/jetbrains/cidr/lang/editor/completion/OCNewExpressionCompletionContributor$InnerCompletionProvider", "getInitialCondition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Condition alwaysTrue = Conditions.alwaysTrue();
        Condition condition;
        if (ocFile instanceof OCCodeFragment) {
            final Condition<OCSymbol> completionFilter = ((OCCodeFragment)ocFile).getCompletionFilter();
            Condition and = null;
            Label_0085: {
                try {
                    if (completionFilter != null) {
                        and = Conditions.and(alwaysTrue, (Condition)completionFilter);
                        break Label_0085;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                and = alwaysTrue;
            }
            condition = and;
        }
        else {
            condition = alwaysTrue;
        }
        if (ocCompletionParameters.getCompletionType() == CompletionType.SMART) {
            final OCType resolve = OCExpectedTypeUtil.getExpectedType((OCExpression)PsiTreeUtil.getContextOfType(ocCompletionParameters.getPosition(), new Class[] { OCCppNewExpression.class })).resolve((PsiFile)ocFile);
            if (resolve instanceof OCPointerType) {
                OCType ocType = ((OCPointerType)resolve).getRefType();
                if (ocType instanceof OCCppReferenceType) {
                    ocType = ((OCCppReferenceType)ocType).getRefType();
                }
                Condition condition2;
                if (ocType instanceof OCStructType) {
                    final OCSmartCompletionContributor.TypeMatchingCondition<OCSymbol> typeMatchingCondition = new OCSmartCompletionContributor.TypeMatchingCondition<OCSymbol>(ocFile, ocCompletionParameters.getPosition().getParent(), OCPointerType.to(ocType)) {
                        @Override
                        protected OCType getSymbolType(final OCSymbol ocSymbol, final PsiElement psiElement) {
                            return OCPointerType.to(super.getSymbolType(ocSymbol, psiElement));
                        }
                    };
                    typeMatchingCondition.acceptTypeDeclarations(true);
                    condition2 = (Condition)typeMatchingCondition;
                }
                else {
                    condition2 = (ocSymbol -> {
                        try {
                            if (!(ocSymbol instanceof OCFunctionSymbol)) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        return false;
                    });
                }
                return (Condition<OCSymbol>)Conditions.and(condition, condition2);
            }
        }
        return (Condition<OCSymbol>)condition;
    }
    
    private static OCSymbolGroupContext a() {
        final OCSymbolGroupContext ocSymbolGroupContext = new OCSymbolGroupContext("constructor", new OCSymbolKind[0]);
        ocSymbolGroupContext.addSymbolContexts(OCSymbolGroupContext.CONSTRUCTOR_CONTEXT.getSymbolContexts());
        ocSymbolGroupContext.addSymbolContext(OCSymbolKind.NAMESPACE);
        ocSymbolGroupContext.addSymbolContext(OCSymbolKind.NAMESPACE_ALIAS);
        ocSymbolGroupContext.addSymbolContext(OCSymbolKind.MACRO);
        return ocSymbolGroupContext;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
