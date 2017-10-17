// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.PsiReference;
import com.intellij.util.QueryExecutor;

public class OCPropertyReferencesSearch implements QueryExecutor<PsiReference, ReferencesSearch.SearchParameters>
{
    public boolean execute(@NotNull final ReferencesSearch.SearchParameters searchParameters, @NotNull final Processor<PsiReference> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "queryParameters", "com/jetbrains/cidr/lang/search/OCPropertyReferencesSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCPropertyReferencesSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (boolean)new ReadAction<Boolean>() {
            protected void run(@NotNull final Result<Boolean> result) {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/search/OCPropertyReferencesSearch$1", "run"));
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
            if (!(elementToSearch instanceof OCSymbolDeclarator)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCPropertySymbol symbol = ((OCSymbolDeclarator<OCPropertySymbol>)elementToSearch).getSymbol();
        try {
            if (!(symbol instanceof OCPropertySymbol)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCPropertySymbol ocPropertySymbol = symbol;
        return ((OCSymbolWithParent<T, OCClassSymbol>)ocPropertySymbol).getParent().processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)(ocMethodSymbol -> {
            Label_0029: {
                try {
                    if (ocMethodSymbol.getGeneratedFromProperty() != ocPropertySymbol) {
                        break Label_0029;
                    }
                    final ReferencesSearch.SearchParameters searchParameters2 = searchParameters;
                    final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                    final PsiElement psiElement2 = elementToSearch;
                    final Processor processor2 = processor;
                    final boolean b = a(searchParameters2, ocMethodSymbol2, psiElement2, (Processor<PsiReference>)processor2);
                    if (b) {
                        break Label_0029;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final ReferencesSearch.SearchParameters searchParameters2 = searchParameters;
                    final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                    final PsiElement psiElement2 = elementToSearch;
                    final Processor processor2 = processor;
                    final boolean b = a(searchParameters2, ocMethodSymbol2, psiElement2, (Processor<PsiReference>)processor2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        }));
    }
    
    private static boolean a(final ReferencesSearch.SearchParameters searchParameters, final OCMethodSymbol ocMethodSymbol, final PsiElement psiElement, final Processor<PsiReference> processor) {
        final String name = ocMethodSymbol.getName();
        return PsiSearchHelper.SERVICE.getInstance(psiElement.getProject()).processElementsWithWord((psiElement2, n) -> {
            Object o = null;
            if (psiElement2 instanceof OCSendMessageExpression) {
                o = ((OCSendMessageExpression)psiElement2).getMessageSelector();
            }
            else if (psiElement2 instanceof OCSelectorExpression) {
                o = ((OCSelectorExpression)psiElement2).getSelector();
            }
            else if (psiElement2 instanceof OCQualifiedExpression) {
                o = ((OCQualifiedExpression)psiElement2).getName();
            }
            if (name.equals(o)) {
                final PsiReference reference = psiElement2.getReference();
                Label_0103: {
                    try {
                        if (reference == null) {
                            return true;
                        }
                        final PsiReference psiReference = reference;
                        final PsiElement psiElement3 = psiElement;
                        final boolean b = psiReference.isReferenceTo(psiElement3);
                        if (b) {
                            break Label_0103;
                        }
                        return true;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final PsiReference psiReference = reference;
                        final PsiElement psiElement3 = psiElement;
                        final boolean b = psiReference.isReferenceTo(psiElement3);
                        if (b) {
                            return processor.process((Object)reference);
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
            return true;
        }, searchParameters.getEffectiveSearchScope(), name, (short)1, true);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
