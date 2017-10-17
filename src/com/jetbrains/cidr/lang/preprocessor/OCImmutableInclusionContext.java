// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.List;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;

public interface OCImmutableInclusionContext
{
    boolean isProcessed(@Nullable final VirtualFile p0);
    
    @NotNull
    Set<VirtualFile> getProcessedFiles();
    
    @Nullable
    Project getProject();
    
    @Nullable
    OCResolveConfiguration getConfiguration();
    
    @NotNull
    List<OCFile> getPrecompiledHeaders();
    
    boolean hasRootFile();
    
    @NotNull
    PsiFile getRootFile();
    
    @NotNull
    OCLanguageKind getLanguageKind();
    
    @NotNull
    OCInclusionContext derive();
    
    @NotNull
    OCInclusionContext derive(@NotNull final PsiFile p0);
    
    @NotNull
    OCInclusionContext derive(@NotNull final OCParsingNameScope p0);
    
    @Nullable
    OCMacroSymbol getDefinition(@Nullable final String p0);
    
    @Nullable
    OCMacroSymbol getDefinition(@Nullable final String p0, @NotNull final SignaturePart p1);
    
    @Nullable
     <T> T getCompilerFeature(@NotNull final OCCompilerFeatures.Type<T> p0);
    
    int getInclusionLevel();
    
    @Nullable
    OCImmutableInclusionContext getParent();
    
    public enum SignaturePart
    {
        NO, 
        HAS_DEFINITION, 
        EXACT_DEFINITION;
    }
}
