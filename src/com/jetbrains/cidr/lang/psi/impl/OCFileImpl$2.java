// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

class OCFileImpl$2 implements Processor<OCSymbol> {
    final /* synthetic */ Processor val$processor;
    
    public boolean process(final OCSymbol ocSymbol) {
        return this.val$processor.process((Object)ocSymbol) && (!(ocSymbol instanceof OCMembersContainer) || ((OCMembersContainer)ocSymbol).processMembers(null, (Processor)this));
    }
}