// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.search;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandName;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.search.searches.DefinitionsScopedSearch;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.application.QueryExecutorBase;

public class CMakeDefinitionsSearch extends QueryExecutorBase<PsiElement, DefinitionsScopedSearch.SearchParameters>
{
    public void processQuery(@NotNull final DefinitionsScopedSearch.SearchParameters searchParameters, @NotNull final Processor<PsiElement> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "queryParameters", "com/jetbrains/cidr/cpp/cmake/search/CMakeDefinitionsSearch", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/cpp/cmake/search/CMakeDefinitionsSearch", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        new ReadAction<Object>() {
            final /* synthetic */ PsiElement val$queryParametersElement = searchParameters.getElement();
            
            protected void run(@NotNull final Result<Object> result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/search/CMakeDefinitionsSearch$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                final PsiReference access$000 = a(this.val$queryParametersElement);
                if (access$000 instanceof PsiPolyVariantReference) {
                    final ResolveResult[] multiResolve = ((PsiPolyVariantReference)access$000).multiResolve(false);
                    for (int length = multiResolve.length, i = 0; i < length; ++i) {
                        final PsiElement element = multiResolve[i].getElement();
                        try {
                            if (element != null) {
                                processor.process((Object)element.getParent().getParent());
                            }
                        }
                        catch (Throwable t2) {
                            throw a(t2);
                        }
                    }
                }
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
    }
    
    @Nullable
    private static PsiReference a(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "queryParametersElement", "com/jetbrains/cidr/cpp/cmake/search/CMakeDefinitionsSearch", "findReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PsiReference psiReference = null;
        if (psiElement instanceof CMakeCommandName) {
            psiReference = psiElement.getParent().getReference();
        }
        else if (psiElement instanceof CMakeArgument) {
            psiReference = psiElement.getReference();
        }
        return psiReference;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
