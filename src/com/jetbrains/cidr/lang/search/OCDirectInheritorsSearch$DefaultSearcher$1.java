// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.openapi.progress.ProgressManager;
import java.util.Iterator;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.psi.search.GlobalSearchScope;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

class OCDirectInheritorsSearch$DefaultSearcher$1 implements Processor<OCSymbol> {
    final /* synthetic */ GlobalSearchScope val$scope;
    final /* synthetic */ boolean val$isProtocol;
    final /* synthetic */ String val$superName;
    final /* synthetic */ boolean val$preferImplementations;
    final /* synthetic */ Processor val$consumer;
    
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
                    this.val$consumer.process((Object)implementation);
                }
                else {
                    this.val$consumer.process((Object)ocSymbol);
                }
            }
            else {
                this.val$consumer.process((Object)ocSymbol);
            }
        }
        else if (ocSymbol instanceof OCProtocolSymbol) {
            final Iterator<String> iterator = ((OCProtocolSymbol)ocSymbol).getProtocolNames().iterator();
            while (iterator.hasNext()) {
                if (Comparing.equal(this.val$superName, (String)iterator.next())) {
                    return this.val$consumer.process((Object)ocSymbol);
                }
            }
        }
        return true;
    }
}