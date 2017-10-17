// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.psi.PsiElement;
import com.intellij.psi.search.PsiSearchHelper;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.PsiReference;
import com.intellij.util.QueryExecutor;

public class OCDollarMacroReferenceSearch implements QueryExecutor<PsiReference, ReferencesSearch.SearchParameters>
{
    public boolean execute(@NotNull final ReferencesSearch.SearchParameters searchParameters, @NotNull final Processor<PsiReference> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "queryParameters", "com/jetbrains/cidr/lang/search/OCDollarMacroReferenceSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCDollarMacroReferenceSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (boolean)new ReadAction<Boolean>() {
            protected void run(@NotNull final Result<Boolean> result) {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/search/OCDollarMacroReferenceSearch$1", "run"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                result.setResult((Object)a(searchParameters, processor));
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }.execute().getResultObject();
    }
    
    private static Boolean a(final ReferencesSearch.SearchParameters searchParameters, final Processor<PsiReference> processor) {
        final PsiElement elementToSearch = searchParameters.getElementToSearch();
        try {
            if (!(elementToSearch instanceof OCDefineDirective)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiSearchHelper instance = PsiSearchHelper.SERVICE.getInstance(elementToSearch.getProject());
        final String name = ((OCDefineDirective)elementToSearch).getName();
        Label_0064: {
            try {
                if (name == null) {
                    break Label_0064;
                }
                final String s = name;
                final String s2 = "$";
                final boolean b = s.startsWith(s2);
                if (!b) {
                    break Label_0064;
                }
                return instance.processElementsWithWord((psiElement2, n) -> {
                    for (final PsiReference psiReference : psiElement2.getReferences()) {
                        Label_0065: {
                            try {
                                if (!psiReference.isReferenceTo(elementToSearch)) {
                                    break Label_0065;
                                }
                                final Processor processor2 = processor;
                                final PsiReference psiReference2 = psiReference;
                                final boolean b = processor2.process((Object)psiReference2);
                                if (!b) {
                                    return false;
                                }
                                break Label_0065;
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            try {
                                final Processor processor2 = processor;
                                final PsiReference psiReference2 = psiReference;
                                final boolean b = processor2.process((Object)psiReference2);
                                if (!b) {
                                    return false;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                        }
                    }
                    return true;
                }, searchParameters.getEffectiveSearchScope(), name.substring(1), (short)1, true);
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final String s = name;
                final String s2 = "$";
                final boolean b = s.startsWith(s2);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return instance.processElementsWithWord((psiElement2, n) -> {
            for (final PsiReference psiReference : psiElement2.getReferences()) {
                Label_0065: {
                    try {
                        if (!psiReference.isReferenceTo(elementToSearch)) {
                            break Label_0065;
                        }
                        final Processor processor2 = processor;
                        final PsiReference psiReference2 = psiReference;
                        final boolean b = processor2.process((Object)psiReference2);
                        if (!b) {
                            return false;
                        }
                        break Label_0065;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final Processor processor2 = processor;
                        final PsiReference psiReference2 = psiReference;
                        final boolean b = processor2.process((Object)psiReference2);
                        if (!b) {
                            return false;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
            return true;
        }, searchParameters.getEffectiveSearchScope(), name.substring(1), (short)1, true);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
