// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.psi.impl.legacy;

import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.modulemap.psi.ModuleMapWildcardModuleId;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.jetbrains.cidr.modulemap.psi.ModuleMapExportDeclaration;
import com.intellij.psi.impl.FakePsiElement;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u00012\u00020\u0002:\u0001\bB\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyExportDeclaration;", "Lcom/intellij/psi/impl/FakePsiElement;", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapExportDeclaration;", "()V", "getParent", "Lcom/intellij/psi/PsiElement;", "getWildcardModuleId", "Lcom/jetbrains/cidr/modulemap/psi/ModuleMapWildcardModuleId;", "Companion", "cidr-lang" })
public final class ModuleMapLegacyExportDeclaration extends FakePsiElement implements ModuleMapExportDeclaration
{
    @NotNull
    private static final ModuleMapLegacyExportDeclaration INSTANCE;
    public static final Companion Companion;
    
    @Nullable
    @Override
    public ModuleMapWildcardModuleId getWildcardModuleId() {
        return null;
    }
    
    @Nullable
    public PsiElement getParent() {
        return null;
    }
    
    static {
        Companion = new Companion(null);
        INSTANCE = new ModuleMapLegacyExportDeclaration();
    }
    
    @NotNull
    public static final /* synthetic */ ModuleMapLegacyExportDeclaration access$getINSTANCE$cp() {
        return ModuleMapLegacyExportDeclaration.INSTANCE;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyExportDeclaration$Companion;", "", "()V", "INSTANCE", "Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyExportDeclaration;", "getINSTANCE", "()Lcom/jetbrains/cidr/modulemap/psi/impl/legacy/ModuleMapLegacyExportDeclaration;", "cidr-lang" })
    public static final class Companion
    {
        @NotNull
        public final ModuleMapLegacyExportDeclaration getINSTANCE() {
            return ModuleMapLegacyExportDeclaration.access$getINSTANCE$cp();
        }
    }
}
