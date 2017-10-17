// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import java.util.Collections;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.preprocessor.OCPreprocessingLexer;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.psi.OCFile;
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
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.modulemap.psi.ModuleMapHeaderDeclaration;
import com.jetbrains.cidr.modulemap.symbols.impl.ModuleMapModuleSymbolImpl;
import kotlin.collections.MapsKt;
import java.util.ArrayList;
import gnu.trove.THashSet;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.modulemap.psi.ModuleMapModuleId;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import com.jetbrains.cidr.modulemap.symbols.impl.ModuleMapFileSymbolImpl;
import java.util.Set;
import com.jetbrains.cidr.modulemap.psi.ModuleMapModuleDeclaration;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.modulemap.psi.ModuleMapFile;
import com.intellij.psi.DummyHolderViewProvider;
import com.intellij.psi.PsiManager;
import com.intellij.psi.FileViewProvider;
import com.jetbrains.cidr.modulemap.psi.impl.legacy.ModuleMapLegacyFileImpl;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapPathResolver;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.OCLanguageKind;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import com.intellij.psi.PsiFile;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextImpl;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016¨\u0006\n" }, d2 = { "com/jetbrains/cidr/modulemap/symbols/ModuleMapSymbolBuilder$ModuleScope$processIncludes$TrackingInclusionContext", "Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContextImpl;", "(Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSymbolBuilder$ModuleScope;Lcom/intellij/psi/PsiFile;Lcom/intellij/util/Processor;)V", "getRootFile", "Lcom/intellij/psi/PsiFile;", "reserveInclude", "", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "once", "cidr-lang" })
public static final class TrackingInclusionContext extends OCInclusionContextImpl
{
    @NotNull
    @Override
    public PsiFile getRootFile() {
        return this.$psiFile;
    }
    
    @Override
    public boolean reserveInclude(@NotNull final VirtualFile virtualFile, final boolean b) {
        Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
        return super.reserveInclude(virtualFile, b) && this.$processor.process((Object)virtualFile);
    }
    
    public TrackingInclusionContext(final ModuleMapSymbolBuilder.ModuleScope this$0, final PsiFile $psiFile, final Processor $processor) {
        this.this$0 = this$0;
        this.$psiFile = $psiFile;
        this.$processor = $processor;
        super(null, ModuleMapSymbolBuilder.access$getProject$p(this$0.this$0), OCLanguageKind.OBJ_C);
    }
}
