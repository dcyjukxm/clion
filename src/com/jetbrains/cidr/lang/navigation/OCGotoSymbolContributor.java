// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;

public class OCGotoSymbolContributor extends OCGotoByNameContributor
{
    public OCGotoSymbolContributor() {
        super((Condition<OCSymbol>)Conditions.alwaysTrue(), true);
    }
}
