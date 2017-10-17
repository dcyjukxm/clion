// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.OCResolveLanguageAndConfiguration;
import com.intellij.psi.PsiFile;

public interface OCConfigurationOwner extends PsiFile
{
    @Nullable
    OCResolveLanguageAndConfiguration getParsedResolveLanguageAndConfiguration();
    
    @NotNull
    OCLanguageKind getKind();
}
