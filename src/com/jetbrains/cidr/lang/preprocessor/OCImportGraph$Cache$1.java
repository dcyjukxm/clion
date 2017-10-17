// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.util.ReadTask;

class OCImportGraph$Cache$1 extends ReadTask {
    @Override
    public void computeInReadAction(@NotNull final ProgressIndicator progressIndicator) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache$1", "computeInReadAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            Cache.access$000(Cache.this, progressIndicator);
        }
        finally {
            Cache.access$100(Cache.this).decrementAndGet();
        }
    }
    
    @Override
    public void onCanceled(@NotNull final ProgressIndicator progressIndicator) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indicator", "com/jetbrains/cidr/lang/preprocessor/OCImportGraph$Cache$1", "onCanceled"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Cache.access$200(Cache.this, false);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}