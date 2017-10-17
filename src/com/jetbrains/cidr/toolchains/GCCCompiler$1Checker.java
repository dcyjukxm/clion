// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import org.jetbrains.annotations.NotNull;

class 1Checker
{
    final /* synthetic */ StringBuilder val$result;
    
    1Checker(final StringBuilder val$result) {
        this.val$result = val$result;
    }
    
    void check(@NotNull final String s, final OCCompilerFeatures.Feature feature) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "check", "com/jetbrains/cidr/toolchains/GCCCompiler$1Checker", "check"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.check(s, "feature", feature.name());
    }
    
    void check(@NotNull final String s, @NotNull final String s2, @NotNull final String s3) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "check", "com/jetbrains/cidr/toolchains/GCCCompiler$1Checker", "check"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/toolchains/GCCCompiler$1Checker", "check"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/toolchains/GCCCompiler$1Checker", "check"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        this.val$result.append("#if ").append(s).append("\n").append("____CIDR_test_query_").append(s2).append("->").append(s3).append("=1\n").append("#else\n").append("____CIDR_test_query_").append(s2).append("->").append(s3).append("=0\n").append("#endif\n");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
