// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface OCLanguageKindCalculatorHelper
{
    public static final ExtensionPointName<OCLanguageKindCalculatorHelper> EP_NAME = ExtensionPointName.create("cidr.lang.languageKindHelper");
    
    @Nullable
    OCLanguageKind getSpecifiedLanguage(@NotNull final Project p0, @NotNull final VirtualFile p1);
    
    @Nullable
    OCLanguageKind getLanguageByExtension(@NotNull final Project p0, @NotNull final String p1);
}
