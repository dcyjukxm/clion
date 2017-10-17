// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import kotlin.text.StringsKt;
import java.util.Arrays;
import com.jetbrains.cidr.lang.toolchains.DefaultCidrToolEnvironment;
import kotlin.TypeCastException;
import java.util.Map;
import com.intellij.psi.PsiFile;
import kotlin.collections.CollectionsKt;
import com.jetbrains.cidr.lang.preprocessor.OCPreprocessingLexer;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.util.SmartList;
import kotlin.jvm.internal.Ref$BooleanRef;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Iterator;
import com.jetbrains.cidr.toolchains.PrecompiledInclude;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.toolchains.CompilerInfoCache;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import kotlin.Triple;
import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.List;
import gnu.trove.THashMap;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u000fJ.\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\rH\u0002J.\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\rH\u0002J\u0006\u0010\u001f\u001a\u00020 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR \u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u0012\u001a\"\u0012\u0004\u0012\u00020\r\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0004\u0012\u00020\r0\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/workspace/PrecompiledIncludeMapper;", "", "config", "Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;", "compilerInfoCache", "Lcom/jetbrains/cidr/toolchains/CompilerInfoCache;", "(Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;Lcom/jetbrains/cidr/toolchains/CompilerInfoCache;)V", "getCompilerInfoCache", "()Lcom/jetbrains/cidr/toolchains/CompilerInfoCache;", "getConfig", "()Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;", "creation", "Lgnu/trove/THashMap;", "", "", "Lcom/intellij/openapi/vfs/VirtualFile;", "separators", "", "usage", "Lcom/intellij/util/containers/MultiMap;", "Lkotlin/Triple;", "collect", "", "lang", "Lcom/jetbrains/cidr/lang/OCLanguageKind;", "source", "collectPrecompiledHeaders", "file", "canonicalIncludeName", "collectPrecompiledHeadersInReadAction", "includeName", "getIncludeMap", "Lcom/jetbrains/cidr/cpp/cmake/workspace/PrecompiledIncludeMap;", "clion" })
public final class PrecompiledIncludeMapper
{
    private final THashMap<String, List<VirtualFile>> creation;
    private final MultiMap<String, Triple<String, VirtualFile, String>> usage;
    private final char[] separators;
    @NotNull
    private final OCResolveConfiguration config;
    @NotNull
    private final CompilerInfoCache compilerInfoCache;
    
    public final void collect(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        Intrinsics.checkParameterIsNotNull((Object)ocLanguageKind, "lang");
        final CompilerInfoCache.Entry entry = this.compilerInfoCache.getCompilerInfoCache(this.config.getProject(), this.config.getCompilerSettings(), ocLanguageKind, virtualFile).getResult();
        if (entry != null) {
            final List<PrecompiledInclude> includeMap = entry.includeMap;
            Intrinsics.checkExpressionValueIsNotNull((Object)includeMap, "result.includeMap");
            for (final PrecompiledInclude precompiledInclude : includeMap) {
                final String component1 = precompiledInclude.component1();
                final String component2 = precompiledInclude.component2();
                final boolean component3 = precompiledInclude.component3();
                final String access$toCanonicalIncludeName = a(component2, this.separators);
                if (component3 && virtualFile != null) {
                    this.creation.put((Object)component1, (Object)this.a(this.config, ocLanguageKind, virtualFile, access$toCanonicalIncludeName));
                }
                else {
                    this.usage.putValue((Object)component1, (Object)new Triple((Object)access$toCanonicalIncludeName, (Object)virtualFile, (Object)this.config.getCompilerSettings().getCompilerKey(ocLanguageKind, virtualFile)));
                }
            }
        }
    }
    
    private final List<VirtualFile> a(final OCResolveConfiguration ocResolveConfiguration, final OCLanguageKind ocLanguageKind, final VirtualFile virtualFile, final String s) {
        final Object runReadAction = ApplicationManager.getApplication().runReadAction((Computable)new PrecompiledIncludeMapper$collectPrecompiledHeadersInReadAction$result.PrecompiledIncludeMapper$collectPrecompiledHeadersInReadAction$result$1(this, ocResolveConfiguration, ocLanguageKind, virtualFile, s));
        Intrinsics.checkExpressionValueIsNotNull(runReadAction, "ApplicationManager.getAp\u2026ng, file, includeName) })");
        return (List<VirtualFile>)runReadAction;
    }
    
    private final List<VirtualFile> b(final OCResolveConfiguration ocResolveConfiguration, final OCLanguageKind ocLanguageKind, final VirtualFile virtualFile, final String s) {
        ApplicationManager.getApplication().assertReadAccessAllowed();
        final PsiFile file = PsiManager.getInstance(ocResolveConfiguration.getProject()).findFile(virtualFile);
        if (file instanceof OCFile) {
            final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
            ref$BooleanRef.element = false;
            final SmartList list = new SmartList();
            final OCInclusionContext sourceParsingContext = OCInclusionContext.sourceParsingContext(ocResolveConfiguration, ocLanguageKind, file);
            sourceParsingContext.setResolvePathListener((OCInclusionContext.ResolvePathListener)new PrecompiledIncludeMapper$collectPrecompiledHeaders.PrecompiledIncludeMapper$collectPrecompiledHeaders$1(this, ref$BooleanRef, list, s));
            final OCPreprocessingLexer ocPreprocessingLexer = new OCPreprocessingLexer(sourceParsingContext, (OCFile)file, OCPreprocessingLexer.IncludePreprocessingMode.SKIP);
            ocPreprocessingLexer.start((CharSequence)((OCFile)file).getText());
            while (ocPreprocessingLexer.getTokenType() != null && !ref$BooleanRef.element) {
                ocPreprocessingLexer.advance();
            }
            return ref$BooleanRef.element ? ((List<VirtualFile>)list) : CollectionsKt.emptyList();
        }
        return (List<VirtualFile>)CollectionsKt.emptyList();
    }
    
    @NotNull
    public final PrecompiledIncludeMap getIncludeMap() {
        return new PrecompiledIncludeMap((Map)this.creation, this.usage);
    }
    
    @NotNull
    public final OCResolveConfiguration getConfig() {
        return this.config;
    }
    
    @NotNull
    public final CompilerInfoCache getCompilerInfoCache() {
        return this.compilerInfoCache;
    }
    
    public PrecompiledIncludeMapper(@NotNull final OCResolveConfiguration config, @NotNull final CompilerInfoCache compilerInfoCache) {
        Intrinsics.checkParameterIsNotNull((Object)config, "config");
        Intrinsics.checkParameterIsNotNull((Object)compilerInfoCache, "compilerInfoCache");
        this.config = config;
        this.compilerInfoCache = compilerInfoCache;
        this.creation = (THashMap<String, List<VirtualFile>>)new THashMap();
        this.usage = (MultiMap<String, Triple<String, VirtualFile, String>>)new MultiMap();
        final char[] supportedFileSeparators = this.config.getCompilerSettings().getEnvironment().getSupportedFileSeparators();
        Intrinsics.checkExpressionValueIsNotNull((Object)supportedFileSeparators, "config.compilerSettings.\u2026t.supportedFileSeparators");
        this.separators = supportedFileSeparators;
    }
}
