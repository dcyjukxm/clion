// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.constructors;

import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.util.QueryExecutor;
import com.intellij.util.Query;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.PsiReference;
import com.intellij.util.QueryFactory;

public class OCConstructorImplicitReferenceSearch extends QueryFactory<PsiReference, ReferencesSearch.SearchParameters>
{
    public static final OCConstructorImplicitReferenceSearch INSTANCE;
    
    public static Query<PsiReference> search(final PsiElement psiElement, final SearchScope searchScope) {
        return (Query<PsiReference>)OCConstructorImplicitReferenceSearch.INSTANCE.createQuery((Object)new ReferencesSearch.SearchParameters(psiElement, searchScope, false));
    }
    
    static {
        (INSTANCE = new OCConstructorImplicitReferenceSearch()).registerExecutor((QueryExecutor)new DefaultExecutor());
    }
    
    private static class DefaultExecutor extends OCConstructorReferenceSearchBase
    {
        @Override
        protected boolean onReferenceFromDeclarator(final OCDeclarator ocDeclarator, final OCFunctionSymbol ocFunctionSymbol, final Processor<PsiReference> processor) {
            return true;
        }
        
        @Override
        protected boolean onReferenceFromInitializer(final OCConstructorFieldInitializer ocConstructorFieldInitializer, final OCSymbolDeclarator ocSymbolDeclarator, final OCFunctionSymbol ocFunctionSymbol, final Processor<PsiReference> processor) {
            return true;
        }
        
        @Override
        protected boolean onReferenceFromOtherConstructor(final OCFunctionDefinition ocFunctionDefinition, final OCSymbolDeclarator ocSymbolDeclarator, final OCFunctionSymbol ocFunctionSymbol, final Processor<PsiReference> processor) {
            return processor.process((Object)new OCConstructorReferenceFromOtherConstructor(ocFunctionDefinition, ocSymbolDeclarator, ocFunctionSymbol));
        }
        
        @Override
        protected boolean onReferenceFromStruct(final OCStruct ocStruct, final OCSymbolDeclarator ocSymbolDeclarator, final OCFunctionSymbol ocFunctionSymbol, final Processor<PsiReference> processor) {
            return processor.process((Object)new OCConstructorReferenceFromStruct(ocStruct, ocSymbolDeclarator, ocFunctionSymbol));
        }
    }
}
