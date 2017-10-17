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
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Iterator;
import com.jetbrains.cidr.toolchains.PrecompiledInclude;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.toolchains.CompilerInfoCache;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import kotlin.Triple;
import com.intellij.util.containers.MultiMap;
import java.util.List;
import gnu.trove.THashMap;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.intellij.util.SmartList;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\n¢\u0006\u0002\b\b" }, d2 = { "<anonymous>", "", "path", "Lcom/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol$IncludePath;", "isNextInclude", "", "resolvedHeader", "Lcom/intellij/openapi/vfs/VirtualFile;", "resolve" })
static final class PrecompiledIncludeMapper$collectPrecompiledHeaders$1 implements ResolvePathListener {
    @Override
    public final void resolve(@NotNull final OCIncludeSymbol.IncludePath includePath, final boolean b, @Nullable final VirtualFile virtualFile) {
        Intrinsics.checkParameterIsNotNull((Object)includePath, "path");
        if (virtualFile != null && !this.$isFinished.element) {
            this.$precompiledHeaders.add((Object)virtualFile);
            final Ref$BooleanRef $isFinished = this.$isFinished;
            final String $canonicalIncludeName = this.$canonicalIncludeName;
            final String path = includePath.getPath();
            Intrinsics.checkExpressionValueIsNotNull((Object)path, "path.path");
            $isFinished.element = Intrinsics.areEqual((Object)$canonicalIncludeName, (Object)a(path, PrecompiledIncludeMapper.access$getSeparators$p(this.this$0)));
        }
    }
}