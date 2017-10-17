// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.openapi.progress.ProgressManager;
import java.util.Iterator;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.psi.search.GlobalSearchScope;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.application.QueryExecutorBase;

private static class DefaultSearcher extends QueryExecutorBase<OCClassSymbol, SearchParameters>
{
    public void processQuery(@NotNull final SearchParameters searchParameters, @NotNull final Processor<OCClassSymbol> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/search/OCDirectInheritorsSearch$DefaultSearcher", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCDirectInheritorsSearch$DefaultSearcher", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        OCGlobalProjectSymbolsCache.processTopLevelSymbols(searchParameters.getProject(), (Processor<OCSymbol>)new Processor<OCSymbol>() {
            final /* synthetic */ GlobalSearchScope val$scope = searchParameters.getScope();
            final /* synthetic */ boolean val$isProtocol = searchParameters.isProtocol();
            final /* synthetic */ String val$superName = searchParameters.getClassName();
            final /* synthetic */ boolean val$preferImplementations = searchParameters.isPreferImplementations();
            
            private boolean a(final OCInterfaceSymbol ocInterfaceSymbol) {
                if (this.val$scope.accept(ocInterfaceSymbol.getContainingFile())) {
                    if (this.val$isProtocol) {
                        final Iterator<String> iterator = ocInterfaceSymbol.getProtocolNames().iterator();
                        while (iterator.hasNext()) {
                            if (Comparing.equal(this.val$superName, (String)iterator.next())) {
                                return true;
                            }
                        }
                    }
                    else if (Comparing.equal(this.val$superName, ocInterfaceSymbol.getSuperClassName())) {
                        return true;
                    }
                }
                return false;
            }
            
            public boolean process(final OCSymbol ocSymbol) {
                ProgressManager.checkCanceled();
                if (ocSymbol instanceof OCInterfaceSymbol) {
                    if (!this.a((OCInterfaceSymbol)ocSymbol)) {
                        return true;
                    }
                    if (this.val$preferImplementations) {
                        final OCImplementationSymbol implementation = ((OCInterfaceSymbol)ocSymbol).getImplementation();
                        if (implementation != null) {
                            processor.process((Object)implementation);
                        }
                        else {
                            processor.process((Object)ocSymbol);
                        }
                    }
                    else {
                        processor.process((Object)ocSymbol);
                    }
                }
                else if (ocSymbol instanceof OCProtocolSymbol) {
                    final Iterator<String> iterator = ((OCProtocolSymbol)ocSymbol).getProtocolNames().iterator();
                    while (iterator.hasNext()) {
                        if (Comparing.equal(this.val$superName, (String)iterator.next())) {
                            return processor.process((Object)ocSymbol);
                        }
                    }
                }
                return true;
            }
        }, null);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
