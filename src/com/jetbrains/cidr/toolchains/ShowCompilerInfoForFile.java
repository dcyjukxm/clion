// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.psi.OCFile;
import kotlin.Triple;
import com.jetbrains.cidr.lang.workspace.compiler.CidrCompilerResult;
import java.util.Iterator;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerSettings;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.project.Project;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.intellij.openapi.vfs.VirtualFile;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.Map;
import java.util.Set;
import com.intellij.util.ExceptionUtil;
import com.jetbrains.cidr.lang.toolchains.CidrCompilerSwitches;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.actionSystem.AnActionEvent;
import kotlin.Metadata;
import com.intellij.openapi.project.DumbAwareAction;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J$\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u000e\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0010" }, d2 = { "Lcom/jetbrains/cidr/toolchains/ShowCompilerInfoForFile;", "Lcom/intellij/openapi/project/DumbAwareAction;", "()V", "actionPerformed", "", "e", "Lcom/intellij/openapi/actionSystem/AnActionEvent;", "getContext", "Lcom/jetbrains/cidr/toolchains/ShowCompilerInfoForFile$Context;", "getProjectAndFile", "Lkotlin/Triple;", "Lcom/intellij/openapi/project/Project;", "Lcom/intellij/openapi/vfs/VirtualFile;", "Lcom/jetbrains/cidr/lang/psi/OCFile;", "update", "Context", "cidr-common" })
public final class ShowCompilerInfoForFile extends DumbAwareAction
{
    public void update(@Nullable final AnActionEvent anActionEvent) {
        super.update(anActionEvent);
        if (anActionEvent != null) {
            anActionEvent.getPresentation().setEnabledAndVisible(this.a(anActionEvent) != null);
        }
    }
    
