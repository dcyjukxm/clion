// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Pair;

private static class BasedOnExistingResult
{
    public final boolean shouldInline;
    @Nullable
    public final Pair<PsiFile, Integer> outsideLocation;
    
    private BasedOnExistingResult(final boolean shouldInline, @Nullable final Pair<PsiFile, Integer> outsideLocation) {
        this.shouldInline = shouldInline;
        this.outsideLocation = outsideLocation;
        assert !this.shouldInline;
    }
}
