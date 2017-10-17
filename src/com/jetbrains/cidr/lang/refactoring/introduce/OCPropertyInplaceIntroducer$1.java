// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class OCPropertyInplaceIntroducer$1 implements ItemListener {
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        OCPropertyInplaceIntroducer.access$000(OCPropertyInplaceIntroducer.this, (OCPropertySymbol.PropertySemantics)OCPropertyInplaceIntroducer.this.mySemantics.compute());
    }
}