    public void actionPerformed(@NotNull final AnActionEvent anActionEvent) {
        Intrinsics.checkParameterIsNotNull((Object)anActionEvent, "e");
        final Context b = this.b(anActionEvent);
        if (b != null) {
            final Context context = b;
            final Project component1 = context.component1();
            final VirtualFile component2 = context.component2();
            final OCLanguageKind component3 = context.component3();
            final OCResolveConfiguration component4 = context.component4();
            final OCCompilerSettings compilerSettings = component4.getCompilerSettings();
            final StringBuilder sb = new StringBuilder();
            sb.append("Compiler info for '" + component2.getName() + "' in configuration '" + component4.getDisplayName(false) + "' for language " + component3 + '\n');
            sb.append("\nCompiler kind: " + compilerSettings.getCompiler(component3) + " path: " + compilerSettings.getCompilerExecutable(component3) + '\n');
            sb.append("\nCompiler switches:\n");
            final Iterator<Object> iterator = compilerSettings.getCompilerSwitches(component3, component2).getList(CidrCompilerSwitches.Format.RAW).iterator();
            while (iterator.hasNext()) {
                sb.append("\t").append(iterator.next()).append("\n");
            }
            sb.append("\nRetrieved compiler info:\n");
            if (compilerSettings instanceof OCCompilerSettingsBackedByCompilerCache) {
                final CidrCompilerResult<CompilerInfoCache.Entry> compilerInfo = ((OCCompilerSettingsBackedByCompilerCache)compilerSettings).getCompilerInfo(component3, component2);
                final Throwable error = compilerInfo.getError();
                if (error != null) {
                    sb.append(ExceptionUtil.getThrowableText(error));
                }
                else {
                    final StringBuilder sb2 = sb;
                    final StringBuilder append = new StringBuilder().append("\nDefines:\n");
                    final CompilerInfoCache.Entry result = compilerInfo.getResult();
                    if (result == null) {
                        Intrinsics.throwNpe();
                    }
                    sb2.append(append.append(((CompilerInfoCache.Entry)result).defines).toString());
                    final StringBuilder sb3 = sb;
                    final StringBuilder append2 = new StringBuilder().append("\nFeatures:\n");
                    final CompilerInfoCache.Entry result2 = compilerInfo.getResult();
                    if (result2 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb3.append(append2.append(CollectionsKt.joinToString$default((Iterable)((CompilerInfoCache.Entry)result2).features.entrySet(), (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)ShowCompilerInfoForFile$actionPerformed.ShowCompilerInfoForFile$actionPerformed$2.INSTANCE, 30, (Object)null)).toString());
                    final StringBuilder sb4 = sb;
                    final StringBuilder append3 = new StringBuilder().append("\n\nPCHs:\n");
                    final CompilerInfoCache.Entry result3 = compilerInfo.getResult();
                    if (result3 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb4.append(append3.append(CollectionsKt.joinToString$default((Iterable)((CompilerInfoCache.Entry)result3).precompiledHeaders, (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)ShowCompilerInfoForFile$actionPerformed.ShowCompilerInfoForFile$actionPerformed$3.INSTANCE, 30, (Object)null)).toString());
                    final StringBuilder sb5 = sb;
                    final StringBuilder append4 = new StringBuilder().append("\n\nHeader Search paths:\n");
                    final CompilerInfoCache.Entry result4 = compilerInfo.getResult();
                    if (result4 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb5.append(append4.append(CollectionsKt.joinToString$default((Iterable)((CompilerInfoCache.Entry)result4).headerSearchPaths, (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)ShowCompilerInfoForFile$actionPerformed.ShowCompilerInfoForFile$actionPerformed$4.INSTANCE, 30, (Object)null)).toString());
                }
            }
            else {
                sb.append("\tUnavailable: " + ((OCCompilerSettingsBackedByCompilerCache)compilerSettings).getClass().getSimpleName() + " doesn't implement " + OCCompilerSettingsBackedByCompilerCache.class.getSimpleName());
            }
            FileEditorManager.getInstance(component1).openFile((VirtualFile)new LightVirtualFile("Compiler Info for '" + component2.getName() + "' in '" + component4.getDisplayName(true) + '\'', (FileType)PlainTextFileType.INSTANCE, (CharSequence)sb), false);
        }
    }
    
    private final Triple<Project, VirtualFile, OCFile> a(final AnActionEvent anActionEvent) {
        if (!ApplicationManager.getApplication().isInternal() && !Registry.is("cidr.show.compiler.info")) {
            return null;
        }
        final Project eventProject = AnAction.getEventProject(anActionEvent);
        if (eventProject == null) {
            return null;
        }
        final Project project = eventProject;
        final VirtualFile virtualFile = (VirtualFile)anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE);
        if (virtualFile == null) {
            return null;
        }
        final VirtualFile virtualFile2 = virtualFile;
        Object data;
        if (!((data = anActionEvent.getData(CommonDataKeys.PSI_FILE)) instanceof OCFile)) {
            data = null;
        }
        final OCFile ocFile = (OCFile)data;
        if (ocFile != null) {
            return (Triple<Project, VirtualFile, OCFile>)new Triple((Object)project, (Object)virtualFile2, (Object)ocFile);
        }
        return null;
    }
    
    private final Context b(final AnActionEvent anActionEvent) {
        final Triple<Project, VirtualFile, OCFile> a = this.a(anActionEvent);
        if (a == null) {
            return null;
        }
        final Triple<Project, VirtualFile, OCFile> triple = a;
        final Project project = (Project)triple.component1();
        final VirtualFile virtualFile = (VirtualFile)triple.component2();
        final OCLanguageKind kind = ((OCFile)triple.component3()).getKind();
        final OCResolveConfiguration activeConfiguration = OCInclusionContextUtil.getActiveConfiguration(virtualFile, project);
        if (activeConfiguration != null) {
            final OCResolveConfiguration ocResolveConfiguration = activeConfiguration;
            final Project project2 = project;
            final VirtualFile virtualFile2 = virtualFile;
            final OCLanguageKind ocLanguageKind = kind;
            Intrinsics.checkExpressionValueIsNotNull((Object)ocLanguageKind, "kind");
            final OCResolveConfiguration ocResolveConfiguration2 = ocResolveConfiguration;
            Intrinsics.checkExpressionValueIsNotNull((Object)ocResolveConfiguration2, "configuration");
            return new Context(project2, virtualFile2, ocLanguageKind, ocResolveConfiguration2);
        }
        return null;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\tH\u00c6\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f" }, d2 = { "Lcom/jetbrains/cidr/toolchains/ShowCompilerInfoForFile$Context;", "", "project", "Lcom/intellij/openapi/project/Project;", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "kind", "Lcom/jetbrains/cidr/lang/OCLanguageKind;", "configuration", "Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;", "(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/jetbrains/cidr/lang/OCLanguageKind;Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;)V", "getConfiguration", "()Lcom/jetbrains/cidr/lang/workspace/OCResolveConfiguration;", "getFile", "()Lcom/intellij/openapi/vfs/VirtualFile;", "getKind", "()Lcom/jetbrains/cidr/lang/OCLanguageKind;", "getProject", "()Lcom/intellij/openapi/project/Project;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "cidr-common" })
    private static final class Context
    {
        @NotNull
        private final Project project;
        @NotNull
        private final VirtualFile file;
        @NotNull
        private final OCLanguageKind kind;
        @NotNull
        private final OCResolveConfiguration configuration;
        
        @NotNull
        public final Project getProject() {
            return this.project;
        }
        
        @NotNull
        public final VirtualFile getFile() {
            return this.file;
        }
        
        @NotNull
        public final OCLanguageKind getKind() {
            return this.kind;
        }
        
        @NotNull
        public final OCResolveConfiguration getConfiguration() {
            return this.configuration;
        }
        
        public Context(@NotNull final Project project, @NotNull final VirtualFile file, @NotNull final OCLanguageKind kind, @NotNull final OCResolveConfiguration configuration) {
            Intrinsics.checkParameterIsNotNull((Object)project, "project");
            Intrinsics.checkParameterIsNotNull((Object)file, "file");
            Intrinsics.checkParameterIsNotNull((Object)kind, "kind");
            Intrinsics.checkParameterIsNotNull((Object)configuration, "configuration");
            this.project = project;
            this.file = file;
            this.kind = kind;
            this.configuration = configuration;
        }
        
        @NotNull
        public final Project component1() {
            return this.project;
        }
        
        @NotNull
        public final VirtualFile component2() {
            return this.file;
        }
        
        @NotNull
        public final OCLanguageKind component3() {
            return this.kind;
        }
        
        @NotNull
        public final OCResolveConfiguration component4() {
            return this.configuration;
        }
        
        @NotNull
        public final Context copy(@NotNull final Project project, @NotNull final VirtualFile virtualFile, @NotNull final OCLanguageKind ocLanguageKind, @NotNull final OCResolveConfiguration ocResolveConfiguration) {
            Intrinsics.checkParameterIsNotNull((Object)project, "project");
            Intrinsics.checkParameterIsNotNull((Object)virtualFile, "file");
            Intrinsics.checkParameterIsNotNull((Object)ocLanguageKind, "kind");
            Intrinsics.checkParameterIsNotNull((Object)ocResolveConfiguration, "configuration");
            return new Context(project, virtualFile, ocLanguageKind, ocResolveConfiguration);
        }
        
        @Override
        public String toString() {
            return "Context(project=" + this.project + ", file=" + this.file + ", kind=" + this.kind + ", configuration=" + this.configuration + ")";
        }
        
        @Override
        public int hashCode() {
            final Project project = this.project;
            final int n = ((project != null) ? project.hashCode() : 0) * 31;
            final VirtualFile file = this.file;
            final int n2 = (n + ((file != null) ? file.hashCode() : 0)) * 31;
            final OCLanguageKind kind = this.kind;
            final int n3 = (n2 + ((kind != null) ? kind.hashCode() : 0)) * 31;
            final OCResolveConfiguration configuration = this.configuration;
            return n3 + ((configuration != null) ? configuration.hashCode() : 0);
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this != o) {
                if (o instanceof Context) {
                    final Context context = (Context)o;
                    if (Intrinsics.areEqual((Object)this.project, (Object)context.project) && Intrinsics.areEqual((Object)this.file, (Object)context.file) && Intrinsics.areEqual((Object)this.kind, (Object)context.kind) && Intrinsics.areEqual((Object)this.configuration, (Object)context.configuration)) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
    }
}
