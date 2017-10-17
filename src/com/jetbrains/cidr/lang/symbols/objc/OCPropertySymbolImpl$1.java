// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.intellij.util.Processor;

class OCPropertySymbolImpl$1 implements Processor<OCMemberSymbol> {
    boolean wasGetter;
    boolean wasSetter;
    final /* synthetic */ boolean val$treatSynthesizeAsAccessors;
    final /* synthetic */ String val$getter;
    final /* synthetic */ String val$setter;
    
    public boolean process(final OCMemberSymbol ocMemberSymbol) {
        if (this.val$treatSynthesizeAsAccessors && ocMemberSymbol instanceof OCSynthesizeSymbol && OCPropertySymbolImpl.access$000(OCPropertySymbolImpl.this).equals(ocMemberSymbol.getName())) {
            return false;
        }
        if (ocMemberSymbol instanceof OCMethodSymbol) {
            if (this.val$getter.equals(ocMemberSymbol.getName()) && ((OCMethodSymbol)ocMemberSymbol).isGetter()) {
                this.wasGetter = true;
            }
            if (this.val$setter.equals(ocMemberSymbol.getName()) && ((OCMethodSymbol)ocMemberSymbol).isSetter()) {
                this.wasSetter = true;
            }
            return !this.wasGetter || (!OCPropertySymbolImpl.this.isReadonly() && !this.wasSetter);
        }
        return true;
    }
}