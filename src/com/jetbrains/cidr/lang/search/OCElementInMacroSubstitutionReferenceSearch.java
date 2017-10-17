// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.ArrayUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReferenceBase;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiNamedElement;
import java.util.Iterator;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import java.util.Collection;
import com.intellij.psi.search.SingleTargetRequestResultProcessor;
import com.intellij.psi.search.TextOccurenceProcessor;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.intellij.psi.search.SearchScope;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.containers.HashSet;
import com.intellij.psi.search.PsiSearchHelper;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;
import com.jetbrains.cidr.lang.inspections.OCGlobalSearchScopeForUnusedCode;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.PsiReference;
import com.intellij.util.QueryExecutor;

public class OCElementInMacroSubstitutionReferenceSearch implements QueryExecutor<PsiReference, ReferencesSearch.SearchParameters>
{
    public static boolean isUsedInMacros(final PsiElement psiElement) {
        try {
            if (!(psiElement.getUseScope() instanceof LocalSearchScope)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        new OCElementInMacroSubstitutionReferenceSearch().execute(new ReferencesSearch.SearchParameters(psiElement, psiElement.getUseScope(), false), (Processor<PsiReference>)findFirstProcessor);
        return findFirstProcessor.isFound();
    }
    
    public boolean execute(@NotNull final ReferencesSearch.SearchParameters searchParameters, @NotNull final Processor<PsiReference> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "queryParameters", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (searchParameters.getScopeDeterminedByUser() instanceof OCGlobalSearchScopeForUnusedCode) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (boolean)new ReadAction<Boolean>() {
            protected void run(@NotNull final Result<Boolean> result) {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$1", "run"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                result.setResult((Object)OCElementInMacroSubstitutionReferenceSearch.this.doExecute(searchParameters, processor));
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }.execute().getResultObject();
    }
    
    public boolean doExecute(@NotNull final ReferencesSearch.SearchParameters searchParameters, @NotNull final Processor<PsiReference> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch", "doExecute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch", "doExecute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiElement elementToSearch = searchParameters.getElementToSearch();
        boolean b2 = false;
        Label_0126: {
            Label_0117: {
                try {
                    if (!(searchParameters instanceof MySearchParameters)) {
                        break Label_0117;
                    }
                    final ReferencesSearch.SearchParameters searchParameters2 = searchParameters;
                    final MySearchParameters mySearchParameters = (MySearchParameters)searchParameters2;
                    final boolean b = mySearchParameters.searchForRenameConflicts();
                    if (b) {
                        break Label_0117;
                    }
                    break Label_0117;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final ReferencesSearch.SearchParameters searchParameters2 = searchParameters;
                    final MySearchParameters mySearchParameters = (MySearchParameters)searchParameters2;
                    final boolean b = mySearchParameters.searchForRenameConflicts();
                    if (b) {
                        b2 = true;
                        break Label_0126;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            b2 = false;
        }
        final boolean b3 = b2;
        final OCElementInMacroSubstitutionReferenceSearch.1MacroCallProcessor 1MacroCallProcessor = new OCElementInMacroSubstitutionReferenceSearch.1MacroCallProcessor(elementToSearch, processor, b3);
        Object o = searchParameters.getEffectiveSearchScope();
        if (o instanceof LocalSearchScope) {
            final boolean processElements = PsiTreeUtil.processElements((PsiElementProcessor)new PsiElementProcessor() {
                public boolean execute(@NotNull final PsiElement psiElement) {
                    try {
                        if (psiElement == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$2", "execute"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (!(psiElement instanceof OCMacroCall)) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    final OCReferenceElement macroReferenceElement = ((OCMacroCall)psiElement).getMacroReferenceElement();
                    try {
                        if (macroReferenceElement != null) {
                            return 1MacroCallProcessor.process(macroReferenceElement.getReference());
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    return true;
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            }, ((LocalSearchScope)o).getScope());
            Label_0211: {
                try {
                    if (!b3) {
                        return processElements;
                    }
                    final OCElementInMacroSubstitutionReferenceSearch.1MacroCallProcessor 1MacroCallProcessor2 = 1MacroCallProcessor;
                    final Set<OCMacroSymbol> set = 1MacroCallProcessor2.goodMacros;
                    final boolean b4 = set.isEmpty();
                    if (b4) {
                        return processElements;
                    }
                    break Label_0211;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final OCElementInMacroSubstitutionReferenceSearch.1MacroCallProcessor 1MacroCallProcessor2 = 1MacroCallProcessor;
                    final Set<OCMacroSymbol> set = 1MacroCallProcessor2.goodMacros;
                    final boolean b4 = set.isEmpty();
                    if (b4) {
                        return processElements;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            o = GlobalSearchScope.allScope(elementToSearch.getProject());
        }
        final boolean execute = new OCElementInMacroAndNonCompiledCodeReferencesSearch().execute(searchParameters, (SearchScope)OCSearchScope.getProjectSourcesScope(elementToSearch.getProject()), (Processor<OCElementInMacroAndNonCompiledCodeReferencesSearch.Usage>)new Processor<OCElementInMacroAndNonCompiledCodeReferencesSearch.Usage>() {
            HashSet<OCSymbol> myProcessedDirectives = new HashSet();
            final /* synthetic */ PsiSearchHelper val$helper = PsiSearchHelper.SERVICE.getInstance(elementToSearch.getProject());
            
            public boolean process(final OCElementInMacroAndNonCompiledCodeReferencesSearch.Usage usage) {
                final PsiElement element = usage.getElement();
                if (element instanceof OCMacroCall) {
                    final OCReferenceElement macroReferenceElement = ((OCMacroCall)element).getMacroReferenceElement();
                    if (macroReferenceElement != null) {
                        return 1MacroCallProcessor.process(macroReferenceElement.getReference());
                    }
                }
                else if (!(element instanceof OCDefineDirective)) {
                    return true;
                }
                final OCDefineDirective ocDefineDirective = (OCDefineDirective)element;
                final String name = ocDefineDirective.getName();
                if (name == null) {
                    return true;
                }
                if (this.myProcessedDirectives.add(((OCSymbolDeclarator<Object>)ocDefineDirective).getSymbol())) {
                    new OCElementInMacroAndNonCompiledCodeReferencesSearch().execute(new ReferencesSearch.SearchParameters((PsiElement)ocDefineDirective, o, searchParameters.isIgnoreAccessScope(), searchParameters.getOptimizer()), (Processor<OCElementInMacroAndNonCompiledCodeReferencesSearch.Usage>)this);
                }
                return this.val$helper.processElementsWithWord((TextOccurenceProcessor)new TextOccurenceProcessor() {
                    SingleTargetRequestResultProcessor resultProcessor = new SingleTargetRequestResultProcessor((PsiElement)ocDefineDirective);
                    
                    public boolean execute(@NotNull final PsiElement psiElement, final int n) {
                        try {
                            if (psiElement == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$3$1", "execute"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        return this.resultProcessor.processTextOccurrence(psiElement, n, (Processor)1MacroCallProcessor);
                    }
                    
                    private static IllegalArgumentException a(final IllegalArgumentException ex) {
                        return ex;
                    }
                }, o, name, (short)1, true);
            }
        });
        if (b3) {
            1MacroCallProcessor.badMacros.retainAll(1MacroCallProcessor.goodMacros);
            for (final OCMacroSymbol ocMacroSymbol : 1MacroCallProcessor.badMacros) {
                processor.process((Object)new BadMacroSubstitutionReference(1MacroCallProcessor.badMacroReferences.get(ocMacroSymbol), ocMacroSymbol));
            }
        }
        return execute;
    }
    
    @Nullable
    private static String a(final PsiElement psiElement) {
        try {
            if (psiElement instanceof PsiNamedElement) {
                return ((PsiNamedElement)psiElement).getName();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class MySearchParameters extends ReferencesSearch.SearchParameters
    {
        private boolean mySearchForRenameConflicts;
        
        public MySearchParameters(@NotNull final PsiElement psiElement, final SearchScope searchScope, final boolean b, final boolean mySearchForRenameConflicts) {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementToSearch", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$MySearchParameters", "<init>"));
            }
            super(psiElement, searchScope, b);
            this.mySearchForRenameConflicts = mySearchForRenameConflicts;
        }
        
        public boolean searchForRenameConflicts() {
            return this.mySearchForRenameConflicts;
        }
    }
    
    public static class BadMacroSubstitutionReference extends PsiReferenceBase
    {
        private OCMacroSymbol myMacroSymbol;
        
        public BadMacroSubstitutionReference(@NotNull final PsiElement psiElement, final OCMacroSymbol myMacroSymbol) {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$BadMacroSubstitutionReference", "<init>"));
            }
            super(psiElement, new TextRange(0, psiElement.getTextLength()));
            this.myMacroSymbol = myMacroSymbol;
        }
        
        public PsiElement resolve() {
            return null;
        }
        
        @NotNull
        public Object[] getVariants() {
            Object[] empty_OBJECT_ARRAY;
            try {
                empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
                if (empty_OBJECT_ARRAY == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$BadMacroSubstitutionReference", "getVariants"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return empty_OBJECT_ARRAY;
        }
        
        public OCMacroSymbol getMacroSymbol() {
            return this.myMacroSymbol;
        }
        
        public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
            return this.myElement;
        }
        
        private static IncorrectOperationException a(final IncorrectOperationException ex) {
            return ex;
        }
    }
}
