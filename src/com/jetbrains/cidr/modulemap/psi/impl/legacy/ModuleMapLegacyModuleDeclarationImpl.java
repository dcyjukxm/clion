// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl.legacy;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.modulemap.psi.ModuleMapUseDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapUmbrellaDirDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapRequiresDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapModuleId;
import com.jetbrains.cidr.modulemap.psi.ModuleMapLinkDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapInferredSubmoduleDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapHeaderDeclaration;
import java.util.Collections;
import com.jetbrains.cidr.modulemap.psi.ModuleMapExportDeclaration;
import com.jetbrains.cidr.modulemap.psi.ModuleMapConflictDeclaration;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.modulemap.psi.ModuleMapConfigMacrosDeclaration;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.modulemap.psi.ModuleMapAttributes;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.jetbrains.cidr.modulemap.psi.ModuleMapModuleDeclaration;
import com.intellij.psi.impl.FakePsiElement;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bH\u0016J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000bH\u0016J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000bH\u0016J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000bH\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\n\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000bH\u0016J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000bH\u0016J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u000bH\u0016J\b\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020#H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006%" }, d2 = { "Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyModuleDeclarationImpl;", "Lcom/intellij/psi/impl/FakePsiElement;", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapModuleDeclaration;", "frameworkName", "", "(Ljava/lang/String;)V", "getFrameworkName", "()Ljava/lang/String;", "getAttributes", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapAttributes;", "getConfigMacrosDeclarationList", "", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapConfigMacrosDeclaration;", "getConflictDeclarationList", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapConflictDeclaration;", "getExportDeclarationList", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapExportDeclaration;", "getHeaderDeclarationList", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapHeaderDeclaration;", "getInferredSubmoduleDeclarationList", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapInferredSubmoduleDeclaration;", "getLinkDeclarationList", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapLinkDeclaration;", "getModuleDeclarationList", "getModuleId", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapModuleId;", "getParent", "Lcom/intellij/psi/PsiElement;", "getRequiresDeclarationList", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapRequiresDeclaration;", "getUmbrellaDirDeclarationList", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapUmbrellaDirDeclaration;", "getUseDeclarationList", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapUseDeclaration;", "isExplicit", "", "isFramework", "cidr-lang" })
public final class ModuleMapLegacyModuleDeclarationImpl extends FakePsiElement implements ModuleMapModuleDeclaration
{
    @NotNull
    private final String frameworkName;
    
    @Nullable
    @Override
    public ModuleMapAttributes getAttributes() {
        return null;
    }
    
    @NotNull
    @Override
    public List<ModuleMapConfigMacrosDeclaration> getConfigMacrosDeclarationList() {
        final List emptyList = ContainerUtil.emptyList();
        Intrinsics.checkExpressionValueIsNotNull((Object)emptyList, "ContainerUtil.emptyList()");
        return (List<ModuleMapConfigMacrosDeclaration>)emptyList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapConflictDeclaration> getConflictDeclarationList() {
        final List emptyList = ContainerUtil.emptyList();
        Intrinsics.checkExpressionValueIsNotNull((Object)emptyList, "ContainerUtil.emptyList()");
        return (List<ModuleMapConflictDeclaration>)emptyList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapExportDeclaration> getExportDeclarationList() {
        final List<ModuleMapLegacyExportDeclaration> singletonList = Collections.singletonList(ModuleMapLegacyExportDeclaration.Companion.getINSTANCE());
        Intrinsics.checkExpressionValueIsNotNull((Object)singletonList, "Collections.singletonLis\u2026portDeclaration.INSTANCE)");
        return (List<ModuleMapExportDeclaration>)singletonList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapHeaderDeclaration> getHeaderDeclarationList() {
        final List<ModuleMapLegacyUmbrellaHeader> singletonList = Collections.singletonList(new ModuleMapLegacyUmbrellaHeader(this.frameworkName));
        Intrinsics.checkExpressionValueIsNotNull((Object)singletonList, "Collections.singletonLis\u2026llaHeader(frameworkName))");
        return (List<ModuleMapHeaderDeclaration>)singletonList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapInferredSubmoduleDeclaration> getInferredSubmoduleDeclarationList() {
        final List<ModuleMapLegacyInferredSubmoduleDeclaration> singletonList = Collections.singletonList(ModuleMapLegacyInferredSubmoduleDeclaration.Companion.getINSTANCE());
        Intrinsics.checkExpressionValueIsNotNull((Object)singletonList, "Collections.singletonLis\u2026duleDeclaration.INSTANCE)");
        return (List<ModuleMapInferredSubmoduleDeclaration>)singletonList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapLinkDeclaration> getLinkDeclarationList() {
        final List emptyList = ContainerUtil.emptyList();
        Intrinsics.checkExpressionValueIsNotNull((Object)emptyList, "ContainerUtil.emptyList()");
        return (List<ModuleMapLinkDeclaration>)emptyList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapModuleDeclaration> getModuleDeclarationList() {
        final List emptyList = ContainerUtil.emptyList();
        Intrinsics.checkExpressionValueIsNotNull((Object)emptyList, "ContainerUtil.emptyList()");
        return (List<ModuleMapModuleDeclaration>)emptyList;
    }
    
    @Nullable
    @Override
    public ModuleMapModuleId getModuleId() {
        return new ModuleMapLegacyModuleId(this.frameworkName);
    }
    
    @NotNull
    @Override
    public List<ModuleMapRequiresDeclaration> getRequiresDeclarationList() {
        final List emptyList = ContainerUtil.emptyList();
        Intrinsics.checkExpressionValueIsNotNull((Object)emptyList, "ContainerUtil.emptyList()");
        return (List<ModuleMapRequiresDeclaration>)emptyList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapUmbrellaDirDeclaration> getUmbrellaDirDeclarationList() {
        final List emptyList = ContainerUtil.emptyList();
        Intrinsics.checkExpressionValueIsNotNull((Object)emptyList, "ContainerUtil.emptyList()");
        return (List<ModuleMapUmbrellaDirDeclaration>)emptyList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapUseDeclaration> getUseDeclarationList() {
        final List emptyList = ContainerUtil.emptyList();
        Intrinsics.checkExpressionValueIsNotNull((Object)emptyList, "ContainerUtil.emptyList()");
        return (List<ModuleMapUseDeclaration>)emptyList;
    }
    
    @Override
    public boolean isExplicit() {
        return false;
    }
    
    @Override
    public boolean isFramework() {
        return true;
    }
    
    @Nullable
    public PsiElement getParent() {
        return null;
    }
    
    @NotNull
    public final String getFrameworkName() {
        return this.frameworkName;
    }
    
    public ModuleMapLegacyModuleDeclarationImpl(@NotNull final String frameworkName) {
        Intrinsics.checkParameterIsNotNull((Object)frameworkName, "frameworkName");
        this.frameworkName = frameworkName;
    }
}
