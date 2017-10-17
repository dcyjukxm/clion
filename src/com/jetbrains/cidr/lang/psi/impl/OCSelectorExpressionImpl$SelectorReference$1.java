// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.CommonProcessors;

class OCSelectorExpressionImpl$SelectorReference$1 extends CommonProcessors.FindFirstProcessor<OCMemberSymbol> {
    protected boolean accept(final OCMemberSymbol ocMemberSymbol) {
        return ocMemberSymbol instanceof OCMethodSymbol;
    }
}