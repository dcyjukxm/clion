// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.openapi.progress.ProgressManager;
import java.util.Iterator;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.QueryExecutorBase;
import com.intellij.util.QueryExecutor;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.Query;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.QueryFactory;

public class OCDirectInheritorsSearch extends QueryFactory<OCClassSymbol, SearchParameters>
{
    public static final OCDirectInheritorsSearch INSTANCE;
    
    public static Query<OCClassSymbol> search(final OCClassDeclaration ocClassDeclaration) {
        final Project project = ocClassDeclaration.getProject();
        return search(ocClassDeclaration.getName(), GlobalSearchScope.allScope(project), project, ocClassDeclaration instanceof OCProtocol, ocClassDeclaration instanceof OCImplementation);
    }
    
    public static Query<OCClassSymbol> search(final OCClassSymbol ocClassSymbol) {
        final Project project = ocClassSymbol.getProject();
        return search(ocClassSymbol.getName(), GlobalSearchScope.allScope(project), project, ocClassSymbol instanceof OCProtocolSymbol, ocClassSymbol instanceof OCImplementationSymbol);
    }
    
    public static Query<OCClassSymbol> search(final String s, final GlobalSearchScope globalSearchScope, final Project project, final boolean b, final boolean b2) {
        return (Query<OCClassSymbol>)OCDirectInheritorsSearch.INSTANCE.createQuery((Object)new SearchParameters(globalSearchScope, s, b, project, b2));
    }
    
    static {
        (INSTANCE = new OCDirectInheritorsSearch()).registerExecutor((QueryExecutor)new DefaultSearcher());
    }
    
    public static class SearchParameters
    {
        private final GlobalSearchScope myScope;
        private final String myClassName;
        private final boolean isProtocol;
        private final Project myProject;
        private final boolean myPreferImplementations;
        
        public SearchParameters(final GlobalSearchScope myScope, final String myClassName, final boolean isProtocol, final Project myProject, final boolean myPreferImplementations) {
            this.myScope = myScope;
            this.myClassName = myClassName;
            this.isProtocol = isProtocol;
            this.myProject = myProject;
            this.myPreferImplementations = myPreferImplementations;
        }
        
        public GlobalSearchScope getScope() {
            return this.myScope;
        }
        
        public String getClassName() {
            return this.myClassName;
        }
        
        public Project getProject() {
            return this.myProject;
        }
        
        public boolean isProtocol() {
            return this.isProtocol;
        }
        
        public boolean isPreferImplementations() {
            return this.myPreferImplementations;
        }
    }
    
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
}
