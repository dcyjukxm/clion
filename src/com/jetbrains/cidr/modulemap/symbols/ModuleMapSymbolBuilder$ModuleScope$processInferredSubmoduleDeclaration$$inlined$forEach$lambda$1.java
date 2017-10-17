// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import java.util.Collections;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCPreprocessingLexer;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Collection;
import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import com.jetbrains.cidr.modulemap.psi.ModuleMapInferredSubmoduleMember;
import kotlin.collections.CollectionsKt;
import com.jetbrains.cidr.modulemap.psi.ModuleMapInferredSubmoduleDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapUmbrellaDirDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapWildcardModuleId;
import com.jetbrains.cidr.modulemap.psi.ModuleMapExportDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapHeaderName;
import java.util.Iterator;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.modulemap.psi.ModuleMapHeaderDeclaration;
import java.util.Map;
import com.jetbrains.cidr.modulemap.symbols.impl.ModuleMapModuleSymbolImpl;
import kotlin.collections.MapsKt;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import java.util.Set;
import com.jetbrains.cidr.modulemap.psi.ModuleMapModuleDeclaration;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.ArrayList;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapPathResolver;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Metadata;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Processor;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005" }, d2 = { "<anonymous>", "", "includedHeader", "Lcom/intellij/openapi/vfs/VirtualFile;", "kotlin.jvm.PlatformType", "process" })
static final class ModuleMapSymbolBuilder$ModuleScope$processInferredSubmoduleDeclaration$$inlined$forEach$lambda$1<T> implements Processor<VirtualFile> {
    public final boolean process(final VirtualFile virtualFile) {
        final ModuleMapPathResolver pathResolver = this.this$0.getPathResolver();
        Intrinsics.checkExpressionValueIsNotNull((Object)virtualFile, "includedHeader");
        if (pathResolver.containsHeader(virtualFile)) {
            this.this$0.a(virtualFile, this.$isExplicit$inlined, this.$isExporting$inlined, true);
            return true;
        }
        return false;
    }
}