// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.CommonProcessors;

static final class OCGotoDeclarationHandler$1 extends CommonProcessors.FindFirstProcessor<OCMemberSymbol> {
    protected boolean accept(final OCMemberSymbol ocMemberSymbol) {
        return !ocMemberSymbol.isDefinition();
    }
}