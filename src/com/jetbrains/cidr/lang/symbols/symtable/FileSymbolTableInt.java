// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import java.util.Set;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.intellij.util.Processor;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import gnu.trove.THashSet;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Function;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.ProjectAndVirtualFileOwner;

public interface FileSymbolTableInt extends ProjectAndVirtualFileOwner
{
    void compact();
    
    @NotNull
    ContextSignature getSignature();
    
    VirtualFile getContainingFile();
    
    PsiFile getFile();
    
    @NotNull
    Project getProject();
    
    void internSymbols(final Function<OCSymbol, OCSymbol> p0);
    
    void updateOffsets(final int p0, final int p1, final int p2, final THashSet<OCSymbol> p3);
    
    void init(@Nullable final Project p0, @Nullable final VirtualFile p1);
    
    @Nullable
     <T extends OCSymbol> T findSymbol(@NotNull final PsiElement p0, @NotNull final Class<T> p1);
    
    @Nullable
     <T extends OCSymbol> T findSymbol(@Nullable final String p0, @NotNull final Class<T> p1);
    
    boolean processFile(final Processor<OCSymbol> p0);
    
    boolean processFile(final Processor<OCSymbol> p0, final int p1, final int p2);
    
    boolean processIncludes(final Processor<OCIncludeSymbol> p0);
    
    boolean processSymbols(@NotNull final Processor<OCSymbol> p0, @Nullable final String p1, @Nullable final Map<OCSymbol, VirtualFile> p2, final OCInclusionContext p3);
    
    boolean shallowProcessSymbols(@NotNull final Processor<OCSymbol> p0);
    
    boolean processSymbols(@NotNull final Processor<OCSymbol> p0, @Nullable final String p1, @NotNull final Set<FileSymbolTable> p2, @Nullable final Map<OCSymbol, VirtualFile> p3, @Nullable final VirtualFile p4, @NotNull final OCInclusionContext p5, @Nullable final VirtualFile p6);
    
    boolean isEmpty();
    
    void append(final OCSymbol p0);
    
    void sortSymbols();
}
