// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import java.util.function.BiFunction;

static final class ClangTidyAnnotator$2 implements BiFunction<String, String, Boolean> {
    private boolean skipOptionValue = false;
    
    @Override
    public Boolean apply(final String s, final String s2) {
        if (this.skipOptionValue) {
            this.skipOptionValue = false;
            return false;
        }
        if (s.equals("-F") || s.equals("-I") || s.equals("-iquote")) {
            this.skipOptionValue = true;
            return false;
        }
        if (s.startsWith("-F") || s.startsWith("-I") || s.startsWith("-iquote")) {
            return false;
        }
        return true;
    }
}