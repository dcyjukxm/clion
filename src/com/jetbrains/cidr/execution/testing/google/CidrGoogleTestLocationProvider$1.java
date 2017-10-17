// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

static final class CidrGoogleTestLocationProvider$1 extends CommonProcessors.FindProcessor<OCSymbol> {
    @Contract("null -> false")
    protected boolean accept(final OCSymbol ocSymbol) {
        return ocSymbol instanceof OCStructSymbol && CidrGoogleTestUtil.isGoogleTestClass((OCStructSymbol)ocSymbol);
    }
}