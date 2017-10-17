// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

class FileSymbolTable$1 implements Processor<OCSymbol> {
    final /* synthetic */ long val$complexOffset;
    final /* synthetic */ String val$symbolName;
    final /* synthetic */ Class val$symbolClass;
    final /* synthetic */ OCSymbol[] val$result;
    final /* synthetic */ OCSymbol[] val$possible;
    
    public boolean process(OCSymbol delegate) {
        delegate = delegate.getDelegate();
        if ((this.val$complexOffset == -1L || delegate.getOffset() == OCSymbolOffsetUtil.getTextOffset(this.val$complexOffset)) && (this.val$symbolName == null || Comparing.equal(this.val$symbolName, delegate.getName())) && this.val$symbolClass.isAssignableFrom(((OCClassSymbol)delegate).getClass())) {
            if (this.val$complexOffset == -1L || delegate.getComplexOffset() == this.val$complexOffset) {
                this.val$result[0] = delegate;
                return false;
            }
            this.val$possible[0] = delegate;
        }
        if (delegate instanceof OCNamespaceSymbol) {
            return ((OCNamespaceSymbol)delegate).processMembers(null, (Processor<OCSymbol>)this);
        }
        return !(delegate instanceof OCClassSymbol) || ((OCClassSymbol)delegate).processMembers(this.val$symbolName, (Processor<OCMemberSymbol>)(ocMemberSymbol -> processor.process((Object)ocMemberSymbol)));
    }
}