// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCImplementPropertyAccessorsQuickFix;

class OCImplementationChecker$2 extends OCImplementPropertyAccessorsQuickFix {
    final /* synthetic */ OCMethodSymbol val$symbol;
    
    @NotNull
    @Override
    public String getText() {
        String string = null;
        Label_0035: {
            StringBuilder append;
            try {
                append = new StringBuilder().append("Implement missing ");
                if (this.val$symbol.isGetter()) {
                    final String s = "setter";
                    break Label_0035;
                }
            }
            catch (IllegalStateException ex) {
                throw c(ex);
            }
            final String s = "getter";
            try {
                string = append.append(s).append(" method").toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCImplementationChecker$2", "getText"));
                }
            }
            catch (IllegalStateException ex2) {
                throw c(ex2);
            }
        }
        return string;
    }
    
    private static IllegalStateException c(final IllegalStateException ex) {
        return ex;
    }
}