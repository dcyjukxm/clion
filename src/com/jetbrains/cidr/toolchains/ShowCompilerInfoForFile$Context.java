// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import kotlin.Metadata;

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
