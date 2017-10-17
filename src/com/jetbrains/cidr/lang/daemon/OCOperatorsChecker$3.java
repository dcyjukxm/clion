// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCChangeTypeIntentionAction;

class OCOperatorsChecker$3 extends OCChangeTypeIntentionAction {
    final /* synthetic */ OCFunctionSymbol val$functionSymbol;
    final /* synthetic */ boolean val$isVolatile;
    
    @Override
    protected String getTextInternal() {
        StringBuilder append;
        try {
            append = new StringBuilder().append("Make ").append(this.val$functionSymbol.getNameWithKindLowercase());
            if (this.val$isVolatile) {
                final String s = " volatile";
                return append.append(s).toString();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final String s = " non-volatile";
        return append.append(s).toString();
    }
    
    @NotNull
    @Override
    public String getFamilyName() {
        String s;
        try {
            s = "Change volatile qualifier";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCOperatorsChecker$3", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}