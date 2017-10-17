// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.Collection;
import com.intellij.lang.annotation.AnnotationSession;
import org.jetbrains.annotations.Contract;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiFile;

public interface OCFile extends PsiFile, OCElement
{
    public static final Key<UnusedChecksMode> UNUSED_CHECKS = Key.create("UNUSED_CHECKS");
    public static final Key<UnusedChecksMode> DFA_UNUSED_CHECKS = Key.create("DFA_UNUSED_CHECKS");
    public static final Key<Boolean> DFA_UNREACHABLE_CODE = Key.create("DFA_UNREACHABLE_CODE");
    
    boolean processSymbolsRecursively(@NotNull final Processor<OCSymbol> p0);
    
    @Nullable
     <T extends OCSymbol> T findSymbol(@Nullable final String p0, final Class<T> p1);
    
    @Nullable
     <T extends OCSymbol> T findSymbol(@NotNull final OCElement p0, final Class<T> p1);
    
    @NotNull
    OCNamespaceLikeSymbol getMembersContainer(final boolean p0);
    
    @Nullable
    OCSymbol getSameNamedClass();
    
    String getMainClassName();
    
    @Nullable
    OCSymbolDeclarator findFirstClass();
    
    OCSymbolDeclarator findClass(final String p0);
    
    List<OCIncludeDirective> findIncludeDirectives();
    
    @Nullable
    @Contract("null -> null")
    OCIncludeDirective findIncludeDirective(@Nullable final VirtualFile p0);
    
    void pushAnnotationSession(final AnnotationSession p0);
    
    void popAnnotationSession();
    
    @Nullable
    AnnotationSession getCurrentAnnotationSession();
    
    @NotNull
    Object getAnnotationSessionLock();
    
    void markIncludedFrom(final OCFile p0);
    
    Collection<OCFile> resetIncludingFiles();
    
    Collection<OCFile> getIncludingFiles();
    
    @Nullable
    OCFile getAssociatedFile();
    
    @Nullable
    OCFile getAssociatedFileWithSameName();
    
    boolean isHeader();
    
    boolean isCpp();
    
    @NotNull
    OCLanguageKind getKind();
    
    boolean isInProjectSources();
    
    boolean isInProjectSourcesOrLibraries();
    
    boolean isInLibraries();
    
    boolean hasExtraTopLevelDefinitions();
    
    public enum UnusedChecksMode
    {
        ENABLED, 
        DISABLED, 
        IN_LOCAL_FILE;
    }
}
