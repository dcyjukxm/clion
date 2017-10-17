// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.compiler;

import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageStandard;

static final class OCCompilerFeatures$1 implements Type<OCLanguageStandard> {
    @Override
    public String toString() {
        return "LANGUAGE_STANDARD";
    }
    
    @NotNull
    @Override
    public OCLanguageStandard getDefault() {
        OCLanguageStandard cpp98;
        try {
            cpp98 = OCLanguageStandard.CPP98;
            if (cpp98 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$1", "getDefault"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return cpp98;
    }
    
    @NotNull
    @Override
    public OCLanguageStandard compute(@NotNull final OCImmutableInclusionContext ocImmutableInclusionContext) {
        try {
            if (ocImmutableInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$1", "compute"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCLanguageStandard cStandard = null;
        Label_0113: {
            OCLanguageStandard ocLanguageStandard = null;
            Label_0078: {
                try {
                    if (!ocImmutableInclusionContext.getLanguageKind().isCpp()) {
                        break Label_0113;
                    }
                    final OCImmutableInclusionContext ocImmutableInclusionContext2 = ocImmutableInclusionContext;
                    final String s = "__cplusplus";
                    final String s2 = OCCompilerFeatures.access$000(ocImmutableInclusionContext2, s);
                    ocLanguageStandard = OCLanguageStandard.getCppStandard(s2);
                    if (ocLanguageStandard == null) {
                        break Label_0078;
                    }
                    return ocLanguageStandard;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCImmutableInclusionContext ocImmutableInclusionContext2 = ocImmutableInclusionContext;
                    final String s = "__cplusplus";
                    final String s2 = OCCompilerFeatures.access$000(ocImmutableInclusionContext2, s);
                    ocLanguageStandard = OCLanguageStandard.getCppStandard(s2);
                    if (ocLanguageStandard == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$1", "compute"));
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            return ocLanguageStandard;
            try {
                cStandard = OCLanguageStandard.getCStandard(OCCompilerFeatures.access$000(ocImmutableInclusionContext, "__STDC_VERSION__"));
                if (cStandard == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$1", "compute"));
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return cStandard;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}