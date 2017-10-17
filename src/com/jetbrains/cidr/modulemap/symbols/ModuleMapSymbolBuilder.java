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
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.util.Processor;
import java.util.Collection;
import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import com.intellij.openapi.vfs.VirtualFile;
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
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapPathResolver;
import com.intellij.openapi.project.Project;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ2\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSymbolBuilder;", "", "project", "Lcom/intellij/openapi/project/Project;", "pathResolver", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;", "(Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;)V", "buildLegacy", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapFileSymbol;", "frameworkName", "", "processFile", "psiFile", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapFile;", "processModuleDeclaration", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "parent", "moduleDeclaration", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapModuleDeclaration;", "processedHeaders", "", "ModuleScope", "cidr-lang" })
public final class ModuleMapSymbolBuilder
{
    private final Project project;
    private final ModuleMapPathResolver pathResolver;
    
    @NotNull
    public final ModuleMapFileSymbol buildLegacy(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "frameworkName");
        final ModuleMapFileSymbol processFile = this.processFile(new ModuleMapLegacyFileImpl(s, (FileViewProvider)new DummyHolderViewProvider(PsiManager.getInstance(this.project))));
        if (processFile == null) {
            Intrinsics.throwNpe();
        }
        return processFile;
    }
    
    @Nullable
    public final ModuleMapFileSymbol processFile(@Nullable final ModuleMapFile moduleMapFile) {
        if (moduleMapFile == null) {
            return null;
        }
        final HashMap hashMap = ContainerUtil.newHashMap();
        for (final ModuleMapModuleDeclaration moduleMapModuleDeclaration : moduleMapFile.getModuleDeclarations()) {
            final ModuleMapModuleSymbol moduleMapModuleSymbol = null;
            final ModuleMapPathResolver pathResolver = this.pathResolver;
            final ModuleMapModuleDeclaration moduleMapModuleDeclaration2 = moduleMapModuleDeclaration;
            Intrinsics.checkExpressionValueIsNotNull((Object)moduleMapModuleDeclaration2, "it");
            final ModuleMapModuleSymbol processModuleDeclaration$default = processModuleDeclaration$default(this, moduleMapModuleSymbol, pathResolver, moduleMapModuleDeclaration2, (Set)null, 8, (Object)null);
            if (processModuleDeclaration$default != null) {
                hashMap.put(processModuleDeclaration$default.getName(), processModuleDeclaration$default);
            }
        }
        if (hashMap.isEmpty()) {
            return ModuleMapFileSymbol.Companion.getEMPTY();
        }
        final HashMap<String, ModuleMapModuleSymbol> hashMap2 = (HashMap<String, ModuleMapModuleSymbol>)hashMap;
        Intrinsics.checkExpressionValueIsNotNull((Object)hashMap2, "modules");
        return new ModuleMapFileSymbolImpl(hashMap2);
    }
    
    @Nullable
    public final ModuleMapModuleSymbol processModuleDeclaration(@Nullable final ModuleMapModuleSymbol moduleMapModuleSymbol, @NotNull final ModuleMapPathResolver moduleMapPathResolver, @NotNull final ModuleMapModuleDeclaration moduleMapModuleDeclaration, @NotNull final Set<String> set) {
        Intrinsics.checkParameterIsNotNull((Object)moduleMapPathResolver, "pathResolver");
        Intrinsics.checkParameterIsNotNull((Object)moduleMapModuleDeclaration, "moduleDeclaration");
        Intrinsics.checkParameterIsNotNull((Object)set, "processedHeaders");
        final ModuleMapModuleId moduleId = moduleMapModuleDeclaration.getModuleId();
        if (moduleId != null) {
            final String text = moduleId.getText();
            if (text != null) {
                final String s = text;
                return new ModuleScope(s, moduleMapModuleSymbol, (moduleMapModuleSymbol != null) ? moduleMapPathResolver.derive(s, moduleMapModuleDeclaration.isFramework()) : moduleMapPathResolver, moduleMapModuleDeclaration, set).processModuleDeclaration();
            }
        }
        return null;
    }
    
    public ModuleMapSymbolBuilder(@NotNull final Project project, @NotNull final ModuleMapPathResolver pathResolver) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        Intrinsics.checkParameterIsNotNull((Object)pathResolver, "pathResolver");
        this.project = project;
        this.pathResolver = pathResolver;
    }
    
    @NotNull
    public static final /* synthetic */ Project access$getProject$p(final ModuleMapSymbolBuilder moduleMapSymbolBuilder) {
        return moduleMapSymbolBuilder.project;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\u0010\fJ<\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\b\b\u0002\u0010(\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020&H\u0002J\u0010\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u0003H\u0002J\u0010\u0010,\u001a\u00020\"2\u0006\u0010\b\u001a\u00020\tH\u0002J(\u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020/2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010)\u001a\u00020&H\u0002J\u0010\u00100\u001a\u00020\"2\u0006\u0010\b\u001a\u00020\tH\u0002J\u001e\u00101\u001a\u00020&2\u0006\u0010.\u001a\u00020/2\f\u00102\u001a\b\u0012\u0004\u0012\u00020/03H\u0002J\u0010\u00104\u001a\u00020\"2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0006\u00105\u001a\u00020\u0005J\u0010\u00106\u001a\u00020\"2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u00107\u001a\u00020\"2\u0006\u0010\b\u001a\u00020\tH\u0002R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00030\u00030\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00030\u00030\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00030\u00030\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R*\u0010\u0019\u001a\u001e\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00030\u0003\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00050\u00050\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00068" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSymbolBuilder$ModuleScope;", "", "myName", "", "parent", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "pathResolver", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;", "moduleDeclaration", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapModuleDeclaration;", "myProcessedHeaders", "", "(Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSymbolBuilder;Ljava/lang/String;Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;Lcom/jetbrains/cidr/modulemap/psi/ModuleMapModuleDeclaration;Ljava/util/Set;)V", "getModuleDeclaration", "()Lcom/jetbrains/cidr/modulemap/psi/ModuleMapModuleDeclaration;", "myExcludeHeaders", "Ljava/util/ArrayList;", "kotlin.jvm.PlatformType", "myExportModules", "myHeaders", "myModule", "getMyName", "()Ljava/lang/String;", "getMyProcessedHeaders", "()Ljava/util/Set;", "mySubModules", "Ljava/util/HashMap;", "myUmbrellaDir", "myUmbrellaHeader", "getParent", "()Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "getPathResolver", "()Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;", "createSubmodule", "", "moduleName", "headerName", "isExplicit", "", "isExporting", "isFramework", "isInferred", "escape", "text", "processExports", "processHeader", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "processHeaders", "processIncludes", "processor", "Lcom/intellij/util/Processor;", "processInferredSubmoduleDeclaration", "processModuleDeclaration", "processSubmodules", "processUmbrellaDir", "cidr-lang" })
    public final class ModuleScope
    {
        private ModuleMapModuleSymbol myModule;
        private String myUmbrellaHeader;
        private final ArrayList<String> myExcludeHeaders;
        private final ArrayList<String> myHeaders;
        private final ArrayList<String> myExportModules;
        private String myUmbrellaDir;
        private final HashMap<String, ModuleMapModuleSymbol> mySubModules;
        @NotNull
        private final String myName;
        @Nullable
        private final ModuleMapModuleSymbol parent;
        @NotNull
        private final ModuleMapPathResolver pathResolver;
        @NotNull
        private final ModuleMapModuleDeclaration moduleDeclaration;
        @NotNull
        private final Set<String> myProcessedHeaders;
        
        @NotNull
        public final ModuleMapModuleSymbol processModuleDeclaration() {
            final boolean framework = this.moduleDeclaration.isFramework();
            this.c(this.moduleDeclaration);
            this.a(this.moduleDeclaration);
            this.b(this.moduleDeclaration);
            final boolean b = this.moduleDeclaration.getModuleDeclarationList().isEmpty() && this.moduleDeclaration.getInferredSubmoduleDeclarationList().isEmpty();
            final String myName = this.myName;
            final ModuleMapModuleSymbol parent = this.parent;
            final ModuleMapPathResolver pathResolver = this.pathResolver;
            final String myUmbrellaHeader = this.myUmbrellaHeader;
            final String myUmbrellaDir = this.myUmbrellaDir;
            final List trimToSize = ContainerUtil.trimToSize((List)this.myHeaders);
            Intrinsics.checkExpressionValueIsNotNull((Object)trimToSize, "ContainerUtil.trimToSize(myHeaders)");
            final List trimToSize2 = ContainerUtil.trimToSize((List)this.myExcludeHeaders);
            Intrinsics.checkExpressionValueIsNotNull((Object)trimToSize2, "ContainerUtil.trimToSize(myExcludeHeaders)");
            final List trimToSize3 = ContainerUtil.trimToSize((List)this.myExportModules);
            Intrinsics.checkExpressionValueIsNotNull((Object)trimToSize3, "ContainerUtil.trimToSize(myExportModules)");
            Map<String, ModuleMapModuleSymbol> emptyMap;
            if (b) {
                emptyMap = (Map<String, ModuleMapModuleSymbol>)MapsKt.emptyMap();
            }
            else {
                final HashMap<String, ModuleMapModuleSymbol> mySubModules = this.mySubModules;
                Intrinsics.checkExpressionValueIsNotNull((Object)mySubModules, "mySubModules");
                emptyMap = mySubModules;
            }
            this.myModule = new ModuleMapModuleSymbolImpl(myName, parent, pathResolver, myUmbrellaHeader, myUmbrellaDir, trimToSize, trimToSize2, trimToSize3, emptyMap, framework, this.moduleDeclaration.isExplicit(), false);
            if (!b) {
                this.d(this.moduleDeclaration);
                this.e(this.moduleDeclaration);
            }
            final ModuleMapModuleSymbol myModule = this.myModule;
            if (myModule == null) {
                Intrinsics.throwNpe();
            }
            return myModule;
        }
        
        private final void c(final ModuleMapModuleDeclaration moduleMapModuleDeclaration) {
            for (final ModuleMapHeaderDeclaration moduleMapHeaderDeclaration : moduleMapModuleDeclaration.getHeaderDeclarationList()) {
                final ModuleMapHeaderName headerName = moduleMapHeaderDeclaration.getHeaderName();
                if (headerName != null) {
                    final String text = headerName.getText();
                    if (text == null) {
                        continue;
                    }
                    final String unquoteString = StringUtil.unquoteString(text);
                    final Set<String> myProcessedHeaders = this.myProcessedHeaders;
                    final String s = unquoteString;
                    Intrinsics.checkExpressionValueIsNotNull((Object)s, "headerName");
                    if (!myProcessedHeaders.add(s)) {
                        continue;
                    }
                    if (moduleMapHeaderDeclaration.isUmbrella()) {
                        this.myUmbrellaHeader = unquoteString;
                    }
                    else if (moduleMapHeaderDeclaration.isExclude()) {
                        this.myExcludeHeaders.add(unquoteString);
                    }
                    else {
                        this.myHeaders.add(unquoteString);
                    }
                }
            }
        }
        
        private final void a(final ModuleMapModuleDeclaration moduleMapModuleDeclaration) {
            final Iterator<ModuleMapExportDeclaration> iterator = moduleMapModuleDeclaration.getExportDeclarationList().iterator();
            while (iterator.hasNext()) {
                final ModuleMapWildcardModuleId wildcardModuleId = iterator.next().getWildcardModuleId();
                if (wildcardModuleId != null) {
                    final String text = wildcardModuleId.getText();
                    if (text == null) {
                        continue;
                    }
                    this.myExportModules.add(text);
                }
            }
        }
        
        private final void b(final ModuleMapModuleDeclaration moduleMapModuleDeclaration) {
            final Iterator<ModuleMapUmbrellaDirDeclaration> iterator = moduleMapModuleDeclaration.getUmbrellaDirDeclarationList().iterator();
            while (iterator.hasNext()) {
                this.myUmbrellaDir = iterator.next().getName();
            }
        }
        
        private final void d(final ModuleMapModuleDeclaration moduleMapModuleDeclaration) {
            for (final ModuleMapModuleDeclaration moduleMapModuleDeclaration2 : moduleMapModuleDeclaration.getModuleDeclarationList()) {
                final ModuleMapSymbolBuilder this$0 = ModuleMapSymbolBuilder.this;
                final ModuleMapModuleSymbol myModule = this.myModule;
                final ModuleMapPathResolver pathResolver = this.pathResolver;
                final ModuleMapModuleDeclaration moduleMapModuleDeclaration3 = moduleMapModuleDeclaration2;
                Intrinsics.checkExpressionValueIsNotNull((Object)moduleMapModuleDeclaration3, "submoduleDeclaration");
                final ModuleMapModuleSymbol processModuleDeclaration = this$0.processModuleDeclaration(myModule, pathResolver, moduleMapModuleDeclaration3, this.myProcessedHeaders);
                if (processModuleDeclaration != null) {
                    this.mySubModules.put(processModuleDeclaration.getName(), processModuleDeclaration);
                }
            }
        }
        
        private final void e(final ModuleMapModuleDeclaration moduleMapModuleDeclaration) {
            final List<ModuleMapInferredSubmoduleDeclaration> inferredSubmoduleDeclarationList = moduleMapModuleDeclaration.getInferredSubmoduleDeclarationList();
            if (!inferredSubmoduleDeclarationList.isEmpty()) {
                final ModuleMapInferredSubmoduleDeclaration moduleMapInferredSubmoduleDeclaration = (ModuleMapInferredSubmoduleDeclaration)CollectionsKt.first((List)inferredSubmoduleDeclarationList);
                final boolean explicit = moduleMapInferredSubmoduleDeclaration.isExplicit();
                final boolean b = !moduleMapInferredSubmoduleDeclaration.getInferredSubmoduleMemberList().isEmpty();
                final ArrayList arrayList = ContainerUtil.newArrayList();
                if (this.myUmbrellaHeader != null) {
                    final ModuleMapPathResolver pathResolver = this.pathResolver;
                    final String myUmbrellaHeader = this.myUmbrellaHeader;
                    if (myUmbrellaHeader == null) {
                        Intrinsics.throwNpe();
                    }
                    final VirtualFile resolveHeader = pathResolver.resolveHeader(myUmbrellaHeader);
                    if (resolveHeader != null) {
                        arrayList.add(resolveHeader);
                    }
                }
                else if (this.myUmbrellaDir != null) {
                    final ModuleMapPathResolver pathResolver2 = this.pathResolver;
                    final String myUmbrellaDir = this.myUmbrellaDir;
                    if (myUmbrellaDir == null) {
                        Intrinsics.throwNpe();
                    }
                    final VirtualFile resolveDir = pathResolver2.resolveDir(myUmbrellaDir);
                    if (resolveDir != null && resolveDir.isDirectory()) {
                        final ArrayList<VirtualFile> list = (ArrayList<VirtualFile>)arrayList;
                        final VirtualFile[] array = resolveDir.getChildren();
                        final ArrayList<VirtualFile> list2 = list;
                        final VirtualFile[] array2 = array;
                        final ArrayList<VirtualFile> list3 = new ArrayList<VirtualFile>();
                        for (int i = 0; i < array2.length; ++i) {
                            final VirtualFile virtualFile = array2[i];
                            if (OCFileTypeHelpers.isHeaderFile(virtualFile.getName())) {
                                list3.add(virtualFile);
                            }
                        }
                        list2.addAll(list3);
                    }
                }
                for (final VirtualFile virtualFile2 : (ArrayList<VirtualFile>)arrayList) {
                    if (this.myUmbrellaDir != null) {
                        final VirtualFile virtualFile3 = virtualFile2;
                        Intrinsics.checkExpressionValueIsNotNull((Object)virtualFile3, "it");
                        if (!this.a(virtualFile3, explicit, b, false)) {
                            return;
                        }
                    }
                    final VirtualFile virtualFile4 = virtualFile2;
                    Intrinsics.checkExpressionValueIsNotNull((Object)virtualFile4, "it");
                    this.a(virtualFile4, (Processor<VirtualFile>)new Processor<VirtualFile>(this, explicit, b) {
                        public final boolean process(final VirtualFile virtualFile) {
                            final ModuleMapPathResolver pathResolver = this.this$0.getPathResolver();
                            Intrinsics.checkExpressionValueIsNotNull((Object)virtualFile, "includedHeader");
                            if (pathResolver.containsHeader(virtualFile)) {
                                this.this$0.a(virtualFile, this.$isExplicit$inlined, this.$isExporting$inlined, true);
                                return true;
                            }
                            return false;
                        }
                    });
                }
            }
        }
        
        private final boolean a(final VirtualFile virtualFile, final boolean b, final boolean b2, final boolean b3) {
            final String relativeHeaderPath = this.pathResolver.getRelativeHeaderPath(virtualFile);
            if (relativeHeaderPath != null) {
                final String s = relativeHeaderPath;
                if (!this.myProcessedHeaders.contains(s)) {
                    final String nameWithoutExtension = virtualFile.getNameWithoutExtension();
                    Intrinsics.checkExpressionValueIsNotNull((Object)nameWithoutExtension, "file.nameWithoutExtension");
                    createSubmodule$default(this, this.a(nameWithoutExtension), s, b, b2, false, b3, 16, (Object)null);
                    this.myProcessedHeaders.add(s);
                }
                return true;
            }
            return false;
        }
        
        private final boolean a(final VirtualFile virtualFile, final Processor<VirtualFile> processor) {
            ApplicationManager.getApplication().assertReadAccessAllowed();
            final PsiFile file = PsiManager.getInstance(ModuleMapSymbolBuilder.access$getProject$p(ModuleMapSymbolBuilder.this)).findFile(virtualFile);
            if (file instanceof OCFile) {
                final OCPreprocessingLexer ocPreprocessingLexer = new OCPreprocessingLexer((OCInclusionContext)new ModuleMapSymbolBuilder$ModuleScope$processIncludes.TrackingInclusionContext(this, file, (Processor)processor), (OCFile)file, true);
                ocPreprocessingLexer.start((CharSequence)((OCFile)file).getText());
                while (ocPreprocessingLexer.getTokenType() != null) {
                    ocPreprocessingLexer.advance();
                }
            }
            return true;
        }
        
        private final void a(final String s, final String s2, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
            ProgressManager.checkCanceled();
            final ModuleMapModuleSymbol myModule = this.myModule;
            final ModuleMapPathResolver pathResolver = this.pathResolver;
            final String s3 = null;
            final String s4 = null;
            final List<String> singletonList = Collections.singletonList(s2);
            Intrinsics.checkExpressionValueIsNotNull((Object)singletonList, "Collections.singletonList(headerName)");
            this.mySubModules.put(s, new ModuleMapModuleSymbolImpl(s, myModule, pathResolver, s3, s4, singletonList, CollectionsKt.emptyList(), b2 ? ModuleMapModuleSymbol.Companion.getMODULE_EXPORT_WILDCARD() : CollectionsKt.emptyList(), MapsKt.emptyMap(), b3, b, b4));
        }
        
        private final String a(final String s) {
            final char c = '_';
            final StringBuilder sb = new StringBuilder();
            int n = 0;
            final int n2 = s.length() - 1;
            if (n <= n2) {
                while (true) {
                    final char char1 = s.charAt(n);
                    if (n == 0 && Character.isDigit(char1)) {
                        sb.append(c);
                    }
                    sb.append((!Character.isDigit(char1) && (char1 < 'A' || char1 > 'Z') && (char1 < 'a' || char1 > 'z')) ? c : char1);
                    if (n == n2) {
                        break;
                    }
                    ++n;
                }
            }
            final String string = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull((Object)string, "sb.toString()");
            return string;
        }
        
        @NotNull
        public final String getMyName() {
            return this.myName;
        }
        
        @Nullable
        public final ModuleMapModuleSymbol getParent() {
            return this.parent;
        }
        
        @NotNull
        public final ModuleMapPathResolver getPathResolver() {
            return this.pathResolver;
        }
        
        @NotNull
        public final ModuleMapModuleDeclaration getModuleDeclaration() {
            return this.moduleDeclaration;
        }
        
        @NotNull
        public final Set<String> getMyProcessedHeaders() {
            return this.myProcessedHeaders;
        }
        
        public ModuleScope(@Nullable final String myName, @NotNull final ModuleMapModuleSymbol parent, @NotNull final ModuleMapPathResolver pathResolver, @NotNull final ModuleMapModuleDeclaration moduleDeclaration, final Set<String> myProcessedHeaders) {
            Intrinsics.checkParameterIsNotNull((Object)myName, "myName");
            Intrinsics.checkParameterIsNotNull((Object)pathResolver, "pathResolver");
            Intrinsics.checkParameterIsNotNull((Object)moduleDeclaration, "moduleDeclaration");
            Intrinsics.checkParameterIsNotNull((Object)myProcessedHeaders, "myProcessedHeaders");
            this.myName = myName;
            this.parent = parent;
            this.pathResolver = pathResolver;
            this.moduleDeclaration = moduleDeclaration;
            this.myProcessedHeaders = myProcessedHeaders;
            this.myExcludeHeaders = (ArrayList<String>)ContainerUtil.newArrayList();
            this.myHeaders = (ArrayList<String>)ContainerUtil.newArrayList();
            this.myExportModules = (ArrayList<String>)ContainerUtil.newArrayList();
            this.mySubModules = (HashMap<String, ModuleMapModuleSymbol>)ContainerUtil.newHashMap();
        }
    }
}
