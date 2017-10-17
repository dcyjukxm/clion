// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import java.util.Iterator;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.visitors.OCTypeNameVisitor;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.psi.search.GlobalSearchScope;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import java.util.HashSet;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.openapi.application.QueryExecutorBase;

private static class DefaultSearcher extends QueryExecutorBase<OCStructSymbol, OCStructInheritorsSearch.SearchParameters>
{
    public void processQuery(@NotNull final OCStructInheritorsSearch.SearchParameters searchParameters, @NotNull final Processor<OCStructSymbol> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/search/OCDirectStructInheritorsSearch$DefaultSearcher", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCDirectStructInheritorsSearch$DefaultSearcher", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCQualifiedName structName = searchParameters.getStructName();
        Label_0116: {
            try {
                if (structName == null) {
                    return;
                }
                final OCQualifiedName ocQualifiedName = structName;
                final String s = ocQualifiedName.getName();
                if (s == null) {
                    return;
                }
                break Label_0116;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final OCQualifiedName ocQualifiedName = structName;
                final String s = ocQualifiedName.getName();
                if (s == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final GlobalSearchScope scope = searchParameters.getScope();
        final HashSet set = new HashSet();
        ((Processor)new Processor<String>() {
            public boolean process(final String s) {
                if (!set.contains(s)) {
                    set.add(s);
                    OCGlobalProjectSymbolsCache.processAliasNamesForType(searchParameters.getProject(), s, (Processor<String>)this);
                }
                return true;
            }
        }).process((Object)structName.getName());
        OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(searchParameters.getProject(), (Processor<OCSymbol>)new Processor<OCSymbol>() {
            private boolean a(final OCStructSymbol ocStructSymbol) {
                if (scope.accept(ocStructSymbol.getContainingFile())) {
                    final PsiFile file = searchParameters.getFile();
                    for (final OCType ocType : ocStructSymbol.getBaseCppClasses((PsiElement)file)) {
                        ProgressManager.checkCanceled();
                        if (!set.contains(new OCTypeNameVisitor(OCType.Presentation.SHORT, true, false, true, (PsiElement)file).getName(ocType))) {
                            continue;
                        }
                        final OCType resolve = ocType.resolve(file);
                        if (!(resolve instanceof OCStructType)) {
                            continue;
                        }
                        final Iterator<OCStructSymbol> iterator2 = ((OCStructType)resolve).getStructs().iterator();
                        while (iterator2.hasNext()) {
                            if (Comparing.equal((Object)structName, (Object)iterator2.next().getResolvedQualifiedName())) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            
            public boolean process(final OCSymbol ocSymbol) {
                ProgressManager.checkCanceled();
                if (ocSymbol instanceof OCStructSymbol) {
                    if (!this.a((OCStructSymbol)ocSymbol)) {
                        return true;
                    }
                    processor.process((Object)ocSymbol);
                }
                return true;
            }
        }, null);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
