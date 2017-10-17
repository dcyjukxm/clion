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
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import java.util.HashSet;
import com.intellij.psi.search.GlobalSearchScope;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

class OCDirectStructInheritorsSearch$DefaultSearcher$2 implements Processor<OCSymbol> {
    final /* synthetic */ GlobalSearchScope val$scope;
    final /* synthetic */ OCStructInheritorsSearch.SearchParameters val$p;
    final /* synthetic */ HashSet val$aliases;
    final /* synthetic */ OCQualifiedName val$superName;
    final /* synthetic */ Processor val$consumer;
    
    private boolean a(final OCStructSymbol ocStructSymbol) {
        if (this.val$scope.accept(ocStructSymbol.getContainingFile())) {
            final PsiFile file = this.val$p.getFile();
            for (final OCType ocType : ocStructSymbol.getBaseCppClasses((PsiElement)file)) {
                ProgressManager.checkCanceled();
                if (!this.val$aliases.contains(new OCTypeNameVisitor(OCType.Presentation.SHORT, true, false, true, (PsiElement)file).getName(ocType))) {
                    continue;
                }
                final OCType resolve = ocType.resolve(file);
                if (!(resolve instanceof OCStructType)) {
                    continue;
                }
                final Iterator<OCStructSymbol> iterator2 = ((OCStructType)resolve).getStructs().iterator();
                while (iterator2.hasNext()) {
                    if (Comparing.equal((Object)this.val$superName, (Object)iterator2.next().getResolvedQualifiedName())) {
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
            this.val$consumer.process((Object)ocSymbol);
        }
        return true;
    }
}