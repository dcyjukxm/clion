// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import java.util.Set;
import java.util.function.BiFunction;

static final class GCCCompiler$2 implements BiFunction<String, String, Boolean> {
    boolean archAdded = false;
    boolean skipOptionValue = false;
    final /* synthetic */ Set val$skipOptions;
    
    @Override
    public Boolean apply(String trim, final String s) {
        trim = trim.trim();
        final boolean access$000 = GCCCompiler.access$000(trim);
        if (this.skipOptionValue) {
            this.skipOptionValue = false;
            if (!access$000) {
                return false;
            }
        }
        if (this.val$skipOptions.contains(trim)) {
            this.skipOptionValue = access$000;
            return false;
        }
        if (trim.startsWith("-o")) {
            if (trim.equals("-o")) {
                this.skipOptionValue = true;
            }
            return false;
        }
        if ("-arch".equals(trim)) {
            if (this.archAdded) {
                this.skipOptionValue = true;
                return false;
            }
            this.archAdded = true;
        }
        if ("-include".equals(trim) && this.val$skipOptions.contains(s)) {
            return false;
        }
        return true;
    }
}