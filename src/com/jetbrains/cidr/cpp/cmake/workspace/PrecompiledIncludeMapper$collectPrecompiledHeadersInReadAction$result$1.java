// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import java.util.Map;
import com.intellij.psi.PsiFile;
import kotlin.collections.CollectionsKt;
import com.jetbrains.cidr.lang.preprocessor.OCPreprocessingLexer;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.util.SmartList;
import kotlin.jvm.internal.Ref$BooleanRef;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Iterator;
import com.jetbrains.cidr.toolchains.PrecompiledInclude;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.toolchains.CompilerInfoCache;
import kotlin.Triple;
import com.intellij.util.containers.MultiMap;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import kotlin.Metadata;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.List;
import com.intellij.openapi.util.Computable;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003" }, d2 = { "<anonymous>", "", "Lcom/intellij/openapi/vfs/VirtualFile;", "compute" })
static final class PrecompiledIncludeMapper$collectPrecompiledHeadersInReadAction$result$1<T> implements Computable<List<? extends VirtualFile>> {
    @NotNull
    public final List<VirtualFile> compute() {
        return this.this$0.b(this.$config, this.$lang, this.$file, this.$includeName);
    }
}