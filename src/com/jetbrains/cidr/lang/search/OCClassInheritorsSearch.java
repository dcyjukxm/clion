// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.util.Pair;
import java.util.HashSet;
import com.intellij.util.containers.Stack;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.QueryExecutorBase;
import com.intellij.util.QueryExecutor;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.intellij.psi.search.GlobalSearchScope;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.intellij.util.Query;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.QueryFactory;

public class OCClassInheritorsSearch extends QueryFactory<OCClassSymbol, SearchParameters>
{
    public static final OCClassInheritorsSearch INSTANCE;
    
    public static Query<OCClassSymbol> search(final OCClassDeclaration ocClassDeclaration) {
        final Project project = ocClassDeclaration.getProject();
        return search(ocClassDeclaration.getName(), ocClassDeclaration instanceof OCProtocol, GlobalSearchScope.allScope(project), project, ocClassDeclaration instanceof OCImplementation);
    }
    
    public static Query<OCClassSymbol> search(final OCClassSymbol ocClassSymbol) {
        final Project project = ocClassSymbol.getProject();
        return search(ocClassSymbol.getName(), ocClassSymbol instanceof OCProtocolSymbol, GlobalSearchScope.allScope(project), project, ocClassSymbol instanceof OCImplementationSymbol);
    }
    
    public static Query<OCClassSymbol> search(final String s, final boolean b, final GlobalSearchScope globalSearchScope, final Project project, final boolean b2) {
        return (Query<OCClassSymbol>)OCClassInheritorsSearch.INSTANCE.createQuery((Object)new SearchParameters(globalSearchScope, s, b, project, b2));
    }
    
    static {
        (INSTANCE = new OCClassInheritorsSearch()).registerExecutor((QueryExecutor)new DefaultExecutor());
    }
    
    public static class SearchParameters
    {
        private final GlobalSearchScope myScope;
        private final String myClassName;
        private final Project myProject;
        private boolean myProtocol;
        private final boolean myPreferImplementations;
        
        public SearchParameters(final GlobalSearchScope myScope, final String myClassName, final boolean myProtocol, final Project myProject, final boolean myPreferImplementations) {
            this.myScope = myScope;
            this.myClassName = myClassName;
            this.myProtocol = myProtocol;
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
            return this.myProtocol;
        }
        
        public boolean isPreferImplementations() {
            return this.myPreferImplementations;
        }
    }
    
    private static class DefaultExecutor extends QueryExecutorBase<OCClassSymbol, SearchParameters>
    {
        public void processQuery(@NotNull final SearchParameters searchParameters, @NotNull final Processor<OCClassSymbol> processor) {
            try {
                if (searchParameters == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/search/OCClassInheritorsSearch$DefaultExecutor", "processQuery"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (processor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCClassInheritorsSearch$DefaultExecutor", "processQuery"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final GlobalSearchScope scope = searchParameters.getScope();
            final Project project = searchParameters.getProject();
            final Stack stack = new Stack();
            final HashSet<Pair> set = new HashSet<Pair>();
            stack.push((Object)Pair.create((Object)searchParameters.getClassName(), (Object)searchParameters.isProtocol()));
            while (!stack.isEmpty()) {
                ProgressManager.checkCanceled();
                final Pair pair = (Pair)stack.pop();
                try {
                    if (!set.add(pair)) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                OCDirectInheritorsSearch.search((String)pair.getFirst(), GlobalSearchScope.allScope(project), project, (boolean)pair.getSecond(), searchParameters.isPreferImplementations()).forEach(ocClassSymbol -> {
                    try {
                        if (processor == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCClassInheritorsSearch$DefaultExecutor", "lambda$processQuery$0"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        stack.push((Object)Pair.create((Object)ocClassSymbol.getName(), (Object)(ocClassSymbol instanceof OCProtocolSymbol)));
                        if (!scope.contains(ocClassSymbol.getContainingFile())) {
                            return true;
                        }
                        final Processor processor2 = processor;
                        final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                        final boolean b = processor2.process((Object)ocClassSymbol2);
                        if (!b) {
                            return false;
                        }
                        return true;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final Processor processor2 = processor;
                        final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                        final boolean b = processor2.process((Object)ocClassSymbol2);
                        if (!b) {
                            return false;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    return true;
                });
            }
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
