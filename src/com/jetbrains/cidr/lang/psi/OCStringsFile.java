// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;

public interface OCStringsFile extends PsiFile
{
    public static final String UNKNOWN_LANGUAGE = "unknown";
    
    @NotNull
    OCLocalizedString[] getStringPairs();
    
    @Nullable
    OCLocalizedString findStringPair(final String p0);
    
    String getLocalizationName();
}
