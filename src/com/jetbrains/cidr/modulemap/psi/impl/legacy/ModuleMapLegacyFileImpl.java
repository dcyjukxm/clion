// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl.legacy;

import com.intellij.lang.Language;
import com.jetbrains.cidr.modulemap.ModuleMapLanguage;
import kotlin.collections.CollectionsKt;
import com.jetbrains.cidr.modulemap.psi.ModuleMapExternModuleDeclaration;
import java.util.Collections;
import com.jetbrains.cidr.modulemap.psi.ModuleMapModuleDeclaration;
import java.util.List;
import com.intellij.psi.impl.source.LightPsiFileImpl;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.modulemap.ModuleMapFileType;
import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.jetbrains.cidr.modulemap.psi.ModuleMapFile;
import com.intellij.extapi.psi.LightPsiFileBase;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0016J\u0015\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u0010H\u0016¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0014H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001a" }, d2 = { "Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyFileImpl;", "Lcom/intellij/extapi/psi/LightPsiFileBase;", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapFile;", "frameworkName", "", "provider", "Lcom/intellij/psi/FileViewProvider;", "(Ljava/lang/String;Lcom/intellij/psi/FileViewProvider;)V", "getFrameworkName", "()Ljava/lang/String;", "clearCaches", "", "copyLight", "Lcom/intellij/psi/impl/source/LightPsiFileImpl;", "viewProvider", "getChildren", "", "Lcom/intellij/psi/PsiElement;", "()[Lcom/intellij/psi/PsiElement;", "getExternModuleDeclarations", "", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapExternModuleDeclaration;", "getFileType", "Lcom/intellij/openapi/fileTypes/FileType;", "getModuleDeclarations", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapModuleDeclaration;", "cidr-lang" })
public final class ModuleMapLegacyFileImpl extends LightPsiFileBase implements ModuleMapFile
{
    @NotNull
    private final String frameworkName;
    
    @NotNull
    public FileType getFileType() {
        final ModuleMapFileType instance = ModuleMapFileType.INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull((Object)instance, "ModuleMapFileType.INSTANCE");
        return (FileType)instance;
    }
    
    @Override
    public void clearCaches() {
    }
    
    @NotNull
    @Override
    public PsiElement[] getChildren() {
        final PsiElement[] empty_ARRAY = PsiElement.EMPTY_ARRAY;
        Intrinsics.checkExpressionValueIsNotNull((Object)empty_ARRAY, "PsiElement.EMPTY_ARRAY");
        return empty_ARRAY;
    }
    
    @Nullable
    @Override
    public LightPsiFileImpl copyLight(@Nullable final FileViewProvider fileViewProvider) {
        return null;
    }
    
    @NotNull
    @Override
    public List<ModuleMapModuleDeclaration> getModuleDeclarations() {
        final List<ModuleMapLegacyModuleDeclarationImpl> singletonList = Collections.singletonList(new ModuleMapLegacyModuleDeclarationImpl(this.frameworkName));
        Intrinsics.checkExpressionValueIsNotNull((Object)singletonList, "Collections.singletonLis\u2026ationImpl(frameworkName))");
        return (List<ModuleMapModuleDeclaration>)singletonList;
    }
    
    @NotNull
    @Override
    public List<ModuleMapExternModuleDeclaration> getExternModuleDeclarations() {
        return (List<ModuleMapExternModuleDeclaration>)CollectionsKt.emptyList();
    }
    
    @NotNull
    public final String getFrameworkName() {
        return this.frameworkName;
    }
    
    public ModuleMapLegacyFileImpl(@NotNull final String frameworkName, @NotNull final FileViewProvider provider) {
        Intrinsics.checkParameterIsNotNull((Object)frameworkName, "frameworkName");
        Intrinsics.checkParameterIsNotNull((Object)provider, "provider");
        super(provider, ModuleMapLanguage.INSTANCE);
        this.frameworkName = frameworkName;
    }
}
