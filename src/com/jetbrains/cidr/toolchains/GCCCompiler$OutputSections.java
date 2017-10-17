// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import org.jetbrains.annotations.NotNull;

static class OutputSections
{
    final String defines;
    final String preprocessed;
    final String featureChecks;
    
    public OutputSections(@NotNull final String defines, @NotNull final String preprocessed, @NotNull final String featureChecks) {
        if (defines == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defines", "com/jetbrains/cidr/toolchains/GCCCompiler$OutputSections", "<init>"));
        }
        if (preprocessed == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "preprocessed", "com/jetbrains/cidr/toolchains/GCCCompiler$OutputSections", "<init>"));
        }
        if (featureChecks == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "featureTests", "com/jetbrains/cidr/toolchains/GCCCompiler$OutputSections", "<init>"));
        }
        this.defines = defines;
        this.preprocessed = preprocessed;
        this.featureChecks = featureChecks;
    }
}
